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

public class BlurFilter implements Filter {
    public BufferedImage apply(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage result = new BufferedImage(w, h, img.getType());

        int[][] kernel = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        int kernelSize = 3;
        int kernelSum = 9;

        for (int y = 1; y < h - 1; y++) {
            for (int x = 1; x < w - 1; x++) {
                int r = 0, g = 0, b = 0;
                for (int ky = 0; ky < kernelSize; ky++) {
                    for (int kx = 0; kx < kernelSize; kx++) {
                        int px = x + kx - 1;
                        int py = y + ky - 1;
                        Color c = new Color(img.getRGB(px, py));
                        r += c.getRed();
                        g += c.getGreen();
                        b += c.getBlue();
                    }
                }
                result.setRGB(x, y, new Color(r / kernelSum, g / kernelSum, b / kernelSum).getRGB());
            }
        }

        return result;
    }
}


