package mayıntarlası;

public class MayınTarlası implements Runnable {
    GUI gui=new GUI();
    public static void main(String[] args) {
    new Thread(new MayınTarlası()).start();
    }

    @Override
    public void run() {
       while(true){
           gui.repaint();
           if(gui.resetter ==false){
           gui.checkVictoryStatus();
       }
       }
           
    }
    
}
