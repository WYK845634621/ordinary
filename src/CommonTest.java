import org.junit.Test;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/25 14:09
 */
public class CommonTest {
    @Test
    public void test() {

        //这两个词的hash值相等
        String s1 = "通话";
        String s2 = "重地";
        System.out.println(s1.hashCode() + "///" + s2.hashCode());

    }
}
