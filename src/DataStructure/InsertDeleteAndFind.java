package DataStructure;

import java.util.*;

public class InsertDeleteAndFind {
    /*
    给定一个包含 [0，n ) 中独特的整数的黑名单 B，写一个函数从 [ 0，n ) 中返回一个不在 B 中的随机整数。

    对它进行优化使其尽量少调用系统方法 Math.random() 。
    错误
    内存超出
     */
    class Solution {

        int[] list;
        Random random = new Random();
        int size;
        public Solution(int N, int[] blacklist) {
            //对blacklist排序 使得
            
            Arrays.sort(blacklist);
            size = N-blacklist.length;
            list = new int[size];
            if (blacklist.length==0){
                for (int i = 0; i < N; i++) {
                    list[i]=i;
                }
            }else{
                for (int i = 0,j=0; i < N; i++) {
                    if (j<blacklist.length&&i==blacklist[j]){
                        j++;
                    }else{
                        list[i-j]=i;
                    }
                }
            }


        }

        public int pick() {
            return list[random.nextInt(size)];
        }
    }
    /* code.380. 常数时间插入、删除和获取随机元素

    设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
    insert(val)：当元素 val 不存在时，向集合中插入该项。
    remove(val)：元素 val 存在时，从集合中移除该项。
    getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
    */
    /*
    1、插入，删除，获取随机元素这三个操作的时间复杂度必须都是 O(1)。
2、getRandom 方法返回的元素必须等概率返回随机元素，也就是说，如果集合里面有 n 个元素，每个元素被返回的概率必须是 1/n。
我们先来分析一下：对于插入，删除，查找这几个操作，哪种数据结构的时间复杂度是 O(1)？

HashSet 肯定算一个对吧。哈希集合的底层原理就是一个大数组，我们把元素通过哈希函数映射到一个索引上；如果用拉链法解决哈希冲突，那么这个索引可能连着一个链表或者红黑树。
那么请问对于这样一个标准的 HashSet，你能否在 O(1) 的时间内实现 getRandom 函数？

其实是不能的，因为根据刚才说到的底层实现，元素是被哈希函数「分散」到整个数组里面的，更别说还有拉链法等等解决哈希冲突的机制，基本做不到 O(1) 时间等概率随机获取元素。
除了 HashSet，还有一些类似的数据结构，比如哈希链表 LinkedHashSet，我们前文 手把手实现LRU算法 和 手把手实现LFU算法 讲过这类数据结构的实现原理，本质上就是哈希表配合双链表，元素存储在双链表中。
但是，LinkedHashSet 只是给 HashSet 增加了有序性，依然无法按要求实现我们的 getRandom 函数，因为底层用链表结构存储元素的话，是无法在 O(1) 的时间内访问某一个元素的。
根据上面的分析，对于 getRandom 方法，如果想「等概率」且「在 O(1) 的时间」取出元素，一定要满足：底层用数组实现，且数组必须是紧凑的。
这样我们就可以直接生成随机数作为索引，从数组中取出该随机索引对应的元素，作为随机元素。

但如果用数组存储元素的话，插入，删除的时间复杂度怎么可能是 O(1) 呢？
可以做到！对数组尾部进行插入和删除操作不会涉及数据搬移，时间复杂度是 O(1)。
所以，如果我们想在 O(1) 的时间删除数组中的某一个元素 val，可以先把这个元素交换到数组的尾部，然后再 pop 掉。
交换两个元素必须通过索引进行交换对吧，那么我们需要一个哈希表 valToIndex 来记录每个元素值对应的索引。
     */
    static class RandomizedSet {
        Map<Integer,Integer> map;
        //将数字的值与在list中位置的索引联系起来
        List<Integer> list;
        //list用来实现随机取数时为O(1)操作
        Random rand = new Random();
        //产生随机数
        /** Initialize your data structure here. */
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)){
                return false;
            }
            map.put(val,list.size());
            list.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            Integer idx = map.remove(val);
            if (idx==null){
                return false;
            }
            //先需要将删除的元素与最末尾的元素交换位置后进行删除
            if (idx!=list.size()-1){
                int last = list.remove(list.size()-1);
                map.put(last,idx);
                //将最后位置的索引换到a
                list.set(idx,list.get(list.size()-1));
                //map中更新移动最后位置节点的索引
            }else{
                list.remove(list.size()-1);
                //map.remove()
            }
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }

    public static void main(String[] args) {
        // 初始化一个空的集合。
        RandomizedSet randomSet = new RandomizedSet();

        // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        System.out.println(randomSet.insert(1));

        // 返回 false ，表示集合中不存在 2 。
        System.out.println(randomSet.remove(2));

        // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        System.out.println(randomSet.insert(2));

        // getRandom 应随机返回 1 或 2 。
        System.out.println(randomSet.getRandom());

        // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        System.out.println(randomSet.remove(1));

        // 2 已在集合中，所以返回 false 。
        System.out.println(randomSet.insert(2));

        // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
        System.out.println(randomSet.getRandom());

    }
}
