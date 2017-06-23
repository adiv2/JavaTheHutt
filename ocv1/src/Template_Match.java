import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgcodecs.Imgcodecs.*;
import static org.opencv.imgproc.Imgproc.rectangle;

public class Template_Match
{
    static {System.loadLibrary( Core.NATIVE_LIBRARY_NAME );}
    static  String sourceFilePath = "/home/aditya/JavaTheHutt/ocv1/source2.jpg";
    String templateFilePath = "/home/aditya/JavaTheHutt/ocv1/template.jpg";
    public void match(Mat source)
    {
        Mat greySource = new Mat();
        Mat greyTemplate = new Mat();
        Mat template = imread(templateFilePath);
        source.convertTo(greySource,CV_LOAD_IMAGE_GRAYSCALE);
        template.convertTo(greyTemplate,CV_LOAD_IMAGE_GRAYSCALE);
        Mat result = new Mat();
        Imgproc.matchTemplate(greySource, template, result, Imgproc.TM_CCOEFF_NORMED);
        //Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        Point matchLoc;
        matchLoc = mmr.maxLoc;
        rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(), matchLoc.y + template.rows()), new Scalar(0, 0, 255));
        System.out.println(mmr.maxVal+" "+matchLoc.x);
        imwrite("/home/aditya/JavaTheHutt/ocv1/result.jpg",source);
    }

    public static void main(String[] args)
    {
        Template_Match template_match = new Template_Match();
        Mat source = imread(sourceFilePath);
        template_match.match(source);
    }
}
