package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class PolychromeFilter implements PixelFilter {
    private int numChromes,width;

    public PolychromeFilter() {
        numChromes = Integer.parseInt(JOptionPane.showInputDialog("How many chromes?"));
        width = 255/numChromes;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                short val = grid[r][c];
                int newVal = (val/width)*width+width/2;
                grid[r][c]=(short)newVal;
            }
        }

        img.setPixels(grid);
        return img;
    }
}