package org.ywb.enums;

import static org.ywb.enums.EnumFactory.enumFactory;

/**
 * @author yuwenbo
 */
public class EnumsApplication {

    public static void main(String[] args) {
        System.out.println(enumFactory().getDeleteStatus().getDescByCode(1));
        System.out.println(enumFactory().getDeleteStatus().getCodeByDesc("未删除"));
    }
}
