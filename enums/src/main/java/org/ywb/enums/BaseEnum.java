package org.ywb.enums;

/**
 * @author WenboYu
 * e-mail 18629015421@163.com
 * github https://github.com/xiao-ren-wu
 * @version 1
 * @date 2019/8/24 16:29
 * @since jdk1.8
 * <p>
 * 只有实现该接口，枚举才能被工厂管理{@link EnumFactory}
 */
public interface BaseEnum {
    /**
     * code code code
     *
     * @return code
     */
    int getCode();

    /**
     * desc desc desc
     *
     * @return desc
     */
    String getDesc();
}
