import  ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.io.Opener;

import java.awt.*;

public class Conv
{
    public static void main(String[] args)
    {

        ImagePlus imagePlus = new ImagePlus("/home/aditya/Pictures/fits_images2/bc222063.fits");
        byte[] x = new FileSaver(imagePlus).serialize();
        ImagePlus imagePlus2 = new Opener().deserialize(x);
        try
        {
            IJ.run(imagePlus2, "16-bit", "");
            IJ.run(imagePlus2, "Enhance Contrast...", "saturated=0.9 normalize equalize");
            IJ.run(imagePlus2, "Min...", "value=9000");
            byte[] y = new FileSaver(imagePlus2).serialize();
            ImagePlus im3 = new Opener().deserialize(y);
            IJ.saveAs(im3, "png", "/home/aditya/Desktop/fil.png");
        }
        catch (Exception e){System.out.print(e.getMessage());}

    }
}
