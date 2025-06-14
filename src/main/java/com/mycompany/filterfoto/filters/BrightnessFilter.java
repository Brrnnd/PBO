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

public class BrightnessFilter implements Filter {
    private int level;

    public BrightnessFilter(int level) {
        this.level = level;
    }

    public BufferedImage apply(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color c = new Color(img.getRGB(x, y));
                int r = Math.min(Math.max(c.getRed() + level, 0), 255);
                int g = Math.min(Math.max(c.getGreen() + level, 0), 255);
                int b = Math.min(Math.max(c.getBlue() + level, 0), 255);
                result.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }
        return result;
    }
}


