package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class ColorNoiseFilter implements PixelFilter {
private double n;
    public ColorNoiseFilter(){
        String r = JOptionPane.showInputDialog ( null , "enter the noise probability boi" );
        n = Double.parseDouble ( r);


    }
    @Override
    public DImage processImage ( DImage img ) {
        short[][] red  = img.getRedChannel ();
        short[][] green = img.getGreenChannel ();
        short[][] blue = img.getBlueChannel ();
        for (int r = 0; r < red.length; r++) {
            for (int w = 0; w < red[r].length; w++) {
                if (Math.random () < n ){
                    red[r][w] = (short)(Math.random ()*256);
                    green[r][w] = (short)(Math.random ()*256);
                    blue[r][w] = (short)(Math.random ()*256);

                }

            }

        }


        img.setColorChannels ( red , green , blue );
        return img;
    }

}
