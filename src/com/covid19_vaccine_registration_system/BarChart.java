package com.covid19_vaccine_registration_system;

import javax.swing.*;
import java.awt.*;

// Reference Code : http://www.java2s.com/Code/Java/2D-Graphics-GUI/Simplebarchart.htm

public class BarChart extends JPanel {
    private double[] values;
    private String[] names;
    private String title;
    private Color color;

    public BarChart(double[] v, String[] n, String t, Color c) {
        names = n;
        values = v;
        title = t;
        color = c;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null || values.length == 0)
            return;
        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < values.length; i++) {
            if (minValue > values[i])
                minValue = values[i];
            if (maxValue < values[i])
                maxValue = values[i];
        }

        Dimension d = getSize();
        int clientWidth = d.width;
        int clientHeight = d.height;
        int barWidth = clientWidth / values.length;

        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 15);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth(title);
        int y = titleFontMetrics.getAscent();
        int x = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, x, y);

        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue)
            return;
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        y = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);

        for (int i = 0; i < values.length; i++) {
            int valueX = i * barWidth + 1;
            int valueY = top;
            int height = (int) (values[i] * scale);
            if (values[i] >= 0)
                valueY += (int) ((maxValue - values[i]) * scale);
            else {
                valueY += (int) (maxValue * scale);
                height = -height;
            }

            g.setColor(color);
            g.fillRect(valueX, valueY, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueX, valueY, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(names[i]);
            x = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(names[i], x, y);
        }
    }
}
