import java.util.Scanner;

public class Main
{
    private void split()
    {
        Scanner sc = new Scanner(System.in);
        int t= sc.nextInt();
        for(int l=0;l<t;l++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int p = n*m;
            if (p%2 ==0 )
            {
                System.out.println("Yes");
            } else
            {
                System.out.println("No");
            }
        }
    }

    public static void main(String args[])
    {
        Main m = new Main();
        m.split();
    }
}
