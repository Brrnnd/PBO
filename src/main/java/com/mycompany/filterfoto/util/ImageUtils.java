/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filterfoto.util;

/**
 *
 * @author brene
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;


public class ImageUtils {

    public static BufferedImage loadImage(File file) throws IOException {
        return ImageIO.read(file);
    }

    public static boolean saveImage(BufferedImage image, File file, String format) throws IOException {
        return ImageIO.write(image, format, file);
    }

    public static BufferedImage copyImage(BufferedImage src) {
        BufferedImage copy = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        Graphics2D g2d = copy.createGraphics();
        g2d.drawImage(src, 0, 0, null);
        g2d.dispose();
        return copy;
    }
}
