package arcade.chess;

import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Application of a {@code King} chess piece.
 */
public class King extends Piece {

    /**
     * Constructs a {@code King} object with a certain color
     * and coordinates.
     *
     * @param a the color
     * @param i the x-coordinate
     * @param j the y-coordinate
     */
    public King(boolean a, int i, int j) {
        super();
        setColor((byte) (a ? -1 : 1));
        setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code King} can move one step
     * vertically, horizontally, and diagonally.
     */
    public void possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        addV(true, list);
        addV(false, list);
        addH(true, list);
        addH(false, list);
        addD(true, true, list);
        addD(false, false, list);
        addD(true, false, list);
        addD(false, true, list);
        this.setPossibleMoves(list);
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        if (getColor() == -1) {
            return new Image("/kingW.png", 80, 80, true, false);

        } else {
            return new Image("/kingB.png", 80, 80, true, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public char getType() {
        return 'K';
    }
}