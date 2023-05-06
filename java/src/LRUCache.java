package src;

// Implement a cache with a specified capacity using LRU eviction policy.
import java.util.HashMap;

class LRUCache {
    class DNode {
        int key, value;
        DNode prev, next;
    }

    private void addNode(DNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DNode node) {
        DNode prev = node.prev;
        DNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }
    
    private void moveToHead(DNode node) {
        removeNode(node);
        addNode(node);
    }
    
    private DNode popTail() {
        DNode res = tail.prev;
        removeNode(res);
        return res;
    }
    
    private HashMap<Integer, DNode> cache;
    private int capacity;
    private DNode head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    
        head = new DNode();
        tail = new DNode();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        DNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
    
        moveToHead(node);
    
        return node.value;
    }
    
    public void put(int key, int value) {
        DNode node = cache.get(key);
    
        if (node == null) {
            DNode newNode = new DNode();
            newNode.key = key;
            newNode.value = value;
    
            cache.put(key, newNode);
            addNode(newNode);
    
            if (cache.size() > capacity) {
                DNode tail = popTail();
                cache.remove(tail.key);
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}


/* 
//////////////////////////////////////
// Problem: Implement an efficient LRU (Least Recently Used) Cache.
// Data structure: LinkedHashMap

import java.util.*;

class LRUCache {
    private LinkedHashMap<Integer, Integer> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}

*/