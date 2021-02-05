package gameoflife;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Panel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
    
    int width = 1300; 
    int height = 700;
    int pixsize = 10;
    
    int xwidth = width/pixsize;
    int xheight = height/pixsize;
    int[][] spatial = new int[xwidth][xheight];
    int[][] afterspatial = new int[xwidth][xheight];
    boolean begin = true;
    boolean clicked = false;
    
    
    public Panel(){
        setSize(width,height);
        setBackground(Color.BLACK);
        setLayout(null);
        
        new Timer(80,this).start();
        addMouseMotionListener(this);
        addMouseListener(this);
        
        
       }
public void paintComponent(Graphics g){
    super.paintComponent(g);
   g.setColor(Color.GRAY);
    spacegrid(g);
    spawn(g);
    display(g);
}

private void spacegrid(Graphics g){
    for(int i = 0;i<spatial.length;i++){
        g.drawLine(0,pixsize*i,width,i*pixsize);
        g.drawLine(pixsize*i,0,i*pixsize,height);
    }
        

}

private void spawn(Graphics g){
    if(begin){
         for(int i = 0;i<xwidth;i++){
             for(int k =0;k<xheight;k++){
                 if((int)(Math.random()*5)==0){
                     afterspatial[i][k]=1;
               
                 }
             }
        }
         begin  = false;
    }
}
private void display(Graphics g){
    g.setColor(Color.WHITE);
    copyspatial();
    
     for(int i = 0;i<xwidth;i++){
             for(int k =0;k<xheight;k++){
                 if(spatial[i][k]==1)
                 g.fillRect(i*pixsize,k*pixsize,pixsize,pixsize);
                 
                }
             }
        }


private int lookat(int x,int y){
    int living = 0;
    //look at cells around the selected one
    living +=spatial[(x+xwidth-1) % xwidth][(y+xheight-1) % xheight];
    living += spatial[(x+xwidth-1) % xwidth][(y+xheight-1)% xheight];
    
    living += spatial[(x+xwidth+1)%xwidth][(y+xheight-1)% xheight];
    living += spatial[(x+xwidth-1)%xwidth][(y+xheight)% xheight];
    
    living += spatial[(x+xwidth+1)%xwidth][(y+xheight)% xheight];
     living +=spatial[(x+xwidth-1)%xwidth][(y+xheight+1)% xheight];
     
    living += spatial[(x + xwidth)%xwidth][(y+xheight+1)% xheight];
    living += spatial[(x+xwidth+1)%xwidth][(y+xheight+1)% xheight];
    
    return living;
}
public void actionPerformed(ActionEvent e){

   int living;
    for(int i = 0;i<xwidth;i++){
             for(int k =0;k<xheight;k++){
                 living = lookat(i,k);
                  if(living ==3){
                  afterspatial[i][k]=1;
                  }
                  else if  ((living ==2) && spatial[i][k]==1){
                      afterspatial[i][k]=1;
                }
                  else{
                      afterspatial[i][k]=0;
             }
          }
    }    
    repaint();

}
private void copyspatial(){
     for(int i = 0;i<xwidth;i++){
             for(int k =0;k<xheight;k++){
                 spatial[i][k] = afterspatial[i][k];
                }
             }
}

public void mouseDragged(MouseEvent e ){
    int x = e.getX()/pixsize;
    int y = e.getX()/pixsize;
    
    if(spatial[x][y]==0){
        afterspatial[x][y] = 1;
    }
    else if (spatial[x][y]==1){
        afterspatial[x][y]=0;
    }
    
}
    
 public void mouseMoved(MouseEvent e){   
     
     
 }
 public void mouseClicked(MouseEvent e){
     

     
 }
 
 public void mousePressed(MouseEvent e){
         int x = e.getX()/pixsize;
    int y = e.getX()/pixsize;
    
    clicked = true;
    
    if(spatial[x][y]==0 && clicked){
        afterspatial[x][y] = 1;
    }
    else if (spatial[x][y]==1 && clicked){
        afterspatial[x][y]=0;
    }
    
     
     
 }
 
 public void mouseReleased(MouseEvent e ){
     clicked = false;
 }
 
 public void mouseEntered(MouseEvent e){
 }
 public void mouseExited(MouseEvent e){
 }
 

}


