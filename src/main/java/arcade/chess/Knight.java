package arcade.chess;

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
                //all Ls
                coordinate = 10 * (getX() - (e * getColor())) + (getY() + (i * (2 * getColor())));
                if (isInGrid(coordinate)) {
                    Square square = getSquare().getBoard().squareArr[coordinate / 10][coordinate % 10];
                    if (square.getPiece().getColor() != this.getColor()) {
                        list.add(coordinate);
                    }
                }
                //all guns
                coordinate = 10 * (getX() - (e * (2 * getColor()))) + (getY() + (i * getColor()));
                if (isInGrid(coordinate)) {
                    Square square = getSquare().getBoard().squareArr[coordinate / 10][coordinate % 10];
                    if (square.getPiece().getColor() != this.getColor()) {
                        list.add(coordinate);
                    }
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