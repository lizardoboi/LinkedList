package com.example.linkedlist;

public class MainTest {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        // Вставка
        long startTime = System.nanoTime();
        for (int i = 0; i < 2500; i++) {
            linkedList.add("Element" + i);
        }
        long insertTime = System.nanoTime() - startTime;

        // Извлечение
        startTime = System.nanoTime();
        for (int i = 0; i < 2500; i++) {
            linkedList.get(i);
        }
        long retrieveTime = System.nanoTime() - startTime;

        // Удаление
        startTime = System.nanoTime();
        for (int i = 0; i < 2500; i++) {
            linkedList.removeByIndex(0);
        }
        long deleteTime = System.nanoTime() - startTime;

        // Результаты
        System.out.println("Время вставки (нс): " + insertTime / 10000.0);
        System.out.println("Время извлечения (нс): " + retrieveTime / 10000.0);
        System.out.println("Время удаления (нс): " + deleteTime / 10000.0);
    }
}

