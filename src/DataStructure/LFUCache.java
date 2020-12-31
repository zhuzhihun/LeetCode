package DataStructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
实现 LFUCache 类：

LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
void put(int key, int value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 */
public class LFUCache {
    //访问次数最少的minFreq与缓存的大小capacity
    int minFreq,capacity;
    //记录关键字key与节点node间的对应
    Map<Integer,Node> key_table;
    //将频数freq作为key对应不同频数下的多组节点链表LinkedList
    Map<Integer, LinkedList<Node>> freq_table;
    //初始化 对空间赋值 对map进行初始化
    public LFUCache(int capacity) {
        this.capacity=capacity;
        //初始化最小次数为0
        this.minFreq=0;
        key_table = new HashMap<Integer, Node>();
        freq_table = new HashMap<Integer,LinkedList<Node>>();
    }
    /*
    如果键存在于缓存中，则获取键的值，否则返回 -1
     */
    public int get(int key) {
        if (capacity<1){//容量小于1
            return -1;
        }
        if (!key_table.containsKey(key)){//键不存在与缓存中 返回-1
            return -1;
        }
        //键存在缓存中
        /*
        key-table不需要动？
         */
        //根据key从key_table中找到 节点 node
        Node node=key_table.get(key);
        //根据节点的次数freq从freq_table中找到对应的linkedList
        //将此节点node从对应频数freq的linkedList中移除
        //将更新后的linkedList存储到对应freq中的freq_table中
        freq_table.get(node.freq).remove(node);
        //判断缓存最小访问次数是否需要改变
        changeMinFreq(node);
        //获取频数freq+1的LinkedList
        node.freq=node.freq+1;
        //先判断freq+1的LinkedList的是否存在freq-table中
        containKey(node);

        //返回key 对应的 value值
        return node.value;
    }
    /*
    判断freq+1的LinkedList的是否存在freq-table中
    不存在就创建添加
    存在就更新
     */
    public void containKey(Node node){
        if (!freq_table.containsKey(node.freq)){//不存在 创建-添加-添加
            LinkedList<Node> newLinkedList = new LinkedList<>();
            newLinkedList.addFirst(node);
            freq_table.put(node.freq,newLinkedList);
        }else{//当次频数freq+1已经存在  先获取  再更新
            freq_table.get(node.freq).addFirst(node);
        }
    }
    /*
    判断缓存中的minFreq是否需要改变
     */
    public void changeMinFreq(Node node){
        if (minFreq==node.freq&&freq_table.get(node.freq).isEmpty()){
            //如果访问次数最小的为寻找到节点的访问次数
            //并且此时访问最少次数的linkedList为空  访问最小值minFreq++
            minFreq++;
        }
    }
    /*
    如果键已存在，则变更其值；如果键不存在，
    请插入键值对。当缓存达到其容量时，
    则应该在插入新项之前，
    使最不经常使用的项无效。
    在此问题中，当存在平局
    （即两个或更多个键具有相同使用频率）时，
    应该去除 最久未使用 的键。
     */
    public void put(int key, int value) {
        if (capacity<1){//容量空间不足 返回
            return;
        }
        if (key_table.containsKey(key)){//键已存在，则变更其值
            //获取key对应的节点
            Node node = key_table.get(key);
            //变更节点node对应的value值
            node.value=value;
            //根据节点的频数对应freq_table的关键字freq 找到对应的LinkedList<Node>
            freq_table.get(node.freq).remove(node);
            //对linkedList移除node

            //判断最小访问次数需不需要改变
            changeMinFreq(node);
            //改变节点的访问次数
            node.freq=node.freq+1;
            //根据几点次数判断freq_tab中是否有此频数node.freq的LinkedList链表
            containKey(node);
        }else{//键不存在，插入键值对
            //创建节点 其频数为1
            Node node = new Node(key,value,1);
            //存入到key_table中
            key_table.put(key,node);
            //判断缓存空间是否已满
            if (capacity==key_table.size()-1){
                //表明缓存空间已满
                //先删除频数最小并且 存在时间最久的消息
                Node n = freq_table.get(minFreq).removeLast();
                //对应的key_table中也要移除
                key_table.remove(n.key);

            }
            //新插入的节点访问次数必定最小 且为1
            minFreq=1;
            //查看是否含有频数为1的 存在
            containKey(node);
        }
    }
    class Node{
        int key,value,freq;
        public Node(int k,int v,int f){
            this.key=k;
            this.value=v;
            this.freq=f;
        }
    }
}
