package org.ywb.demo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ywb.demo.dao.UserMapper;
import org.ywb.demo.pojo.User;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusDemoApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void select(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void insert(){
        User user = new User();
        user.setName("小明");
        user.setAge(31);
        user.setManagerId(1088250446457389058L);
        user.setCreateTime(LocalDateTime.now());
        int insert = userMapper.insert(user);
        System.out.println("影响记录数："+insert);
    }

    /**
     * 查询名字中包含'雨'并且年龄小于40
     * where name like '%雨%' and age < 40
     */
    @Test
    public void selectByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","雨").lt("age",40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 创建日期为2019年2月14日并且直属上级姓名为王姓
     * date_format(create_time,'%Y-%m-%d') and manager_id in (select id from user where name like '王%')
     */
    @Test
    public void selectByWrapper2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')=2019-02-14 or true=true")
                .inSql("manager_id","select id from user where name like '王%'");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓，（年龄小于40或者邮箱不为空）
     */
    @Test
    public void selectByWrapper3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name","王").and(wq-> wq.lt("age",40).or().isNotNull("email"));

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

    }

    /**
     * 名字为王姓，（年龄小于40，并且年龄大于20，并且邮箱不为空）
     */
    @Test
    public void selectWrapper4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").and(wq -> wq.between("age", 20, 40).and(wqq -> wqq.isNotNull("email")));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * （年龄小于40或者邮箱不为空）并且名字为王姓
     * （age<40 or email is not null）and name like '王%'
     */
    @Test
    public void selectWrapper5(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.nested(wq->wq.lt("age",40).or().isNotNull("email")).likeRight("name","王");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 年龄为30,31,35,34的员工
     */
    @Test
    public void selectWrapper6(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("age", Arrays.asList(30,31,34,35));

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 只返回满足条件的一条语句即可
     * limit 1
     */
    @Test
    public void selectWrapper7(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("age", Arrays.asList(30,31,34,35)).last("limit 1");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 查找为王姓的员工的姓名和年龄
     */
    @Test
    public void selectWrapper8(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age").likeRight("name","王");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 查询所有员工信息除了创建时间和员工ID列
     */
    @Test
    public void selectWrapper9(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class,info->!info.getColumn().equals("create_time")
                &&!info.getColumn().equals("manager_id"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectWrapper10(){
        User user = new User();
        user.setName("红");
        user.setAge(32);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperAllEq(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * lambda 条件构造表达式
     *  LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda();
     *  LambdaQueryWrapper<User> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
     *  LambdaQueryWrapper<User> lambdaQueryWrapper2 = Wrappers.lambdaQuery();
     *
     * 查找名字中包含‘雨’，并且年龄小于40的员工
     */
    @Test
    public void lambdaSelect(){
//        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper.like(User::getName,"雨");
//
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("name","雨");
//
//        List<User> userList = userMapper.selectList(queryWrapper);

        List<User> userList = new LambdaQueryChainWrapper<>(userMapper).like(User::getName, "雨").ge(User::getAge, 20).list();
        userList.forEach(System.out::println);


    }


    @Test
    public void MySqlTest(){
        List<User> userList = userMapper.selectAll();
        userList.forEach(System.out::println);
    }


    @Test
    public void selectPage(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",20);

        //设置当前页和页容量
        Page<User> page = new Page<>(1, 2,false);

        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);

        System.out.println("总页数："+userIPage.getPages());
        System.out.println("总记录数："+userIPage.getTotal());
        userIPage.getRecords().forEach(System.out::println);
    }


    @Test
    public void updateTest1(){
        User user = new User();
        user.setId(1088250446457389058L);
        user.setEmail("update@email");
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }

    @Test
    public void updateTest2(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","李艺伟").eq("age",26);

        User user = new User();
        user.setEmail("update2@email");
        int rows = userMapper.update(user, updateWrapper);
        System.out.println(rows);
    }


    @Test
    public void updateTest3(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","李艺伟").eq("age",26).set("email","update3@email.com");
        userMapper.update(null,updateWrapper);
    }

    @Test
    public void updateByLambda(){
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(User::getName,"李艺伟").eq(User::getAge,26).set(User::getAge,27);
        userMapper.update(null,lambdaUpdateWrapper);
    }


}




























