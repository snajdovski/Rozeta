package com.snajdovski.textencryptor;

import java.util.Random;

class PasswordGenerator {
    private static final String lower_case = "abcdefghijklmnopqrstuvwxyz";
    private static final String number = "0123456789";
    private static Random random = new Random();
    private static final String special_case = ",.-?:_()+-*/~`!@#$%^&=<>/\\'\"|[]{}";
    private static final String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    PasswordGenerator() {}

    static String generate(String str, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            int type = getType(str);
            if (type == 1) {
                sb.append(upper_case.charAt(getNumber(26)));
            } else if (type == 2) {
                sb.append(lower_case.charAt(getNumber(26)));
            } else if (type == 3) {
                sb.append(number.charAt(getNumber(10)));
            } else if (type == 4) {
                sb.append(special_case.charAt(getNumber(33)));
            }
        }
        return sb.toString();
    }

    private static int getType(String str) {
        char charAt = str.charAt(random.nextInt(str.length()));
        if (charAt == 'l') {
            return 2;
        }
        if (charAt == 'n') {
            return 3;
        }
        if (charAt != 's') {
            return charAt != 'u' ? 0 : 1;
        }
        return 4;
    }

    private static int getNumber(int i) {
        return random.nextInt(i);
    }
}