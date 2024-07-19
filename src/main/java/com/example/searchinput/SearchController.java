package com.example.searchinput;

import ch.qos.logback.core.util.StringUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
public class SearchController {
    private static final int RESULT_COUNT = 10;
    private static final List<String> data = new ArrayList<>();

    static {
        for (int i = 0; i < 100000; i++) {
            String randStr = RandomStringUtils.randomAlphabetic(10);
            data.add(randStr.toLowerCase());
        }
        Collections.sort(data);
    }
    @GetMapping("/searchSubString")
    public List<String> searchSubString(@RequestParam(value = "str", required = false) String searchString) {
        int cnt = 0;
        List<String> resultList = new ArrayList<>();
        if (StringUtil.isNullOrEmpty(searchString)) {
            for (int i = 0; i < RESULT_COUNT; i++) {
                int ranIdx = new Random().nextInt(data.size() - 1);
                resultList.add(data.get(ranIdx));
            }

        } else {
            searchString = searchString.toLowerCase();
            for (int i = 0; i < data.size() && cnt < RESULT_COUNT; i++) {
                String s = data.get(i);
                if (s.startsWith(searchString)) {
                    resultList.add(s);
                    cnt++;
                }
            }

        }

        return resultList;
    }

    @GetMapping("/searchString")
    public String searchString(@RequestParam(value = "str", required = false) String searchString) {
        return searchString;
    }
}

