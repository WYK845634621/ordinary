import org.junit.Test;

import java.util.*;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/11/20 19:12
 */
public class JDK8Test {


    /*
    lambda表达式的存在主要解决匿名内部类
     */
    @Test
    public void test1(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> set = new TreeSet<>(comparator);
        set.add(4);
        set.add(42);
        set.add(14);
        set.add(49);
        set.add(24);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){
        Comparator<Integer> comparator = (a1,a2) -> Integer.compare(a1,a2);
        TreeSet<Integer> set = new TreeSet<>(comparator);
        set.add(4);
        set.add(42);
        set.add(14);
        set.add(49);
        set.add(24);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
