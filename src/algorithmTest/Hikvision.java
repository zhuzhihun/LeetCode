package algorithmTest;


//1.写一个单例模式
class Singleton {
    private static Singleton singleton;
    public Singleton(){}
    private static Singleton getInstance(){
        if (singleton==null){
            synchronized (Singleton.class){
                if (singleton==null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
//2.sql语句 、
// 1. “A”课程分数高于“B”课程的学生sid
// 2. 教师名字中包含“王”的
// 3.
// 4. 平均分超过90的同学的id 与平均成绩


//3.三个Thread1,2,3对数字从9减到0  当为0时输出System.out.println("结束递减")
public class Hikvision {
    public static void main(String[] args) {
        new Thread(new MyThread(1)).start();
        new Thread(new MyThread(2)).start();
        new Thread(new MyThread(3)).start();

    }
}


class MyThread implements Runnable{
    //定义一个线程ID
    private int threadID;
    //需要进行叠加的数字
    private static int printNum = 9;
    //构造方法获取thread的ID
    public MyThread(int threadID){
        this.threadID = threadID;
    }
    @Override
    public void run() {
        while (printNum  >=0){
            synchronized (MyThread.class){
                System.out.println("当前的线程是:"+"---->"+threadID+"线程");
                int index = (printNum%3+1);//计算当前线程的数是和要对其操作的数来求得对应
                if(index == threadID){
                    System.out.println("线程"+threadID+":"+(printNum--));
                    if (printNum<0){
                        System.out.println("为0结束！");
                    }
                    MyThread.class.notifyAll();
                }else {
                    try {
                        MyThread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
