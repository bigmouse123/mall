package com.jiankun.mall.pojo.vo;

import com.jiankun.mall.pojo.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/10 16:48
 */
@Data
public class CategoryVO {
    private Integer id;

    private Integer parentId;

    private Integer parentName;

    private String name;

    private Byte status;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    private Category parent;

    public String getParentName() {
        if (parent != null) {
            return parent.getName();
        }
        return "";
    }

}
