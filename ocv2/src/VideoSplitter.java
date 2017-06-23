import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

import java.util.ArrayList;

import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.videoio.Videoio.*;
import static org.opencv.videoio.Videoio.CAP_PROP_FPS;
import static org.opencv.videoio.Videoio.CAP_PROP_FRAME_COUNT;

public class VideoSplitter extends TempMatch
{
    ArrayList<Mat> matFrames = new ArrayList();
    int codec;
    double FPS;
    static int persons=0;
    void split(int numberOfFrames,int everyXSeconds)
    {
        int frames=0;
        int seconds=10;
        VideoCapture videoCapture = new VideoCapture("/home/aditya/Videos/Webcam/test9.mp4",CV_CAP_FFMPEG);
        //VideoCapture videoCapture = new VideoCapture(0);
        System.out.println(videoCapture.isOpened());
        //videoCapture.release();
        int totalFrames=(int) Math.ceil(videoCapture.get(CAP_PROP_FRAME_COUNT));
        if(numberOfFrames!=0){totalFrames=numberOfFrames;}
        Mat frame= new Mat();
        for (int i=0;i<=totalFrames;i++)
        {
            int frameCheck;
            videoCapture.read(frame);
            codec=(int)videoCapture.get(CAP_PROP_FOURCC);
            frames= (int) Math.ceil(videoCapture.get(CAP_PROP_FPS));
            if(everyXSeconds==0){frameCheck=0;}
            else {seconds=everyXSeconds; frameCheck=i%(frames*seconds);}
            if(frameCheck==0)
            {
                //System.out.println("Resolution: "+frame.width()+"x"+frame.height());
                FPS=videoCapture.get(CAP_PROP_FPS);
                //System.out.println("FPS: "+frames);
                //System.out.println("Total frames: "+totalFrames);
                //System.out.println("Write "+i);
                //writeMat(frame,"/home/aditya/Desktop/B1/Boruto_"+i+".jpg");
                //matFrames.add(frame);
                animeFace(frame,i);
            }


        }
        System.out.println("FaceList size "+facesList.size());
        System.out.println("codec "+codec);
        int fac=0;
        for(Faces faces:facesList)
        {
            imwrite("/home/aditya/Desktop/"+faces.name+".png",faces.mat);
        }
    }

    void merge()
    {
        VideoWriter videoWriter = new VideoWriter();
        Size size = new Size(1024,720);
        //codec=VideoWriter.fourcc('D', 'I', 'V', 'X');
        System.out.println("codec "+codec);
        videoWriter.open("test.mp4",828601953,FPS,size,false);
        if (videoWriter.isOpened()){System.out.println("Frames in list "+matFrames.size());}
        for (Mat mats:matFrames)
        {
            //System.out.println(mats.width());
            videoWriter.write(mats);
        }
        matFrames.clear();
        videoWriter.release();

    }


    public static void main(String[] args)
    {
        VideoSplitter videoSplitter = new VideoSplitter();
        videoSplitter.split(0,4);
        //videoSplitter.merge();
    }

}
