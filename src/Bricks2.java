import java.awt.*;
import java.awt.image.BufferedImage;

public class Bricks2 extends GameObject{
    private BufferedImage bricks;

    public Bricks2(float x, float y, ID id, BufferedImage bricks) {
        super(x, y, id);
        this.bricks = bricks;
        lowX = getBounds().x;
        lowY = getBounds().y+45;
        lowWidth = getBounds().width;
        lowHeight = 10;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bricks, (int)x, (int)y, null);

        //draw bounds
        //g.setColor(Color.red);
        //g.drawRect((int) x, (int) y, 164, 10);
        //g.drawRect(lowX, lowY, lowWidth, lowHeight);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 164, 10); //official height is 55
    }

    @Override
    public void setLowX(int lowX) {
        super.setLowX(lowX);
    }

    @Override
    public void setLowY(int lowY) {
        super.setLowY(lowY);
    }

    @Override
    public void setLowWidth(int lowWidth) {
        super.setLowWidth(lowWidth);
    }

    @Override
    public void setLowHeight(int lowHeight) {
        super.setLowHeight(lowHeight);
    }

    public Rectangle getBoundsLow() {
        return new Rectangle(lowX, lowY, lowWidth, lowHeight); //official height is 55
    }
}
