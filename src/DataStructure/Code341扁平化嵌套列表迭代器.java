package DataStructure;/*
* 扁平化嵌套列表迭代器
* https://mp.weixin.qq.com/s/uEmD5YVGG5LHQEmJQ2GSfw
* */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Code341扁平化嵌套列表迭代器 {
    //DataStructure.myNestedIterator my = new DataStructure.myNestedIterator();
}
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface DataStructure.NestedInteger {
 *
 *     // @return true if this DataStructure.NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this DataStructure.NestedInteger holds, if it holds a single integer
 *     // Return null if this DataStructure.NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this DataStructure.NestedInteger holds, if it holds a nested list
 *     // Return null if this DataStructure.NestedInteger holds a single integer
 *     public List<DataStructure.NestedInteger> getList();
 * }
 */
class NestedIterator implements Iterator<Integer> {

    Iterator<Integer> it;
    public NestedIterator(List<NestedInteger> nestedList) {
        List<Integer> result = new LinkedList<>();
        for (NestedInteger node : nestedList) {
            // 以每个节点为根遍历
            traverse(node, result);
        }
        // 得到 result 列表的迭代器
        this.it = result.iterator();
    }

    private void traverse(NestedInteger root, List<Integer> result) {


    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}
//符合迭代器惰性求值的特性的解法
class myNestedIterator implements Iterator<Integer> {
    private LinkedList<NestedInteger> list;

    public myNestedIterator(List<NestedInteger> nestedList) {
        // 不直接用 nestedList 的引用，是因为不能确定它的底层实现
        // 必须保证是 LinkedList，否则下面的 addFirst 会很低效
        list = new LinkedList<>(nestedList);
    }

    public Integer next() {
        // hasNext 方法保证了第一个元素一定是整数类型
        return list.remove(0).getInteger();
        //移除第一个元素list.remove(0)是NestedInteger类型 并且是Integer 利用其.getInteger方法获取整数
    }

    public boolean hasNext() {
        // 循环拆分列表元素，直到列表第一个元素是整数类型
        while (!list.isEmpty() && !list.get(0).isInteger()) {//当list不为空时，并且列表中地一个元素不是整数
            // 当列表开头第一个元素是列表类型时，进入循环
            List<NestedInteger> first = list.remove(0).getList();//将第一个列表取出
            // 将第一个列表打平并按顺序添加到开头
            // 需要对first列表从最后一个元素开始取  这样取完后保证 first中列表还是按顺序保存到list中的最开头。
            for (int i = first.size() - 1; i >= 0; i--) {
                list.addFirst(first.get(i));//将取出的数据添加到开头
            }
        }
        return !list.isEmpty();//当list为空时，即没有元素  返回false.
    }
}
/**
 * Your DataStructure.NestedIterator object will be instantiated and called as such:
 * DataStructure.NestedIterator i = new DataStructure.NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

// NestedInteger类
class NestedInteger {
    private Integer val;
    private List<NestedInteger> list;

    public NestedInteger(Integer val) {
        this.val = val;
        this.list = null;
    }
    public NestedInteger(List<NestedInteger> list) {
        this.list = list;
        this.val = null;
    }

    // 如果其中存的是一个整数，则返回 true，否则返回 false
    public boolean isInteger() {
        return val != null;
    }

    // 如果其中存的是一个整数，则返回这个整数，否则返回 null
    public Integer getInteger() {
        return this.val;
    }

    // 如果其中存的是一个列表，则返回这个列表，否则返回 null
    public List<NestedInteger> getList() {
        return this.list;
    }



}
