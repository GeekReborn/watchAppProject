package net.sxbang.watchapp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysUserDAOTests {

    @Autowired
    SysUserDAO sysUserDAO;

    @Test
    void testFindAll() {
        sysUserDAO.findAll().stream().forEach(System.out::println);
    }

}
