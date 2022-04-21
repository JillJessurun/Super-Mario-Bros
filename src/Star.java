import java.awt.*;
import java.awt.image.BufferedImage;

public class Star extends GameObject{

    private BufferedImage star;

    public Star(float x, float y, ID id, BufferedImage star) {
        super(x, y, id);
        this.star = star;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(star, (int)x, (int)y, null);
        g.setColor(Color.red);
        //g.drawRect((int) x + 5, (int) y + 5, 70, 65);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 5, (int) y + 5, 70, 65);
    }
}
