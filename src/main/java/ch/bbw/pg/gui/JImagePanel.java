package ch.bbw.pg.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author P. Gatzka
 * @version 11.01.2022
 * Project: Spoon
 */
public class JImagePanel extends JPanel {

    private BufferedImage image;

    public void setImage(BufferedImage image) {
        System.out.println(image.getWidth());
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null)
            g.drawImage(image, 0, 0, this);
    }
}
