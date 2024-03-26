package com.example.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private ListNode<T>[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    // Конструктор
    public LinkedList() {
        this.elements = new ListNode[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Конструктор копирования
    public LinkedList(LinkedList<T> other) {
        this.size = other.size;
        this.elements = new ListNode[other.elements.length];
        for (int i = 0; i < other.size; i++) {
            this.elements[i] = new ListNode<>(other.elements[i].data);
        }
    }

    // Деструктор
    public void LinkedListDest() {
        clear();
    }

    // Очистка списка
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
    // Опрос размера списка
    public int getSize() {
        return size;
    }
    // Проверка на пустоту
    public boolean isEmpty() {
        return size == 0;
    }
    //Опрос наличия заданного значения
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }
    //Чтение значения с заданным номером в списке
    public T get(int index) {
        ListNode<T> node = getNode(index);
        return node.data;
    }
    //Изменение значения с заданным номером в списке
    public void set(int index, T newData) {
        ListNode<T> node = getNode(index);
        node.data = newData;
    }
    //получение позиции в списке для заданного значения
    public int indexOf(T data) {
        for (int i = 0; i < size; i++) {
            if (elements[i].data.equals(data)) {
                return i;
            }
        }
        return -1;
    }
    //добавление элемента
    public void add(T data) {
        addAtIndex(size, data);
    }
    //добавление по индексу
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ensureCapacity();
        ListNode<T> newNode = new ListNode<>(data);
        if (index == 0) {
            if (!isEmpty()) {
                newNode.next = elements[0];
                elements[0].prev = newNode;
            }
            for (int i = size; i > index; i--) {
                elements[i] = elements[i - 1];
            }
            elements[0] = newNode;
        } else if (index == size) {
            ListNode<T> prevNode = elements[size - 1];
            prevNode.next = newNode;
            newNode.prev = prevNode;
            elements[size] = newNode;
        } else {
            ListNode<T> prevNode = elements[index - 1];
            ListNode<T> nextNode = elements[index];
            prevNode.next = newNode;
            nextNode.prev = newNode;
            newNode.prev = prevNode;
            newNode.next = nextNode;
            // Сдвигаем остальные элементы вправо
            for (int i = size; i > index; i--) {
                elements[i] = elements[i - 1];
            }
        }
        elements[index] = newNode;
        size++;
    }
    //удаление по индексу
    public void removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (size == 1) {
            elements[0] = null;
        } else if (index == 0) {
            elements[1].prev = null;
            elements[0] = elements[1];
        } else if (index == size - 1) {
            elements[index - 1].next = null;
        } else {
            elements[index - 1].next = elements[index + 1];
            elements[index + 1].prev = elements[index - 1];
        }

        // Удаляем ссылки на удаляемый элемент
        elements[index] = null;
        // Сдвигаем оставшиеся элементы массива на одну позицию влево
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    private ListNode<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }
    //проверка содержимого
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            ListNode<T>[] newElements = new ListNode[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }
    //удаление по значению
    public void removeByValue(T data) {
        int index = indexOf(data);
        if (index != -1) {
            removeByIndex(index);
        }
    }
    // Метод для получения прямого итератора, указывающего на первый элемент списка
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the list");
                }
                ListNode<T> currentNode = elements[currentIndex++];
                return currentNode.data;
            }
        };
    }
    // Метод для получения "неустановленного" прямого итератора
    public Iterator<T> end() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false; // Всегда возвращает false, так как это "неустановленный" итератор
            }

            @Override
            public T next() {
                throw new NoSuchElementException("No more elements in the list");
            }
        };
    }

    // Операция доступа по чтению и записи к текущему значению *
    public T getCurrentValue(Iterator<T> it) {
        if (it instanceof LinkedListIterator) {
            ListNode<T> currentNode = ((LinkedListIterator<T>) it).current;
            if (currentNode != null) {
                return currentNode.data;
            } else {
                throw new NoSuchElementException("Iterator is not positioned");
            }
        } else {
            throw new IllegalArgumentException("Iterator type not supported");
        }
    }

    // Операция инкремента для перехода к следующему (к предыдущему для обратного итератора) значению в списке ++
    public void increment(Iterator<T> it) {
        if (it instanceof LinkedListIterator) {
            ((LinkedListIterator<T>) it).increment();
        } else {
            throw new IllegalArgumentException("Iterator type not supported");
        }
    }

    // Операция декремента для перехода к предыдущему (к следующему для обратного итератора) значению в списке --
    public void decrement(Iterator<T> it) {
        if (it instanceof LinkedListIterator) {
            ((LinkedListIterator<T>) it).decrement();
        } else {
            throw new IllegalArgumentException("Iterator type not supported");
        }
    }

    // Проверка равенства однотипных итераторов ==
    public boolean isEqualTo(Iterator<T> it1, Iterator<T> it2) {
        if (it1 instanceof LinkedListIterator && it2 instanceof LinkedListIterator) {
            return ((LinkedListIterator<T>) it1).isEqualTo((LinkedListIterator<T>) it2);
        } else {
            throw new IllegalArgumentException("Iterator types not supported");
        }
    }

    // Проверка неравенства однотипных итераторов !=
    public boolean isNotEqualTo(Iterator<T> it1, Iterator<T> it2) {
        return !isEqualTo(it1, it2);
    }

    static class ListNode<T> {
        T data;
        ListNode<T> prev;
        ListNode<T> next;

        ListNode(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
}


