import java.awt.*;

public class HUD {

    public int STARS = 0;

    public void tick(){

    }

    public void render(Graphics g){
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        g.setColor(Color.YELLOW);
        g.setFont(font);
        g.drawString("Star Count: " + STARS, 10, 30);
    }
}
