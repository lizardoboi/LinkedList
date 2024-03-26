package com.example.linkedlist;

import java.util.Iterator;

public class IteratorTest {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element1");
        linkedList.add("Element2");
        linkedList.add("Element3");

        // Создание прямого итератора
        Iterator<String> iterator = linkedList.iterator();
        System.out.println("Прямой итератор:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
