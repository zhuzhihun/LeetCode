package Tree;

import java.util.LinkedList;
//二叉树序列化与反序列化
public class Code297 {//二叉树的序列化与反序列化
    public String serialize(TreeNode root){
        //将树变为字符串
        StringBuilder sb = new StringBuilder();
        serialize(root,sb,1);
        // 得到的string如下：
        // "1,2,#,4,#,#,3,#,#,"
        // 需要去掉最后面的逗号。
        //sb.delete(sb.length()-2,sb.length()-1);
        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }
    public TreeNode deserialize(String data){
        //将字符变为二叉树
        LinkedList<String> linkedList = new LinkedList<>();
        for (String s:data.split(",")){
            linkedList.add(s);
        }
        return deserialize(linkedList,1);

    }
    //前序遍历构造
    public void serialize(TreeNode root, StringBuilder sb){//前序遍历构造sb缓存
        if (root==null){
            //此时 树的节点为空
            sb.append("#");//使用#代替 空值 null
            sb.append(",");
            return;
        }
        sb.append(root.val).append(",");//
        serialize(root.left,sb);
        serialize(root.right,sb);
    }

    public TreeNode deserialize(LinkedList<String> node){
        if (node.isEmpty()) return null;
        /********前序遍历*******/
        //列表最左侧就是根节点
        String first = node.remove();
        if (first.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left=deserialize(node);
        root.right=deserialize(node);
        return root;

    }
    //后序遍历构造
    public void serialize(TreeNode root, StringBuilder sb, int i){
        if (root ==null){
            sb.append("#").append(",");
            return;
        }
        serialize(root.left,sb,i);
        serialize(root.right,sb,i);
        sb.append(root.val).append(",");

    }

    public TreeNode deserialize(LinkedList<String> node, int i){
        if (node.isEmpty()){
            return null;
        }
        String last = node.removeLast();
        if (last.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(last));
        root.right=deserialize(node, i);
        root.left=deserialize(node, i) ;
        return root;
    }
    //层次遍历构造
    public String serializecc(TreeNode root){
        StringBuilder sb =new StringBuilder();
        if (root==null){
            return sb.toString();
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()){

            TreeNode node = linkedList.remove();
            //判断 node == null 时
            if (node==null) {
                sb.append("#").append(",");
                continue;
            }
            sb.append(node.val).append(",");

            if (root.left!=null){
                linkedList.add(root.left);
            }
            if (root.right!=null){
                linkedList.add(root.right);
            }
        }
        return sb.toString();
    }
    public TreeNode deserializecc(String sb){
        if (sb.isEmpty()){
            return null;
        }
//        LinkedList<String> linkedList = new LinkedList<>();
//        for (String s:sb.split(",")){
//            linkedList.add(s);
//        }
        String[] nodes = sb.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        for (int i = 1; i < nodes.length;) {
            //列表中存在的都是 父节点；
            //取出父节点
            TreeNode pare = linkedList.remove();
            // 父节点对应的左侧子节点的值
            String left = nodes[i++];
            if (!left.equals("#")){
                pare.left=new TreeNode(Integer.parseInt(left));
                linkedList.add(pare.left);
            }else{
                pare.left=null;
            }
            // 父节点对应的右侧子节点的值
            String right =nodes[i++];
            if (!right.equals("#")){
                pare.right = new TreeNode(Integer.parseInt(right));
                linkedList.add(pare.right);
            }
            else {
                root.right=null;
            }

        }
        return root;
    }



    public static void main(String[] args) {
        Code297 c =new Code297();
        TreeNode root1= new TreeNode(1);
        TreeNode root2= new TreeNode(2);
        TreeNode root3= new TreeNode(3);
        root1.left=root2;root1.right=root3;
        TreeNode root4= new TreeNode(4);
        TreeNode root5= new TreeNode(5);
        root3.left=root4;root3.right=root5;
        String s = c.serializecc(root1);
        TreeNode root = c.deserializecc(s);

    }
}
