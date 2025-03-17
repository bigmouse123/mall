package com.jiankun.mall.pojo.vo;

import com.jiankun.mall.pojo.Product;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/14 21:27
 */
@Data
public class CartVO {
    private Integer id;

    private Integer userId;

    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private String productMainImage;

    private Integer quantity;

    private Integer checked;

//    private Product product;
}
