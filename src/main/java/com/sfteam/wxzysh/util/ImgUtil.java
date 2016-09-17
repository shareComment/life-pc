package com.sfteam.wxzysh.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @ClassName: ImgUtil.java
 * @Description: 图片处理
 * @author: knight
 * @date: 2016年7月15日 下午12:30:31
 * @company: sfteam
 */
public final class ImgUtil {

	/**
	 * 
	 * @Title: getAvatar
	 * @Description: 头像圆形处理
	 * @return: void
	 * @author: knight
	 * @date: 2016年7月28日 下午12:38:21
	 */
	public static void getAvatar(String inputPath, String outputPath) throws IOException{
		BufferedImage bi1 = ImageIO.read(new File(inputPath));
		 
        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
                BufferedImage.TYPE_INT_RGB);
 
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1
                .getHeight());
 
        Graphics2D g2 = bi2.createGraphics();
        g2.setBackground(Color.WHITE);
        g2.fill(new Rectangle(bi2.getWidth(), bi2.getHeight()));
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1, 0, 0, null);
        g2.dispose();
 
        try {
            ImageIO.write(bi2, "jpg", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
