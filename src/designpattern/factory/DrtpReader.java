package designpattern.factory;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/24 17:32
 */
public class DrtpReader implements DbfReader {
    @Override
    public String read(String text) {
        return "DrtpReader";
    }
}
