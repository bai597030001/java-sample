package com.example.designmode.iterator;

import java.util.*;

/**
 * @program: java-sample
 * @description: jdk迭代器
 * @author: baijd-a
 * @create: 2020-08-11 19:20
 **/
public class JDKIterator {
    private static List<String> arrayList = new ArrayList<String>() {{
        add("a");
        add("b");
        add("c");
    }};
    private static List<String> linkedList = new LinkedList<String>() {{
        add("d");
        add("e");
        add("f");
    }};
    private static Set<String> hashSet = new HashSet<String>() {{
        add("g");
        add("h");
        add("i");
    }};
    private static Set<String> treeSet = new TreeSet<String>() {{
        add("g");
        add("h");
        add("i");
    }};
    private static Map<String, String> hashMap = new HashMap<String, String>() {{
        put("1", "11");
        put("2", "22");
        put("3", "33");
    }};
    private static Map<String, String> treeMap = new TreeMap<String, String>() {{
        put("4", "44");
        put("5", "55");
        put("6", "66");
    }};

    public static void main(String[] args) {
        Iterator<String> iterator = arrayList.iterator();
        // ArrayList
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        // LinkedList
        iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        // HashSet
        iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        // TreeSet
        iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        // HashMap
        Iterator<Map.Entry<String, String>> mapIterator = hashMap.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, String> next = mapIterator.next();
            System.out.println(next.getKey() + ":" + next.getValue());
        }
        // TreeMap
        mapIterator = treeMap.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, String> next = mapIterator.next();
            System.out.println(next.getKey() + ":" + next.getValue());
        }
    }
}
