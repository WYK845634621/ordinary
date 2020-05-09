package designpattern.factory;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/24 17:33
 */
public class OfferReader implements DbfReader {
    @Override
    public String read(String text) {
        return "OfferReader";
    }
}
