import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;

public class GamePanel extends JPanel implements ActionListener {
    //setting screen w x h
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    //unit size
    static final int UNIT_SIZE = 25;
    //total units
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    //the higher the delay the better
    static final int DELAY = 75;
    //Array to hold the body of the snake
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    //initial body parts
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    //the direction in which snake will be initially
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        //after finishing calling this GamePanel
        //call startGame
        startGame();
    }

    public void startGame(){
        //create a new apple on the screen
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //to make it is easier to see
        //converting into matrix the grid

        //moving all into a if statement

        if(running){
            //commenting the grid lines, they were to help vizualize
            /*
            for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            */

            //setting color of apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            //drawing the snake's head and body
            for(int i=0;i<bodyParts;i++){
                if(i==0){
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else{
                    //g.setColor(new Color(45,180,0));
                    //random body colours
                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            //Score Card
            g.setColor(Color.red);
            //font, size
            g.setFont(new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            //the string and the location i.e. top of the screen
            g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
        }
        //gameOver
        else{
            gameOver(g);
        }
    }

    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    public void move(){
        for(int i=bodyParts;i>0;i--){
            x[i] = x[i-1]; //moving the body/array of snake
            y[i] = y[i-1];
        }
        //directions
        switch(direction){
            //Going UP
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            //Going Down
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple(){
        //eat apple and create a new apple and inc the size of snake
        if((x[0] == appleX) && (y[0] == appleY)){
            //play Apple Eaten Sound
            playEatSound();
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions(){
        //the snake was going straight and out of the screen
        //so writing code for collisions
        for(int i=bodyParts;i>0;i--){
            //gameOver 
            //checks if head collides with the body
            if((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
            }
        }
        //checks if the head collides with the boundary
        //left border
        //x[0] == head
        if(x[0]<0){
            running = false;
        }
        //right border
        if(x[0]>=SCREEN_WIDTH){
            running = false;
        }
        //top border
        if(y[0]<0){
            running = false;
        }
        //bottom border
        if(y[0]>=SCREEN_HEIGHT){
            running = false;
        }
        //stop the timer
        if(!running){
            timer.stop();
        }
    }

    public void gameOver(Graphics g){
        //Score after gameOver too
        //Score Card
        g.setColor(Color.red);
        //font, size
        g.setFont(new Font("Ink Free",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        //the string and the location i.e. top of the screen
        g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());

        //Setting Game Over Text
        //color
        g.setColor(Color.red);
        //font, size
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        //the string and the location i.e. center of the screen
        g.drawString("GAME OVER",(SCREEN_WIDTH - metrics2.stringWidth("GAME OVER"))/2,SCREEN_HEIGHT/2);
    }

    public void playEatSound() {
    // Attribution: Apple_Crunch_16.wav by Koops -- https://freesound.org/s/20279/ -- License: Attribution 4.0
    try {
        File soundFile = new File("Snake/res/sounds/appleEaten.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        //Listen and stop when done
        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event){
                if(event.getType() == LineEvent.Type.STOP){
                    clip.close();
                }
            }
        });
        clip.start();
    } catch (Exception e) {
        System.out.println("Audio Error: " + e.getMessage());
    }
}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //not able to see the body so writing the code here
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    //inner class
    public class MyKeyAdapter extends KeyAdapter {
        //To override a method
        @Override
        public void keyPressed(KeyEvent e){
            //implementing the movement to the snake
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    //limit to 90 only not 180
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                
                case KeyEvent.VK_RIGHT:
                    //limit to 90 only not 180
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                
                case KeyEvent.VK_UP:
                    //limit to 90 only not 180
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    //limit to 90 only not 180
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;               
            }

            if(!running && e.getKeyCode() == KeyEvent.VK_R){
                restartGame();
            }
        }
    }

    public void restartGame() {
        //Reset Everything

        //Reset Snake body
        bodyParts = 6;
        //Score
        applesEaten = 0;

        //Direction of the snake
        direction = 'R';

        //Reset Snake position
        for(int i=0;i< GAME_UNITS;i++){
            x[i] = 0;
            y[i] = 0;
        }

        //status and timer
        running = true;
        timer.start();

        //create a new apple
        newApple();

        //refresh & repaint the screen
        repaint();
    }
}