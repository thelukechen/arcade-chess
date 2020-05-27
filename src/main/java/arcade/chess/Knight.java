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

        this.setColor(a);
        this.setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Knight} can move.
     */
    public int[] possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        int coordinate;
        
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
            return new Image("/knightW.png");
        } else {
            return new Image("/knightB.png");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getType() {
        return "Knight";
    }
}