package Thread;

public class MyObject {
    synchronized public void methodA(){
        System.out.println("执行方法A");
    }
    synchronized public void methodB(){
        System.out.println("执行方法B");
    }
}
