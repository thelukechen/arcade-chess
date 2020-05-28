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

        this.setSide(a ? -1 : 1);
        this.setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code King} can move one step
     * vertically, horizontally, and diagonally.
     */
    public int[] possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        int coordinate;
        //up
        if (lookVertical(true) > 0) {
            coordinate = 10 * getX() + (getY() + getSide());
            list.add(coordinate);
        }
        //down
        if (lookVertical(false) > 0) {
            coordinate = 10 * getX() + (getY() - getSide());
            list.add(coordinate);
        }
        //right
        if (lookHorizontal(true) > 0) {
            coordinate = 10 * (getX() - getSide()) + getY();
            list.add(coordinate);
        }
        //left
        if (lookHorizontal(false) > 0) {
            coordinate = 10 * (getX() + getSide()) + getY();
            list.add(coordinate);
        }
        //up the positive diagonal
        if (lookDiagonal(true, true) > 0) {
            coordinate = 10 * (getX() - getSide()) + (getY() + getSide());
            list.add(coordinate);
        }
        //down the positive diagonal
        if (lookDiagonal(false, false) > 0) {
            coordinate = 10 * (getX() + getSide()) + (getY() - getSide());
            list.add(coordinate);
        }
        //up the negative diagonal
        if (lookDiagonal(true, false) > 0) {
            coordinate = 10 * (getX() + getSide()) + (getY() + getSide());
            list.add(coordinate);
        }
        //down the negative diagonal
        if (lookDiagonal(false, true) > 0) {
            coordinate = 10 * (getX() - getSide()) + (getY() - getSide());
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
            return new Image("/kingW.png");
        } else {
            return new Image("/kingB.png");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getType() {
        return "King";
    }
}
