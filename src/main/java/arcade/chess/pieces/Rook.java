package arcade.chess.pieces;

import arcade.chess.App;
import arcade.chess.Piece;
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Application of a {@code Rook} chess piece.
 */
public class Rook extends Piece {

    /**
     * Constructs a {@code Rook} object with a certain color
     * and coordinates.
     * @param a the color
     * @param i the x-coordinate
     * @param j the y-coordinate
     */
    public Rook(boolean a, int i, int j) {
        super();
        setColor((byte) (a ? -1 : 1));
        setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Rook} can move vertically
     * and horizontally.
     */
    public void possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        addV(true, list);
        addV(false, list);
        addH(true, list);
        addH(false, list);
        this.setPossibleMoves(list);
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        if (getColor() == -1) {
            return new Image("/rookW.png", 80, 80, true, false);
        } else {
            //return App.loadImage("rookB.png");
            return new Image("/rookB.png", 80, 80, true, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public char getType() {
        return 'R';
    }
}
