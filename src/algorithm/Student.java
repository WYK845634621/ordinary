package algorithm;

import java.util.Comparator;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2024/9/23 14:50
 */
public class Student {

    private int id;
    private int hight;
    private int weight;

    public Student(int id, int hight, int weight) {
        this.id = id;
        this.hight = hight;
        this.weight = weight;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
