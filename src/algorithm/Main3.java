package algorithm;


import java.util.*;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2024/9/21 13:48
 */
public class Main3 {


    public static int totalSize;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        totalSize = in.nextInt();
        int n = in.nextInt();
        in.nextLine();
        Map<String,FileSystem> map = new TreeMap<>();

        int currentSize = 0;
        for (int i = 0; i < n; i++) {
            String[] temp = in.nextLine().split(" ");
            String name = temp[1];
            if ("put".equals(temp[0])){
                int size = Integer.parseInt(temp[2]);
                if (map.containsKey(name)){
                    continue;
                }else {
                    //判断大小
                    currentSize = currentSize + size;
                    if (currentSize > totalSize){
                        List<FileSystem> list = new ArrayList<>(map.values());
                        list.sort(new Comparator<FileSystem>() {
                            @Override
                            public int compare(FileSystem o1, FileSystem o2) {
                                if (o1.getNum() == o2.getNum()){
                                    return (int) (o2.getDate() - o1.getDate());
                                }else {
                                    return o1.getNum() - o2.getNum();
                                }
                            }
                        });

                        int removeSize = currentSize - totalSize;
                        for (int j = 0; j < list.size(); j++) {
                            removeSize = removeSize - list.get(j).getSize();
                            map.remove(list.get(j).getName());
                            if (removeSize <= 0){
                                break;
                            }
                        }

                    }
                    FileSystem system = new FileSystem(name,size,0,new Date().getTime());
                    map.put(name,system);

                }

            }else if ("get".equals(temp[0])){
                if (map.containsKey(name)){
                    FileSystem system = map.get(name);
                    system.setDate(new Date().getTime());
                    system.setNum(system.num+1);
                    map.put(name,system);
                }
            }


        }

        if (map.size() > 0){
            map.keySet().forEach(e -> {
                System.out.print(e + ",");
            });
        }else {
            System.out.println("NONE");
        }



    }

    static class FileSystem{
        String name;
        int size;
        int num;
        long date;

        public FileSystem() {
        }

        public FileSystem(String name, int size, int num, long date) {
            this.name = name;
            this.size = size;
            this.num = num;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }
    }



}
