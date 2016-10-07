/*
 * BSSwing.java
 *
 * Copyright 2016 Aditya Gholba & Hamir Singh Shekhawat <aditya@aditya-ubuntu>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 *
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class grid
{
    int[][] board =  new int[9][9];
    int winner,loser;
    int emptyBoard()
    {
        int i,j;
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
        int i,j,columns=0;
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
    static private int i,j,luck;

    private int random()
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
            System.out.println("Invalid input");
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

    private int resetpos(int size)
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
    int rounds=0;
    private int acc=10;
    int enemyHealth=0;
    private int[][] board3 = new int[9][9];
    int fire(ships s1,int i, int j)
    {
        int xmaker=0;
        if((9>i) && (9>j) && (i>=0) && (j>=0))
        {
            if(s1.board[i][j]==1)
            {
                s1.board[i][j] =2;
                rounds++;
                enemyHealth=0;
                health(s1);
                board3[i][j]=5;
                xmaker=1;
            }
            else
            {
                rounds++;
                enemyHealth=0;
                health(s1);
                board3[i][j]=2;
            }
        }
        else
        {System.out.println("Error!");fire(s1,i,j);}
        if(xmaker==1)
        {return 1;}
        else
        {return 0;}
    }

    private int health(ships s1)
    {
        int a,b;
        for(a=0;a<9;a++)
        {
            for(b=0;b<9;b++)
            {
                if(s1.board[a][b]==1)
                {
                    enemyHealth++;
                }
            }
        }
        if(enemyHealth==0)
        {
            stats();
            winner=100;
        }
        else
        {System.out.println("Enemy health remaining: "+enemyHealth);}
        return 0;
    }

    private int stats()
    {
        //acc =1200/rounds;
        acc = (12/rounds)*100;
        System.out.println("\n\n\nYOU WIN!! \nStats:\nNumber of bombs used: "+rounds+"\nAccuracy: "+acc+"%");
        return 0;
    }
}

class navy extends bombs
{
    int setNavy(int size,int i,int j,int luck)
    {
            setShip(size, i, j, luck);
            showBoard();
        return 0;
    }

}

class enemy extends navy
{
    private int[][] board2 =  new int[9][9];
    private int i,j,rounds1;
    int yourHealth;
    private int hit(navy n1,int i,int j)
    {
        int a, b;
        for (a = 0; a < 9; a++) {
            for (b = 0; b < 9; b++) {
                if (n1.board[a][b] == 3)
                {
                    if(b<8 && b>0 && a!=8 && n1.board[a+1][b]!=1) {
                        if (n1.board[a][b + 1] == 1 && b < 8)
                        {
                            randomHit(n1, a, b + 1);
                            n1.board[a][b] = 6;
                            break;
                        }
                        else if (n1.board[a][b - 1] == 1 && b > 0)
                        {
                            randomHit(n1, a, b - 1);
                            n1.board[a][b] = 6;
                            break;
                        }
                    }
                    else if(a<8 && a>0 && b!=8 && n1.board[a][b+1]!=1)
                    {
                        if (n1.board[a + 1][b] == 1 && a < 8)
                        {
                        randomHit(n1, a + 1, b);
                            n1.board[a][b] = 6;
                        break;
                        }
                        else if (n1.board[a - 1][b] == 1 && a >0 )
                        {
                            randomHit(n1, a - 1, b);
                            n1.board[a][b] = 6;
                            break;
                        }
                    }

                }
            }
        }
        randomHit(n1, i, j);
        return 0;
    }
     private int  randomHit(navy n1,int i,int j)
     {  if(board2[i][j]==0)
        {
            System.out.println(i+" "+j);
            if(n1.board[i][j]==1)
            {
                System.out.println("Hit!");
                n1.board[i][j] =3;
                rounds1++;
                yourHealth=0;
                health1(n1);
                board2[i][j]=1;
            }
            else
            {
                System.out.println("Miss");
                n1.board[i][j] =2;
                rounds1++;
                yourHealth=0;
                health1(n1);
                board2[i][j]=1;
            }
        }
        else {target(n1);}
         System.out.println("YOUR SHIPS");
         n1.showBoard();
    return 0;
    }


    private int health1(navy n1)
    {
        int a,b;
        for(a=0;a<9;a++)
        {
            for(b=0;b<9;b++)
            {
                if(n1.board[a][b]==1)
                {
                    yourHealth++;
                }
            }
        }
        if(yourHealth==0)
        {
            stats1();
            winner=100;
            loser=100;
        }
        else
        {System.out.println("Your health remaining: "+yourHealth);}
        return 0;
    }

    private int stats1()
    {
        int acc1 =1200/rounds;
        System.out.println("\n\n\nYOU WIN!! \nStats:\nNumber of bombs used: "+rounds1+"\nAccuracy: "+acc1);
        return 0;
    }

    private int rand1()
    {
        i = (int)(Math.random()*9);
        j = (int)(Math.random()*9);
        return 0;
    }

    int target(navy n1)
    {
        rand1();
        hit(n1,i,j);
        return 0;
    }
}

class game extends enemy
{
    int gameplay(ships s1, navy n1,int i, int j)
    {
        int xmaker2 = 0;
        if (winner != 100) {
            System.out.println("Enter coordinates");
            if (fire(s1, i, j) == 1)
            {
                xmaker2 = 1;
            }
            target(n1);
        }
        if (xmaker2 == 1)
        {return 1;}
        else
        {return 0;}
    }
}

public class BSSwing
{
    private grid g = new grid();
    private ships s1 = new ships();
    private navy n1 = new navy();
    private game g1 = new game();
    private int luck;
    private JPanel panel;
    private JPanel LeftPanel;
    private JButton BeginButton;
    private JButton EButton;
    private JButton a0Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton b0Button;
    private JButton b1Button;
    private JButton b2Button;
    private JButton b3Button;
    private JButton b4Button;
    private JButton b5Button;
    private JButton b6Button;
    private JButton b7Button;
    private JButton b8Button;
    private JButton c0Button;
    private JButton c1Button;
    private JButton c2Button;
    private JButton c3Button;
    private JButton c4Button;
    private JButton c5Button;
    private JButton c6Button;
    private JButton c7Button;
    private JButton c8Button;
    private JButton d0Button;
    private JButton d1Button;
    private JButton d2Button;
    private JButton d3Button;
    private JButton d4Button;
    private JButton d5Button;
    private JButton d6Button;
    private JButton d7Button;
    private JButton d8Button;
    private JButton e0Button;
    private JButton e1Button;
    private JButton e2Button;
    private JButton e3Button;
    private JButton e4Button;
    private JButton e5Button;
    private JButton e6Button;
    private JButton e7Button;
    private JButton e8Button;
    private JButton f0Button;
    private JButton f1Button;
    private JButton f2Button;
    private JButton f3Button;
    private JButton f4Button;
    private JButton f5Button;
    private JButton f6Button;
    private JButton f7Button;
    private JButton f8Button;
    private JButton g0Button;
    private JButton g1Button;
    private JButton g2Button;
    private JButton g3Button;
    private JButton g4Button;
    private JButton g5Button;
    private JButton g6Button;
    private JButton g7Button;
    private JButton g8Button;
    private JButton h0Button;
    private JButton h1Button;
    private JButton h2Button;
    private JButton h3Button;
    private JButton h4Button;
    private JButton h5Button;
    private JButton h6Button;
    private JButton h7Button;
    private JButton h8Button;
    private JButton i0Button;
    private JButton i1Button;
    private JButton i2Button;
    private JButton i3Button;
    private JButton i4Button;
    private JButton i5Button;
    private JButton i6Button;
    private JButton i7Button;
    private JButton i8Button;
    private JLabel fireLabel;
    private JLabel enemyHealthButton;
    private JLabel yourHealthButton;
    private JLabel result;
    private JPanel FirstPanel;
    private JLabel introText;
    private JLabel introText2;
    private JPanel buttonPanel;
    private JPanel buttonPanel1;
    private JPanel RightPanel;
    private JLabel SetShipText;
    private JButton a0Button1;
    private JButton a1Button1;
    private JButton a2Button1;
    private JButton a3Button1;
    private JButton a4Button1;
    private JButton a5Button1;
    private JButton a6Button1;
    private JButton a7Button1;
    private JButton a8Button1;
    private JButton b0Button1;
    private JButton b1Button1;
    private JButton b2Button1;
    private JButton b3Button1;
    private JButton b4Button1;
    private JButton b5Button1;
    private JButton b6Button1;
    private JButton b7Button1;
    private JButton b8Button1;
    private JButton c0Button1;
    private JButton c1Button1;
    private JButton c2Button1;
    private JButton c3Button1;
    private JButton c4Button1;
    private JButton c5Button1;
    private JButton c6Button1;
    private JButton c7Button1;
    private JButton c8Button1;
    private JButton d0Button1;
    private JButton d1Button1;
    private JButton d2Button1;
    private JButton d3Button1;
    private JButton d4Button1;
    private JButton d5Button1;
    private JButton d6Button1;
    private JButton d7Button1;
    private JButton d8Button1;
    private JButton e0Button1;
    private JButton e1Button1;
    private JButton e2Button1;
    private JButton e3Button1;
    private JButton e4Button1;
    private JButton e5Button1;
    private JButton e6Button1;
    private JButton e7Button1;
    private JButton e8Button1;
    private JButton f0Button1;
    private JButton f1Button1;
    private JButton f2Button1;
    private JButton f3Button1;
    private JButton f4Button1;
    private JButton f5Button1;
    private JButton f6Button2;
    private JButton f7Button1;
    private JButton f8Button1;
    private JButton g0Button1;
    private JButton g1Button1;
    private JButton g2Button1;
    private JButton g3Button1;
    private JButton g4Button1;
    private JButton g5Button1;
    private JButton g6Button1;
    private JButton g7Button1;
    private JButton g8Button1;
    private JButton h0Button1;
    private JButton h1Button1;
    private JButton h2Button1;
    private JButton h3Button1;
    private JButton h4Button1;
    private JButton h5Button1;
    private JButton h6Button1;
    private JButton h7Button1;
    private JButton h8Button1;
    private JButton i0Button1;
    private JButton i1Button1;
    private JButton i2Button1;
    private JButton i3Button1;
    private JButton i4Button1;
    private JButton i5Button1;
    private JButton i6Button1;
    private JButton i7Button1;
    private JButton i8Button1;
    private JComboBox orientation;
    private JLabel orientationText;
    private JLabel result1;
    private JLabel instructions1;
    private JLabel instructions2;
    private JLabel instructions3;
    private JLabel instructions4;
    private JButton nextButton;
    private JLabel shipSize;
    private JLabel author;
    private JFrame frame = new JFrame();


    private BSSwing()
    {
        EButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.exit(0);
            }
        });
        BeginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.out.println("This is the layout of game.");
                g.emptyBoard();
                g.showBoard();
                s1.setpos();
                s1.showBoard();
                yourHealthButton.setVisible(true);
                enemyHealthButton.setVisible(true);
                BeginButton.setVisible(false);
                BeginButton.setEnabled(false);
                LeftPanel.setVisible(false);
                RightPanel.setVisible(true);
                FirstPanel.setEnabled(false);
                FirstPanel.setVisible(false);
            }
        });


        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                JButton jb = (JButton) actionEvent.getSource();
                int i = (int) jb.getClientProperty("i_co");
                int j = (int) jb.getClientProperty("j_co");
                if (g1.gameplay(s1, n1, i, j) == 1) {
                    jb.setText("X");
                    jb.setBackground(Color.red);
                } else {
                    jb.setBackground(Color.darkGray);
                }
                jb.setEnabled(false);
                String h = String.valueOf(g1.enemyHealth);
                String h1 = String.valueOf(g1.yourHealth);
                enemyHealthButton.setText("Enemy health is :" + h);
                yourHealthButton.setText("Your health is  :" + h1);
                if (g1.winner == 100 && g1.loser != 100) {
                    result.setVisible(true);
                    yourHealthButton.setVisible(false);
                    enemyHealthButton.setVisible(false);
                } else if (g1.winner == 100 && g1.loser == 100) {
                    result1.setVisible(true);
                    yourHealthButton.setVisible(false);
                    enemyHealthButton.setVisible(false);
                }
            }
        };
        a0Button.addActionListener(listener);
        a1Button.addActionListener(listener);
        a2Button.addActionListener(listener);
        a3Button.addActionListener(listener);
        a4Button.addActionListener(listener);
        a5Button.addActionListener(listener);
        a6Button.addActionListener(listener);
        a7Button.addActionListener(listener);
        a8Button.addActionListener(listener);
        b0Button.addActionListener(listener);
        b1Button.addActionListener(listener);
        b2Button.addActionListener(listener);
        b3Button.addActionListener(listener);
        b4Button.addActionListener(listener);
        b5Button.addActionListener(listener);
        b6Button.addActionListener(listener);
        b7Button.addActionListener(listener);
        b8Button.addActionListener(listener);
        c0Button.addActionListener(listener);
        c1Button.addActionListener(listener);
        c2Button.addActionListener(listener);
        c3Button.addActionListener(listener);
        c4Button.addActionListener(listener);
        c5Button.addActionListener(listener);
        c6Button.addActionListener(listener);
        c7Button.addActionListener(listener);
        c8Button.addActionListener(listener);
        d0Button.addActionListener(listener);
        d1Button.addActionListener(listener);
        d2Button.addActionListener(listener);
        d3Button.addActionListener(listener);
        d4Button.addActionListener(listener);
        d5Button.addActionListener(listener);
        d6Button.addActionListener(listener);
        d7Button.addActionListener(listener);
        d8Button.addActionListener(listener);
        e0Button.addActionListener(listener);
        e1Button.addActionListener(listener);
        e2Button.addActionListener(listener);
        e3Button.addActionListener(listener);
        e4Button.addActionListener(listener);
        e5Button.addActionListener(listener);
        e6Button.addActionListener(listener);
        e7Button.addActionListener(listener);
        e8Button.addActionListener(listener);
        f0Button.addActionListener(listener);
        f1Button.addActionListener(listener);
        f2Button.addActionListener(listener);
        f3Button.addActionListener(listener);
        f4Button.addActionListener(listener);
        f5Button.addActionListener(listener);
        f6Button.addActionListener(listener);
        f7Button.addActionListener(listener);
        f8Button.addActionListener(listener);
        g0Button.addActionListener(listener);
        g1Button.addActionListener(listener);
        g2Button.addActionListener(listener);
        g3Button.addActionListener(listener);
        g4Button.addActionListener(listener);
        g5Button.addActionListener(listener);
        g6Button.addActionListener(listener);
        g7Button.addActionListener(listener);
        g8Button.addActionListener(listener);
        h0Button.addActionListener(listener);
        h1Button.addActionListener(listener);
        h2Button.addActionListener(listener);
        h3Button.addActionListener(listener);
        h4Button.addActionListener(listener);
        h5Button.addActionListener(listener);
        h6Button.addActionListener(listener);
        h7Button.addActionListener(listener);
        h8Button.addActionListener(listener);
        i0Button.addActionListener(listener);
        i1Button.addActionListener(listener);
        i2Button.addActionListener(listener);
        i3Button.addActionListener(listener);
        i4Button.addActionListener(listener);
        i5Button.addActionListener(listener);
        i6Button.addActionListener(listener);
        i7Button.addActionListener(listener);
        i8Button.addActionListener(listener);

        ActionListener listener1 = new ActionListener()
        {
            int size = 3;
            int nextStep = 0;

            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                JButton jb = (JButton) actionEvent.getSource();
                int i = (int) jb.getClientProperty("i_co");
                int j = (int) jb.getClientProperty("j_co");
                n1.setNavy(size, i, j, luck);
                jb.setBackground(Color.red);
                Component[] test = buttonPanel1.getComponents();
                if (size == 3 && luck == 0) {
                    for (int a = 0; a < 81; a++) {
                        int inneri = (Integer) ((JButton) test[a]).getClientProperty("i_co");
                        int innerj = (Integer) ((JButton) test[a]).getClientProperty("j_co");
                        if (inneri == i && innerj == j && j <= 6) {
                            (test[a + 1]).setBackground(Color.red);
                            (test[a + 2]).setBackground(Color.red);
                            //System.out.println(((JButton)test[a]).getClientProperty("i_co")+" "+((JButton)test[a]).getClientProperty("j_co"));
                        } else if ((inneri == i && innerj == j && j > 6)) {
                            (test[a - 1]).setBackground(Color.red);
                            (test[a - 2]).setBackground(Color.red);
                        }
                    }
                }
                if (size == 4 && luck == 0) {
                    for (int a = 0; a < 81; a++) {
                        int inneri = (Integer) ((JButton) test[a]).getClientProperty("i_co");
                        int innerj = (Integer) ((JButton) test[a]).getClientProperty("j_co");
                        if (inneri == i && innerj == j && j <= 5) {
                            (test[a + 1]).setBackground(Color.red);
                            (test[a + 2]).setBackground(Color.red);
                            (test[a + 3]).setBackground(Color.red);
                        } else if (inneri == i && innerj == j && j > 5) {
                            (test[a - 1]).setBackground(Color.red);
                            (test[a - 2]).setBackground(Color.red);
                            (test[a - 3]).setBackground(Color.red);
                        }
                    }
                }
                if (size == 5 && luck == 0) {
                    for (int a = 0; a < 81; a++) {
                        int inneri = (Integer) ((JButton) test[a]).getClientProperty("i_co");
                        int innerj = (Integer) ((JButton) test[a]).getClientProperty("j_co");
                        if (inneri == i && innerj == j && j <= 4) {
                            (test[a + 1]).setBackground(Color.red);
                            (test[a + 2]).setBackground(Color.red);
                            (test[a + 3]).setBackground(Color.red);
                            (test[a + 4]).setBackground(Color.red);
                            //System.out.println(((JButton)test[a]).getClientProperty("i_co")+" "+((JButton)test[a]).getClientProperty("j_co"));
                        } else if (inneri == i && innerj == j && j > 4) {
                            (test[a - 1]).setBackground(Color.red);
                            (test[a - 2]).setBackground(Color.red);
                            (test[a - 3]).setBackground(Color.red);
                            (test[a - 4]).setBackground(Color.red);
                        }
                    }
                }

                if (size == 3 && luck == 1) {
                    for (int a = 0; a < 81; a++) {
                        int inneri = (Integer) ((JButton) test[a]).getClientProperty("i_co");
                        int innerj = (Integer) ((JButton) test[a]).getClientProperty("j_co");
                        if (inneri == i && innerj == j && i <= 6 && i != 0) {
                            (test[a + 9]).setBackground(Color.red);
                            (test[a + 18]).setBackground(Color.red);
                            //System.out.println(((JButton)test[a]).getClientProperty("i_co")+" "+((JButton)test[a]).getClientProperty("j_co"));
                        } else if ((inneri == i && innerj == j && i > 6 && i != 0)) {
                            (test[a - 9]).setBackground(Color.red);
                            (test[a - 18]).setBackground(Color.red);
                        } else if (inneri == i && innerj == j && i == 0) {
                            //System.out.println(a);
                            (test[a - 72]).setBackground(Color.red);
                            (test[a - 63]).setBackground(Color.red);
                        }
                    }
                }
                if (size == 4 && luck == 1) {
                    for (int a = 0; a < 81; a++) {
                        int inneri = (Integer) ((JButton) test[a]).getClientProperty("i_co");
                        int innerj = (Integer) ((JButton) test[a]).getClientProperty("j_co");
                        if (inneri == i && innerj == j && i <= 5 && i != 0) {
                            (test[a + 9]).setBackground(Color.red);
                            (test[a + 18]).setBackground(Color.red);
                            (test[a + 27]).setBackground(Color.red);
                            //System.out.println(((JButton)test[a]).getClientProperty("i_co")+" "+((JButton)test[a]).getClientProperty("j_co"));
                        } else if ((inneri == i && innerj == j && i > 5 && i != 0)) {
                            (test[a - 9]).setBackground(Color.red);
                            (test[a - 18]).setBackground(Color.red);
                            (test[a - 27]).setBackground(Color.red);
                        } else if (inneri == i && innerj == j && i == 0) {
                            //System.out.println(a);
                            (test[a - 72]).setBackground(Color.red);
                            (test[a - 63]).setBackground(Color.red);
                            (test[a - 54]).setBackground(Color.red);
                        }
                    }
                }
                if (size == 5 && luck == 1) {
                    for (int a = 0; a < 81; a++) {
                        int inneri = (Integer) ((JButton) test[a]).getClientProperty("i_co");
                        int innerj = (Integer) ((JButton) test[a]).getClientProperty("j_co");
                        if (inneri == i && innerj == j && i <= 4 && i != 0) {
                            (test[a + 9]).setBackground(Color.red);
                            (test[a + 18]).setBackground(Color.red);
                            (test[a + 27]).setBackground(Color.red);
                            (test[a + 36]).setBackground(Color.red);
                            //System.out.println(((JButton)test[a]).getClientProperty("i_co")+" "+((JButton)test[a]).getClientProperty("j_co"));
                        } else if ((inneri == i && innerj == j && i > 4 && i != 0)) {
                            (test[a - 9]).setBackground(Color.red);
                            (test[a - 18]).setBackground(Color.red);
                            (test[a - 27]).setBackground(Color.red);
                            (test[a - 36]).setBackground(Color.red);
                        } else if (inneri == i && innerj == j && i == 0) {
                            //System.out.println(a);
                            (test[a - 72]).setBackground(Color.red);
                            (test[a - 63]).setBackground(Color.red);
                            (test[a - 54]).setBackground(Color.red);
                            (test[a - 45]).setBackground(Color.red);
                        }
                    }
                }
                size++;
                nextStep++;
                if (size < 6) {
                    shipSize.setText("Ship Size " + (size));
                } else {
                    shipSize.setVisible(false);
                }
                if (nextStep == 3) {

                    nextButton.setVisible(true);
                }
            }
        };
        b0Button1.addActionListener(listener1);
        b1Button1.addActionListener(listener1);
        b2Button1.addActionListener(listener1);
        b3Button1.addActionListener(listener1);
        b4Button1.addActionListener(listener1);
        b5Button1.addActionListener(listener1);
        b6Button1.addActionListener(listener1);
        b7Button1.addActionListener(listener1);
        b8Button1.addActionListener(listener1);
        c0Button1.addActionListener(listener1);
        c1Button1.addActionListener(listener1);
        c2Button1.addActionListener(listener1);
        c3Button1.addActionListener(listener1);
        c4Button1.addActionListener(listener1);
        c5Button1.addActionListener(listener1);
        c6Button1.addActionListener(listener1);
        c7Button1.addActionListener(listener1);
        c8Button1.addActionListener(listener1);
        d0Button1.addActionListener(listener1);
        d1Button1.addActionListener(listener1);
        d2Button1.addActionListener(listener1);
        d3Button1.addActionListener(listener1);
        d4Button1.addActionListener(listener1);
        d5Button1.addActionListener(listener1);
        d6Button1.addActionListener(listener1);
        d7Button1.addActionListener(listener1);
        d8Button1.addActionListener(listener1);
        e0Button1.addActionListener(listener1);
        e1Button1.addActionListener(listener1);
        e2Button1.addActionListener(listener1);
        e3Button1.addActionListener(listener1);
        e4Button1.addActionListener(listener1);
        e5Button1.addActionListener(listener1);
        e6Button1.addActionListener(listener1);
        e7Button1.addActionListener(listener1);
        e8Button1.addActionListener(listener1);
        f0Button1.addActionListener(listener1);
        f1Button1.addActionListener(listener1);
        f2Button1.addActionListener(listener1);
        f3Button1.addActionListener(listener1);
        f4Button1.addActionListener(listener1);
        f5Button1.addActionListener(listener1);
        f6Button2.addActionListener(listener1);
        f7Button1.addActionListener(listener1);
        f8Button1.addActionListener(listener1);
        g0Button1.addActionListener(listener1);
        g1Button1.addActionListener(listener1);
        g2Button1.addActionListener(listener1);
        g3Button1.addActionListener(listener1);
        g4Button1.addActionListener(listener1);
        g5Button1.addActionListener(listener1);
        g6Button1.addActionListener(listener1);
        g7Button1.addActionListener(listener1);
        g8Button1.addActionListener(listener1);
        h0Button1.addActionListener(listener1);
        h1Button1.addActionListener(listener1);
        h2Button1.addActionListener(listener1);
        h3Button1.addActionListener(listener1);
        h4Button1.addActionListener(listener1);
        h5Button1.addActionListener(listener1);
        h6Button1.addActionListener(listener1);
        h7Button1.addActionListener(listener1);
        h8Button1.addActionListener(listener1);
        i0Button1.addActionListener(listener1);
        i1Button1.addActionListener(listener1);
        i2Button1.addActionListener(listener1);
        i3Button1.addActionListener(listener1);
        i4Button1.addActionListener(listener1);
        i5Button1.addActionListener(listener1);
        i6Button1.addActionListener(listener1);
        i7Button1.addActionListener(listener1);
        i8Button1.addActionListener(listener1);
        a0Button1.addActionListener(listener1);
        a1Button1.addActionListener(listener1);
        a2Button1.addActionListener(listener1);
        a3Button1.addActionListener(listener1);
        a4Button1.addActionListener(listener1);
        a5Button1.addActionListener(listener1);
        a6Button1.addActionListener(listener1);
        a7Button1.addActionListener(listener1);
        a8Button1.addActionListener(listener1);
        orientation.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                luck = orientation.getSelectedIndex();
            }
        });

        nextButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                LeftPanel.setVisible(true);
                RightPanel.setVisible(false);
            }
        });
    }

    private void gui()
    {
        frame.setVisible(true);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setContentPane(panel);

        //Visibility
        yourHealthButton.setVisible(false);
        enemyHealthButton.setVisible(false);
        result.setVisible(false);
        result1.setVisible(false);
        LeftPanel.setVisible(false);
        RightPanel.setVisible(false);
        nextButton.setVisible(false);

        //Font Size
        fireLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        enemyHealthButton.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        yourHealthButton.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        result.setFont(new Font("Ubuntu", Font.PLAIN, 40));
        result1.setFont(new Font("Ubuntu", Font.PLAIN, 40));
        orientationText.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        SetShipText.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        introText.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        introText2.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        instructions1.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        instructions2.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        instructions3.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        instructions4.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        shipSize.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        author.setFont(new Font("Ubuntu", Font.PLAIN, 18));

    }

    public static void main(String[] args)
    {
        BSSwing bs = new BSSwing();
        bs.gui();
    }
}
