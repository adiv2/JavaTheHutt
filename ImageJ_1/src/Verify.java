import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Verify
{

    public  static  void main(String[] args )
    {
        Verify verify = new Verify();
        verify.tupleVerify();

    }

    void tupleVerify()
    {
        File[] files = new File("/home/aditya/Pictures/png_images_asassn").listFiles();
        String[] write = new String[files.length];
        int i=0;
        for(File file:files)
        {
            String fileName = file.getName().replace(".png","");
            write[i]=fileName;
            i++;
        }
        File[] files2 = new File("/home/aditya/Pictures/jpeg_images_asassn").listFiles();
        String[] read = new String[files2.length];
        i=0;
        for(File file:files2)
        {
            String fileName = file.getName().replace(".jpeg","");
            read[i]=fileName;
            i++;
        }
        Arrays.sort(read);
        Arrays.sort(write);
        for(int j=0;j<read.length;j++)
        {
            if(read[j].equalsIgnoreCase(write[j]))
            {

            }
            else
            {
                System.out.println("Mismatch ! read is "+read[j]+" write is "+write[j]);
            }
        }


    }

    void cloudsVerify()
    {
        File file = new File("/home/aditya/Downloads/log1.txt");
        ArrayList<String> allList = new ArrayList<>();
        ArrayList<String> imageList = new ArrayList<>();
        ArrayList<String> checkList = new ArrayList<>();
        try
        {
            allList  = (ArrayList<String>) FileUtils.readLines(file);
        }
        catch (Exception e){}
        File[] files = new File("/home/aditya/Desktop/t/A/AO/").listFiles();
        for (File file2:files)
        {
            String fileName = file2.getName().replace(".png","");
            imageList.add(fileName);

        }

        for (String image:imageList)
        {
            for (String match:allList)
            {
                if(match.contains(image))
                {
                    checkList.add(match);
                }
            }
        }
        int list=0;
        for (String match:checkList)
        {
            if(match.contains("clouds"))
            {list++;System.out.println(match);}
        }
        System.out.println(list);
    }
}
