package com.qingshixun.project.eshop.security;

import com.qingshixun.project.eshop.util.Md5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Md6PasswordEncoder implements PasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(Md5Util.encode((String)rawPassword));
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return Md5Util.encode((String)rawPassword);
    }
}
