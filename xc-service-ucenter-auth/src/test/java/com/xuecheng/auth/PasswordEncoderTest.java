package com.xuecheng.auth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: 98050
 * @Time: 2019-07-26 11:12
 * @Feature:
 */
public class PasswordEncoderTest {

    @Test
    public void test(){
        String password = "111111";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 10; i++) {
            String result = passwordEncoder.encode(password);
            System.out.println(result);
            System.out.println(passwordEncoder.matches(password, result));
        }
    }
}
