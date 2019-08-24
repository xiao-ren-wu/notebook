package org.ywb.enums;

import java.util.Objects;

/**
 * @author WenboYu
 * e-mail 18629015421@163.com
 * github https://github.com/xiao-ren-wu
 * @version 1
 * @date 2019/8/24 15:58
 * @since jdk1.8
 */
class MyArrays{

    static MyList asList(BaseEnum... a) {
        return new MyList(a);
    }

    public static class MyList{
        private final BaseEnum[] baseEnums;
        MyList(BaseEnum[] array) {
            baseEnums = Objects.requireNonNull(array);
        }

        public int getCodeByDesc(String desc){
            if(desc==null||desc.length()==0){
                throw new IllegalArgumentException("desc can not be null in MyArrays.MyList.getCodeByDesc");
            }
            for(BaseEnum baseEnum:baseEnums){
                if(desc.equals(baseEnum.getDesc())){
                    return baseEnum.getCode();
                }
            }
            throw new IllegalArgumentException("The desc{"+desc+"} may be a wrong value ~ please check your param and try again ~");
        }

        public String getDescByCode(int code){
            for (BaseEnum baseEnum:baseEnums){
                if(code==baseEnum.getCode()){
                    return baseEnum.getDesc();
                }
            }
            throw new IllegalArgumentException("The code{"+code+"} may be a wrong value ~ please check your param and try again ~");
        }
    }
}
