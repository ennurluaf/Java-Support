package com.support.list;

public class LinkedMap<K, V> implements Cloneable, Iterable<Node<K,V>>{

    private Node<K, V> head;
    private int size;

    public LinkedMap() {
        this.head = null;
        this.size = 0;
    }

    public void put(K key, V value) {
        if (head == null) {
            head = new Node<>(key, value);
            return;
        }
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            if (current.next == null) {
                break;
            }
            current = current.next;
        }
        size++;
        current.next = new Node<>(key, value);
    }

    public V get(K key) {
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void remove(K key) {
        if (head == null) {
            return;
        }
        if (head.key.equals(key)) {
            head = head.next;
            return;
        }
        Node<K, V> current = head;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
        size--;
    }

    public void clear() {
        head = null;
        size = 0;
    }   

    @Override
    public LinkedMap<K, V> clone() {
        LinkedMap<K, V> cloned = new LinkedMap<>();
        Node<K, V> current = head;
        while (current != null) {
            cloned.put(current.key, current.value);
            current = current.next;
        }
        return cloned;
    }

    @Override
    public java.util.Iterator<Node<K, V>> iterator() {
        return new java.util.Iterator<Node<K, V>>() {
            private Node<K, V> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                Node<K, V> temp = current;
                current = current.next;
                return temp;
            }
        };
    }

    public int size() {
        return size;
    }

    public LinkedMap<V, K> invert() {
        LinkedMap<V, K> inverted = new LinkedMap<>();
        Node<K, V> current = head;
        while (current != null) {
            inverted.put(current.value, current.key);
            current = current.next;
        }
        return inverted;
    }

    public int indexOf(K key) {
        Node<K, V> current = head;
        int index = 0;
        while (current != null) {
            if (current.key.equals(key)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

}
