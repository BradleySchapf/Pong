public class Ball {
    public Rect rect;
    public Rect leftPaddle;
    public Rect rightPaddle;
    public double Xspeed;
    public double Yspeed;
    public int Xdirection;
    public int Ydirection;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle, double Xspeed, double Yspeed, int Xdirection, int Ydirection) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.Xspeed = Xspeed;
        this.Yspeed = Yspeed;
        this.Xdirection = Xdirection;
        this.Ydirection = Ydirection;
    }

    public void update(double dt) {
        this.rect.y += this.Yspeed * this.Ydirection * dt;
        this.rect.x += this.Xspeed * this.Xdirection * dt;

        if (this.rect.y <= Constants.TOOLBAR_HEIGHT + Constants.VT_PADDING || this.rect.y >= Constants.SCREEN_HEIGHT - Constants.INSET_BOTTOM - Constants.VT_PADDING) {
            this.Ydirection *= -1;
            this.Yspeed += 7;
        }
        else if (this.rect.x <= this.leftPaddle.x + this.leftPaddle.width && this.rect.x + this.rect.height >= this.leftPaddle.x && this.rect.y <= this.leftPaddle.y + this.leftPaddle.height && this.rect.y + this.rect.height >= this.leftPaddle.y) {
            this.Xdirection *= -1;
            this.Xspeed += 10;
        }

        else if (this.rect.x + this.rect.width <= this.rightPaddle.x + this.rightPaddle.width && this.rect.x + this.rect.width >= this.rightPaddle.x && this.rect.y <= this.rightPaddle.y + this.rightPaddle.height && this.rect.y + this.rect.height >= this.rightPaddle.y) {
            this.Xdirection *= -1;
            this.Xspeed += 10;
        }
        else if (this.rect.x <= 0) {
            Constants.AI_SCORE += 1;
            System.out.println("You lost a point :(");
            System.out.println("Score: " + Constants.PLAYER_SCORE + " - " + Constants.AI_SCORE);
            // Thread.sleep(3000);

            this.rect.y = Constants.SCREEN_HEIGHT / 2;
            this.rect.x = Constants.SCREEN_WIDTH / 2;
            this.Xspeed = 100;
            this.Yspeed = 100;
        }

        else if (this.rect.x >= Constants.SCREEN_WIDTH) {
            Constants.PLAYER_SCORE += 1;
            System.out.println("You won a point!");
            System.out.println("Score: " + Constants.PLAYER_SCORE + " - " + Constants.AI_SCORE);
            
            this.rect.y = Constants.SCREEN_HEIGHT / 2;
            this.rect.x = Constants.SCREEN_WIDTH / 2;
            this.Xspeed = 100;
            this.Yspeed = 100;
        }
    }
}
