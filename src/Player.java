import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

    private Handler handler;
    private BufferedImage mario;
    private KeyInput keyInput;
    private Game game;
    private Bricks brick;

    private final int jumpHeight = 660; //smaller number = higher jump
    private int jumpIncrease = 0;
    private boolean jumpHeightReached = false;

    public Player(float x, float y, ID id, Handler handler, BufferedImage mario, KeyInput keyInput, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.mario = mario;
        this.keyInput = keyInput;
        this.game = game;
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
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //collision code (under here happens when colliding)

                        /*
                        if (y < 660 && y > 610) {
                            jumpHeightReached = true;
                            y = 655; //height low side bricks
                        }else{
                            y = tempObject.y - getBounds().height; //mario on top of bricks
                            jumpHeightReached = false;
                            handler.jump = false;
                            jumpIncrease = 0;
                        }

                         */

                        //y = tempObject.y - getBounds().height;

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
            if (!jumpHeightReached) {
                //jump till given height
                if (y >= jumpHeight) {
                    jumpIncrease = jumpIncrease - 3;
                }else {
                    jumpHeightReached = true;
                }

                //jumping against the brick from below, stay under brick
                if (getBounds().intersects(brick.getBoundsLow())) {
                    y = 654;
                    jumpHeightReached = false;
                }
            }

            //going down
            if (jumpHeightReached) {
                //jumping on the brick
                if (getBounds().intersects(brick.getBounds())) {
                    y = brick.y - getBounds().height; //mario on top of bricks
                    jumpHeightReached = false;
                    handler.jump = false;
                    jumpIncrease = 0;
                }else{
                    //hold s
                    if (handler.down) {
                        jumpIncrease = jumpIncrease + 2;
                    }

                    if (y <= 743) {
                        jumpIncrease++;
                        //resetting stats
                    } else {
                        y = 743;
                        jumpHeightReached = false;
                        handler.jump = false;
                        jumpIncrease = 0;
                    }
                }
            }

        }else{
            //when not on a brick, always stay on ground
            if (x < 1440 || x > 1664){
                y = 743;
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
