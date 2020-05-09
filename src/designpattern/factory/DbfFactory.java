package designpattern.factory;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/24 17:35
 */
public class DbfFactory {
    private static String drtp = "DrtpReader";
    private static String offer = "OfferReader";
    private static String system = "SystemReader";

    public static DbfReader createReader(Class clazz){
        String name = clazz.getSimpleName();
        DbfReader reader = null;
        if (drtp.equals(name)){
            reader = new DrtpReader();
        }else if (offer.equals(name)){
            reader = new OfferReader();
        }else if (system.equals(name)){
            reader = new SystemReader();
        }
        return reader;
    }

    public static void main(String[] args) {
        DbfReader reader = DbfFactory.createReader(SystemReader.class);
        System.out.println(reader.read("hello"));
    }
}
