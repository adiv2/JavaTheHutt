import java.io.*;
class grid
{
public int[][] board =  new int[9][9];
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
	
	static int luck,i,j;
	int toss()
	{
		luck = (int)(Math.random()*10);
		return 0;
	}
	int coordinates()
	{
		i = (int)(Math.random()*8);
		j = (int)(Math.random()*8);
		return 0;
	}
	
	int setShip(int size)
	{
		if(size>9)
		{
			System.out.println("Fuck off");
		}
		else
		{
		toss();
		coordinates();
		System.out.println(i+" "+j);
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
			if(check==size)
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
			{setShip(size);}
			
		}
		else
		{
			
			int check=0;
			int k;
			if(j<=(8-size))
			{
				for(k=j;k<(j+size);k++)
				{
					if(board[k][j]!=1){check++;}
				}
			}
			else
			{
				for(k=j;k>(j-size);k--)
				{
					if(board[k][j]!=1){check++;}
				}	
			}
			if(check==size)
			{
			if(j<=(8-size))
			{
				for(k=j;k<(j+size);k++)
				{
					board[k][j]=1;
				}
			}
			else
			{
				for(k=j;k>(j-size);k--)
				{
					board[k][j]=1;
				}	
			}
			}
			else
			{setShip(size);}
			
		}
	}
	return 0;
	}
}

class bombs extends ships
{
	private int i,j,rounds=0,h=0;;
	int fire(ships s1) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String aim = br.readLine();
        if((aim.length())>2){System.out.println("Error!"); fire(s1);}
        else
        i=  aim.charAt(0)- 64;
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
			}
			else
			{
				System.out.println("Miss");
				rounds++;
				h=0;
				health(s1);
				
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
			}
			else
			{
				System.out.println("Miss");
				rounds++;
				h=0;
				health(s1);
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
		}
		else
		{System.out.println(h+" health remaining"); fire(s1);}
		return 0;
	}
	
	int stats()
	{
		System.out.println("\n\n\nYOU WIN!! \nStats:\nNumber of bombs used: "+rounds+"\nAccuracy: "+12/rounds*100+"%\n");
	    return 0;
	}
}		
	

public class battleShip
{
	public static void main (String[] args) throws java.lang.Exception
	{
		grid g = new grid();
		g.emptyBoard();
		g.showBoard();
		ships s1 = new ships();
		s1.setShip(3);
		s1.showBoard();
		bombs b1 = new bombs();
		b1.fire(s1);
		s1.showBoard();
	}
}
