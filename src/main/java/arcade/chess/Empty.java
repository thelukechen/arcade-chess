package arcade.chess;

import javafx.scene.image.Image;

public class Empty extends Piece {

    public Empty(int i, int j) {
        setCoordinate(10 * i + j);
        this.setSide(0);
    }

    public int[] possibleMoves() {
        return new int[0];
    }

    public Image image() {
        return null;
    }

    public String getType() {
        return "Empty";
    }
}
