package algorithm;


import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2024/9/21 13:48
 */
public class Main2 {

    static class TreeNode{
        char v;
        TreeNode left;
        TreeNode right;

        public TreeNode(char v) {
            this.v = v;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] s = in.nextLine().split(" ");
        String houxu = s[0];
        String zhongxu = s[1];

        TreeNode node = buildTree(houxu, zhongxu);
        List<Character> list = sort(node);
        System.out.println();


    }

    private static TreeNode buildTree(String postorder, String inorder) {
        if (postorder.isEmpty() || inorder.isEmpty()) {
            return null;
        }

        char rootVal = postorder.charAt(postorder.length() - 1);
        TreeNode root = new TreeNode(rootVal);

        int rootIndexInInorder = inorder.indexOf(rootVal);

        String leftInorder = inorder.substring(0, rootIndexInInorder);
        String rightInorder = inorder.substring(rootIndexInInorder + 1);

        int leftSize = leftInorder.length();
        String leftPostorder = postorder.substring(0, leftSize);
        String rightPostorder = postorder.substring(leftSize, postorder.length() - 1);

        root.left = buildTree(leftPostorder, leftInorder);
        root.right = buildTree(rightPostorder, rightInorder);

        return root;
    }

    public static List<Character> sort(TreeNode node){
        List<Character> result = new ArrayList<>();
        if (node == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();

            result.add(poll.v);

            if (poll.left != null){
                queue.offer(poll.left);
            }
            if (poll.right != null){
                queue.offer(poll.right);
            }
        }

        return result;


    }

}
