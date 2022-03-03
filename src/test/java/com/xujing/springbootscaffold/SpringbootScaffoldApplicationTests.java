package com.xujing.springbootscaffold;

import com.xujing.springbootscaffold.core.Tester;
import org.junit.jupiter.api.Test;


class SpringbootScaffoldApplicationTests extends Tester {
//    @Autowired
//    private UserMapper userMapper;
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
//        userList.forEach(System.err::println);
    }

    @Test
    void contextLoads() {

    }
}
