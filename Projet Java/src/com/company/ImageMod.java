package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageMod {
    public static ImageIcon rotate(Icon imageIcon1, Double degrees) {
        BufferedImage image = new BufferedImage(
                imageIcon1.getIconWidth(),
                imageIcon1.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g1 = image.createGraphics();
        // paint l'icon sur la buffuredImage
        imageIcon1.paintIcon(null, g1, 0,0);
        g1.dispose();
        // Calcul la taille de la nouvelle image en fonction de l'angle de la rotation
        double radians = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
        int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);

        // Crée une nouvelle image
        BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotate.createGraphics();
        // Calculate the "anchor" point around which the image will be rotated
        int x = (newWidth - image.getWidth()) / 2;
        int y = (newHeight - image.getHeight()) / 2;
        AffineTransform at = new AffineTransform();
        at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
        at.translate(x, y);
        g2d.setTransform(at);
        // Paint l'image original
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return new ImageIcon(rotate);
    }
}
