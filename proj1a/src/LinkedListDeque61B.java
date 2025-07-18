import java.util.List;

import java.util.ArrayList;


public class LinkedListDeque61B<T> implements Deque61B<T>{
    private Node sentinel;
    private int size;

    public class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x, sentinel.next, sentinel);
        sentinel.next = newNode;
        newNode.next.prev = newNode;
        size ++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size ++;
    }

    @Override
    public List<T> toList() {
        List<T> result = new ArrayList<>();
        Node current = sentinel;
        while (current.next != sentinel) {
            result.add(current.next.item);
            current = current.next;
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = sentinel.next;
        while (index > 0) {
            current = current.next;
            index --;
        }
        return current.item;
    }

    @Override
    public T getRecursive(int index) {
        Node current = sentinel.next;
        if (index < 0 || index >= size) {
            return null;
        } else {
            return getRecursiveHelper(index, sentinel.next);
        }
    }

    public T getRecursiveHelper(int index, Node start) {
        if (index == 0){
            return start.item;
        } else {
            return getRecursiveHelper(index - 1, start.next);
        }
    }
}
