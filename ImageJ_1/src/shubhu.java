import java.util.ArrayList;
import java.util.Scanner;

public class shubhu
{
    ArrayList<tree> treeArrayList = new ArrayList();

    void insertRoot(int newValue)
    {
        treeArrayList.add(new tree(newValue));
        System.out.println(treeArrayList);
    }

    void insert(int newValue, tree root)
    {
        if(treeArrayList.isEmpty())
        {
            insertRoot(newValue);
            System.out.println(treeArrayList+" ");
        }

        if (treeArrayList.size() > 0)
        {
            int index = treeArrayList.indexOf(root);
            if (newValue > treeArrayList.get(index).value)
            {
                if (treeArrayList.get(index).right == 0)
                {
                    tree newNode = new tree(newValue);
                    treeArrayList.add(newNode);
                    treeArrayList.get(index).right = treeArrayList.indexOf(newNode);
                    System.out.println(treeArrayList);
                }
                else
                {
                    insert(newValue, treeArrayList.get(treeArrayList.get(index).right));
                }
            }
            if (newValue < treeArrayList.get(index).value)
            {
                if (treeArrayList.get(index).left == 0)
                {
                    tree newNode = new tree(newValue);
                    treeArrayList.add(newNode);
                    treeArrayList.get(index).left=treeArrayList.indexOf(newNode);
                    System.out.println(treeArrayList);
                }
                else
                {
                    insert(newValue, treeArrayList.get(treeArrayList.get(index).left));
                }
            }

        }
    }

    void print()
    {
        for (int i = 0; i<treeArrayList.size();i++)
        {
            System.out.print(treeArrayList.get(i).value);
        }
    }

    tree returnRoot()
    {
        return  treeArrayList.get(0);
    }

    public static void main(String[] args)
    {
        int newValue;
        shubhu sb = new shubhu();
        Scanner sc = new Scanner(System.in);
        System.out.println("insert value");
        newValue = sc.nextInt();
        sb.insertRoot(newValue);
        newValue = sc.nextInt();
        sb.insert(newValue,sb.returnRoot());
        newValue = sc.nextInt();
        sb.insert(newValue,sb.returnRoot());
        newValue = sc.nextInt();
        sb.insert(newValue,sb.returnRoot());
        sb.print();
    }
}


class tree
{
    int value=0;
    int left=0;
    int right=0;

    public tree(int value)
    {
        this.value=value;
    }
}