package top.stuck.link.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 输出工具类
 * Created on 2020-04-29
 *
 * @author Octopus
 */
public class IOUtil {

    private static Logger logger = LoggerFactory.getLogger(IOUtil.class);

    public static void outputImage(HttpServletResponse response, BufferedImage image) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            logger.error("获取response输出流失败");
            return;
        }
        try {
            ImageIO.write(image, "png", out);
            out.flush();
        } catch (IOException e) {
            logger.error("输出图片失败");
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                logger.error("关闭response输出流失败");
            }
        }
    }
}
