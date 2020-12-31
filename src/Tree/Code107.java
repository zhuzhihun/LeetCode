package Tree;

import java.util.*;

public class Code107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root){
        LinkedList<List<Integer>> lists = new LinkedList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int length = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if (treeNode.left!=null){
                    queue.offer(treeNode.left);
                }
                if (treeNode.right!=null){
                    queue.offer(treeNode.right);
                }
            }

            lists.add(0,list);//用于在列表的指定位置插入指定元素，并将当前处于该位置的元素及其后续元素的索引加 1。
        }

        return  lists;
    }
    public TreeNode sortedArrayToBST(int[] nums){
        return helper(nums,0,nums.length-1);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        if (left>right){
            return null;
        }
        int mid = (left+right)/2;
        // 总是选择中间位置左边的数字作为根节点
        // 总是选择中间位置右边的数字作为根节点
        //int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left=helper(nums,left,mid-1);
        root.right = helper(nums,mid+1,right);
        return root;
    }
    public TreeNode sortedListToBST(ListNode head){
        List<Integer> list = new ArrayList<>();
        int i=0;
        while (head!=null){
            //map.put(i,head.val);
            list.add(head.val);
            head = head.next;
            i++;
        }
        return helper(list,0,list.size()-1);
    }
    private TreeNode helper(List<Integer> list, int left, int right) {
        if (left>right){
            return null;
        }
        int mid = (left+right)/2;
        // 总是选择中间位置左边的数字作为根节点
        // 总是选择中间位置右边的数字作为根节点
        //int mid = (left + right + 1) / 2;
        //map.get(mid)
        int val = list.get(mid);
        TreeNode root = new TreeNode(val);
        root.left=helper(list,left,mid-1);
        root.right = helper(list,mid+1,right);
        return root;
    }

    private TreeNode helper(Map<Integer,Integer> map, int left, int right) {
        if (left>right){
            return null;
        }
        int mid = (left+right)/2;
        // 总是选择中间位置左边的数字作为根节点
        // 总是选择中间位置右边的数字作为根节点
        //int mid = (left + right + 1) / 2;
        //map.get(mid)
        int val = map.get(mid);
        TreeNode root = new TreeNode(val);
        root.left=helper(map,left,mid-1);
        root.right = helper(map,mid+1,right);
        return root;
    }
    public static void main(String[] args) {
        Code107 code107 = new Code107();
        ListNode listNode = new ListNode(6);
        listNode.next=new ListNode(3);
        listNode.next.next = new ListNode(9);
        code107.sortedListToBST(listNode);
























    }
}
