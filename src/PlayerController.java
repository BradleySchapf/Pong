import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public KL keyListener;
    public Rect ball;
    public int score;

    public PlayerController(Rect rect, KL keyListener, int score) {
        this.rect = rect;
        this.keyListener = keyListener;
        this.score = score;
    }

    public PlayerController(Rect rect, Rect ball, int score) {
        this.rect = rect;
        this.ball = ball;
        this.keyListener = null;
        this.score = score;
    }


    public void update(double dt) {
        if (keyListener != null) {
            if (keyListener.isKeyPressed(KeyEvent.VK_S)) {
                moveDown(dt);
            }
            else if (keyListener.isKeyPressed(KeyEvent.VK_W)) {
                moveUp(dt);
            }
        }
        else {
            if (ball.y > rect.y + Constants.PADDLE_HEIGHT / 2) {
                moveDown(dt);
            }
            else if (ball.y < rect.y + Constants.PADDLE_HEIGHT / 2) {
                moveUp(dt);
            }
        }
    }

    public void moveUp (double dt) {
        if (this.rect.y >= Constants.TOOLBAR_HEIGHT + Constants.VT_PADDING) {
            this.rect.y -= Constants.PADDLE_SPEED * dt;
        }
    }

    public void moveDown (double dt) { 
        if (this.rect.y + Constants.PADDLE_HEIGHT <= Constants.SCREEN_HEIGHT - Constants.VT_PADDING) {
            this.rect.y += Constants.PADDLE_SPEED * dt;
        }
    }
}
