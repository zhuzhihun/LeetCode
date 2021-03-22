package dataStructure;
import java.util.HashMap;

/*
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？

 */
//最近最少使用
public class LRUCache {

    //146.LRU缓存机制（中等）
    //LRU 缓存算法的核心数据结构就是哈希链表，双向链表和哈希表的结合体
    /*
    双链表节点类
     */
    class Node {
        public int key,val;
        public Node next,prev;
        public Node(int k,int v){//构造函数
            this.key=k;
            this.val=v;
        }
    }
    /*
        依靠Node类型构建一个双链表， 以及API
        初始化头节点与尾节点
    */
    class DoubleList{
        //头尾虚节点
        private Node head,tail;
        //链表元素数
        private int size;
        public DoubleList(){
            //初始化双链表的数据
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next=tail;//头尾节点分别指向
            tail.prev=head;
            size=0;
        }
        // 在链表尾部添加节点 x，时间 O(1)
        public void addLast(Node node){
            node.prev=tail.prev;
            node.next=tail;
            tail.prev.next=node;
            tail.prev=node;
            size++;
        }

        // 删除链表中的 x 节点（x 一定存在）
        // 由于是双链表且给的是目标 Node 节点，时间 O(1)
        public void remove(Node node){
            //x一定存在 移除中间节点
            node.prev.next=node.next;
            node.next.prev=node.prev;
            size--;
        }

        // 删除链表中第一个节点，并返回该节点，时间 O(1)
        public Node removeFirst(){
            //先判断链表中是否还有节点
            if (head.next==tail){
                return null;
            }
            //获取要删除的节点
            Node first=head.next;
            //进行删除操作
            remove(first);
            //返回第一个节点
            return first;
        }

        // 返回链表长度，时间 O(1)
        public int size(){
            return size;
        }
    }
    /*
    我们实现的双链表 API 只能从尾部插入，也就是说靠尾部的数据是最近使用的，靠头部的数据是最久为使用的。
    将双向链表和哈希表结合起来
     */
    //定义属性与方法
    //key->Node(key,val)
    private HashMap<Integer,Node> map;
    //Node(k1,v1)<-->Node(k2,v2)...
    private DoubleList cache;
    //最大容量
    private int cap;
    /*
    以正整数作为容量 capacity 初始化 LRU 缓存
     */
    public LRUCache(int capacity){
        this.cap=capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }
    /*
    如果关键字 key 存在于缓存中，
    则返回关键字的值，否则返回 -1 。
     */
    public int get(int key){//get操作会使关键key变为最近使用
        if(map.containsKey(key)){
            //缓存cache中含有 key
            makeRecently(key);
            //则返回关键字的值
            return map.get(key).val;
            //<key,node>的值，从map中获取对应的值 node
            //node.val即为返回的值
        }
        return -1;
    }
    /*
    如果关键字已经存在，则变更其数据值；
    如果关键字不存在，则插入该组「关键字-值」。
    当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，
    从而为新的数据值留出空间。
     */
    public void put(int key,int value){
        if (map.containsKey(key)){//关键字已经存在
            //map中的映射可以不需要管  map中可以不需要按顺序排放  只是充当是否存在的标识符
            //从cache中移除node为key
            deleteKey(key);
            addRecently(key,value);
        }
        //关键字不存在，则插入该组「关键字-值」。
        Node node = new Node(key,value);
        //先创建node节点
        if (cache.size==cap){
            //此时缓存已满，先需要去掉最久未使用
            deleteLeastRecently();
        }
        //添加为最近使用的元素
        addRecently(key,value);

    }


/*
要同时维护一个双链表 cache 和一个哈希表 map，很容易漏掉一些操作，
比如说删除某个 key 时，在 cache 中删除了对应的 Node，但是却忘记在 map 中删除 key。
 */
   //在这两种数据结构之上提供一层抽象 API。
    /* 将某个 key 提升为最近使用的 */
    public void makeRecently(int key){
        //获取 key 对应的节点Node
        Node now = map.get(key);
        //将获得的此节点从DoubleList中移除
        cache.remove(now);
        //将此节点添加为最近访问位置
        cache.addLast(now);
        //map中已经存在映射
    }

    /* 添加最近使用的元素 */
    public void addRecently(int key,int val){
        //根据 key 与 val创建Node节点
        Node node = new Node(key,val);
        //将此节点添加为最近访问位置
        cache.addLast(node);
        //将map与此节点形成映射
        map.put(key,node);
    }

    /* 删除某一个 key */
    public void deleteKey(int key){
        //先从map中获取key对应的node
        Node node = map.get(key);
        //将获取到的node节点从DoubleList中移除
        cache.remove(node);
        //将map中形成的映射去掉
        map.remove(key);
    }

    /* 删除最久未使用的元素 */
    public void deleteLeastRecently(){
        //从DoubleList中获取最久未使用节点node
        Node node = cache.removeFirst();
        //删除map中对应的key
        map.remove(node.key);

    }


    /*
    使用LinkedHashMap实现
     */

}
