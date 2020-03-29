package com.yingglish.collections.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertEquals;

/**
 * 字符串查找
 */
public class FindAStringInGivenList {
    public static List<String> findUsingLoopWithRegex(String search, List<String> list) {

        List<String> matches = new ArrayList<String>();

        String pattern = ".*"+search+".*";
        Pattern p = Pattern.compile(pattern);

        for(String str: list) {
            if (p.matcher(str).matches()) {
                matches.add(str);
            }
        }

        return matches;
    }


    public static List<String> findUsingLoop(String search, List<String> list) {

        List<String> matches = new ArrayList<>();

        for(String str: list) {
            if (str.contains(search)) {
                matches.add(str);
            }
        }

        return matches;
    }

    public static List<String> findUsingStream(String search, List<String> list) {

        return list.stream()
                .filter(str -> str.trim().contains(search))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Jack and Jill");
        list.add("James and Sarah");
        list.add("Sam and Louise");
        list.add("Jack");
        list.add("");

        List matchingElements = findUsingLoopWithRegex("Jack", list);
        assertEquals(2, matchingElements.size());
        assertEquals("Jack and Jill", matchingElements.get(0));
        assertEquals("Jack", matchingElements.get(1));

    }
}
