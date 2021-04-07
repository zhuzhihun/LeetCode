import java.util.HashMap;

public  class GetClassTest {
    static int cnt = 6;
    static{
        cnt += 9;
    }
    private static final String MESSAGE="taobao";


    public final static native int w();

    public  static void main(String[] args){
        //取模运算，余数的符号跟被除数符号相同
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(null,1);
        hashMap.put(1,null);


        System.out.println((-3)%2);
        System.out.println(4%3);
        System.out.println((-3)%(-2));
        System.out.println(4%(-3));
        int score[] = new int[10];
        double floor = Math.floor(-8.5);
        int ints[]={};
        System.out.println("====================");

        GetClassTest test = new GetClassTest();
        //第一种方式获取Class对象
        GetClassTest stu1 = new GetClassTest();//这一new 产生一个Student对象，一个Class对象。

        Class stuClass = stu1.getClass();//获取Class对象
        Class cla = test.getClass();
        System.out.println(cla.getName());
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = GetClassTest.class;
        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        try {
            Class stuClass3 = Class.forName("GetClassTest");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}