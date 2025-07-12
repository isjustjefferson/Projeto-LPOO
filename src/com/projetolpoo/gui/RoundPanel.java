package com.projetolpoo.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JPanel;

public class RoundPanel extends JPanel {
    /**
     
*/
  private static final long serialVersionUID = 1L;
  private int cornerRadius = 100;
  private Image image;

    public RoundPanel() {
        setOpaque(false);
        setBackground(new Color(200, 200, 255));
    }

    public void setImage(Image img) {
        this.image = img;
        repaint();
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        if (image != null) {
            int size = Math.min(width, height) - 10;
            int x = (width - size) / 2;
            int y = (height - size) / 2;

            Shape circle = new java.awt.geom.Ellipse2D.Float(x, y, size, size);
            g2.setClip(circle);

            g2.drawImage(image, x, y, size, size, this);
        }

        g2.dispose();
    }}