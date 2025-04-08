package com.jiankun.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/8 9:55
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class MyAlipayConfig {
    private String gatewayUrl;
    private String appid;
    private String privateKey;
    private String alipayPublicKey;
    private String notifyUrl;
    private String returnUrl;
}
