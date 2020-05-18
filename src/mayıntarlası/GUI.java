package mayıntarlası;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;





public class GUI extends JFrame {
    
    public boolean resetter=false;
    
    int bosluk=5;
    int yakin = 0;
    
    Date startDate= new Date();        //timer için gerekli olan nesneleri tanımlıyoruz.
    Date endDate; 
    
    public int mx= -100;
    public int my = -100;
    
    public boolean flagger=false;
    
    public int gülücükX=605;
    public int gülücükY=5;
    
    public int bayrakciX=445;           //bunlar da bayrak gülücük ve boşluk değerlerinin x ve y koordinat değerleri.. 
    public int bayrakciY=5;
    
    public int spacingX=90;
    public int spacingY = 10;       
    
    
    public int bayrakciMerkezX=bayrakciX+35;
    public int bayrakciMerkezY=bayrakciY+35;
    
    public int minusX = spacingX+160;
    public int minusY = spacingY;
    
     public int plusX =spacingX+240;
     public int plusY = spacingY;
     
   
    public int smileyCenterX = gülücükX+35;
    public int smileyCenterY =gülücükY+35;
    
    public int sayacX=1200;
    public int sayacY=5;
    
    public int vicMesX=800;
   public int vicMesY=-50;
    
    public long san=0;
    String vicMes="Daha değil...";
    
    
    public boolean happiness=true;
    Random rnd= new Random();
    
    public boolean victory = false;
    public boolean defeat = false;
    
    int [][]mayin = new int [16][9];
    int [][] komsu=new int [16][9];
    boolean[][] acıkta = new boolean[16][9];
    boolean [][] bayrakli=new boolean[16][9];
    
    public GUI(){                                                   //burdan sonra  da artık GUI yani arayüzü tanımlamaya başlıyoruz..
        this.setTitle("MayınTarlası");
        this.setSize(1300, 760);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);         
        this.setResizable(false);  //bu çok önemli çünkü oyunumuzda belirli sınırlar içerisinde işlemler yaptırdık setResizable metodu da false olursa kullanıcı, oyunun boyutunu değiştiremeyecektir..
        
             for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){ 
               if(rnd.nextInt(100)<20){
                   mayin[i][j]=1;
               }else {
                   mayin[i][j]=0;                         
               }
               acıkta[i][j]=false;
               
                }
                
            }
             
             for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){ 
                  yakin=0;
                   for (int m=0;m<16;m++){
                      for (int n=0;n<9;n++){ 
                          if(!(m==i&&n==j)){
                          if(isN(i,j,m,n)==true)
                             
                             yakin++;
                          }
                          
                      }
                      
                   }
                    komsu[i][j]=yakin;
                }
                } 
        
        Board board=new Board();
        this.setContentPane(board);
        
        Move move =new Move();
        this.addMouseMotionListener(move);
        
        Click click =new Click();
        this.addMouseListener(click);
        
    }
    
    public class Board extends JPanel{
        public void paintComponent(Graphics g){         //çizim için kullandığımız metoddur oyunun büyük kısmı burda yazılır.
            g.setColor(Color.GRAY);
            g.fillRect(0,0,1300,800);
            
            for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){ 
                    g.setColor(Color.DARK_GRAY);
                    
                   
                   
                    if (acıkta[i][j]==true){
                        g.setColor(Color.white);
                        if (mayin[i][j]==1){
                            g.setColor(Color.black);
                        }
                    }
                    if(mx>=bosluk+i*80 && mx< bosluk+i*80+80-2*bosluk&& my >= bosluk+j*80+80+26 && my < bosluk+j*80+26+80+80-2*bosluk){
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    g.fillOval(bosluk+i*80, bosluk+j*80+80, 80-2*bosluk, 80-2*bosluk);
                       if (acıkta[i][j]==true){
                           g.setColor(Color.red);
                           if (mayin[i][j]==0 && komsu[i][j] !=0){
                                   if(komsu[i][j]==1){      //burada  da tıkladığımız kutunun yanında kaç tane mayın olduğunu gösteren sayıları renklendiriyoruz..
                                   g.setColor(Color.blue);
                               }else if(komsu[i][j]==2){
                                   g.setColor(Color.yellow);
                               }else if(komsu[i][j]==3){
                                   g.setColor(Color.green);
                               }else if(komsu[i][j]==4){
                                   g.setColor(Color.orange);
                               }else if(komsu[i][j]==5){
                                   g.setColor(Color.black);
                               }else if(komsu[i][j]==6){
                                   g.setColor(Color.pink);
                               }else if(komsu[i][j]==7){
                                   g.setColor(Color.orange);
                               }else if(komsu[i][j]==8){
                                   g.setColor(Color.red);
                               }
                             //Burada da mayınımızı çizmeye başlıyoruz.g.fillRect metodu bize belirli boyutlarda belirli yerlerde kareler çizdirir.
                           g.setFont(new Font("Tahoma",Font.BOLD,40));
                           g.drawString(Integer.toString(komsu[i][j]), i*80+27, j*80+80+55);
                           }else if (mayin[i][j]==1){
                               g.fillRect(i*80+10+20,j*80+80+20,20,40);
                               g.fillRect(i*80+20,j*80+80+10+20,40,20);
                               g.fillRect(i*80+5+20,j*80+80+5+20,30,30);
                           }
                           }
                           //bayrağımızı çiziyoruz.
                           if( bayrakli[i][j]==true){
                              g.setColor(Color.white);
          g.fillRect(i*80+32+5, j*80+80+15+5, 5, 40);
          g.fillRect(i*80+20+5,j*80+80+50+5,30,10);
          g.setColor(Color.red);
          g.fillRect(i*80+16+5, j*80+80+15+5, 20, 15);
          g.setColor(Color.yellow);
          g.drawRect(i*80+16+5, j*80+80+15+5, 20, 15);
          g.drawRect(i*80+17+5,j*80+80+16+5,18,13);
           g.drawRect(i*80+18+5,j*80+80+17+5,16,11);
            
                           }
                }
            }
            //boşluk ayarlama için "+" ve "-" çiziyoruz
            g.setColor(Color.BLACK);
            g.fillRect(spacingX, spacingY, 300, 60);
            
            g.setColor(Color.white);
            g.fillRect(minusX+5, minusY+10, 40,40);
            g.fillRect(plusX+5, plusY+10, 40, 40);
            
            g.setFont(new Font("Tahoma",Font.PLAIN,35));
            g.drawString("Boşluklar",spacingX+20,spacingY+45);
            
            g.setColor(Color.BLACK);
            g.fillRect(minusX+15,minusY+27,20,6);
            g.fillRect(plusX+15, plusY+27, 20, 6);
              g.fillRect(plusX+22, plusY+20, 6, 20);
              
              g.setColor(Color.white);
              g.drawString(Integer.toString(bosluk), minusX+50, minusY+40);
            //Gülücüğümüzü çiziyoruz eğer true ise gülüyor else ise yani false döndürüyorsa üzülüyor...
         g.setColor(Color.yellow);
         g.fillOval(gülücükX, gülücükY, 70, 70);
         g.setColor(Color.red);
         g.fillOval(gülücükX+17, gülücükY+20,10, 10);
          g.fillOval(gülücükX+43, gülücükY+20,10, 10);
          if(happiness==true){
              g.fillOval(gülücükX+13, gülücükY+42,8, 8);
           g.fillOval(gülücükX+15, gülücükY+45,8, 8);
           g.fillOval(gülücükX+49,gülücükY+42,8, 8);
           g.fillOval(gülücükX+47, gülücükY+45,8, 8);

           g.fillRect(gülücükX+20,gülücükY+45, 31, 8);
          }
          else{
              g.fillRect(gülücükX+20, gülücükY+40, 31, 8);
               g.fillOval(gülücükX+45, gülücükY+42,8, 8);
               g.fillOval(gülücükX+17, gülücükY+42,8, 8);
          }
          //bayrak
          g.setColor(Color.white);
          g.fillRect(bayrakciX+32, bayrakciY+15, 5, 40);
          g.fillRect(bayrakciX+20,bayrakciY+50,30,10);
          g.setColor(Color.red);
          g.fillRect(bayrakciX+16, bayrakciY+15, 20, 15);
          g.setColor(Color.yellow);
          g.drawRect(bayrakciX+16, bayrakciY+15, 20, 15);
          g.drawRect(bayrakciX+17,bayrakciY+16,18,13);
           g.drawRect(bayrakciX+18,bayrakciY+17,16,11);
           
           g.setColor(Color.BLACK);
           if(flagger==true){
               g.setColor(Color.red);
           }
           g.drawOval(bayrakciX-3,bayrakciY+2, 70, 70);
            g.drawOval(bayrakciX-2,bayrakciY+3, 68, 68);
            g.drawOval(bayrakciX-1,bayrakciY+4, 66, 66);
          //süre
          g.setColor(Color.black);
          g.fillRect(sayacX-80,sayacY, 150, 70);
          if(defeat==false&&victory==false){
          san = (int)((new Date ().getTime()-startDate.getTime())/1000);
          }
          if(san>999){
              san=999;
          }
          g.setColor(Color.white);
          if(victory==true){
              g.setColor(Color.green);
          }else if(defeat==true){
              g.setColor(Color.red);
             }
          g.setFont(new Font("Tahoma",Font.PLAIN,80));
          g.drawString(Long.toString(san),sayacX-50,sayacY+65);
          //mesajlar
          if (victory ==true){
              g.setColor(Color.GREEN);
              
             vicMes = "Kazandin!";
              
          }
          else if(defeat ==true){
          g.setColor(Color.red);   
          vicMes="Kaybettin";
            
         
          }
         if(victory ==true || defeat==true){
            vicMesY=-50 + (int) (new Date().getTime()- endDate.getTime())/10;
          if(vicMesY > 70){
              vicMesY=70;
          }
          g.setFont(new Font("Tahoma",Font.PLAIN,70));
           g.drawString(vicMes, vicMesX, vicMesY);
         }   
          
    }
    }     
    public class Move implements MouseMotionListener {//Oyunun en önemli noktası nasıl oynanacağıdır.Burada mouse metodunu construct ederek elde etiiğimiz metodlarla oyunu oynamak için gerekli şeyleri hazırlıyoruz.

        @Override
        public void mouseDragged(MouseEvent e) {
           
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx=e.getX();
            my=e.getY();
            repaint();
        }
        
    }
    public class Click implements MouseListener {
    

        @Override
        public void mouseClicked(MouseEvent e) {
            
             mx=e.getX();
             my=e.getY();
            
             if(mx>=minusX+20&&mx<minusX+60&&my>=minusY+20&&my<minusY+60){
                 bosluk--;
                 if(bosluk<1){
                     bosluk=1;  
                 }
             }else if(mx>=plusX+20&&mx<plusX+60&&my>=plusY+20&&my<plusY+60){
                 bosluk++;
                 if(bosluk>10){
                     bosluk=10;
                 }
             }
             
            if(inBoxX()!=-1&&inBoxY()!=-1){
                if(flagger==true&&acıkta[inBoxX()][inBoxY()]==false){
                    if( bayrakli[inBoxX()][inBoxY()]==false){
                         bayrakli[inBoxX()][inBoxY()]=true;
                    }else{
                         bayrakli[inBoxX()][inBoxY()]=false;
                    }
                }else{
                    if( bayrakli[inBoxX()][inBoxY()] == false){
                 acıkta[inBoxX()][inBoxY()]=true;
                    }
                    }
            }else{
                
            }
           
                
            
            if (inSmiley() == true){
                resetAll();
            }
            if(inFlagger()==true){
                if(flagger==false){
                flagger=true;
                }else{
                    flagger=false;
                }
            }
        }
    
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    
         public void checkVictoryStatus(){
             if(defeat==false){
             for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){ 
                 if (acıkta[i][j]&&mayin[i][j]==1){
                     defeat=true;
                     happiness=false;
                     endDate = new Date();
                     
                                            }
                
                
                                        }
                                    }  
                            }
             if(totalBoxesRevealed()>=144-totalMines()&& victory==false){
              victory=true;
              endDate = new Date();
             
             }
        }
          public int totalMines(){
              int total=0;
              for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){ 
               if(mayin[i][j]==1){
                   total++;
               }
                }
                
            }
            return total;
        }
           public int totalBoxesRevealed(){
               int total=0;
                for (int i=0;i<16;i++){
               for (int j=0;j<9;j++){ 
              if(acıkta[i][j]==true){
                  total++;
               }
             }
                
          }
            return total;
        }
        
    
    public void resetAll(){
        resetter=true;
       
        flagger=false;
        
       vicMes="Daha değil...";
       
       startDate= new Date();
        
        happiness=true;
        victory=false;
        defeat=false;
       //vicMesY=-50;
        
        for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){ 
               if(rnd.nextInt(100)<20){
                   mayin[i][j]=1;
               }else {
                   mayin[i][j]=0;
               }
               acıkta[i][j]=false;
                bayrakli[i][j]=false;
                }
                
            }
             
             for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){ 
                  yakin=0;
                   for (int m=0;m<16;m++){
                      for (int n=0;n<9;n++){ 
                          if(!(m==i&&n==j)){
                          if(isN(i,j,m,n)==true)
                             
                              yakin++;
                          }
                          
                      }
                      
                   }
                    komsu[i][j]=yakin;
                }
                } 
        resetter=false;
    }
    public boolean inSmiley(){
        int dif =(int) Math.sqrt(Math.abs(mx-smileyCenterX)*Math.abs(mx-smileyCenterX)+Math.abs(my-smileyCenterY)*Math.abs(my-smileyCenterY));
        if(dif < 35){
            return true;
        }
        return false;
    }
    
      public boolean inFlagger(){
        int dif =(int) Math.sqrt(Math.abs(mx-bayrakciMerkezX)*Math.abs(mx-bayrakciMerkezX)+Math.abs(my-bayrakciMerkezY)*Math.abs(my-bayrakciMerkezY));
        if(dif < 35){
            return true;
        }
        return false;
    }
    public int inBoxX(){
         for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){ 
                    if(mx>=bosluk+i*80 && mx< bosluk+i*80+80-2*bosluk&& my >= bosluk+j*80+80+26 && my < bosluk+j*80+26+80+80-2*bosluk){
                      return i;
                    }
                    
                }
            }
        return -1;
    }
    public int inBoxY() {
        for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){ 
                    if(mx>=bosluk+i*80 && mx< bosluk+i*80+80-2*bosluk&& my >= bosluk+j*80+80+26 && my < bosluk+j*80+26+80+80-2*bosluk){
                      return j;
                    }
                    
                }
            }
        return -1;
       
    }
    public boolean isN(int mX,int mY, int cX , int cY){
        if(mX - cX < 2 && mX-cX > -2 && mY - cY < 2 && mY-cY > -2&& mayin[cX][cY]==1){
            return true;
        }
        return false;
    }
   
        
    

}
