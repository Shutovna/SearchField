package com.example.searchinput;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchControllerTest {
    public static final String TEST_STRING = "Hello";
    private SearchController searchController;

    @BeforeEach
    public void setUp() {
        searchController = new SearchController();
    }

    @Test
    public void testSearchString_withNullSearchString() {
        String result = searchController.searchString(null);
        assertNull(result);
    }

    @Test
    public void testSearchString_withNormalString() {
        String result = searchController.searchString(TEST_STRING);
        assertEquals(TEST_STRING, result);
    }

    @Test
    public void testSearchSubString_withNullSearchString() {
        List<String> result = searchController.searchSubString(null);
        assertNotNull(result);
        assertEquals(10, result.size());
    }

    @Test
    public void testSearchSubString_withEmptySearchString() {
        List<String> result = searchController.searchSubString("");
        assertNotNull(result);
        assertEquals(10, result.size());
    }

    @Test
    public void testSearchSubString_withSpecificSearchString_returnNormalResult() {
        for (char c = 'a'; c <= 'z'; c++) {
            String searchString = String.valueOf(c);
            testSearchBySubStr(searchString);
        }
    }

    private void testSearchBySubStr(String searchString) {
        List<String> result = searchController.searchSubString(searchString);
        assertNotNull(result);
        assertTrue(result.size() <= 10);

        for (String s : result) {
            assertTrue(s.startsWith(searchString));
        }
    }

    @Test
    public void testSearchSubString_withSpecificSearchString() {
        String searchString = "ab";
        testSearchBySubStr(searchString);
        searchString = "xy";
        testSearchBySubStr(searchString);
    }
}