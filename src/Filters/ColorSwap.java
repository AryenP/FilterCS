package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class ColorSwap implements PixelFilter {

    @Override
    public DImage processImage ( DImage img ) {
        short[][] red  = img.getRedChannel ();
        short[][] green = img.getGreenChannel ();
        short[][] blue = img.getBlueChannel ();


        img.setColorChannels ( blue , red , green );
        return img;
    }
}
