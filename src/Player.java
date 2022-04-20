import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

    private Handler handler;
    private BufferedImage mario;
    private KeyInput keyInput;
    private Game game;

    private final int jumpHeight = 690;
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
                if (tempObject.getId() == ID.Enemy) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //collision code (under here happens when colliding)
                        game.dead = true;
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

        //jump
        if(handler.jump){
            //going up
            if (y >= jumpHeight) {
                jumpIncrease = jumpIncrease - 3;
            }else{
                jumpHeightReached = true;
            }

            //going down
            if (jumpHeightReached){
                //hold s
                if (handler.down){
                    jumpIncrease = jumpIncrease + 2;
                }

                if (y<= 743) {
                    jumpIncrease++;
                //resetting stats
                }else{
                    y = 743;
                    jumpHeightReached = false;
                    handler.jump = false;
                    jumpIncrease = 0;
                }
            }

            //keyInput.jump = false;
            System.out.println(y);
        }

        collision();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(mario, (int)x, (int)y, null);

        //draw bounds
        //g.setColor(Color.red);
        //g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle ((int)x, (int)y, 70, 104);
    }
}
