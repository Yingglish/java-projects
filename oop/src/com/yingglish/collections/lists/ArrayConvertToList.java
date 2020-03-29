package com.yingglish.collections.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class ArrayConvertToList {
    public void givenAnStringArray_whenConvertArrayToList_thenListCreated() {
        String[] flowers = { "Ageratum", "Allium", "Poppy", "Catmint" };
        List<String> flowerList = Arrays.asList(flowers);

        assertNotNull(flowerList);
        assertEquals(flowerList.size(), 4);
        assertEquals(flowerList.get(0), "Ageratum");
        assertEquals(flowerList.get(1), "Allium");
        assertEquals(flowerList.get(2), "Poppy");
        assertEquals(flowerList.get(3), "Catmint");
    }

    public void givenAnIntArray_whenConvertArrayToList_thenListWithOneElementCreated() {
        int[] primitives = { 1, 2, 3, 4 };
        List numbers = Arrays.asList(primitives);

        assertNotNull(numbers);
        assertEquals(numbers.size(), 1);
        assertEquals(numbers.get(0), primitives);
    }

    public void givenAnStringArray_whenConvertArrayToListAndAddAnElement_thenThrowUnsupportedOperationException() {
        String[] flowers = { "Ageratum", "Allium", "Poppy", "Catmint" };
        List<String> flowerList = Arrays.asList(flowers);

        assertNotNull(flowerList);
        assertEquals(flowerList.size(), 4);
        assertEquals(flowerList.get(0), "Ageratum");
        assertEquals(flowerList.get(1), "Allium");
        assertEquals(flowerList.get(2), "Poppy");
        assertEquals(flowerList.get(3), "Catmint");

        flowerList.add("Celosia");
    }

    public void givenAnStringArray_whenIterateArrayAndAddTheElementsToNewListAndAddAnElement_thenListOk() {
        String[] flowers = { "Ageratum", "Allium", "Poppy", "Catmint" };

        List<String> flowerList = new ArrayList<>();
        for(String flower: flowers) {
            flowerList.add(flower);
        }

        assertNotNull(flowerList);
        assertEquals(flowerList.size(), 4);

        assertEquals(flowerList.get(0), "Ageratum");
        assertEquals(flowerList.get(1), "Allium");
        assertEquals(flowerList.get(2), "Poppy");
        assertEquals(flowerList.get(3), "Catmint");

        flowerList.add("Celosia");

        assertEquals(flowerList.size(), 5);
        assertEquals(flowerList.get(4), "Celosia");
    }
}
