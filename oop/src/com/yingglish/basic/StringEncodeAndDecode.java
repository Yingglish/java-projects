package com.yingglish.basic;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class StringEncodeAndDecode {
    @Test
    public void german_string_encode_1() {
        String germanString = "Entwickeln Sie mit Vergnügen";
        byte[] germanBytes = germanString.getBytes();

        String asciiEncodedString = new String(germanBytes, StandardCharsets.US_ASCII); // Entwickeln Sie mit Vergn?gen
        if (asciiEncodedString.equals(germanString)) {
            System.out.println("true");
        }
    }

    @Test
    public void german_string_encode_2() {
        String rawString = "Entwickeln Sie mit Vergnügen";
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(rawString);

        String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
        if (utf8EncodedString.equals(rawString)) {
            System.out.println("true");
        }

    }

    @Test
    public void english_string_encode_1() {
        String englishString = "Develop with pleasure";
        byte[] englishBytes = englishString.getBytes();

        String asciiEncondedEnglishString = new String(englishBytes, StandardCharsets.US_ASCII);
        if (asciiEncondedEnglishString.equals(englishString)) {
            System.out.println("true");
        }
    }
}
