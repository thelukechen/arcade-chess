package arcade.chess;

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

        this.setColor(a);
        this.setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Rook} can move diagonally.
     */
    public int[] possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        int coordinate;
        //up the positive diagonal
        for (int i = 1; i <= lookDiagonal(true, true); i++) {
            coordinate = 10 * (getX() - (i * getSide())) + (getY() + (i * getSide()));
            list.add(coordinate);
        }
        //down the positive diagonal
        for (int i = 1; i <= lookDiagonal(false, false); i++) {
            coordinate = 10 * (getX() + (i * getSide())) + (getY() - (i * getSide()));
            list.add(coordinate);
        }
        //up the negative diagonal
        for (int i = 1; i <= lookDiagonal(true, false); i++) {
            coordinate = 10 * (getX() + (i * getSide())) + (getY() + (i * getSide()));
            list.add(coordinate);
        }
        //down the negative diagonal
        for (int i = 1; i <= lookDiagonal(false, true); i++) {
            coordinate = 10 * (getX() - (i * getSide())) + (getY() - (i * getSide()));
            list.add(coordinate);
        }

        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        if (getColor()) {
            return new Image("/bishopW.png");
        } else {
            return new Image("/bishopB.png");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getType() {
        return "Bishop";
    }
}
