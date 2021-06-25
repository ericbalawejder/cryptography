package com.examples.cryptography.util;

public class Util {

    // https://mkyong.com/java/java-how-to-convert-bytes-to-hex/
    // For printing byte[] arrays.
    public static String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02X ", b));
        }
        return stringBuilder.toString();
    }

    public static String bytesToHex(byte[] bytes, int hexPerLine) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            stringBuilder.append(String.format("%02X ", bytes[i]));
            if ((i + 1) % hexPerLine == 0) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
