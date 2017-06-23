import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;

public class Copy
{
    void copyFiles()
    {
        File[] file = new File("/home/aditya/Pictures/jpeg_images_asassn/").listFiles();
        Character b = new Character('b');
        int i =0;
        for(File files:file)
        {

            if (files.getName().charAt(0)== b && (i>100 && i<250))
            {File dest = new File("/home/aditya/Pictures/jpeg_images_asassn2/"+files.getName());
                try
                {
                    FileUtils.copyFile(files,dest);
                }
                catch (Exception e){System.out.println(e.getMessage());}
            }
            i++;
        }
    }

    public  static  void main(String[] args )
    {
        Copy copy = new Copy();
        copy.copyFiles();
    }
}
