package com.jiankun.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jiankun.mall.config.MyAlipayConfig;
import com.jiankun.mall.constant.OrderStatusConstant;
import com.jiankun.mall.pojo.Order;
import com.jiankun.mall.service.IOrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/8 9:55
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private MyAlipayConfig myAlipayConfig;
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/pay")
    public void pay(Long orderNo, HttpServletResponse response) throws IOException {
        System.out.println("AlipayController.pay");
        //查询订单信息
        Order order = orderService.selectById(orderNo);
        //初始化SDK, 创建Client，通过SDK提供的Client负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(myAlipayConfig.getGatewayUrl(), myAlipayConfig.getAppid(),
                myAlipayConfig.getPrivateKey(), "json", "UTf-8", myAlipayConfig.getAlipayPublicKey(), "RSA2");
        //创建Request，设置参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(myAlipayConfig.getNotifyUrl());
        request.setReturnUrl(myAlipayConfig.getReturnUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", order.getOrderNo());//我们自己生成订单编号
        bizContent.put("total_amount", order.getPayment());//订单总金额
        bizContent.put("subject", "我的订单支付");//支付的名称
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");//固定配置

        request.setBizContent(bizContent.toString());
        //指定请求，拿到响应结果
        String form = null;
        try {
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(form);//将表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    @PostMapping("/notify")
    public void notifyUrl(HttpServletRequest request) throws AlipayApiException, ParseException {
        //TRADE_SUCCESS
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            // 支付宝验签
            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, myAlipayConfig.getAlipayPublicKey(), "UTF-8");
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));//2025-04-04 23:26:44
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
                // 更新订单为已支付
                Long orderNo = Long.valueOf(params.get("out_trade_no"));
                //orderService.updateStatus(orderNo, OrderStatusConstant.ORDER_STATUS_PAIED);
                //TODO: 也可以进一步更新其他信息...
                Order order = new Order();
                order.setOrderNo(orderNo);
                order.setStatus(OrderStatusConstant.PAID);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                order.setPaymentTime(format.parse(params.get("gmt_payment")));
                orderService.updateByPrimaryKeySelective(order);
            }
        }
    }

    @GetMapping("/return")
    public String returnUrl() {
        return "redirect:/page/order/list";
    }

}
