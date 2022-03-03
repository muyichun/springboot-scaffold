package com.xujing.springbootscaffold;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

/**
 * 更精简的方式去测试mapper
 */
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MapperScan("com.xujing.springbootscaffold.modules.test.mapper")
//class MybatisPlusSampleTest extends Tester {
class MybatisPlusSampleTest {
//    @Autowired
//    private UserMapper userMapper;
    @Test
    void testInsert() {
//        User user = new User();
//        user.setName("x1");
//        user.setAge(10);
//        user.setEmail("289651938@qq.com");
//        System.err.println(user);
//        userMapper.insert(user);
//        assertThat(user.getId()).isNotNull();
//        System.err.println(user);
    }
    @Test
    void testList() {
//        List<Map<String, Object>> mapList = userMapper.selectMaps(Wrappers.<User>query().orderByAsc("age"));
//        mapList.forEach(System.err::println);
    }

    /**
     * 此测试方法需要在@SpringBootTest的注解下启用
     */
    @Test
    void testPage() {
        //baseMapper 自带分页实现
//        Page<User> page = new Page<>(2,2);
//        IPage<User> list = userMapper.selectPage(
//                page,
//                Wrappers.<User>lambdaQuery().eq(User::getAge, 28)
//        );
//        System.err.println(list.getTotal());
//        System.err.println(list.getCurrent());
//        System.err.println(list.getSize());
//        list.getRecords().forEach(System.err::println);
        //xml 自定义分页
//        JSONObject o = new JSONObject();
//        o.put("age", 28);
//        IPage<User> list2 = userMapper.test(page, o);
//        System.err.println(list2.getTotal());
//        System.err.println(list2.getCurrent());
//        System.err.println(list2.getSize());
    }
    @Test
    void testMenu() {
//        userMapper.getUserMenu(3).forEach(System.err::println);
    }
}