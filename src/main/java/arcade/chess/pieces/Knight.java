package arcade.chess.pieces;

import arcade.chess.Piece;
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Application of a {@code Knight} chess piece.
 */
public class Knight extends Piece {

    /**
     * Constructs a {@code Knight} object with a certain color
     * and coordinates.
     *
     * @param a the color
     * @param i the x-coordinate
     * @param j the y-coordinate
     */
    public Knight(boolean a, int i, int j) {
        super();
        setColor((byte) (a ? -1 : 1));
        setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Knight} can move.
     */
    public void possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        int coordinate;
        int[] one = new int[] {1, -1};
        for (Integer e : one) {
            for (Integer i : one) {
                coordinate = 10 * (getX() - (e * getColor())) + (getY() + (i * (2 * getColor()))); //all Ls
                if (isValidMove(coordinate)) {
                    list.add(coordinate);
                }
                coordinate = 10 * (getX() - (e * (2 * getColor()))) + (getY() + (i * getColor())); //all guns
                if (isValidMove(coordinate)) {
                    list.add(coordinate);
                }
            }
        }
        this.setPossibleMoves(list);
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        if (getColor() == -1) {
            return new Image("/knightW.png", 80, 80, true, false);
        } else {
            return new Image("/knightB.png", 80, 80, true, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public char getType() {
        return 'N';
    }
}