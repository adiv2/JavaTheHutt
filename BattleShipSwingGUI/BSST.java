import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension.*;
import java.awt.event.*;
import java.awt.Graphics2D.*;
class grid
{
public int[][] board =  new int[9][9];
public int winner;
int emptyBoard()
{
	int i=0,j=0;
	for(i=0;i<9;i++)
	{
		for(j=0;j<9;j++)
		{
			board[i][j]=0;
		}
	}
	return 0;
}

void showBoard()
{
	int i=0,j=0,columns=0;
	int x=65;
	char s = (char) x;
	System.out.print("* ");	
	for(j=0;j<9;j++)
	{
	System.out.print(columns+" ");
	columns++;
	}
	System.out.print("\n" +s +" ");	
	for(i=0;i<9;i++)
	{
		for(j=0;j<9;j++)
		{
			System.out.print(board[i][j]+" ");
		}
		x++;
		s = (char) x;
		if(x<74){System.out.print("\n" +s +" ");}
	}
	System.out.print("\n\n");
}
}
class ships extends grid
{
static int i,j,luck;

int random()
{
i = (int)(Math.random()*9);
j = (int)(Math.random()*9);
luck = (int)(Math.random()*10);
return 0;
}

int setShip(int size, int i, int j,int luck)
	{
		if(size>9)
		{
			System.out.println("Fuck off");
		}
		else
		{
		if((luck%2)==0)
		{
			int check=0;
			int k;
			if(j<=(8-size))
			{
				for(k=j;k<(j+size);k++)
				{
					if(board[i][k]!=1){check++;}
				}
			}
			else
			{
				for(k=j;k>(j-size);k--)
				{
					if(board[i][k]!=1){check++;}
				}	
			}
			if(check>=size)
			{
			if(j<=(8-size))
			{
				for(k=j;k<(j+size);k++)
				{
					board[i][k]=1;
				}
			}
			else
			{
				for(k=j;k>(j-size);k--)
				{
					board[i][k]=1;
				}	
			}
			}
			else
			{resetpos(size);}
			
		}
		else
		{
			
			int check=0;
			int k;
			if(i<=(8-size))
			{
				for(k=i;k<(i+size);k++)
				{
					if(board[k][j]!=1){check++;}
				}
			}
			else
			{
				for(k=i;k>(i-size);k--)
				{
					if(board[k][j]!=1){check++;}
				}	
			}
			if(check>=size)
			{
			if(i<=(8-size))
			{
				for(k=i;k<(i+size);k++)
				{
					board[k][j]=1;
				}
			}
			else
			{
				for(k=i;k>(i-size);k--)
				{
					board[k][j]=1;
				}	
			}
			}
			else
			{resetpos(size);}
			
		}
	}
	return 0;
	}
	
	int resetpos(int size)
	{
		random();
		setShip(size,i,j,luck);
		return 0;
	}
	
	int setpos()
	{
		int size=3;
		for(int a=0;a<3;a++)
		{
		resetpos(size);
		size++;
		}
		return 0;
	}
}

class bombs extends ships
{
	void showBoard3()
{
	int i=0,j=0,columns=0;
	int x=65;
	char s = (char) x;
	System.out.print("* ");	
	for(j=0;j<9;j++)
	{
	System.out.print(columns+" ");
	columns++;
	}
	System.out.print("\n" +s +" ");	
	for(i=0;i<9;i++)
	{
		for(j=0;j<9;j++)
		{
			System.out.print(board3[i][j]+" ");
		}
		x++;
		s = (char) x;
		if(x<74){System.out.print("\n" +s +" ");}
	}
	System.out.print("\n\n");
}
	public int i,j,rounds=0,h=0;
	int[][] board3 = new int[9][9];
	int fire(ships s1) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String aim = br.readLine();
        if((aim.length())>2){System.out.println("Error!"); fire(s1);}
        else
        i=  aim.charAt(0)- 65;
        j= aim.charAt(1)-48;
        if((9>i) && (9>j) && (i>=0) && (j>=0))
        {
			if(s1.board[i][j]==1)
			{
				System.out.println("Hit!");
				s1.board[i][j] =2;
				rounds++;
				h=0;
				health(s1);
				board3[i][j]=5;
				showBoard3();
			}
			else
			{
				System.out.println("Miss");
				rounds++;
				h=0;
				health(s1);
				board3[i][j]=2;
				showBoard3();
			}
		}
		else if((39>i) && (9>j) && (i>=30) && (j>=0))
		{
			i=i-30;
			if(s1.board[i][j]==1)
			{
				System.out.println("Hit!");
				s1.board[i][j] =2;
				rounds++;
				h=0;
				health(s1);
				board3[i][j]=5;
				showBoard3();
			}
			else
			{
				System.out.println("Miss");
				rounds++;
				h=0;
				health(s1);
				board3[i][j]=2;
				showBoard3();
			}
		}
		else
		{System.out.println("Error!");fire(s1);}
        return 0;
	}
	
	int health(ships s1) throws java.lang.Exception
	{
		int a,b;
		for(a=0;a<9;a++)
		{
			for(b=0;b<9;b++)
			{
				if(s1.board[a][b]==1)
				{
					h++;
				}
			}
		}
		if(h==0)
		{
			stats();
			winner=100;
		}
		else
		{System.out.println("Enemy health remaining: "+h);}
		return 0;
	}
	
	int stats()
	{
		int acc = 12/rounds*100;
		System.out.println("\n\n\nYOU WIN!! \nStats:\nNumber of bombs used: "+rounds+"\nAccuracy: "+acc+"%\n");
	    return 0;
	}
}
 class navy extends ships
 {
	 int setNavy(int i,int j,int luck,int size)
	 {
		System.out.println("Enter the Starting Coordinates of the ship size "+size+" in the following format. Example A0 X. X=1 to place vertically,X=0 to place horizontally");
        setShip(size,i,j,luck);
		size++;
		showBoard();
        return 0;
	}
		
}
class enemy extends bombs
{
	public int[][] board2 =  new int[9][9];
	int i,j,rounds1;;
	int hit(navy n1,int i,int j) throws java.lang.Exception
	{
		if(board2[i][j]==0)
		{
			System.out.println(i+" "+j);
			if(n1.board[i][j]==1)
			{
				System.out.println("Hit!");
				n1.board[i][j] =3;
				rounds1++;
				h=0;
				health1(n1);
				board2[i][j]=1;
				j++;
				if(j<8)
				{
				hit(n1,i,j);
				}
			}
			else
			{
				System.out.println("Miss");
				n1.board[i][j] =2;
				rounds1++;
				h=0;
				health1(n1);
				board2[i][j]=1;
			}
		}
		else {target(n1);}
	    return 0;
	}
	
	
	int health1(navy n1) throws java.lang.Exception
	{
		int a,b;
		for(a=0;a<9;a++)
		{
			for(b=0;b<9;b++)
			{
				if(n1.board[a][b]==1)
				{
					h++;
				}
			}
		}
		if(h==0)
		{
			stats();
			winner=100;
		}
		else
		{System.out.println("Your health remaining: "+h);}
		return 0;
	}
	
	int rand1()
	{
		i = (int)(Math.random()*9);
		j = (int)(Math.random()*9);
		return 0;
	}
	
	int target(navy n1) throws java.lang.Exception
	{
		rand1();
		hit(n1,i,j);
		return 0;
	}
}

class game extends enemy
{
	int gameplay(ships s1, navy n1) throws java.lang.Exception
	{
		while(winner!=100)
		{
		System.out.println("Enter coordinates");
		fire(s1);
		target(n1);
		}
		return 0;
	}
}
class bgui
{
		grid g = new grid();
		ships s1 = new ships();
		navy n1 = new navy();
		game g1 = new game();
		public void go()
		{
		//Frame stuff
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Main Panel stuff
		JPanel panel = new JPanel();
		panel.setBackground(Color.red);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		frame.setContentPane(panel);
		
		//Left Panel stuf
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.red);
		panel1.setLayout(new GridLayout(40,40));
		panel.add(panel1);
		
		//Center Panel stuff
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.blue);
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		panel.add(panel2);
		
		//Show button
		JButton BeginButton = new JButton("Begin game");
		panel1.add(BeginButton);
		BeginButton.addActionListener(new Sbutton());
		BeginButton.setPreferredSize(new Dimension(500,500));
		
		//Set Some new button 
		JButton setShipButton = new JButton("Set ship ");
		panel1.add(setShipButton);
		setShipButton.addActionListener(new SSbutton());
		setShipButton.setPreferredSize(new Dimension(100,100));
		
		ArrayList<JButton> firelist;
		}
		
		//Show button Action
		
		class Sbutton implements ActionListener
		{
			public void actionPerformed(ActionEvent Event)
			{
				System.out.println("This is the layout of game.");
				g.emptyBoard();
				g.showBoard();
				s1.setpos();
			}
		}
		
		//Set navy  action 
		
		class SSbutton implements ActionListener
		{
			public void actionPerformed(ActionEvent Event)
			{
			n1.setNavy(1,1,0,3);
			}
		}
}
public class BSST
{
	public static void main (String[] args) throws java.lang.Exception
	{
		bgui swing1 = new bgui();
		swing1.go();
	}
}

