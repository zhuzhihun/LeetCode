package Tree;

public class Code101 {
    public boolean isSymmetric(TreeNode root){
        if (root==null){
            return true;
        }
        //helper(root.left,root.right);
        return helper(root.left,root.right);
    }
    public boolean helper(TreeNode p , TreeNode q){
        if (p==null&&q==null){
            return true;
        }
        else if (p==null||q==null){
            return false;
        }else if (p.val!=q.val){
            return false;
        }else {
            return helper(p.left,q.right)&&helper(p.right,q.left);
        }

        //return true;
    }

    public static void main(String[] args) {
        Code101 c =new Code101();

    }
}
