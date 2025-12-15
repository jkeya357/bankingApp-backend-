package com.nzimbu_yeno.nzimbu.domain;

import java.security.SecureRandom;
public class AccountNumber {

    private static final int length = 12;
    private static final SecureRandom random = new SecureRandom();

    public static String generate(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(random.nextInt(9) + 1);

        for(int i = 1; i < length; i++){
            stringBuilder.append(random.nextInt(10));
        }

        return stringBuilder.toString();
    }
}
