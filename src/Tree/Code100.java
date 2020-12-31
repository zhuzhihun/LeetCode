package Tree;

public class Code100 {
    public boolean isSameTree(TreeNode p, TreeNode q){
        if ((p==null&&q!=null)||(p!=null&&q==null)){
            return false;
        }
        if (p==null&&q==null){
            return true;
        }
        boolean le =isSameTree(p.left,q.left);
        if (p.val!=q.val){
            return false;
        }
        boolean ri = isSameTree(p.right,q.right);

        return ri&&le;
    }

    public static void main(String[] args) {

    }
}
