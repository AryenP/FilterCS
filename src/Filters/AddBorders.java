package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class AddBorders implements PixelFilter {
    private int size;
    public AddBorders(){
        String borderSize = JOptionPane.showInputDialog ("What size?" );
        size = Integer.parseInt ( borderSize );
    }
    @Override
    public DImage processImage ( DImage img ) {
        short[][]gridorg = img.getBWPixelGrid ();
        short[][] grid = new short[img.getHeight ()+size*2][img.getWidth ()+size*2];
        for (int r = 0; r < gridorg.length; r++) {
            for (int c = 0; c < gridorg[0].length; c++) {
              grid[r+size][c+size] = gridorg[r][c] ;
            }
        }
        img.setPixels ( grid );
        return img;
    }
}
