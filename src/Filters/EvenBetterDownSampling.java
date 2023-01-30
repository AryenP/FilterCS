package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class EvenBetterDownSampling implements PixelFilter , Interactive {
    private int percent;
    public EvenBetterDownSampling(){
        percent = Integer.parseInt ( JOptionPane.showInputDialog ( "How much Percent Zoom?" ) );
    }

    @Override
    public DImage processImage ( DImage img ) {
        short[][] grid = img.getBWPixelGrid();
        double p = percent/100.0;
        int height = (int)(p* grid.length);
        int width = (int)(p*grid[0].length);
        short[][] gridOut = new short[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gridOut[i][j] = grid[i*width/grid[0].length][j*height/ grid.length];
            }
        }
        img.setPixels ( gridOut );
        return img;
        }

    @Override
    public void mouseClicked ( int mouseX, int mouseY, DImage img ) {

    }

    @Override
    public void keyPressed ( char key ) {

    }
}

