import ij.IJ;
import ij.ImagePlus;
import ij.io.Opener;
import ij.process.ImageProcessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;


public class ImageJ_1
{
    void read()
    {
        ArrayList<String> sources = new ArrayList<>();
        ArrayList<String> destination = new ArrayList<>();
        File[] files = new File("/home/aditya/Desktop/C2").listFiles();


        for (File file : files)
        {
            if (file.isFile())
            {
                sources.add(file.getAbsolutePath());
                destination.add("/home/aditya/Desktop/t/A/Test/"+file.getName());
            }
        }

        for (int i = 0; i < sources.size(); i++)
        {
            write(sources.get(i), destination.get(i));

        }
    }

    private  void write(String source, String destination)
    {

        ArrayList<String> blackHex = new ArrayList<>();
        float blackPixels = 0;
        float whitePixels = 0;
        float whitePixelsInGrid=0;
        float blackPixelsInGrid=0;
        int dense=0;
        ArrayList<String> whiteRange = new ArrayList<>();
        ImagePlus imgPlus = new ImagePlus(source);
        ImageProcessor ip = moon1(imgPlus).getProcessor();
        BufferedImage bufferedImage = ip.getBufferedImage();
        BufferedImage bufferedImage1 = convertToRGB(bufferedImage);
        //FileSaver fs = new FileSaver(imgPlus);
        //fs.saveAsJpeg("/home/aditya/Pictures/4k2.jpg");
        for (int y = 0; y < 2048; y = y + 64)
        {
            for (int x = 0; x < 2048; x = x + 64)
            {
                for (int i = x; i < x + 64; i++)
                {
                    for (int j = y; j < y + 64; j++)
                    {
                        Color c = new Color(bufferedImage1.getRGB(i, j));
                        String hex = "#" + Integer.toHexString(c.getRGB()).substring(2);
                        //System.out.println("Pixel at "+i+","+j+" "+hex);
                        if(c.getRed()<50)
                        {
                            blackPixels++;
                            blackPixelsInGrid++;
                        } else
                        {
                            whitePixels++;
                            whitePixelsInGrid++;
                            if (!whiteRange.contains(hex))
                            {
                                whiteRange.add(hex);
                            }
                        }

                    }
                }
                int totalPixelsInGrid = (int)whitePixelsInGrid + (int)blackPixelsInGrid;
                float whitePixelDensity = whitePixelsInGrid/totalPixelsInGrid;
                if (((whitePixelDensity*100)>89 && (whitePixelDensity*100)<99) || ((whitePixelDensity*100)>60 && (whitePixelDensity*100)<70))
                {//System.out.println(x+","+y);
                    for (int p1=x;p1<x+64;p1++)
                    {
                        for (int p2=y;p2<y+64;p2++)
                        {
                            Color pink = new Color	(255,104,150);
                            int rgb = pink.getRGB();
                            bufferedImage1.setRGB(p1,p2,rgb);
                        }
                    }
                    dense++;
                }

                whitePixelsInGrid=0;
                blackPixelsInGrid=0;

            }
        }

            System.out.println(source);
            System.out.println("Dense blocks:" + dense);

        // IJ.saveAs(imgPlus,"jpeg","/home/aditya/Desktop/pink_cloud.jpeg");
        try
        {
            //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //ImageIO.write(bufferedImage2, "png", byteArrayOutputStream);
            ImagePlus imgPlus2 = new ImagePlus("work",bufferedImage1);
            IJ.saveAs(imgPlus2, "png",destination);
        }
        catch (Exception e){e.getMessage();}
    }

    private  BufferedImage convertToRGB(BufferedImage image)
    {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    ImagePlus moon1(ImagePlus imgPlus)
    {
       // IJ.setMinAndMax(imgPlus, 52, 164);
        // IJ.run(imgPlus, "Smooth", "");
        return imgPlus;
    }


    public static void main(String[] ags)
    {
        ImageJ_1 ij1 = new ImageJ_1();
        ij1.read();
    }
}
