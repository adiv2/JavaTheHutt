import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.equalizeHist;

public class JCVTest_1
{
    public static void main(String[] args)
    {
        try
        {

            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber("/Data/WebRoot/Movies/Kpop/tts.mp4");
            fFmpegFrameGrabber.
// Convert frame to an buffered image so it can be processed and saved
            Image img = (new BufferToImage((VideoFormat) buf.getFormat()).createImage(buf));
            buffImg = new BufferedImage(img.getWidth(this), img.getHeight(this), BufferedImage.TYPE_INT_RGB);

        }
        catch (Exception e)
        {
            System.out.println("error: " + e.getMessage());
        }

    }
}