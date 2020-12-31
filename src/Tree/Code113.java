package Tree;

import java.util.*;

public class Code113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum){
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (root==null){
            return ans;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        dfs(root,sum,ans,queue);
        return ans;
    }
    public void dfs(TreeNode root, int sum, List<List<Integer>> ans, LinkedList<Integer> queue){
        if (root==null){
            return;
        }
        if (root.left==null&&root.right==null){
            if (sum==root.val){
                //说明此时的路径之和为sum
                //保存
                queue.add(root.val);
                ans.add(new ArrayList<>(queue));
                queue.remove();

            }
        }
        queue.add(root.val);
        if (root.left!=null){
            dfs(root.left, sum-root.val,ans,queue);
            queue.removeLast();
        }
        if (root.right!=null){
            dfs(root.right, sum-root.val,ans,queue);
            queue.removeLast();
        }

    }
    public List<List<Integer>> pathSum(TreeNode root, int sum, int count){
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){

        }
        return res;
    }

    public static void main(String[] args) {
        Vector vector = new Vector();

        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(1);
        TreeNode n7 = new TreeNode(4,n5,n6);
        TreeNode n8 = new TreeNode(13);
        TreeNode n9 = new TreeNode(8,n8,n7);
        TreeNode n3 = new TreeNode(7);
        TreeNode n4 = new TreeNode(2);
        TreeNode n2 = new TreeNode(11,n3,n4);
        TreeNode n1 = new TreeNode(4,n2,null);
        TreeNode n0 = new TreeNode(5,n1,n9);
        Code113 c = new Code113();
//        List<List<Integer>> lists = c.pathSum(n0,22);
//        System.out.println(lists.toString());
        c.flattenq(n0);
        while (n0!=null){
            System.out.println(n0.val);
            n0=n0.right;
        }
    }
    //114
    public void flatten(TreeNode root){
        if (root==null){
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        flattens(root,list);
        for (int i = 0; i < list.size()-1; i++) {
            TreeNode curr=list.get(i);
            TreeNode next=list.get(i+1);
            curr.left=null;
            curr.right=next;
        }

    }
    public void flattens(TreeNode root, List<TreeNode> list){
        if (root==null){
            return;
        }
        //ListNode list = new ListNode(root.val);
        list.add(root);
        flattens(root.left,list);
        flattens(root.right,list);
    }
    public void unflatten(TreeNode root){
        //将右子树移接到当前节点左子树的最右叶子节点
        TreeNode curr = root;

        while (curr!=null){
            if (curr.left!=null){
                TreeNode next = curr.left;
                TreeNode pre = next;
                while (pre.right!=null){
                    pre=pre.right;
                }
                pre.right=curr.right;
                curr.right=next;
                curr.left=null;
            }
            curr=curr.right;

        }

    }
    public void flattenq(TreeNode root) {
        if(root==null){
            return;
        }
        flattenq(root.left);
        flattenq(root.right);
        TreeNode node = root.right;
        root.right=root.left;
        root.left = null;
        while(root.right != null){
            root = root.right;
        }
        root.right = node;
    }
}
