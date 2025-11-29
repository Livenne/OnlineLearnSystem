package com.livenne.common.constant;

import java.util.regex.Pattern;

public interface AuthConstant {
    Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{6,18}$");
    Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z!@#$%^&*\\-+_=]{8,18}$");
}
