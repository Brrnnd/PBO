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

public class SharpenFilter implements Filter {
    public BufferedImage apply(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage result = new BufferedImage(w, h, img.getType());

        int[][] kernel = {
            { 0, -1,  0 },
            {-1,  5, -1 },
            { 0, -1,  0 }
        };

        for (int y = 1; y < h - 1; y++) {
            for (int x = 1; x < w - 1; x++) {
                int r = 0, g = 0, b = 0;
                for (int ky = 0; ky < 3; ky++) {
                    for (int kx = 0; kx < 3; kx++) {
                        int px = x + kx - 1;
                        int py = y + ky - 1;
                        Color c = new Color(img.getRGB(px, py));
                        int weight = kernel[ky][kx];
                        r += c.getRed() * weight;
                        g += c.getGreen() * weight;
                        b += c.getBlue() * weight;
                    }
                }
                r = Math.min(Math.max(r, 0), 255);
                g = Math.min(Math.max(g, 0), 255);
                b = Math.min(Math.max(b, 0), 255);
                result.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }

        return result;
    }
}


