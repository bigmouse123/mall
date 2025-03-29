package com.jiankun.mall.pojo.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/29 14:54
 */
@Getter
public enum AdminRoleEnum {


    SUPER_ADMIN("超级管理员", Byte.valueOf("1")),
    ADMIN("管理员", Byte.valueOf("2"));

    private final String text;

    private final Byte value;

    AdminRoleEnum(String text, Byte value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值的value
     * @return 枚举值
     */
    public static AdminRoleEnum getEnumByValue(Byte value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (AdminRoleEnum anEnum : AdminRoleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
