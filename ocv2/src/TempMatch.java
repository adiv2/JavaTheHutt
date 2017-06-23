import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.util.ArrayList;
import java.util.Arrays;

import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.*;

public class TempMatch extends OCV_1
{
    ArrayList<Faces> facesList = new ArrayList<>();
    ArrayList<Faces> facesCopyList = new ArrayList<>();


    public static void main(String[] args)
    {
        TempMatch tm = new TempMatch();
        tm.tempMatch();
        //tm.animeFace();
    }

    protected void tempMatch()
    {
        Mat source = readMat("/home/aditya/Desktop/IMG_20170330_115307.jpg");
        Mat template = readMat("/home/aditya/Desktop/lolhp.jpg");
        for (int i = 1; i <= 10; i++)
        {
            Size size = new Size((template.width() / i), (template.height() / i));
            Mat smallTemp = new Mat();
            resize(template, smallTemp, size);
            Mat result = new Mat();
            System.out.println("i=" + i + " size:" + (smallTemp.width()) + " height " + (smallTemp.height()));
            Imgproc.matchTemplate(source, smallTemp, result, Imgproc.TM_CCOEFF_NORMED);
            Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
            Point matchLoc;
            matchLoc = mmr.maxLoc;
            System.out.println("Max val " + mmr.maxVal);
            if (mmr.maxVal >= 0.91)
            {
                rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(), matchLoc.y + template.rows()), new Scalar(0, 255, 0), 10);
                //writeMat(source,"/home/aditya/Desktop/watchFound.jpg");
                System.out.print("Found at " + matchLoc.x);
                imwrite("/home/aditya/Desktop/watchFound.jpg", source);
                break;
            }
        }
    }

    protected void animeFace(Mat image, int f)
    {


        CascadeClassifier cascadeClassifier = new CascadeClassifier("/home/aditya/ApexApps/imIO5.1/humanface3.xml");
        //int f=0;
        //for(Mat image:matFrames)
        MatOfRect faceDetections = new MatOfRect();
        cascadeClassifier.detectMultiScale(image, faceDetections);
        //System.out.println("Rect array length "+faceDetections.toArray().length);
        int rN=0;

            if(faceDetections.toArray().length>=1)
            {
                for (int j = 0; j < 1; j++)
                {
                    Rect rect = faceDetections.toArray()[faceDetections.toArray().length - 1];
                    rN++;
                    Rect rect1 = new Rect(rect.x, rect.y, rect.width, rect.height);
                    Mat r = image.submat(rect1);
                    imwrite("/home/aditya/Desktop/facer.png", r);
                    //rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
                    if (r.width() > 180)
                    {
                        //System.out.println("Array length "+faceDetections.toArray().length);
                        faceMatch(r, f);
                    }
                }
            }
            String filename = "/home/aditya/Desktop/output" + Integer.toString(f) + ".png";
            System.out.println(String.format("Writing %s", filename));
            imwrite(filename, image);

    }

    protected void faceMatch(Mat source,int f)
    {
        //System.out.println("facematch was called");
        if(facesList.size()==0)
        {
            Faces faces2 = new Faces();
            faces2.mat=source.clone();
            imwrite("/home/aditya/firstFace.png",faces2.mat);
            faces2.name="person_"+VideoSplitter.persons;
            facesList.add(faces2);
            imwrite("/home/aditya/Desktop/Person/" + faces2.name +"_"+ Integer.toString(f) + ".png", source);

        }
        else
        {
            double maxvals[]=new double[facesList.size()];
            int numVals=0;
            Boolean newFace = false;
            System.out.println("FC size "+facesList.size());
            for (Faces facesObj : facesList)
            {
                Mat template = facesObj.mat;
                Mat result = new Mat();
                Imgproc.matchTemplate(source, template, result, Imgproc.TM_CCOEFF_NORMED);                Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
                Point matchLoc;
                matchLoc = mmr.maxLoc;
                //System.out.println("Max val " + mmr.maxVal);
                if (mmr.maxVal >= 0.8)
                {
                    //rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(), matchLoc.y + template.rows()), new Scalar(0, 255, 0), 10);
                    //writeMat(source,"/home/aditya/Desktop/watchFound.jpg");
                    System.out.println("Max val " + mmr.maxVal);
                    System.out.println("Found at " + matchLoc.x);
                    imwrite("/home/aditya/Desktop/Person/" + facesObj.name +"_"+ Integer.toString(f) + ".png", source);
                    System.out.println("/Person/" + facesObj.name + "_" + Integer.toString(f) + ".png");
                }
                if(mmr.maxVal<0.8 ){numVals++;}
                if(numVals==(facesList.size()) && numVals!=0){System.out.println("New true "+mmr.maxVal);newFace=true;}
            }

            facesCopyList.clear();
                if(newFace)
                {
                    System.out.println("Smallest max vals are "+maxvals[0]);//+" "+maxvals[1]+" "+maxvals[2]+" ");
                    Faces faces1 = new Faces();
                    faces1.mat=source.clone();
                    faces1.name="person_"+VideoSplitter.persons;
                    facesCopyList.add(faces1);
                    imwrite("/home/aditya/Desktop/Person/" + faces1.name +"_"+ Integer.toString(f) + ".png", source);

                }

            for (Faces facesObj:facesCopyList)
            {
                //if(!facesList.contains(facesObj))
                {
                    facesList.add(facesObj);
                }
            }
            facesCopyList.clear();
        }
        System.out.println();
    }
}

class Faces extends VideoSplitter
{
    String name;
    Mat mat;
    Faces()
    {
        persons++;
    }
}