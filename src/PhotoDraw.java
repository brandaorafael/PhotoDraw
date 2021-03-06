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


public class PhotoDraw extends JPanel {

    public static BufferedImage image;

    public PhotoDraw()                       // set up graphics window
    {
        super();
        setBackground(Color.WHITE);

        try {
            //image = ImageIO.read(new File("/Users/Rafael/Documents/teste.jpg"));
//            image = ImageIO.read(new File("/home/rafael.brandao/Documents/Projetos/Pessoal/PhotoDraw/src/cores.jpg"));
            image = ImageIO.read(new File("/home/rafael.brandao/Documents/Projetos/Pessoal/PhotoDraw/src/tereza.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    public float mono(float red, float green, float blue){
//        return (float) ((0.2125 * red) + (0.7154 * green) + (0.0721 * blue));
        return (float) ((0.299 * red) + (0.587 * green) + (0.114 * blue));
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

//        BufferedImage tela = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//
//        g = tela.createGraphics();

        int[][] array = new int[width][height];
        int lastX = 0, lastY = 0;
        int nextX = -1, nextY = -1;
        int randomX, randomY;

        Random gerador = new Random();

//        nextX = gerador.nextInt(width);
//        nextY = gerador.nextInt(height);

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
        float alpha = 1.0f;
        for (int j = 0; j < width; j++) {
            for (int k = 0; k < height; k++) {
                alpha = mono(img.getSampleFloat(j, k, 0)/255.0f, img.getSample(j, k, 1)/255.0f, img.getSample(j, k, 2)/255.0f);
                if (alpha > 0.4f){
                    alpha = 1.0f;
                } else {
                    alpha = 0.0f;
                }
                g.setColor(new Color(0, 0, 0, 1-alpha));
                g.drawOval( j , k , 1 , 1 );

//                array[j][k] = img.getSample(j, k, 0);
//                if(img.getSample(j, k, 0) < 100){
//                    g.setColor(new Color(0,0,0,alpha));
//                    if(alpha > 0.0f){
//                        alpha -= 0.00001f;
//                        if(alpha < 0.0f){
//                            alpha = 0.0f;
//                        }
//                    }
//                    g.drawOval( lastX , lastY , 1 , 1 );
//                    lastX = j;
//                    lastY = k;
//                }
            }
        }
//        int max = 0;
//        for (int j = 0; j < width; j++) {
//            System.out.println("0: " + img.getSample(j, 0, 0) + "/ 1: " + img.getSample(j, 0, 1) + "/ 2: " + img.getSample(j, 0, 2));
//
//        }
//        System.out.println("MAX = " + max);
//        System.out.println("lastX = " + lastX);
//        System.out.println("lastY = " + lastY);

//        for(int i = 0; i <= 1000; i++){
//            for(int j = 0; j <= 200; j++){
////                randomX = (gerador.nextInt(width)/10) - width/20;
////                randomY = (gerador.nextInt(height)/10) - height/20;
////
////                if(lastX + randomX > 0 && lastX + randomX < width){
////                    if(lastY + randomY > 0 && lastY + randomY < height){
////                        if(nextX == -1){
////                            nextX = lastX + randomX;
////                            nextY = lastY + randomY;
////                        }else if(array[lastX + randomX][lastY + randomY] < array[nextX][nextY]){
////                            nextX = lastX + randomX;
////                            nextY = lastY + randomY;
////                        }
////                    }
////                }
//
//                randomX = (gerador.nextInt(width));
//                randomY = (gerador.nextInt(height));
//
//                if(nextX == -1){
//                    nextX = randomX;
//                    nextY = randomY;
//                }else if(array[randomX][randomY] < array[nextX][nextY]){
//                    nextX = randomX;
//                    nextY = randomY;
//                }
//            }
//            System.out.println("nextX = " + nextX);
//            System.out.println("nextY = " + nextY);
//            System.out.println("array = " + array[nextX][nextY]);
//            g.drawLine(lastX, lastY, nextX, nextY);
//            lastX = nextX;
//            lastY = nextY;
//            nextX = -1;
//            nextY = -1;
//        }

//        try {
//            ImageIO.write(tela, "JPG", new File("C:/Users/Ploomes/Documents/Rafael Brandão/out.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
