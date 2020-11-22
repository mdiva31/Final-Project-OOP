import java.awt.*;
import javax.swing.*;

public class Board extends JPanel{
    private ImageIcon titleImage;
    private int[] snakexlength=new int[750];
    private int[] snakeylength=new int[750];

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon mulutkanan;
    private ImageIcon mulutkiri;
    private ImageIcon mulutatas;
    private ImageIcon mulutbawah;
    private ImageIcon gambarular;

    private Timer timer;
    private int delay=100;

    private int lengthofsnake = 5;
    private int movea = 0;

    public Board()
    {

    }   

    public void paint(Graphics g)
    {
            if(movea == 0)
        {
            snakexlength[0] = 700;
            snakexlength[1] = 675;
            snakexlength[2] = 650;
            snakexlength[3] = 625;
            snakexlength[4] = 600;
            snakeylength[0] = 330;
            snakeylength[1] = 330;
            snakeylength[2] = 330;
            snakeylength[3] = 330;
            snakeylength[4] = 330;
        }

        g.setColor(Color.blue);
        g.drawRect(0,10,2480,85);
        titleImage = new ImageIcon("images/snake-title.jpg");
        titleImage.paintIcon(this, g, 25, 11);
        titleImage = new ImageIcon("images/snaketitle.jpg");
        titleImage.paintIcon(this, g, 450, 11);
        titleImage = new ImageIcon("images/Qwerty.png");
        titleImage.paintIcon(this, g, 875, 11);
        g.setColor(Color.blue);
        g.drawRect(0,74,2480,577);
        g.setColor(Color.white);
        g.fillRect(0,75,2480,625);

        mulutkanan= new ImageIcon("images/rightmouth.png");
        mulutkanan.paintIcon(this,g,snakexlength[0], snakeylength[0]);

        for(int a=0;a<lengthofsnake;a++)
        {
            if(a==0 && right)  
            {
                mulutkanan= new ImageIcon("images/rightmouth.png");
                mulutkanan.paintIcon(this,g,snakexlength[a], snakeylength[a]);
            } 

            if(a==0 && left) 
            {
                mulutkiri = new ImageIcon("images/leftmouth.png");
                mulutkiri.paintIcon(this,g,snakexlength[a], snakeylength[a]);
            } 

            if(a==0 && up) 
            {
                mulutatas= new ImageIcon("images/upmouth.png");
                mulutatas.paintIcon(this,g,snakexlength[a], snakeylength[a]);
            }

            if(a==0 && down) 
            {
                mulutbawah= new ImageIcon("images/downmouth.png");
                mulutbawah.paintIcon(this,g,snakexlength[a], snakeylength[a]);
            } 

            if (a!=0)
            {
                gambarular = new ImageIcon("images/snakeimage.png");
                gambarular.paintIcon(this,g,snakexlength[a], snakeylength[a]);
            }
        }
        g.dispose();
    }
    
}