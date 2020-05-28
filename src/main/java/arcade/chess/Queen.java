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

        this.setSide(a ? -1 : 1);
        this.setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Queen} can move vertically
     * , horizontally, and diagonally.
     */
    public int[] possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        int coordinate;
        //up
        for (int i = 1; i <= lookVertical(true); i++) {
            coordinate = 10 * getX() + (getY() + (i * getSide()));
            list.add(coordinate);
        }
        //down
        for (int i = 1; i <= lookVertical(false); i++) {
            coordinate = 10 * getX() + (getY() - (i * getSide()));
            list.add(coordinate);
        }
        //right
        for (int i = 1; i <= lookHorizontal(true); i++) {
            coordinate = 10 * (getX() - (i * getSide())) + getY();
            list.add(coordinate);
        }
        //left
        for (int i = 1; i <= lookHorizontal(false); i++) {
            coordinate = 10 * (getX() + (i * getSide())) + getY();
            list.add(coordinate);
        }
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
            return new Image("/queenW.png");
        } else {
            return new Image("/queenB.png");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getType() {
        return "Queen";
    }
}
