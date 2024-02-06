import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Graphics;
//import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListener = new KL();
    public Rect playerOne, ball, ai;
    public PlayerController playerController;
    public PlayerController aiController;
    public Ball ballController;
    public Text leftScoreText, rightScoreText;
    public int leftScore, rightScore;

    public Window() { 
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        g2 = (Graphics2D)this.getGraphics();
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSET_BOTTOM = this.getInsets().bottom;

        playerOne =         new Rect(Constants.HZ_PADDING, Constants.VT_PADDING + Constants.TOOLBAR_HEIGHT, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        ai =                new Rect(Constants.SCREEN_WIDTH - Constants.HZ_PADDING - Constants.PADDLE_WIDTH, Constants.VT_PADDING + Constants.TOOLBAR_HEIGHT, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        ball =              new Rect(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, Constants.BALL_WIDTH, Constants.BALL_WIDTH, Constants.PADDLE_COLOR);
        playerController =  new PlayerController(playerOne, keyListener,   0);
        aiController =      new PlayerController(ai, ball, 0);
        ballController =    new Ball(ball, playerOne, ai, 100, 100, -1, -1);

        rightScoreText = new Text(Constants.PLAYER_SCORE, new Font("Times New Roman", Font.PLAIN, 20), Constants.SCREEN_WIDTH - 10 - 20, Constants.TOOLBAR_HEIGHT + 20);
        leftScoreText = new Text(Constants.AI_SCORE, new Font("Times New Roman", Font.PLAIN, 20), 10, Constants.TOOLBAR_HEIGHT + 20);

    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        playerController.update(dt);
        aiController.update(dt);
        ballController.update(dt);
        leftScoreText.text = String.valueOf(Constants.PLAYER_SCORE);
        rightScoreText.text = String.valueOf(Constants.AI_SCORE);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        leftScoreText.draw(g2);
        rightScoreText.draw(g2);
        playerOne.draw(g2);
        ai.draw(g2);
        ball.draw(g2);
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

/*            try {
                Thread.sleep(30);
            } catch (Exception e) {

            } */
        }
    }
    
}
