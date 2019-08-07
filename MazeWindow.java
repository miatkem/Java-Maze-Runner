import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;

/*MAZE GRAPHIC CLASS DRAWN ON A JPANEL*/
public class MazeWindow extends JPanel
{
    /*VARIABLES*/
    private static final boolean WALL=false;
    private static final boolean CELL=true;
    boolean[][] maze;
    boolean flashlightMode;
    Point player,exit;
    int height, width;
    
    public MazeWindow(boolean[][] maze, Point player, Point exit)
    {
        height=maze.length;
        width=maze[0].length;
        this.maze=maze;
        this.exit=exit;
        this.player=player;
        flashlightMode=false;
    }
    /*UPDATE DISPLAY WITH NEW INFORMATION*/
    public void setPoints(boolean[][] maze, Point player, Point exit)
    {
        height=maze.length;
        width=maze[0].length;
        this.maze = maze;
        this.exit=exit;
        this.player=player;
        repaint();
    }
    
    /*REPAINT METHOD*/
    public void paintComponent (Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK); //cover old frame with black rectangle
        g2.fill(new Rectangle2D.Double(0, 0, 700, 700));
        paintMaze(g2); //print new maze graphic
    }
    
    /*PAINT MAZE*/
    public void paintMaze(Graphics2D g2)
    {
        /*LOOP THROUGH MAZE 2D ARRAY*/
        for(int y = 0; y< maze.length; y++)
        {
            for(int x = 0; x<maze[0].length; x++)
            {
                /*DETERMINE COLOR*/
                if(player.getY()==y&&player.getX()==x) //player
                    g2.setPaint(Color.GREEN);
                else if(exit.getY()==y&&exit.getX()==x) //exit
                    g2.setPaint(Color.RED);
                else if(maze[x][y]==WALL) //wall
                    g2.setPaint(Color.BLACK);
                else if(flashlightMode&&(x>player.getX()+3||y>player.getY()+3||x<player.getX()-3||y<player.getY()-3)) //darkness
                    g2.setPaint(Color.BLACK);
                else //cell
                    g2.setPaint(Color.WHITE);
                /*PAINT GRID CELL*/
                g2.fill(new Rectangle2D.Double(x*(500.0/width),y*(500.0/height),(500.0/width),(500.0/height)));
            }
        }
    }
    
    public void setOriginalMode()
    {
        flashlightMode=false;
        repaint();
    }
    
    public void setFlashlightMode()
    {
        flashlightMode=true;
        repaint();
    }
    
    public void setPlayer(Point player)
    {
        this.player=player;
        repaint();
    }
}