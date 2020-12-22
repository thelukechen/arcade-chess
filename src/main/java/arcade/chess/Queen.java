package arcade.chess;

import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Application of a {@code Queen} chess piece.
 */
public class Queen extends Piece {

    /**
     * Constructs a {@code Queen} object with a certain color
     * and coordinates.
     * @param a the color
     * @param i the x-coordinate
     * @param j the y-coordinate
     */
    public Queen(boolean a, int i, int j) {
        super();
        setColor((byte) (a ? -1 : 1));
        setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Queen} can move vertically
     * , horizontally, and diagonally.
     */
    public void possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        addV(true, list);
        addV(false, list);
        addH(true, list);
        addH(false, list);
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
            return new Image("/queenW.png", 80, 80, true, false);
        } else {
            return new Image("/queenB.png", 80, 80, true, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public char getType() {
        return 'Q';
    }
}
