package dataStructure;
/*
    使用LinkedHashMap实现
     */

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU extends LinkedHashMap<Integer,Integer> {

    private int capacity;

    public LRU(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

//    public void put(int key, int value) {
//        super.put(key, value);//使用父类的put方法 可以不需要重写
//    }

    @Override
    //重写removeEldestEntry方法实现不同的缓存策略
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
