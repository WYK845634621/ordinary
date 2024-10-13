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

        TreeNode node = buildTree(houxu,zhongxu);

        List<Character> list = sort(node);

        list.forEach(System.out::print);

    }

    private static List<Character> sort(TreeNode node) {
        List<Character> list = new ArrayList<>();
        if (node == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()){

            TreeNode poll = queue.poll();
            list.add(poll.v);
            if (poll.left != null){
                queue.offer(poll.left);
            }
            if (poll.right != null){
                queue.offer(poll.right);
            }

        }
        return list;


    }

    private static TreeNode buildTree(String houxu, String zhongxu) {
        if (houxu.isEmpty() || zhongxu.isEmpty()){
            return null;
        }

        char root = houxu.charAt(houxu.length() - 1);
        int rootIndex = zhongxu.indexOf(root);

        TreeNode node = new TreeNode(root);


        String leftString = zhongxu.substring(0, rootIndex);
        String rightString = zhongxu.substring(rootIndex + 1);

        int length = leftString.length();

        String leftHouxu = houxu.substring(0, length);
        String rightHouxu = houxu.substring(length,houxu.length() - 1);

        node.left = buildTree(leftHouxu,leftString);
        node.right = buildTree(rightHouxu,rightString);

        return node;

    }


}
