package com.ics.cloud.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BPwdUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String BCryptPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}