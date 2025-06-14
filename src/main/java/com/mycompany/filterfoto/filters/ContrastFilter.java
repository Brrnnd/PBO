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

public class ContrastFilter implements Filter {
    private final int contrast;

    public ContrastFilter(int contrast) {
        this.contrast = contrast;
    }

    @Override
    public BufferedImage apply(BufferedImage image) {
        BufferedImage result = new BufferedImage(
            image.getWidth(), image.getHeight(), image.getType()
        );

        // Rumus kontras
        double factor = (259.0 * (contrast + 255)) / (255 * (259 - contrast));

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));

                int red = clamp((int)(factor * (color.getRed() - 128) + 128));
                int green = clamp((int)(factor * (color.getGreen() - 128) + 128));
                int blue = clamp((int)(factor * (color.getBlue() - 128) + 128));

                Color newColor = new Color(red, green, blue);
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }

    private int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}

