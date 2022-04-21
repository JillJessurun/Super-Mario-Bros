import java.awt.*;

public abstract class GameObject {

    protected float x, y;
    protected float velX, velY;
    protected ID id;

    //bricks variables
    protected int lowY = 0;
    protected int lowX = 0;
    protected int lowWidth = 0;
    protected int lowHeight = 0;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public Rectangle getBoundsLow(){
        return new Rectangle(lowX, lowY, lowWidth, lowHeight);
    }

    public int getLowY() {
        return lowY;
    }

    public void setLowY(int lowY) {
        this.lowY = lowY;
    }

    public int getLowX() {
        return lowX;
    }

    public void setLowX(int lowX) {
        this.lowX = lowX;
    }

    public int getLowWidth() {
        return lowWidth;
    }

    public void setLowWidth(int lowWidth) {
        this.lowWidth = lowWidth;
    }

    public int getLowHeight() {
        return lowHeight;
    }

    public void setLowHeight(int lowHeight) {
        this.lowHeight = lowHeight;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
