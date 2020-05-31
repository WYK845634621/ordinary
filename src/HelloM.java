import entity.Teacher;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/24 17:42
 */
public class HelloM {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        System.out.println(teacher.getClass().getClassLoader());
        System.out.println(teacher.getClass().getClassLoader().getParent());
        System.out.println(teacher.getClass().getClassLoader().getParent().getParent());
        System.out.println(teacher.getClass().getName());
    }
}
