import entity.Teacher;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Description 守护线程是运行在后台的一种特殊线程,它独立于控制终端并且周期性的执行某种任务或等待处理某些发生的事件,
 * 垃圾回收线程就是特殊的守护线程
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/25 14:09
 */
public class CommonTest {

    public static void main(String[] args) {
        System.out.println("hello");
    }

    @Test
    public void test() {

        //
        // ==: 基本数据类型比较的是指是否相等  对于引用数据类型来说,比较的是引用是否相等
        //这两个词的hash值相等
        String s1 = "通话";
        String s2 = "重地";
        System.out.println(s1.hashCode() + "///" + s2.hashCode());

        Map<String,String> map = new HashMap<>();
        map.put(s1,"hello");
        map.put(s2,"yeah");
        System.out.println(map.get(s1));
        System.out.println(map.get(s2));
    }


    @Test
    public void test1(){
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("four");
        ListIterator<String> iterator = list.listIterator(1);
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<>();
        list.add("hello");
        List<String> l = Collections.unmodifiableList(list);
        l.add("h");
    }


    /**
     * throw是确定的抛出一个异常
     * Throws的声明可能会抛出一个异常
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException {
        try {
            System.out.println("try");
            throw new Exception();
        }catch (Exception e){
            System.out.println("execption");
        }
        finally {
            System.out.println("finally");
        }
    }


    @Test
    public void test5(){
        Teacher t = new Teacher();
        t.setName("hello");
        Teacher t2 = new Teacher();
        t2.setName("hello");
        System.out.println(t.equals(t2));
    }

    @Test
    public void test6(){
        try {
            Files.createDirectories(Paths.get("C:\\temp\\io\\i\\o"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7(){
        List<String> list = new ArrayList<>();
        list.add("hello");
        List<String> l = Collections.unmodifiableList(list);
        l.add("test");
    }

    @Test
    public void test8(){
        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(new Date().toString());
            System.out.println(date);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test9(){
        DecimalFormat df = new DecimalFormat("0.1");
        String s = df.format((float)32/17);
        System.out.println();
        Double d = Double.valueOf("3")/Double.valueOf("24");
    }


    /**
     * 在使用Arrays.asList()转化时,底层还是数组
     */
    @Test
    public void test10(){
        String[] sts = {"hello","a","op"};
        List<String> list = Arrays.asList(sts);
        System.out.println(list);
        sts[2] = "ddl";
        System.out.println(list);
        list.add("ks");
    }

    @Test
    public void test11(){
        ArrayList<Integer> list = new ArrayList<>();
        final int n = 10000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
    }

    @Test
    public void test12(){
        ArrayList<Integer> list = new ArrayList<>();
        final int n = 10000000;
        long start = System.currentTimeMillis();
        list.ensureCapacity(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
    }

    //排序
    @Test
    public void test13(){
        List<Double> list = new ArrayList<>();
        list.add(3.23);
        list.add(-3.23);
        list.add(4.23);
        list.add(1.23);
        Collections.sort(list, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return -o1.compareTo(o2);
            }
        });
        System.out.println(list);
    }


    //foreach方法
    @Test
    public void test14(){
        List<Double> list = new ArrayList<>();
        list.add(3.23);
        list.add(-3.23);
        list.add(4.23);
        list.add(1.23);
        list.forEach(d -> System.out.print(d + ","));
        list.forEach(System.out::println);
    }

    @Test
    public void test15(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(2,4);
        map.put(1,5);
        map.put(9,6);
        map.put(7,0);
        map.forEach((k,v) -> System.out.println(k + v));
    }

    @Test
    public void test16(){
        HashMap<String,String> map = new HashMap<>(4);
        map.put("hello","hello");
        System.out.println(map.size());
    }

    @Test
    public void test17(){
        //参数字符串，如果拼接在请求链接之后，需要对中文进行 URLEncode   字符集 UTF-8
        String param = "key=805639d6262d474e95945191cb63b49e&location=CN101100809";
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            //接口地址
            String url = "https://free-api.heweather.net/s6/weather/now?";
            URL uri = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("accept", "*/*");
            //发送参数
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.flush();
            //接收结果
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            //缓冲逐行读取
            while ( ( line = br.readLine() ) != null ) {
                sb.append(line);
            }
            System.out.println(sb.toString());
        } catch ( Exception ignored ) {
        } finally {
            //关闭流
            try {
                if(is!=null){
                    is.close();
                }
                if(br!=null){
                    br.close();
                }
                if (out!=null){
                    out.close();
                }
            } catch ( Exception ignored ) {}
        }
    }


    @Test
    public void tttt(){
        ArrayList<Integer> a = new ArrayList<>(12);
        System.out.println(a.size());
        a.set(4,88);
    }

    @Test
    public void tsts(){
        HashMap<String,String> map = new HashMap<>();
        map.put(null,null);
        System.out.println(map.get(null));
        map.put(null,null);
        System.out.println(map.size());
    }

    @Test
    public void test21(){
        Long value_amount = 9L;
        System.out.println((int)Math.floor(value_amount));
    }



    @Test
    public void test22(){
        String result = "东征#100#145_工作#51#171_审议#147#172_#31#173_通达#25#192";
        String[] splits = result.split("_");

        StringBuffer value_amount = new StringBuffer();
        StringBuffer value_name = new StringBuffer();

        //预存数据
        for (int i = 0; i < splits.length; i++) {
            String single = splits[i];
            String[] vars = single.split("#");
            String name = "";
            if (Objects.isNull(vars[0]) || vars[0].length() < 2){
                name = vars[2];
            }else {
                name = "null".equals(vars[0]) ? vars[2] : vars[0];
            }
            value_name.append(name + ",");
            value_amount.append(vars[2] + ",");
        }

        //去掉最后一个逗号
        value_amount.deleteCharAt(value_amount.length()-1);
        value_name.deleteCharAt(value_name.length()-1);
        System.out.println(value_amount.toString());
        System.out.println(value_name.toString());
    }


    @Test
    public void test23(){
        double a = 1.0;
        double b = 0.9;
        double c = 0.8;
        double d = 0.7;
        double e = 0.6;
        double f = 0.5;
        double g = 0.4;
        double h = 0.3;
        double i = 0.2;
        System.out.println((int) (a-b));
        System.out.println((int)(b-c));
        System.out.println((int)(c-d));
        System.out.println(d-e);
        System.out.println(e-f);
        System.out.println(f-g);
        System.out.println(g-h);
        System.out.println(h-i);
    }


    @Test
    public void test24(){
        BigDecimal a = new BigDecimal("0.9");
        BigDecimal b = new BigDecimal("0.8");
        System.out.println(a.subtract(b));
        System.out.println(a.compareTo(b));
    }

    @Test
    public void test25(){
        new ThreadPoolExecutor(5,
                8,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Test
    public void test26(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 8, 5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println(threadPoolExecutor.getActiveCount());

    }


    @Test
    public void test27(){
        String str = "<p>这是文本<br/></p>";
        int i = str.indexOf("<");
        System.out.println(i);
        if (-1 != i){
            str = str.substring(3);
            int j = str.indexOf("<");
            str = str.substring(0,j);
        }
        System.out.println(str);
    }

    @Test
    public void test28() throws ParseException {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy.MM.dd");
        now.add(Calendar.DATE,-4);
        String deleteDay = sdf.format(now.getTime());
        System.out.println(deleteDay);
        now.setTime(sdf.parse(deleteDay));

        String index = "ksmonitor-rsyslog-10.254.253.21-2020.06.08";
        Calendar temp = Calendar.getInstance();
        String i = index.substring(index.lastIndexOf("-") + 1);
        System.out.println(i);
        temp.setTime(sdf.parse(i));
        System.out.println(temp.before(now));
    }


    /**
     * list转数组的合理方式
     */
    @Test
    public void test29(){
        List<String> needToDelete = new ArrayList<>();
        needToDelete.add("hello");
        needToDelete.add("hi");
        String[] del = new String[needToDelete.size()];
        needToDelete.toArray(del);
        for (String s : del){
            System.out.println(s);
        }
    }


    @Test
    public void test30(){
        List<Integer> list = new ArrayList<>();
        int size = 212;
        for (int i = 0; i < 212; i++) {
            list.add(i);
        }
        List<Integer> s = list.subList(210, size);
        for (int i = 0; i <s.size(); i++) {
            System.out.println(s.get(i));
        }
    }

    @Test
    public void test31() throws ParseException {
        Calendar temp = Calendar.getInstance();
        SimpleDateFormat sdf2  = new SimpleDateFormat("yyyyMMdd");
        temp.setTime(sdf2.parse("20191213"));
        Calendar bound = Calendar.getInstance();
        bound.add(Calendar.DATE,-70);
        System.out.println(temp.before(bound));
    }


    @Test
    public void test32(){
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        list.add("hello");

        new CopyOnWriteArrayList<String>();
    }


}
