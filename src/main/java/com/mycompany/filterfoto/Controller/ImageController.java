package com.mycompany.filterfoto.Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author brene
 */
import com.mycompany.filterfoto.gui.ImageEditorFrame;

public class ImageController {
    private ImageEditorFrame frame;

    public ImageController() {
        frame = new ImageEditorFrame();
    }

    public void showApp() {
        frame.setVisible(true);
    }
}
