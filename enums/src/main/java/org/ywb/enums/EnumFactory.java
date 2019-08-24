package org.ywb.enums;

/**
 * @author WenboYu
 * e-mail 18629015421@163.com
 * github https://github.com/xiao-ren-wu
 * @version 1
 * @date 2019/8/24 15:26
 * @since jdk1.8
 *
 * 避免在每个枚举里面都写相同的get..by..代码
 * 通过使用工厂统一管理枚举
 *
 * 将枚举交给工厂管理的步骤：
 *
 * 1.被管理的枚举要实现BaseEnum接口{@link BaseEnum}
 * 2.将枚举注册给工厂，eg:
 * <code>
 *     public MyArrays.MyList getDeleteStatus() {
 *         return MyArrays.asList(DeleteStatus.values());
 *     }
 * </code>
 *
 * 使用方法：
 * StatusFactory.me().getDeleteStatus().getDescByCode(1)
 */
public class EnumFactory {

    private static EnumFactory enumFactory;

    private EnumFactory() {
    }

    /**
     * 获取枚举工厂对象
     *
     * @return StatusFactory
     */
    public static EnumFactory enumFactory() {
        if (enumFactory == null) {
            synchronized (EnumFactory.class) {
                if (enumFactory == null) {
                    enumFactory = new EnumFactory();
                }
            }
        }
        return enumFactory;
    }

    /**
     * 将枚举注册给工厂
     *
     * @return MyArrays.MyList
     */
    public MyArrays.MyList getDeleteStatus() {
        return MyArrays.asList(DeleteStatus.values());
    }
}
