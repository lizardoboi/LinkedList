package com.example.linkedlist;

import com.example.linkedlist.LinkedList.ListNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<T> implements Iterator<T> {
    ListNode<T> current;
    private boolean forward;

    public LinkedListIterator(ListNode<T> start, boolean forward) {
        this.current = start;
        this.forward = forward;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T data = current.data;
        if (forward) {
            current = current.next;
        } else {
            current = current.prev;
        }
        return data;
    }

    // Операция доступа по чтению и записи к текущему значению *
    public T getCurrentValue() {
        if (current == null) {
            throw new NoSuchElementException("Iterator is positioned beyond the bounds of the list");
        }
        return current.data;
    }

    // Операция инкремента для перехода к следующему (к предыдущему для обратного итератора) значению в списке ++
    public void increment() {
        if (forward) {
            if (current == null) {
                throw new NoSuchElementException("Iterator is positioned beyond the bounds of the list");
            }
            current = current.next;
        } else {
            if (current == null) {
                throw new NoSuchElementException("Iterator is positioned beyond the bounds of the list");
            }
            current = current.prev;
        }
    }

    // Операция декремента для перехода к предыдущему значению в списке --
    public void decrement() {
        if (forward) {
            if (current == null) {
                throw new NoSuchElementException("Iterator is positioned beyond the bounds of the list");
            }
            current = current.prev;
        } else {
            if (current == null) {
                throw new NoSuchElementException("Iterator is positioned beyond the bounds of the list");
            }
            current = current.next;
        }
    }

    // Проверка равенства однотипных итераторов ==
    public boolean isEqualTo(LinkedListIterator<T> other) {
        return this.current == other.current && this.forward == other.forward;
    }

    // Проверка неравенства однотипных итераторов !=
    public boolean isNotEqualTo(LinkedListIterator<T> other) {
        return !isEqualTo(other);
    }
}

