package algorithmTest;

public class Sort {
    //冒泡排序
    public static int[] bubbleSort(int[] array) {
        int temp = 0;
        // 外层循环，它决定一共走几趟 //-1为了防止溢出
        for (int i = 0; i < array.length - 1; i++) {

            System.out.println("第"+(i+1)+"趟排序结果：");
            //forPrint(a);

            int flag = 0; //通过符号位可以减少无谓的比较，如果已经有序了，就退出循环
            //内层循环，它决定每趟走一次
            for (int j = 0; j < array.length - i - 1; j++) {
            //如果后一个大于前一个,则换位
                if (array[j + 1] > array[j]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = 1;
                }
            }
            forPrint(array);
            if(flag == 0){
                break;
            }
        }
        return array;
    }
    //输出打印函数
    public static void forPrint(int a[]){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        System.out.println("==============================");
        outer:
        for (int i = 0; i < 3; i++)
            inner:for (int j = 0; j < 2; j++) {
                if (j == 1)
                    continue outer;
                System.out.println(j + " and " + i);
            }
        System.out.println("==============================");
        //System.out.println(Math.random()/Math.random());

        int[] a ={2,5,1,6,4,9,8,5,3,1,2,0};
//        System.out.println("初始数组：");
//        forPrint(a);
//        selectSort(a);
    }
    public static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    //选择排序
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println("第"+(i+1)+"趟排序结果：");
            int min=i;
            for (int j = i+1; j <array.length ; j++) {
                if (array[j]<array[min])
                    min=j;
            }
            if (min!=i)
                swap(array,min,i);
            forPrint(array);
        }
    }
    //插入排序
    private static void insertSort(int... array){
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < array.length; i++) {

            // 记录要插入的数据
            int tmp = array[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                array[j] = tmp;
            }

        }

    }
    //快排
    private static void quickSort(int[] array,int start,int end){
        if (start<end){
            int key = array[start];//用来待排序的第一个作为中枢
            int i = start;
            for (int j = start+1; j <= end; j++) {
                if (key>array[j]){
                    swap(array,j,i++);
                }
            }
            array[start] = array[i];
            array[i]=key;
            quickSort(array,start,i-1);
            quickSort(array,i+1,end);
        }
    }
}
