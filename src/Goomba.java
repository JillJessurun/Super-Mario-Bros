import java.awt.*;
import java.awt.image.BufferedImage;

public class Goomba extends GameObject{

    private Handler handler;

    private BufferedImage goomba;

    public Goomba(float x, float y, ID id, Handler handler, BufferedImage goomba) {
        super(x, y, id);
        this.handler = handler;
        this.goomba = goomba;
        velX = 4;
        velY = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 60, 60);
    }

    public void tick() {

        x += velX/2;
        //y += velY;

        //if (y <= 0 || y >= Game.HEIGHT) {
            //velY *= -1;
        //}
        if (x <= -10 || x >= Game.WIDTH - 70) {
            velX *= -1;
        }

    }

    public void render(Graphics g) {
        g.drawImage(goomba, (int)x, (int)y, null);

        //draw bounds
        g.setColor(Color.red);
        //g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
    }
}
