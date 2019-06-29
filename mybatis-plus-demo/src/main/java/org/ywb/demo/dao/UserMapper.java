package org.ywb.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ywb.demo.pojo.User;

import java.util.List;

/**
 * @author YuWenbo
 * e-mail 18629015421@163.com
 * github https://github.com/xiao-ren-wu
 * @version 1
 * @since 2019/6/27 9:51
 */

public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户信息
     * @return list
     */
    List<User> selectAll();
}
