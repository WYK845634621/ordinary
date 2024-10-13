package algorithm;


import java.util.*;

public class Main {

    static class MyNode{
        char v;
        MyNode left;
        MyNode right;

        public MyNode() {
        }
    }

    static class SingleNode{
        String name;
        SingleNode parent;
        List<SingleNode> chidlen;

        public SingleNode(String name) {
            this.name = name;
            chidlen = new ArrayList<>();
        }

        public void addChildren(SingleNode node){
            chidlen.add(node);
            node.parent=this;
        }

        public String getName() {
            return name;
        }

        public SingleNode getParent() {
            return parent;
        }

        public List<SingleNode> getChidlen() {
            return chidlen;
        }
    }

    static class LocaDir{
        static SingleNode root;

        public LocaDir() {
            this.root = new SingleNode("/");
        }

        public static void mkdir(String path){
            boolean flag = false;
            for (SingleNode child : root.getChidlen()){
                if (child.getName().equals(path)){
                    break;
                }
                flag = true;
            }
            if (flag){
                SingleNode newNode = new SingleNode(path);
                root.addChildren(newNode);
            }
        }
        public static void cd(String path){


        }
        public static String pwd(String path){

            return null;
        }




    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }



}


