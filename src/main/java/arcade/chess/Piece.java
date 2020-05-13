package arcade.chess;

public abstract class Piece {

    private int x;
    private int y;
    private boolean firstMove;
    private String image;
    private boolean color;

    private boolean isFirstMove() {
        return firstMove;
    }

}
