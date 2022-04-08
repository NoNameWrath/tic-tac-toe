import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.io.*;
class TicTacToe extends JPanel 
{
    File file=new File("A:/leaderboard.txt");
    char choice='X';
    String name;
    String name2;
    JButton[] buttons=new JButton[9];
    Font newButtonFont=new Font("Ariel", Font. BOLD,16);
    TicTacToe() throws IOException 
    {
        FileWriter fw=new FileWriter(file);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter pw=new PrintWriter(bw);
        JFrame f;   
        f=new JFrame();
        JFrame y=new JFrame();
        name=JOptionPane.showInputDialog(f,"Enter Name of Player 1"); 
        if(name==null)
        {
            JOptionPane.showMessageDialog(f,"Game over"); 
            System.exit(0);
        }
        name2=JOptionPane.showInputDialog(y,"Enter name of player 2");
        if(name2==null)
        {
            JOptionPane.showMessageDialog(f,"Game over"); 
            System.exit(0);
        }
        setLayout(new GridLayout(3,3));
        createButton();
    }

    public void createButton()throws IOException
    {
        for(int i=0;i<9;i++)
        {
            buttons[i]=new JButton();
            buttons[i].setText(" ");
            buttons[i].setBackground(Color.white);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        JButton buttonClicked=(JButton)e.getSource(); 
                        buttonClicked.setText(String.valueOf(choice));
                        buttonClicked.setFont(new Font("Arial", Font.PLAIN, 140));
                        if(choice=='X') 
                        {
                            choice='O';
                            buttonClicked.setBackground(Color.white);
                            buttonClicked.setForeground(Color.GREEN);
                        }
                        else 
                        {
                            choice='X';
                            buttonClicked.setBackground(Color.white);
                            buttonClicked.setForeground(Color.RED);
                        }
                        display();
                    }
                });
            add(buttons[i]);
        }
    }

    void display()
    {
        if(WinCheck()==true) 
        {
            int result=0;
            if(choice=='X') 
                choice='O';
            else 
                choice='X';
            JOptionPane pane=new JOptionPane();
            if(choice=='O')
                result=JOptionPane.showConfirmDialog(pane,name2+" wins click yes if you want to play again","Game over",JOptionPane.YES_NO_OPTION);
            else if(choice=='X')
                result=JOptionPane.showConfirmDialog(pane, name+" wins click yes if you want to play again","Game over",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) 
                reset();
            else 
                System.exit(0);
        }
        else if(Drawcheck()==true)
        {
            JOptionPane pane=new JOptionPane();
            int result=JOptionPane.showConfirmDialog(pane, "Draw click yes if you want to play again ","Game over", JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION)  
                reset();
            else 
                System.exit(0);
        }
    }

    void reset() 
    {
        choice='X';
        for(int i=0;i<9;i++) 
        {
            buttons[i].setText(" ");
            buttons[i].setBackground(Color.white);
        }	
    }

    boolean Drawcheck() 
    {
        boolean f=true;
        for(int i=0;i<9;i++)
        {
            if(buttons[i].getText().charAt(0)==' ') 
            {
                f=false;
            }
        }
        return f;
    }

    boolean WinCheck() 
    {
        if(checkRow()==true||checkColumn()==true||checkDiagonals()==true) 
            return true;
        else 
            return false;
    }

    boolean checkRow() 
    {
        int i=0;
        for(int j=0;j<3;j++) 
        {
            if(buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) && buttons[i].getText().charAt(0) != ' ') //getting infor from button using .getText()
            {
                return true;
            }
            i=i+3;
        }
        return false;
    }

    boolean checkColumn() 
    {
        int i=0;
        for(int j=0;j<3;j++) 
        {
            if(buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText())&& buttons[i].getText().charAt(0) != ' ') 
            {
                return true;
            }
            i++;
        }
        return false;	
    }

    boolean checkDiagonals() 
    {
        if(buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())&& buttons[0].getText().charAt(0) !=' ')
            return true;
        else if(buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())&& buttons[2].getText().charAt(0) !=' ') 
            return true;
        else 
            return false;
    }
    void copy(String file1, String file2)throws IOException
    {
        FileReader fr=new FileReader(file1);
        BufferedReader br=new BufferedReader(fr);
        FileWriter fw=new FileWriter(file2,false);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter pw=new PrintWriter(bw);
        String name;
        while((name=br.readLine())!=null)
        {
            pw.println(name);
        }
        br.close();
        fr.close();
        pw.close();
        bw.close();
        fw.close();
    }
    void search(File f, String sn)throws IOException
    {
        FileWriter fw=new FileWriter(f);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter pw=new PrintWriter(bw);
        Scanner inp=new Scanner(f);
        String name;
        int age;
        boolean eof=true;
        try
        {
            while(eof)
            {
                name=inp.next(); 
                age=inp.nextInt();
                if(name.equalsIgnoreCase(sn))
                {
                   pw.println(name+" "+(age+1));
                }
                else
                pw.println(name+" "+age);
            }
            inp.close();
        }
        catch(Exception e)
        {
            System.out.println("- End of file encountered - ");
            eof=false;
        }
    }
    public static void main() throws IOException
    {
        JFrame window = new JFrame("Abejo-Tic Tac Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe());
        window.setBounds(1000,1000,1000,1000);
        window.setVisible(true);
        window.setLocationRelativeTo(null); 
    }
}