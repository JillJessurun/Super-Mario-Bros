import java.awt.*;
import java.awt.image.BufferedImage;

public class Bricks extends GameObject{

    private BufferedImage bricks;
    public static BufferedImage image;

    public Bricks(float x, float y, ID id, BufferedImage bricks) {
        super(x, y, id);
        this.bricks = bricks;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bricks, (int)x, (int)y, null);

        //draw bounds
        g.setColor(Color.red);
        g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        g.drawRect(getBoundsLow().x, getBoundsLow().y, getBoundsLow().width, getBoundsLow().height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 164, 7); //official height is 55
    }

    public Rectangle getBoundsLow() {
        return new Rectangle((int) x, (int) y + 48, 164, 7); //official height is 55
    }
}
