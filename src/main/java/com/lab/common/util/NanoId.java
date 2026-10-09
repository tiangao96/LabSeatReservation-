package com.lab.common.util;

import java.security.SecureRandom;

public final class NanoId {
    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".toCharArray();
    private static final int SIZE = 21;
    private static final SecureRandom RANDOM = new SecureRandom();

    private NanoId() {
    }

    public static String next() {
        byte[] buf = new byte[21];
        RANDOM.nextBytes(buf);
        char[] out = new char[21];
        for (int i = 0; i < 21; ++i) {
            out[i] = ALPHABET[(buf[i] & 0xFF) % 64];
        }
        return new String(out);
    }
}

