import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/3 15:02
 */
public class CommonUtil {
    public static void release(FileInputStream inputStream, FileOutputStream outputStream,
                               FileChannel inchannel,FileChannel outchannel){
        if (inchannel != null){
            try {
                inchannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (outchannel != null){
            try {
                outchannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (inputStream != null){
            try {
                inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (outputStream != null){
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
