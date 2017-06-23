import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.*;

public class TempMatch extends OCV_1
{
    public static  void main(String[] args)
    {
        TempMatch tm = new TempMatch();
        tm.tempMatch();
    }

    protected void  tempMatch()
    {
        Mat source = readMat("/home/aditya/JavaTheHutt/ocv1/source2.jpg");
        Mat template = readMat("/home/aditya/JavaTheHutt/ocv1/template2.jpg");
        Mat greySource = new Mat();
        Mat greyTemplate = new Mat();
        source.convertTo(greySource,CV_LOAD_IMAGE_GRAYSCALE);
        template.convertTo(greyTemplate,CV_LOAD_IMAGE_GRAYSCALE);
        Core.MinMaxLocResult mmr=null;
        double[] vals = new double[11];
        ArrayList <Core.MinMaxLocResult> mmrvals = new ArrayList<>();
        for(int i=1;i<11;i++)
        {
            Size size = new Size((template.width() / i), (template.height() / i));
            Mat smallTemp = new Mat();
            resize(greyTemplate, smallTemp, size);
            Mat result = new Mat();
            System.out.println("i=" + i + " size:" + (smallTemp.width()) + " height " + (smallTemp.height()));
            Imgproc.matchTemplate(greySource, smallTemp, result, Imgproc.TM_SQDIFF);
            mmr = Core.minMaxLoc(result);
            System.out.println("Max val " + mmr.maxVal);
            vals[i] =  mmr.minVal;
            mmrvals.add(mmr);
        }
        Arrays.sort(vals);
        for(double v:vals)
        {
            System.out.println(v);
        }
        for(Core.MinMaxLocResult mmr2 :mmrvals)
        {
            if(mmr2.minVal==vals[1])
            {
                Point matchLoc = mmr2.minLoc;
                rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(), matchLoc.y + template.rows()), new Scalar(0, 255, 0), 10);
                //writeMat(source,"/home/aditya/Desktop/watchFound.jpg");
                System.out.print("Found at " + matchLoc.x+" max val "+mmr2.maxVal);
                imwrite("/home/aditya/JavaTheHutt/ocv1/result.jpg", source);
            }
        }

    }
}
