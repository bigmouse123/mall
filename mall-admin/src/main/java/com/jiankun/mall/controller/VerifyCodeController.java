package com.jiankun.mall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/10 0:03
 */
@Controller
@RequestMapping("/verifyCode")
public class VerifyCodeController {
    private static final long serialVersionUID = 2376992603034716655L;
    private final Font mFont = new Font("Arial Black", Font.PLAIN, 15); // 设置字体
    private final int lineWidth = 2; // 干扰线的长度=1.414*lineWidth
    private final int width = 88; // 定义图形大小
    private final int height = 25; // 定义图形大小
    private final int count = 200;

    /**
     * 描述：
     *
     * @param fc 描述：
     * @param bc 描述：
     * @return 描述：
     */
    private Color getRandColor(int fc, int bc) { // 取得给定范围随机颜色
        final Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        final int r = fc + random.nextInt(bc - fc);
        final int g = fc + random.nextInt(bc - fc);
        final int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    // 处理post
    @PostMapping
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 描述：
     *
     * @param request  描述：
     * @param response 描述：
     * @throws ServletException 描述：
     * @throws IOException      描述：
     */
    @GetMapping
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.reset();
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");
        // 在内存中创建图象
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        final Graphics2D g = (Graphics2D) image.getGraphics();
        // 生成随机类
        final Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250)); // ---1
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(mFont);
        // 画边框
        g.setColor(getRandColor(0, 20)); // ---2
        // 距离
        g.drawRect(0, 0, width - 1, height - 1);
        // 随机产生干扰线，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < count; i++) {
            g.setColor(getRandColor(150, 200)); // ---3
            final int x = random.nextInt(width - lineWidth - 1) + 1; // 保证画在边框之内
            final int y = random.nextInt(height - lineWidth - 1) + 1;
            final int xl = random.nextInt(lineWidth);
            final int yl = random.nextInt(lineWidth);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            final String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中,调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.setColor(new Color(20 + random.nextInt(130), 20 + random.nextInt(130), 20 + random.nextInt(130))); // --4--50-100
            // 第一个参数是要画上去的字符串 后面两个参数是针对 (0,0) x轴和y轴
            g.drawString(rand, (13 * i) + 10, 20);
        }
        // 把后台生成的验证码放到Session
        HttpSession session = request.getSession();
        session.setAttribute("codeInSession", sRand);

        // 图象生效
        // 它的作用是销毁程序中指定的图形界面资源，如果在使用了graphics获得windows一些图形资源，而不进行关闭的话，由于后期多人使用就会造成内存溢出的情况的，导致程序卡死。
        g.dispose();
        OutputStream os = response.getOutputStream();
        // 输出图象到页面
        ImageIO.write(image, "PNG", os);
        os.flush();
        os.close();
    }
}
