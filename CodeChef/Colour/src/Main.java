import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
public class Main
{
    private void paint()
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int l,r=0,g=0,b=0;
        for(l=0;l<t;l++)
        {
            //System.out.println("Number of rooms");
            int num = sc.nextInt();
            //System.out.println("Enter the colour S");
            String S = sc.next();
            if(S.length()==num)
            {
                for(int i=0;i<num;i++)
                {
                    if(S.charAt(i)=="R".charAt(0) || S.charAt(i)=="r".charAt(0))
                    {
                        r++;
                    }
                    if(S.charAt(i)=="G".charAt(0) || S.charAt(i)=="g".charAt(0))
                    {
                        g++;
                    }
                    if(S.charAt(i)=="B".charAt(0) || S.charAt(i)=="b".charAt(0))
                    {
                        b++;
                    }
                }
            }
            /*
            if(r>g && r>b)
            {System.out.println((num-r));}
            else if(g>b && g>r)
            {System.out.println((num-g));}
            else if(b>g && b>r)
            {System.out.println((num-b));}
            else if(r==g && g==b)
            {System.out.println((num-r));}
            else if(r>g && r==b)
            {System.out.println((num-r));}
            else if(g>r && g==b)
            {System.out.println((num-g));}
            else if(b>r && b==g)
            {System.out.println((num-b));}
            */
        }

            int a[]={r,g,b};
        Arrays.sort(a);
            System.out.print(a[0]+a[1]);


    }

    public static void main(String args[])
    {
        Main m = new Main();
        m.paint();
    }
}
