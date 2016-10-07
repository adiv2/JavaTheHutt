import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public void test ()
    {

        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter number of test cases");
        int t = sc.nextInt();
        int l;
        for(int l1=0;l1<100;l1++)
        {
            for (l = 0; l < t; l++)
            {
                //System.out.println("Enter N and K");
                int n = sc.nextInt();
                int k = sc.nextInt();
                int A[] = new int[n + 1];
                ArrayList S = new ArrayList();

                int kcount = 0, i;
                //System.out.println("Enter the votes");
                for (i = 1; i <= n; i++)
                {
                    A[i] = sc.nextInt();
                }
                for (i = 1; i <= n; i++)
                {
                    if (A[i] != i && S.contains(A[i]) == false)
                    {
                        for (int j = 1; j <= n; j++)
                        {
                            if (A[i] == A[j] && A[A[i]] != A[i])
                            {
                                kcount++;
                                if (kcount == k)
                                {
                                    S.add(A[i]);
                                }
                            }
                        }
                    }
                    kcount = 0;
                }
                System.out.println(S.size());
                S.clear();
            }
        }

    }
    public static void main(String[] args)
    {
        Main m = new Main();
        m.test();

    }
}
