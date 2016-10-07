import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class Graphs
{
    int[][] adj = new int[50][50];
    int n;
    Scanner sc = new Scanner(System.in);
    void create_graph()
    {
        int i,max_edges=50,origin,destin;

        System.out.print(" Enter number of nodes: ");
        n = sc.nextInt();
        max_edges=n*(n-1);

        for(i=1;i<=max_edges;i++)
        {
            System.out.println("\n Enter edge "+i+"( 0 0 to quit) : ");
            origin=sc.nextInt();
            destin=sc.nextInt();

            if((origin == 0) && (destin == 0))
                break;
            if( origin> n || destin > n || origin <= 0 || destin <= 0)
            {
                System.out.println("\n Invalid edge!\n");
                i--;
            }
            else
            {
                adj[origin][destin] = 1;
            }
        }
    }

    void display()
    {
        int i,j;
        for(i=0;i<=n;i++)
        {
            for(j=1;j<=n;j++)
            {
                System.out.print(adj[i][j]+" ");
            }
            System.out.println();
        }
    }
}

class GraphsGUI extends JPanel
{
    public JFrame frame = new JFrame();
    JButton rb = new JButton();
    int[] x_co= new int [50];
    int[] y_co= new int[50];

    int node;

    JRadioButton rb;


    void insert_node()
    {
        rb.isS
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1280,720);
        public drawO n = new drawO();
        frame.add(n);
        System.out.println("Insert new node");
        Scanner sc = new Scanner(System.in);
        node = sc.nextInt();

        x_co[node] = node * ((int)Math.random()*100) +100;
        y_co[node] = node * ((int)Math.random()*100) +100;

    }
}

class drawO extends GraphsGUI
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int check=1;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println("nextnode");
            check=sc.nextInt();
            g.drawOval(x_co[node],y_co[node],50,50);
            n.repaint();
        }while(check !=0);

    }

}
public class GAT
{

    public static void main(String[] args)
    {
        GraphsGUI g1 = new GraphsGUI();
        g1.insert_node();

    }

}
