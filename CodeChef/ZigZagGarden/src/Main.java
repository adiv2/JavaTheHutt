import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    //private int trees[n][];
    private ArrayList pattern = new ArrayList();
    private void input()
    {
        Scanner sc = new Scanner(System.in);
        int t= sc.nextInt();
        for(int l=0;l<t;l++)
        {
            int n = sc.nextInt();
            int trees[][] = new int[n][2];
            for (int i = 0; i < n; i++)
            {
                trees[i][0] = sc.nextInt();
                trees[i][1] = sc.nextInt();
            }
        /*
        for(int i=0;i<n;i++)
        {
            System.out.println(trees[i][0]+"  "+trees[i][1]);
        }
        */
            zigZag(trees, n);
        }
    }

    private void zigZag(int trees[][], int n)
    {
        int count=0;
        for(int i=0;i<11;i++)
        {
            for(int j=0;j<n;j++)
            {
                trees[j][0]= trees[j][0] + (i*trees[j][1]);
            }
            for(int k=1;k<(n-1);k++)
            {
                if(trees[k][0]>trees[k-1][0] && trees[k][0]>trees[k+1][0])
                {
                    count++;
                    if(count==(n-2))
                    {
                        pattern.add(i);
                    }
                }
                else if(trees[k][0]<trees[k-1][0] && trees[k][0]<trees[k+1][0])
                {
                    count++;
                    if(count==(n-2))
                    {
                        pattern.add(i);
                    }
                }
            }
            count=0;
        }
        if(pattern.size()!=0)
        {System.out.println(pattern.size()-1);}
        else{System.out.println("0");}
            System.out.println(pattern.get(0)+" "+pattern.get(pattern.size()-1));
        /*for(int r=0;r<pattern.size();r++)
        {
            System.out.print(pattern.get(r)+" ");
        }
        */
    }

    public static void main(String args[])
    {
        Main m = new Main();
        m.input();
    }
}
