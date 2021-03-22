package array;

public class Code6 {
    public static String convert(String s,int numRows){
        if (numRows == 1){
            return s;
        }
        int len = Math.min(s.length(),numRows);
        String []rows = new String[len];
        for(int i = 0; i< len; i++) rows[i] = "";
        int loc =0;
        boolean flag = false;
        for (int i = 0; i <s.length() ; i++) {
            rows[loc]=rows[loc]+s.substring(i,i+1);

            //System.out.print(rows[loc].toString());
            if (loc == 0 || loc == numRows-1){
                flag=!flag;
            }
            if (flag==true){
                loc=loc+1;
            }else {
                loc=loc-1;
//                switch (loc){
//
//                    case 1:rows[0]+=" ";rows[2]+=" ";rows[3]+=" ";break;
//                    case 2:rows[0]+=" ";rows[1]+=" ";rows[3]+=" ";;break;
//                    case 3:rows[0]+=" ";rows[1]+=" ";rows[2]+=" ";;break;
//                }


            }
        }
        String as = "";
        for (int i = 0; i <len ; i++) {
            System.out.print(rows[i].toString());
            System.out.println();
            as+=rows[i];
        }

        return as;
    }

    public static void main(String[] args) {
        String s="PAYPALISHIRING";
        s=convert(s,3);
        System.out.print(s.toString());
    }
}
