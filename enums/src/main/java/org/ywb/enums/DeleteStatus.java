package org.ywb.enums;


/**
 * @author WenboYu
 * e-mail 18629015421@163.com
 * github https://github.com/xiao-ren-wu
 * @version 1
 * @date 2019/8/24 15:21
 * @since jdk1.8
 */
public enum DeleteStatus implements BaseEnum{
    /**
     * 已删除
     */
    DELETE_YES(1,"已删除"),
    /**
     * 未删除
     */
    DELETE_NO(2,"未删除")
    ;
    /**
     * 状态码
     */
    private int code;
    /**
     * 描述
     */
    private String desc;

    DeleteStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
