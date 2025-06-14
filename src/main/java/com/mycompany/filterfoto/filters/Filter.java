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

public interface Filter {
    BufferedImage apply(BufferedImage src);
}
