/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filterfoto.filters;

/**
 *
 * @author brene
 */
import java.awt.image.BufferedImage;
import java.awt.Color;

public class SepiaFilter implements Filter {
    public BufferedImage apply(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color c = new Color(img.getRGB(x, y));
                int r = (int)(0.393 * c.getRed() + 0.769 * c.getGreen() + 0.189 * c.getBlue());
                int g = (int)(0.349 * c.getRed() + 0.686 * c.getGreen() + 0.168 * c.getBlue());
                int b = (int)(0.272 * c.getRed() + 0.534 * c.getGreen() + 0.131 * c.getBlue());
                r = Math.min(255, r); g = Math.min(255, g); b = Math.min(255, b);
                result.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }
        return result;
    }
}
