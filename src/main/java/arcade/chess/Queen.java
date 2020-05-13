package arcade.chess;

public class Queen extends Piece {

    private String image = "Q";
    private boolean firstMove = false;
    private int x;
    private int y;
    private final boolean color;

    public Queen(int i, int j, boolean type) {
        super();
        x = i;
        y = j;
        color = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isColor() {
        return color;
    }
}