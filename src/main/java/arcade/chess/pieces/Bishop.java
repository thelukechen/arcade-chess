package arcade.chess.pieces;

import arcade.chess.Piece;
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Application of a {@code Rook} chess piece.
 */
public class Bishop extends Piece {

    /**
     * Constructs a {@code Rook} object with a certain color
     * and coordinates.
     * @param a the color
     * @param i the x-coordinate
     * @param j the y-coordinate
     */
    public Bishop(boolean a, int i, int j) {
        super();
        setColor((byte) (a ? -1 : 1));
        setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Bishop} can move diagonally.
     */
    public void possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        addD(true, true, list); //up positive diagonal
        addD(false, false, list); //down positive diagonal
        addD(true, false, list); //up negative diagonal
        addD(false, true, list); //down negative diagonal
        this.setPossibleMoves(list);
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        if (getColor() == -1) {
            return new Image("/bishopW.png", 80, 80, true, false);
        } else {
            return new Image("/bishopB.png", 80, 80, true, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public char getType() {
        return 'B';
    }
}
