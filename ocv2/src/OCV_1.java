import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import static org.opencv.imgproc.Imgproc.rectangle;

public class OCV_1
{
    protected int matches=0;
    protected int notMatch=0;
    protected int dense=0;
    private String readPath ="/home/aditya/Pictures/jpeg_images_asassn2/";
    protected String fileName;
    static {System.loadLibrary( Core.NATIVE_LIBRARY_NAME );}
    protected int bufferedImageType;
    protected ArrayList<Mat> referenceList = new ArrayList<>();
    protected ArrayList<Mat> templateList = new ArrayList<>();

    public static void main(String[] args)
    {
        OCV_1 ocv1 = new OCV_1();
        //ocv1.ovcFunc();
        //ocv1.firstConcat();
        //ocv1.concat();
    }

    protected void ovcFunc()
    {
        File[] files = new File(readPath).listFiles();
        for(File file:files)
        {
            String sourceFilePath = file.getAbsolutePath();
            fileName=file.getName();
            //System.out.println(fileName);
            Mat source = readMat(sourceFilePath);
            compute(sourceFilePath, source);

            if(dense>300)
            {
                //System.out.println(fileName+" is too noisy");
                dense=0;
            }
            if (dense>100 && dense<300)
            {
                match();
            }
            /*
            if(fileName.contains("bc066594") || fileName.contains("be163798")  || fileName.contains("bf131684") || fileName.contains("bf126510") || fileName.contains("bc026871"))
            {

                //System.out.println(fileName+" "+dense);
                //match();
            }
            else
            {
                dense=0;
            }*/
        }
    }

    protected Mat readMat(String path)
    {
        File src = new File(path);
        BufferedImage bufferedImage=null;
        try
        {
            bufferedImage = ImageIO.read(src);
            bufferedImageType = bufferedImage.getType();
        }
        catch (Exception e){System.out.print(e.getMessage());}
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);}catch (Exception e){System.out.print(e.getMessage());}
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return  Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);

    }

    protected void writeMat(Mat write,String path)
    {
        //System.out.print("mat type "+destination.type());
        if (write.type()==16)
        {
            bufferedImageType = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage bufferedImage1;
        bufferedImage1 = new BufferedImage(write.width(), write.height(), bufferedImageType);
        //bufferedImage1 = convertToRGB(bufferedImage1);
        byte[] data = new byte[((int) write.total() * write.channels())];
        write.get(0, 0, data);
        //byte[] data = matOfByte.toArray();
        byte b;
        for (int i = 0; i < data.length; i = i + 3)
        {
            b = data[i];
            data[i] = data[i + 2];
            data[i + 2] = b;
        }
        try
        {
            bufferedImage1.getRaster().setDataElements(0, 0, write.cols(), write.rows(), data);
            File op = new File(path);
            ImageIO.write(bufferedImage1, "jpg", op);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    protected  BufferedImage convertToRGB(BufferedImage image)
    {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    protected  void compute(String source,Mat pinkMat)
    {
        float blackPixels = 0;
        float whitePixels = 0;
        float whitePixelsInGrid=0;
        float blackPixelsInGrid=0;
        referenceList.clear();
        ArrayList<String> whiteRange = new ArrayList<>();
        ImagePlus imgPlus = new ImagePlus(source);
        ImageProcessor ip = moon1(imgPlus).getProcessor();
        BufferedImage bufferedImage = ip.getBufferedImage();
        BufferedImage bufferedImage1 = convertToRGB(bufferedImage);
        for (int y = 0; y < bufferedImage1.getHeight(); y = y + 64)
        {
            for (int x = 0; x < bufferedImage1.getWidth(); x = x + 64)
            {
                for (int i = x; i < x + 64; i++)
                {
                    for (int j = y; j < y + 64; j++)
                    {
                        Color c = new Color(bufferedImage1.getRGB(i, j));
                        String hex = "#" + Integer.toHexString(c.getRGB()).substring(2);
                        //System.out.println("Pixel at "+i+","+j+" "+hex);
                        if (c.getRed()<50)
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
                if ((whitePixelDensity*100)>=99.99 )
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

                    Mat sub = pinkMat.submat(y, y + 64, x, x + 64);
                    //writeMat(sub,destination);
                    templateList.add(sub);

                    //System.out.println(x+" "+y);
                    referenceList.add(sub);

                }
                whitePixelsInGrid=0;
                blackPixelsInGrid=0;

            }
        }
        /*
        System.out.println("Black pixels:" + blackPixels);
        System.out.println("White pixels:" + whitePixels);
        System.out.println("White area:" + (whitePixels * 100 / (whitePixels + blackPixels)));
        System.out.println("Black area:" + (blackPixels * 100 / (whitePixels + blackPixels)));
        System.out.println("Total:" + (whitePixels + blackPixels) + " should be :" + (2048 * 2048));
        System.out.println("White hex range size:" + whiteRange.size());
        System.out.println("White hex range:" + whiteRange);

        System.out.println("Dense blocks:"+dense);
        */
        //concat();
        //IJ.saveAs(imgPlus,"jpeg","/home/aditya/Desktop/pink_cloud.jpeg");
        //try{if(dense>=0){ImageIO.write(bufferedImage1, "jpg", new File(destination));}}catch (Exception e){e.getMessage();}
    }

    protected  void concat()
    {
        if(!referenceList.isEmpty())
        {
            String refFile = "/home/aditya/Desktop/t/Refs/ref_"+fileName;
            Mat reference = readMat(refFile);
            referenceList.add(reference);
            Mat r2 = new Mat();
            if((reference.width()/64)+referenceList.size()<1024)
            {
                Core.hconcat(referenceList, r2);
            }
            else
            {
                ArrayList <Mat> subReferenceList = new ArrayList <> (referenceList.subList(0,1023-(reference.width()/64)));
                Core.hconcat(subReferenceList,r2);
            }
            writeMat(r2,refFile);
        }
    }

    protected  void firstConcat()
    {
        Mat r2 = new Mat();
        String refFile = "/home/aditya/Desktop/t/RefsCloud/ref_"+fileName;
        System.out.println("ref list size "+referenceList.size());
        if(referenceList.size()<1024)
        {
            Core.hconcat(referenceList,r2);
        }
        else
        {
            ArrayList <Mat> subReferenceList = new ArrayList <> (referenceList.subList(0,1024));
            Core.hconcat(subReferenceList,r2);
        }
        writeMat(r2,refFile);
    }

    protected void match()
    {
        int i=0;
        File[] files = new File("/home/aditya/Desktop/t/RefsCloud2/").listFiles();
        double[] mval = new double[templateList.size()*files.length];
        int matches2=0;
        for(File file: files)
        {
            if(matches<dense)
            {
                for (Mat template:templateList)
                {
                    String refImagePath = file.getAbsolutePath();
                    Mat source = readMat(refImagePath);
                    Mat result = new Mat();
                    //Mat template = templateList.get(i);
                    //System.out.println(templateList.indexOf(template));
                    if(matches<=(dense/100)*20)
                    {
                        Imgproc.matchTemplate(source, template, result, Imgproc.TM_CCOEFF_NORMED);
                        //Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
                        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
                        //System.out.println(mmr.maxVal );

                        {
                            mval[i] = mmr.maxVal;
                        }
                        i++;
                        if (mmr.maxVal >= 0.405)
                        {
                            matches++;
                            //System.out.println(mmr.maxVal );
                            //System.out.println(refImagePath);
                            //System.out.println(templateList.indexOf(template));
                        }
                        if (mmr.maxVal < 0.40)
                        {
                            notMatch++;
                            if (refImagePath.contains(fileName))
                            {
                                // System.out.println("Should Match but did NOT! "+refImagePath);
                            }
                        }
                    /*
                    if (mmr.maxVal >= 0.95 && mmr.maxVal < 0.97)
                    {
                        referenceList.add(template);
                    }*/
                        Point matchLoc;
                        matchLoc = mmr.maxLoc;
                        rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(), matchLoc.y + template.rows()), new Scalar(0, 255, 0));
                    }
                    //writeMat(source,"/home/aditya/Desktop/xyzabc"+index+".jpg");
                }
            }

            //System.out.println(fileName);
            //System.out.println("Matches:"+matches);
            //System.out.println("Not matches:"+notMatch);

        }
        templateList.clear();
        matches2=matches;
        Arrays.sort(mval);
        /*
        System.out.print(fileName +" dense:"+dense+" match:"+matches2+" ");
        if (mval.length>10)
        {
            for (int l = mval.length - 1; l > mval.length-11; l--)
            {
                System.out.print(mval[l]+" ");
            }
        }
        else
        {
            for (int l = mval.length - 1; l > 0; l--)
            {
                System.out.print(mval[l]+" ");
            }
        }
        System.out.println();
       */
        if(matches>=(dense*0.15) && matches>0)
        {
            System.out.println(fileName+" has clouds");
        }
        matches=0;
        notMatch=0;
        dense=0;
    }

    ImagePlus moon1(ImagePlus imgPlus)
    {
        //IJ.setMinAndMax(imgPlus, 52, 164);
        //IJ.run(imgPlus, "Smooth", "");
        return imgPlus;
    }


}
