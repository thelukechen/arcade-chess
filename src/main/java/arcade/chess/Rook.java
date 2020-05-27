package arcade.chess;

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

        this.setColor(a);
        this.setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Rook} can move vertically
     * and horizontally.
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
            return new Image("/rookW.png");
        } else {
            return new Image("/rookB.png");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getType() {
        return "Rook";
    }
}