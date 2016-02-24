/**
 * Created by Rafael on 23/02/16.
 */

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class PhotoDraw extends JPanel {

    public static BufferedImage image;

    public PhotoDraw()                       // set up graphics window
    {
        super();
        setBackground(Color.WHITE);

        try {
            image = ImageIO.read(new File("/Users/Rafael/Documents/teste.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    public void paintComponent(Graphics g)  // draw graphics in the panel
    {
        //int width = getWidth();             // width of window in pixels
        //int height = getHeight();           // height of window in pixels

        super.paintComponent(g);            // call superclass to make panel display correctly


        // Drawing code goes here

        //g.drawLine( start x-coordinate , start y-coordinate , end x-coordinate , end y-coordinate );
//        for(int i = 1; i <= 400; i+=10){
//            if(i%2==0){
//                g.drawLine(0, 0, i-5, i+5);
//            } else {
//                g.drawLine(0, 0, i+5, i-5);
//            }
//        }

        int width = image.getWidth();
        int height = image.getHeight();

        int[][] array = new int[width][height];
        int lastX = 0, lastY = 0;
        int nextX, nextY;
        int atualX = 0, atualY = 0;
        int randomX, randomY;

        Random gerador = new Random();

        nextX = gerador.nextInt(width);
        nextY = gerador.nextInt(height);

        Raster img = image.getData();
        for (int j = 0; j < width; j++) {
            for (int k = 0; k < height; k++) {
                array[j][k] = img.getSample(j, k, 0);
                if(img.getSample(j, k, 0) < array[lastX][lastY]){
                    lastX = j;
                    lastY = k;
                }
            }
        }
//        for (int j = 0; j < width; j++) {
//            for (int k = 0; k < height; k++) {
//                array[j][k] = img.getSample(j, k, 0);
//                if(img.getSample(j, k, 0) < 100){
//                    g.drawOval( lastX , lastY , 1 , 1 );
//                    lastX = j;
//                    lastY = k;
//                }
//            }
//        }
        System.out.println("lastX = " + lastX);
        System.out.println("lastY = " + lastY);

        for(int i = 0; i <= 1; i++){
            for(int j = 0; j <= 200; j++){
                randomX = (gerador.nextInt(width)/10) - width/20;
                randomY = (gerador.nextInt(height)/10) - height/20;
                if(nextX + randomX > 0 && nextX + randomX < width){
                    if(nextY + randomY > 0 && nextY + randomY < height){
                        if(array[nextX + randomX][nextY + randomY] < array[nextX][nextY]){
                            atualX = nextX + randomX;
                            atualY = nextY + randomY;
                        }
                    }
                }
            }
            System.out.println("nextX = " + nextX);
            System.out.println("nextY = " + nextY);
            System.out.println("array = " + array[nextX][nextY]);
            nextX = atualX;
            nextY = atualY;
            g.drawLine(lastX, lastY, nextX, nextY);
            lastX = nextX;
            lastY = nextY;
            nextX = gerador.nextInt(width);
            nextY = gerador.nextInt(height);
        }
        //g.drawOval( 150 , 150 , 1 , 1 );
        //g.drawLine(0, 0, width, height);
        //g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args)
    {
        PhotoDraw panel = new PhotoDraw();                            // window for drawing
        JFrame application = new JFrame();                            // the program itself

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // set frame to exit
        // when it is closed
        application.add(panel);


        application.setSize(image.getWidth(), image.getHeight());         // window is 500 pixels wide, 400 high
        application.setVisible(true);
    }
}
