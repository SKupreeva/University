import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.*;

public class main extends JFrame{

    private BufferedImage buffImg1, buffImg2;
    private int houseWidth = 680, houseHeight = 370, lightOffWidth = 500,  lightOffHeight = 320,
            lightOnWidth = 500, lightOnHeight = 320, lightOffX = 0, lightOffY = 400, lightOnX = 0, lightOnY = 400,
            smoke1Width = 30, smoke1Height = 100, smoke1X = 100, smoke1Y = 420, smoke2Width = 30, smoke2Height = 100,
            smoke2X = 400, smoke2Y = 420, smoke3Width = 30, smoke3Height = 100, smoke3X = 100, smoke3Y = 420,
            smoke4Width = 30, smoke4Height = 100, smoke4X = 400, smoke4Y = 420, smoke5Width = 30, smoke5Height = 100,
            smoke5X = 100, smoke5Y = 420;
    private static Image background;
    private static Image house;
    private static Image smoke1;
    private static Image smoke2;
    private static Image smoke3;
    private static Image smoke4;
    private static Image smoke5;
    private Image lightOff;
    private Image lightOn;
    JButton btn;

    public main() {
        setTitle("Lab 6");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        //  Background() - панель, для которой переопределен paintComponent с фоном
        setContentPane(new Background()); // панель устанавливается как contentPane в наследнике JFrame
        Container content = getContentPane(); //теперь все элементы интерфейса будут на этой панели.

        btn = new JButton("Старт");
        btn.setPreferredSize(new Dimension(1000,50));
        btn.setBackground(Color.white);
        btn.setForeground(Color.darkGray);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn.setVisible(false);
                Thread smokeMove = new Thread(new smokeThread());
                smokeMove.start();
                Thread lightChange = new Thread(new lightThread());
                lightChange.start();
                Thread sizeChange = new Thread(new sizeThread());
                sizeChange.start();
            }
        });
        content.add(btn);
        content.add(new Position());
    }

    private static class Background extends JPanel{

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            try {
                background = ImageIO.read(new File("back.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(background, 0,0,null);
        }
    }

    private class Position extends JPanel{
        public Position() {
            setOpaque(false);
            setPreferredSize(new Dimension(1280, 720));
            try {
                house = ImageIO.read(new File("house.png"));
                smoke1 = ImageIO.read(new File("1.png"));
                smoke2 = ImageIO.read(new File("2.png"));
                smoke3 = ImageIO.read(new File("3.png"));
                smoke4 = ImageIO.read(new File("4.png"));
                smoke5 = ImageIO.read(new File("5.png"));
                lightOff =  ImageIO.read(new File("lightOff.png"));
                lightOn =  ImageIO.read(new File("lightOn.png"));
            }
            catch (IOException exc) {};
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;

            graphics2D.drawImage(smoke1, smoke1X, smoke1Y, smoke1Width, smoke1Height, this);
            graphics2D.drawImage(smoke2, smoke2X, smoke2Y, smoke2Width, smoke2Height, this);
            graphics2D.drawImage(smoke3, smoke3X, smoke3Y, smoke3Width, smoke3Height, this);
            graphics2D.drawImage(smoke4, smoke4X, smoke4Y, smoke4Width, smoke4Height, this);
            graphics2D.drawImage(smoke5, smoke5X, smoke5Y, smoke5Width, smoke5Height, this);

            graphics2D.drawImage(lightOff, lightOffX, lightOffY, lightOffWidth, lightOffHeight, this);
            graphics2D.drawImage(house, 0, 330, houseWidth, houseHeight, this);
        }
    }

    public class sizeThread implements Runnable {
        @Override
        public void run() {
            while ((smoke1Width != 0) && (smoke2Width != 0) && (smoke4Width != 0) && (smoke3Width != 0) && (smoke5Width != 0)) {
                smoke1Width--;
                smoke1Height--;
                smoke2Width--;
                smoke2Height--;
                smoke3Width--;
                smoke3Height--;
                smoke4Width--;
                smoke4Height--;
                smoke5Width--;
                smoke5Height--;
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (Exception exc) {
                }
                ;
            }
            while ((smoke1Width != 30) && (smoke2Width != 30) && (smoke4Width != 30) && (smoke3Width != 30) && (smoke5Width != 30)) {
                smoke1Width++;
                smoke1Height++;
                smoke2Width++;
                smoke2Height++;
                smoke3Width++;
                smoke3Height++;
                smoke4Width++;
                smoke4Height++;
                smoke5Width++;
                smoke5Height++;
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (Exception exc) {
                }
                ;
            }
        }
    }

    public class lightThread implements Runnable{
        @Override
        public void run() {
            while (smoke1X > -1) {
                try {
                    Thread.sleep(2000);
                    changePicture(lightOn);
                } catch (Exception exc) { }
            }
        }
    }

    public class smokeThread implements Runnable{
        @Override
        public void run() {
            while (smoke1X > -1) {
                smoke1X = ThreadLocalRandom.current().nextInt(100, 105);
                smoke1Y = ThreadLocalRandom.current().nextInt(0, 450);

                smoke2X = ThreadLocalRandom.current().nextInt(415, 420);
                smoke2Y = ThreadLocalRandom.current().nextInt(0, 450);

                smoke3X = ThreadLocalRandom.current().nextInt(100, 105);
                smoke3Y = ThreadLocalRandom.current().nextInt(0, 450);

                smoke4X = ThreadLocalRandom.current().nextInt(415, 420);
                smoke4Y = ThreadLocalRandom.current().nextInt(0, 450);

                smoke5X = ThreadLocalRandom.current().nextInt(100, 105);
                smoke5Y = ThreadLocalRandom.current().nextInt(0, 450);
                try {
                    Thread.sleep(200);
                } catch (Exception exc) {
                }
                ;
            }
        }
    }

    public void changePicture(Image img) {
        this.lightOff = img;
        repaint();
    }

    public static void main(String[] args) throws IOException {
        new main().setVisible(true);
    }
}
