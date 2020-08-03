package com.example.designmode.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: java-sample
 * @description: jsontest
 * @author: baijd-a
 * @create: 2020-07-29 18:59
 **/
public class JacksionTest {

    private final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void jsonTest() throws JsonProcessingException {
        List<Pair<String, String>> list = new ArrayList<Pair<String, String>>(){{
            add(new Pair<String, String>("d", "123"));
            add(new Pair<String, String>("a", "456"));
            add(new Pair<String, String>("m", "789"));
        }};
        Map<String, String> results = new HashMap<>();
        System.out.println(MAPPER.writeValueAsString(list));
        list.forEach(p -> {
            results.put(p.getKey(), p.getValue());
        });
        System.out.println(MAPPER.writeValueAsString(results));
    }
}
