import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
/*
 *
 * Copyright 2016 Aditya Gholba <aditya@aditya-ubuntu> created on 3/4/16
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
class DiscoBall
{
//Members
    int r,b,g;
//GUI stuff

    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel dText;
    private JPanel bottomPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton exit;
    private JButton button6;
    private JLabel div1;
    private JLabel div2;
    private JLabel waste1;
    private JLabel waste2;
    private JFrame frame= new JFrame();

    public DiscoBall()
    {
        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.exit(0);
            }
        });
    }

    public void radomizer()
    {
        r = (int)(Math.random()*255);
        b = (int)(Math.random()*255);
        g = (int)(Math.random()*255);
    }
    public void dj()
    {
        int ir = (int)(Math.random()*255);
        int ig = (int)(Math.random()*255);
        int ib = (int)(Math.random()*255);
        Color dTextColor =  new Color(ir,ig,ib);
        int dt = dTextColor.hashCode();
        String dText1 = String.format("#%06X", (0xFFFFFF & dt));
        dText.setText(dText1);
        dText.setForeground(dTextColor);
        radomizer();
        Color color1 = new Color(r,g,b);
        radomizer();
        Color color2 = new Color(r,g,b);
        radomizer();
        Color color3 = new Color(r,g,b);
        radomizer();
        Color color4 = new Color(r,g,b);
        radomizer();
        Color color5 = new Color(r,g,b);
        Color[] colors = {color1,color2,color3,color4,color5,dTextColor};
        int a,b,c,d,e,f;
        a = (int) (Math.random() * 4);
        b = (int) (Math.random() * 4);
        c = (int) (Math.random() * 4);
        d = (int) (Math.random() * 4);
        e = (int) (Math.random() * 4);
        f = (int) (Math.random() * 4);
        while(true)
        {
            if(a!=b && a!=c && a!=d && a!=e && a!=f && b!=c && b!=d && b!=e && b!=f && c!=d && c!=e && c!=f && d!=e && d!=f && e!=f)
            {break;}
            else
            {
                a = (int) (Math.random() * colors.length);
                b = (int) (Math.random() * colors.length);
                c = (int) (Math.random() * colors.length);
                d = (int) (Math.random() * colors.length);
                e = (int) (Math.random() * colors.length);
                f = (int) (Math.random() * colors.length);
            }
        }
        int h= Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int m = Calendar.getInstance().get(Calendar.MINUTE);
        int s = Calendar.getInstance().get(Calendar.SECOND);

        button1.setBackground(colors[a]);
        button2.setBackground(colors[b]);
        button3.setBackground(colors[c]);
        button4.setBackground(colors[d]);
        button5.setBackground(colors[e]);
        button6.setBackground(colors[f]);

        button1.setText(Integer.toString((h-(h%10))/10));
        button2.setText(Integer.toString(h%10));
        button3.setText(Integer.toString((m-(m%10))/10));
        button4.setText(Integer.toString(m%10));
        button5.setText(Integer.toString((s-(s%10))/10));
        button6.setText(Integer.toString(s%10));
    }

    public void disco()

    {
        frame.setVisible(true);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);

        dText.setFont(new Font("Ubuntu",Font.PLAIN,120));
        button1.setFont(new Font("Ubuntu",Font.BOLD,120));
        button2.setFont(new Font("Ubuntu",Font.BOLD,120));
        button3.setFont(new Font("Ubuntu",Font.BOLD,120));
        button4.setFont(new Font("Ubuntu",Font.BOLD,120));
        button5.setFont(new Font("Ubuntu",Font.BOLD,120));
        button6.setFont(new Font("Ubuntu",Font.BOLD,120));
        div1.setFont(new Font("Ubuntu",Font.BOLD,120));
        div2.setFont(new Font("Ubuntu",Font.BOLD,120));
        dj();
    }

}

public class DiscoHexClock extends DiscoBall
{
    public static void main(String[] args)
    {
        DiscoHexClock play = new DiscoHexClock();
        while(true)
        {
            play.disco();
            try{Thread.sleep(1000);}
            catch (Exception e){}
        }
    }
}
