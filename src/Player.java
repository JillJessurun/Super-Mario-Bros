import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

    private Handler handler;
    private BufferedImage mario;
    private KeyInput keyInput;
    private Game game;
    private Bricks brick;
    private Bricks2 brick2;
    private HUD hud;

    private int jumpHeight = 450; //smaller number = higher jump
    private int jumpIncrease = 0;
    //private boolean jumpHeightReached = false;
    //private boolean jumpingUp = false;

    public Player(float x, float y, ID id, Handler handler, BufferedImage mario, KeyInput keyInput, Game game, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.mario = mario;
        this.keyInput = keyInput;
        this.game = game;
        this.hud = hud;
    }

    private void collision(){
        for (int i = 0; i < handler.object.size(); i++) {
            if (this.getId() == ID.Player) {
                GameObject tempObject = handler.object.get(i);
                //enemy collision
                if (tempObject.getId() == ID.Enemy) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //collision code (under here happens when colliding)
                        game.dead = true;
                    }
                //brick collision
                }else if (tempObject.getId() == ID.Brick) {
                    brick = (Bricks) tempObject;

                    //go on top of brick
                    if (getBounds().intersects(brick.getBounds())) {
                        //collision code (under here happens when colliding)
                        if(handler.jumpingUp) {
                            System.out.println("jumping on brick");
                            jumpHeight = 250;
                            handler.jump = true;
                        }else{
                            y = brick.y - getBounds().height;
                            jumpIncrease = 0;
                        }
                    }

                    //when hitting the low side of the brick, stay under the brick
                    if (getBounds().intersects(brick.getBoundsLow())) {
                        y = brick.y + 55;
                        handler.jumpHeightReached = true;
                        jumpIncrease = 0;
                    }

                //brick 2 collision
                }else if (tempObject.getId() == ID.Brick2){
                    brick2 = (Bricks2) tempObject;

                    //go on top of brick 2
                    if (getBounds().intersects(brick2.getBounds())) {
                        //collision code (under here happens when colliding)
                        if(handler.jumpingUp) {
                            System.out.println("jumping on brick");
                            jumpHeight = 70;
                            handler.jump = true;
                        }else{
                            y = brick2.y - getBounds().height;
                            jumpIncrease = 0;
                        }
                    }

                    //when hitting the low side of the brick, stay under the brick
                    if (getBounds().intersects(brick2.getBoundsLow())) {
                        y = brick2.y + 55;
                        handler.jumpHeightReached = true;
                        jumpIncrease = 0;
                    }

                //star collision
                }else if (tempObject.getId() == ID.Star){
                    if (getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(tempObject);
                        hud.STARS++;
                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        x += velX * 1.4;
        y += jumpIncrease;

        x = Game.clamp(x, 0, Game.WIDTH - 85);
        //y = Game.clamp(y, 0, Game.HEIGHT - 64);

        collision();

        //jump
        if(handler.jump){
            //going up
            if (!handler.jumpHeightReached) {
                handler.jumpingUp = true;
                //jump till given height
                if (y >= jumpHeight) {
                    jumpIncrease = jumpIncrease - 3;
                }else {
                    handler.jumpHeightReached = true;
                }
            }

            //going down
            if (handler.jumpHeightReached) {
                handler.jumpingUp = false;
                //hold s
                if (handler.down) {
                    jumpIncrease = jumpIncrease + 2;
                }

                if (y <= 500) {
                    jumpIncrease++;
                    //resetting stats
                } else {

                    //when jumped off brick, reset jumpheight
                    if (!getBounds().intersects(brick.getBounds())) {
                        jumpHeight = 450;
                    }

                    y = 500;
                    handler.jumpHeightReached = false;
                    handler.jump = false;
                    jumpIncrease = 0;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(mario, (int)x, (int)y, null);

        //draw bounds
        g.setColor(Color.red);
        //g.drawLine(getBounds().x, getBounds().y, (int)x+getBounds().width, getBounds().y);
        //g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        g.drawString(String.valueOf(y), 1700, 30);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle ((int)x, (int)y, 70, 104);
    }
}
