/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.filterfoto.gui;

/**
 *
 * @author brene
 */
import com.mycompany.filterfoto.filters.*;
import com.mycompany.filterfoto.util.ImageUtils;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
//import javax.imageio.ImageIO;

public class ImageEditorFrame extends javax.swing.JFrame {

    private BufferedImage originalImage;
    private BufferedImage filteredImage;
    private JLabel originalLabel;
    private JLabel filteredLabel;
    
    public ImageEditorFrame() {
        setTitle("Photo Filter App");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        originalLabel = new JLabel("", JLabel.CENTER);
        filteredLabel = new JLabel("", JLabel.CENTER);

        JPanel imagePanel = new JPanel(new GridLayout(1, 2));
        imagePanel.add(new JScrollPane(originalLabel));
        imagePanel.add(new JScrollPane(filteredLabel));
        add(imagePanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open Image");
        JMenuItem saveItem = new JMenuItem("Save Filtered Image");
        JMenuItem exitItem = new JMenuItem("Exit");

        openItem.addActionListener(e -> openImage());
        saveItem.addActionListener(e -> saveImage());
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Filter menu
        JMenu filterMenu = new JMenu("Filters");
        filterMenu.add(createFilterItem("Grayscale", new GrayscaleFilter()));
        filterMenu.add(createFilterItem("Invert", new InvertFilter()));
        filterMenu.add(createFilterItem("Sepia", new SepiaFilter()));
        filterMenu.add(createFilterItem("Blur", new BlurFilter()));
        filterMenu.add(createFilterItem("Sharpen", new SharpenFilter()));
//        filterMenu.add(createFilterItem("Brightness", new BrightnessFilter(50)));
        filterMenu.add(createFilterItem("Vignette", new VignetteFilter()));
        JMenuItem brightnessItem = new JMenuItem("Brightness");
            brightnessItem.addActionListener(e -> {
                if (originalImage == null) {
                    JOptionPane.showMessageDialog(this, "Silakan buka gambar terlebih dahulu.");
                    return;
                }

                String input = JOptionPane.showInputDialog(this, "Masukkan tingkat brightness (-255 hingga 255):", "50");
                if (input != null) {
                    try {
                        int brightnessValue = Integer.parseInt(input);
                        if (brightnessValue < -255 || brightnessValue > 255) {
                            JOptionPane.showMessageDialog(this, "Nilai harus antara -255 hingga 255.");
                            return;
                        }
                        Filter brightnessFilter = new BrightnessFilter(brightnessValue);
                        applyFilter(brightnessFilter);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Input tidak valid. Harus berupa angka.");
                    }
                }
            });
            filterMenu.add(brightnessItem);
            
        JMenuItem contrastItem = new JMenuItem("Contrast");
            contrastItem.addActionListener(e -> {
                String input = JOptionPane.showInputDialog(this, "Masukkan nilai kontras (-100 sampai 100):", "0");
                if (input != null) {
                    try {
                        int value = Integer.parseInt(input);
                        if (value < -100 || value > 100) {
                            JOptionPane.showMessageDialog(this, "Nilai harus antara -100 sampai 100.");
                            return;
                        }
                        applyFilter(new ContrastFilter(value));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Input tidak valid.");
                    }
                }
            });
            filterMenu.add(contrastItem);


        
        // Reset menu
        JMenu actionMenu = new JMenu("Reset");
        JMenuItem resetItem = new JMenuItem("Reset Filtered Image");
        resetItem.addActionListener(e -> resetImage());
        actionMenu.add(resetItem);

        menuBar.add(fileMenu);
        menuBar.add(filterMenu);
        menuBar.add(actionMenu);

        setJMenuBar(menuBar);

    }
    
    private JMenuItem createFilterItem(String name, Filter filter) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(e -> applyFilter(filter));
        return item;
    }

    private void openImage() {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                originalImage = ImageUtils.loadImage(file);
                filteredImage = ImageUtils.copyImage(originalImage);
                originalLabel.setIcon(new ImageIcon(originalImage));
                filteredLabel.setIcon(new ImageIcon(filteredImage));
            } catch (Exception ex) {
                ex.printStackTrace(); // untuk melihat error di console
               JOptionPane.showMessageDialog(this, "Error opening image:\n" + ex.getMessage());            }
        }
    }
    
    private void saveImage() {
        if (filteredImage == null) {
            JOptionPane.showMessageDialog(this, "Tidak ada gambar hasil filter yang bisa disimpan.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Simpan Gambar");

        // Tambahkan filter PNG dan JPG
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Image", "png"));
        chooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPG Image", "jpg", "jpeg"));

        int option = chooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String extension = "png"; // default
            String filePath = file.getAbsolutePath();

            // Tentukan ekstensi dari filter yang dipilih
            String selectedExtension = ((javax.swing.filechooser.FileNameExtensionFilter) chooser.getFileFilter()).getExtensions()[0];
            extension = selectedExtension.toLowerCase();

            if (!filePath.toLowerCase().endsWith("." + extension)) {
                file = new File(filePath + "." + extension);
            }

            try {
                boolean success = ImageUtils.saveImage(filteredImage, file, extension);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Gambar berhasil disimpan di:\n" + file.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(this, "Format penyimpanan tidak didukung.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan gambar.");
            }
        }
    }


    
    private void applyFilter(Filter filter) {
        if (filteredImage == null) return;
        filteredImage = filter.apply(ImageUtils.copyImage(filteredImage)); // apply ke filteredImage bukan originalImage
        filteredLabel.setIcon(new ImageIcon(filteredImage));
    }

    
    private void resetImage() {
        if (originalImage != null) {
            filteredImage = ImageUtils.copyImage(originalImage);
            filteredLabel.setIcon(new ImageIcon(filteredImage));
        }
    }

//    private BufferedImage deepCopy(BufferedImage img) {
//        BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
//        Graphics2D g2d = copy.createGraphics();
//        g2d.drawImage(img, 0, 0, null);
//        g2d.dispose();
//        return copy;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//            SwingUtilities.invokeLater(() -> {
//            new ImageEditorFrame().setVisible(true);
//        });        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ImageEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ImageEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ImageEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ImageEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//      ;
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
