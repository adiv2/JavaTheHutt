import java.util.Scanner;

public class Main
{
    int n=1;
    int num;
    int i,j=0;
    int[][] m ;
    //= new int[10][10];

    public static void main(String[] args)
    {
        Main run = new Main();
        System.out.println("Enter any number");
        Scanner sc = new Scanner(System.in);
        run.num=sc.nextInt();
        int num1 = (int) Math.ceil(Math.sqrt(run.num));
        run.m = new int[num1][num1];
        run.init();
        run.spiral();
        run.print();

    }

    void init()
    {
        for(i=0;i<m.length;i++)
        {
            for(j=0;j<m.length;j++)
            {
                m[i][j]=0;
            }
        }
        i=j=0;
    }

    void print()
    {
        for(i=0;i<m.length;i++)
        {
            for(j=0;j<m.length;j++)
            {
                System.out.print(m[i][j]+"  ");
            }
            System.out.println();
        }
    }

    void spiral()
    {
        int l = m.length-1;
        int l1 = 0;
        while(n<=num)
        {
            if(i<=l1)
            {
                if(i==j && m[i][j]!=0){i=j=(i+1);l--;l1++;}
                if(m[i][j]==0 || m[i][j]==1000)
                {
                    m[i][j]=n;
                    n++;
                }
                if(j!=l){j++;}
            }
            if(j==l && i<=l)
            {
                if(m[i][j]==0 || m[i][j]==1000)
                {
                    m[i][j]=n;
                    n++;
                }
                if(i!=l){i++;}
            }
            if(j<=l && i==l)
            {
                if(m[i][j]==0 || m[i][j]==1000)
                {
                    m[i][j]=n;
                    n++;
                }
                if(j!=0){j--;}
            }
            if(j<=l1 && i<=l)
            {
                if(m[i][j]==0 || m[i][j]==1000)
                {
                    m[i][j]=n;
                    n++;
                }
                if(i!=0){i--;}
            }
            //System.out.println();
            // System.out.println("i is "+i+" j is "+j+" n is "+n+" l "+l+" l1 is "+l1);
        }
    }
}
