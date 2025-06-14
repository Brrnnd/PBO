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

public class VignetteFilter implements Filter {
    public BufferedImage apply(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage result = new BufferedImage(w, h, img.getType());

        double cx = w / 2.0;
        double cy = h / 2.0;
        double maxDist = Math.sqrt(cx * cx + cy * cy);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color c = new Color(img.getRGB(x, y));
                double dx = x - cx;
                double dy = y - cy;
                double dist = Math.sqrt(dx * dx + dy * dy);
                double factor = 1.0 - (dist / maxDist);
                factor = Math.pow(factor, 1.5);

                int r = (int)(c.getRed() * factor);
                int g = (int)(c.getGreen() * factor);
                int b = (int)(c.getBlue() * factor);
                result.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }

        return result;
    }
}


