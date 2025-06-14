/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filterfoto.app;

/**
 *
 * @author brene
 */
import com.mycompany.filterfoto.Controller.ImageController;
import javax.swing.*;

public class PhotoFilterApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageController controller = new ImageController();
            controller.showApp();
        });
    }
}
