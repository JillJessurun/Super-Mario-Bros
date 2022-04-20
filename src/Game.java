/*
author: Jill Jessurun
date: 4-20 :) 2022
goal: super mario bros clone

ideas:
- grab the stars each level, then an arrow appears bottom right (walk to the right to go to next level)
- different characters (luigi, yoshi, bowser, etc.)
- audio
- working tubes
 */

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable{

    //variables
    public static final int WIDTH = 1800;
    public static final int HEIGHT = 1000;
    private Thread thread;
    private boolean running = true;
    public boolean dead = false;

    //instances
    private Handler handler;
    private HUD hud;
    private BufferedImageLoader loader;
    private KeyInput keyInput;

    //background game
    private BufferedImage background;
    public static BufferedImage image;

    //goomba
    private BufferedImage goomba;
    public static BufferedImage image2;

    //mario
    private BufferedImage mario;
    public static BufferedImage image3;

    //constructor
    public Game() throws IOException {
        loader = new BufferedImageLoader();

        //background
        image = loader.loadImage("C:\\Users\\pc\\IdeaProjects\\Super-Mario-Bros\\src\\Images\\background.jpg");
        image2 = loader.loadImage("C:\\Users\\pc\\IdeaProjects\\Super-Mario-Bros\\src\\Images\\goomba.gif");
        image3 = loader.loadImage("C:\\Users\\pc\\IdeaProjects\\Super-Mario-Bros\\src\\Images\\mario.png");
        Image image = new Image(Game.image);
        Image image2 = new Image(Game.image2);
        Image image3 = new Image(Game.image3);
        background = image.grabImage();
        goomba = image2.grabImage();
        mario = image3.grabImage();

        //resize images
        background = image.resizeImage(background, 1800, 1000);

        handler = new Handler();
        new Window(WIDTH, HEIGHT, "Super Mario Bros", this);

        //keylisteners
        this.addKeyListener(new KeyInput(handler));

        hud = new HUD();

        keyInput = new KeyInput(handler);

        //adding objects at startup program
        handler.addObject(new Player(70, 743, ID.Player, handler, mario, keyInput, this));
        handler.addObject(new Goomba(1200, 790, ID.Enemy, handler, goomba));
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            System.out.println("The thread cannot join :(");
        }
    }

    public void tick(){
        handler.tick();
        hud.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //background
        g.drawImage(background, 0, 0, null);

        handler.render(g);
        hud.render(g);

        //game over
        if (dead){
            Font font = new Font("Comic Sans MS", Font.BOLD, 150);
            g.setColor(Color.red);
            g.setFont(font);
            g.drawString("Game Over", 500, 400);
        }

        g.dispose();
        bs.show();
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    //clamp method: if the var is at the max, it stays at the max (same with the min)
    public static float clamp(float var, float min, float max){
        if(var >= max){
            return var = max;
        }else if(var <= min){
            return var = min;
        }else{
            return var;
        }
    }

    public static void main(String[] args) throws IOException {
        new Game();
    }

}
