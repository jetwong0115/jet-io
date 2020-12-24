package org.jet.io.common.utils.common;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class QRCodeUtil {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 生成格式为JPG的二维码工具
     *
     * @param content 二维码内容
     * @param width   二维码宽
     * @param height  二维码高
     * @param path    二维码存放路径
     * @throws WriterException
     * @throws IOException
     */
    public void generateQRCodeFile(String content, int width, int height, Path path) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        if (Objects.nonNull(bitMatrix)) {
            MatrixToImageWriter.writeToPath(bitMatrix, "jpg", path);
        } else {
            log.debug("bitMatrix = NULL");
        }
    }
}
