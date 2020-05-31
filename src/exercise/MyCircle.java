package exercise;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/12 19:02
 */
public class MyCircle extends JFrame {

    //仓库数量
    static int repositoryNum;

    Color color;

    public MyCircle()
    {
        super("一个有5个不同颜色的同心圆"); //显示窗口名称
        setSize(1200,1200);      //设置窗口大小
        setVisible(true);      //设置为可见
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口关闭动作

    }

    public void paint(Graphics g)
    {
        for (int i = 0; i < repositoryNum; i++) {
            //当量
            dl = booms.get(i) * quality.get(i) * 2;
            //第一个圆  蓝色
            color = new Color(0,0,255);
            g.setColor(color);
            g.fillOval(x.get(i), dl, 250, 250);
            //第二个圆  黄色
            color = new Color(255,255,0);
            g.setColor(color);
            g.fillOval(x.get(i)+25, dl +25, 200, 200);
            //第三个圆  红色
            color = new Color(255,0,0);
            g.setColor(color);
            g.fillOval(x.get(i)+50, dl +50, 150, 150);
        }


    }

    static List<Integer> booms = new ArrayList<>();
    //X轴
    static List<Integer> x = new ArrayList<>();
    static Map<String,Integer> kinds = new HashMap<>();
    static {
        kinds.put("太安",1);
        kinds.put("奥克托金",2);
        kinds.put("黑索今",3);
        kinds.put("梯恩梯",4);
        kinds.put("黑火药",5);
        kinds.put("中能复合推进剂",6);
        kinds.put("n",7);

    }
    //质量
    static List<Integer> quality = new ArrayList<>();
    static int dl = 0;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入仓库个数: "); //输入整数
        repositoryNum = scanner.nextInt();
        System.out.println("仓库个数: " + repositoryNum);

        for (int i = 0; i < repositoryNum; i++) {
            System.out.println("输入爆炸物种类: ");    //上面的kinds文字选一个
            booms.add(kinds.get(scanner.next()));
            System.out.println("输入仓库坐标: ");      //跨度写大一点,不然看不出效果  比方第一次10,第二次400
            x.add(scanner.nextInt());
            System.out.println("输入质量: ");           //整数
            quality.add(scanner.nextInt());
        }
        MyCircle e = new MyCircle();
    }

}
