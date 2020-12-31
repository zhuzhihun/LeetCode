package DataStructure;

import java.util.*;

/*
设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：

postTweet(userId, tweetId): 创建一条新的推文
getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
follow(followerId, followeeId): 关注一个用户
unfollow(followerId, followeeId): 取消关注一个用户

 */
public class Twitter {
    /*
    测试主程序
     */
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

        // 用户1关注了用户2.
        twitter.follow(1, 2);

        // 用户2关注了用户1.
        twitter.follow(2, 1);

        // 用户2的获取推文应当返回一个列表，其中包含一个id为5的推文.
        twitter.getNewsFeed(2);

        // 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);

        // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
        // 推文id6应当在推文id5之前，因为它是在5之后发送的.
        twitter.getNewsFeed(1);

        twitter.getNewsFeed(2);

        // 用户2取消关注了用户1.
        twitter.unfollow(2, 1);

        twitter.getNewsFeed(1);
        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // 因为用户1已经不再关注用户2.
        twitter.getNewsFeed(2);
        // 用户1取消关注了用户2.
        twitter.unfollow(1, 2);

        twitter.getNewsFeed(1);
        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // 因为用户1已经不再关注用户2.
        twitter.getNewsFeed(2);

    }
    private static int timestamp = 0;
    class Twit{//推特类
         //int id;//表示时间顺序
         int twId;//用户发的推特Id
         private int timestamp;
         private Twit next;
         //下一个推特的全部信息
         public Twit(){}
         public Twit(int id, int timestamp) {
             this.twId = id;
             this.timestamp = timestamp;
         }
     }

     //用户的id  对用它关注的人  set是用来表明不能重复
     Map<Integer, Set<Integer>> userToFollow;
    //记录关键字key与节点node间的对应
    //Map<Integer, LFUCache.Node> key_table;
    //用户ID 对应自己发的推特
     Map<Integer,Twit> userToTwit;
    //将频数freq作为key对应不同频数下的多组节点链表LinkedList
    //Map<Integer, LinkedList<LFUCache.Node>> freq_table;

    /** Initialize your data structure here. */
    public Twitter() {
        userToFollow = new HashMap<>();
        userToTwit   = new HashMap<>(11);

    }

    /** user 发表一条 tweet 动态 */
    public void postTweet(int userId, int tweetId) {
        //先创建 推特
        timestamp++;
        //用来对推特发送时间排序
        Twit twit = new Twit(tweetId,timestamp);
        //判断用户userId是否存在
        if (!userToTwit.containsKey(userId)){
            //用户不存在  创建用户
            createTwitUser(userId);
        }
        //此时用户存在
        //直接将当前的推文保存到自己的最前面
        twit.next= userToTwit.get(userId);
        //将更新后的链表从新保存到userToTwit中
        userToTwit.put(userId,twit);
//        if (userToTwit.get(userId).size()>10){
//            //确保 每个人记录中只有10条
//            userToTwit.get(userId).removeLast();
//        }
    }
    /*
    当用户id不存在时
    无推文 创建用户
     */
    public void createTwitUser(int userId){
        userToTwit.put(userId,new Twit().next);
        //用户对关注者 创建关注者并添加到 userToFollow中
        createUserToFollower(userId);
    }
    /*
    新用户在userToFollow中创建 新的用户并关注自己
     */
    public void createUserToFollower(int userId){
        Set<Integer> set = new HashSet<>();
        //关注自己
        set.add(userId);
        userToFollow.put(userId,set);
    }
    /** 返回该 user 关注的人（包括他自己）最近的动态 id，
     最多 10 条，而且这些动态必须按从新到旧的时间线顺序排列。*/
    public List<Integer> getNewsFeed(int userId) {
        //1.判断用户存在与否
        if(!userToFollow.containsKey(userId)){
            //用户不存在
            return new ArrayList<>();
        }
        //2.用户存在 先寻找到对应的关注者列表
        Set<Integer> follows= userToFollow.get(userId);
        //n表示关注的人数 从对应用户id的推文中寻找 近十篇推文
        int n = follows.size();
        if (n==0){//没有关注者的时候  实际不存在  起码会关注自己
            return null;
        }
        //LinkedList<Twit>[] linkedLists = new LinkedList[n];
        //创建传入的数组
        Twit[] twits = new Twit[n];
        int i=0;
        for (Integer f:follows){
            twits[i]=userToTwit.get(f);
            i++;
        }
        Twit twit = mergeKLists(twits);
        List<Integer> integerList = new ArrayList<>();
        while (twit!=null){
            integerList.add(twit.twId);
            twit=twit.next;
        }
        return integerList;
    }

    /** follower 关注 followee，如果 Id 不存在则新建 */
    public void follow(int followerId, int followeeId) {
        //0.是否是自己关注自己
        if (followerId==followeeId){
            return;
        }
        //1.先判断follower是否存在
        if (!userToTwit.containsKey(followerId)){
            //关注者不存在
            //1.1先要创建关注者
            createTwitUser(followerId);
            //使用无推文的创建方法创建，此时关注者是自己  并且没有发表推文
        }
        //2.再判断被关注者是否存在
        if (!userToTwit.containsKey(followeeId)){
            //关注者存在  被关注者不存在
            //2.1创建被关注者
            createTwitUser(followeeId);
            //关注该用户
        }
        //3.现在关注者与被关注者都存在  直接关注
        userToFollow.get(followerId).add(followeeId);
    }

    /** follower 取关 followee，如果 Id 不存在则什么都不做 */
    public void unfollow(int followerId, int followeeId) {
        //0.是否是自己取关自己
        if (followerId==followeeId){
            return;
        }
        //1.关注者不存在  无此人  直接返回
        if(!userToFollow.containsKey(followerId)){
            //关注者不存在  无此人  直接返回
            //System.out.println(followerId+"不存在");
            return;
        }
        //2.没有关注此用户 返回
        if (!userToFollow.get(followerId).contains(followeeId)){
            //没有关注此用户 返回
            //System.out.println(followeeId+"不存在");
            return;
        }
        //3.取关
        userToFollow.get(followerId).remove(followeeId);
        //2与3可以合并
    }
    /*
    方法三：使用优先队列合并
    思路

    这个方法和前两种方法的思路有所不同，
    我们需要维护当前每个链表没有被合并的元素的最前面一个，
    k个链表就最多有 k个满足这样条件的元素，
    每次在这些元素里面选取 val 属性最小的元素合并到答案中。
    在选取最小元素的时候，我们可以用优先队列来优化这个过程。
     */
    /*
       存放到小顶堆的时候  每次只需要存放第一个节点，
       当从小顶堆中获取第一个最小之后
       将该节点的后继节点加入到小顶堆进行比较
        */
    //O(kn×logk)
    public Twit mergeKLists(Twit[] lists){
        PriorityQueue<Twit> priorityQueue= new PriorityQueue<>(new Comparator<Twit>() {
            @Override
            public int compare(Twit o1, Twit o2) {
                return o2.timestamp-o1.timestamp;
            }
        });//小顶堆
        for (Twit listNode:lists){//将全部节点存放到小顶堆中？
            if (listNode!=null){//只需要将第一个节点加入到小顶堆中 因为最小值必定在第一位
                priorityQueue.add(listNode);
            }
        }
        Twit head = new Twit();
        Twit tail = head;
        int i=0;
        while(!priorityQueue.isEmpty()&&i++!=10){
            Twit min = priorityQueue.poll();
            //不要直接指向 min 会破会userToTwit中不同user的发布的Twit链表结构
            tail.next=new Twit(min.twId,min.timestamp);
            if (min.next!=null){//没取出一个最小值  则将此最小值链表的后继链表的第一个值（即最小）添加到小顶堆中取进行比较
                priorityQueue.add(min.next);
            }
            tail=tail.next;
        }
        return head.next;
    }
    //ArrayList与LinkedList
}
