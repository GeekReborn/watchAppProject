package net.sxbang.watchapp;

import net.sxbang.watchapp.dao.SysOrderArchiveDAO;
import net.sxbang.watchapp.dao.SysOrderQrCodeDAO;
import net.sxbang.watchapp.dao.SysUserDAO;
import net.sxbang.watchapp.model.SysOrderQrCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class WatchappApplicationTests {
    @Autowired
    SysOrderQrCodeDAO sysOrderQrCodeDAO;

    @Test
    void testPasswordEncoder() {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        // $2a$10$R/WheVVy1E/2rkwS2bLqz..XIMXXXMAt4Q5PZtgxCuqk9g2fQMs7i
        // $2a$10$IfmLUOT9MPZGkxzOway./evdqM5d./qL8sj.j0PASpB/s1ADDDgge
    }

    @Test
    void test() {
//        System.out.println(sysOrderArchiveDAO.findById(277));
//        System.out.println(sysOrderQrCodeDAO.findByCode("H1ztOSWn18l74A75"));
//        sysOrderArchiveDAO.findAll().stream().forEach(System.out::println);
//        sysOrderQrCodeDAO.updateStateByOrderIdAndScene();
    }
}
