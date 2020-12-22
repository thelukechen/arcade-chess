package arcade.chess;

import javafx.scene.image.Image;

/**
 * Application of a {@code Empty} chess piece.
 */
public class Empty extends Piece {

    /**
     * Constructs a {@code Empty} object with certain coordinates.
     * @param i the x-coordinate
     * @param j the y-coordinate
     */
    public Empty(int i, int j) {
        setCoordinate(10 * i + j);
        setColor((byte) 0);
    }

    /**
     * {@inheritDoc}
     * Returns an integer array of size 0 because
     * {@code Empty} objects cannot move
     */
    public void possibleMoves() {
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public char getType() {
        return 'E';
    }
}
