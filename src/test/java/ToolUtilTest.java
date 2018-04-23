import com.kedacom.demo.common.utils.Base64OperateUtil;
import common.BaseJunit4;
import org.junit.Test;

/**
 * 工具类方法测试
 */
public class ToolUtilTest extends BaseJunit4 {
    /**
     * 加密方法测试
     */
    @Test
    public void testEncode() {
        String str = Base64OperateUtil.jdkBase64Encoder("admin");
        System.out.println(str);
    }

    /**
     * 解密方法测试
     */
    @Test
    public void testDecode() {
        String str = Base64OperateUtil.jdkBase64Decoder(Base64OperateUtil.jdkBase64Encoder("admin"));
        System.out.println(str);
    }
}
