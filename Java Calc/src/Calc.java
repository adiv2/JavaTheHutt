import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

class CalcGUI
{
    private JFrame frame = new JFrame();
    private JPanel mainPanel;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a0Button;
    private JLabel display;
    private JButton addB;
    private JButton sub;
    private JButton mul;
    private JButton eq;
    private JButton div;

    private String displayStr = "";


    public CalcGUI()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
               JButton val = (JButton) actionEvent.getSource();
                displayStr = displayStr + val.getClientProperty("keyval");
                display.setText(displayStr);

            }
        };
        a1Button.addActionListener(listener);
        a2Button.addActionListener(listener);
        a3Button.addActionListener(listener);
        a4Button.addActionListener(listener);
        a5Button.addActionListener(listener);
        a6Button.addActionListener(listener);
        a7Button.addActionListener(listener);
        a8Button.addActionListener(listener);
        a9Button.addActionListener(listener);
        a0Button.addActionListener(listener);

        addB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
               //int a1 = Integer.parseInt(displayStr);
                displayStr=displayStr+" +";
                display.setText(displayStr);
            }
        });
        sub.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                displayStr=displayStr+" -";
                display.setText(displayStr);
            }
        });
        mul.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                displayStr=displayStr+" *";
                display.setText(displayStr);
            }
        });
        eq.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                try{calcFunc();}
                catch (Exception e){e.printStackTrace();}
                System.out.println("eq was pressed");
            }
        });
        div.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                displayStr=displayStr+" /";
                display.setText(displayStr);
            }
        });
    }

    void calcDisplay()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setVisible(true);
        display.setFont(new Font("Ubuntu",Font.BOLD,26));
        frame.setSize(400,400);
    }

    void calcFunc() throws Exception
    {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine eng = mgr.getEngineByName("JavaScript");
        String answer = String.valueOf(eng.eval(displayStr));
        System.out.println(eng.eval(displayStr));
        display.setText(answer);
        displayStr="";
    }
}

public class Calc
{

    public static void main(String[] args)
    {
        CalcGUI cg = new CalcGUI();
        cg.calcDisplay();
    }
}
