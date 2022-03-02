package QRCode;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import java.io.ByteArrayOutputStream;

public class QRCodeUtil {
    /**
     * 生成二维码
     * @param text 储存内容
     * @param width  宽度
     * @param height  高度
     * @return
     */
    public static String getQRCodeBase64(String text, int width, int height) {
        QrConfig config = new QrConfig(width, height);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        QrCodeUtil.generate(text, config, ImgUtil.IMAGE_TYPE_PNG, outputStream);
        byte[] qrData = outputStream.toByteArray();

        return "data:image/png;base64," + Base64.encode(qrData);
    }
}
