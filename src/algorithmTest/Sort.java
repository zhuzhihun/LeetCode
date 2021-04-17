package algorithmTest;

public class Sort {
    //冒泡排序
    public static void bubbleSort(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[j]<array[i])
                    swap(array,i,j);
            }
        }
    }
    public static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    //选择排序
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            int min=i;
            for (int j = i+1; j <array.length ; j++) {
                if (array[j]<array[min])
                    min=j;
            }
            if (min!=i)
                swap(array,min,i);
        }
    }
    //插入排序
    private static void insertSort(int[] array){
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
