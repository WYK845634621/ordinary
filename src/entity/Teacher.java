package entity;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/26 10:55
 */
public class Teacher {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Teacher){
            Teacher t = (Teacher) obj;
            return t.getName().equals(this.name);
        }
        return false;
    }
}
