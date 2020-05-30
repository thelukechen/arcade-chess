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

        this.setSide(a ? -1 : 1);
        this.setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Knight} can move.
     */
    public int[] possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        int coordinate;
        int[] one = new int[] {1, -1};
        for (Integer e : one) {
            for (Integer i : one) {
                //all Ls
                coordinate = 10 * (getX() - (e * getSide())) + (getY() + (i * (2 * getSide())));
                if (isInGrid(coordinate)) {
                    Square square = this.getSquare().getBoard().squareArr[coordinate / 10][coordinate % 10];
                    if (square.getPiece().getType().equals("Empty") || square.getPiece().getColor() != this.getColor()) {
                        list.add(coordinate);
                    }
                }
                //all guns
                coordinate = 10 * (getX() - (e * (2 * getSide()))) + (getY() + (i * getSide()));
                if (isInGrid(coordinate)) {
                    Square square = this.getSquare().getBoard().squareArr[coordinate / 10][coordinate % 10];
                    if (square.getPiece().getType().equals("Empty") || square.getPiece().getColor() != this.getColor()) {
                        list.add(coordinate);
                    }
                }
            }
        }
        //array
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        this.setPossibleMoves(array);
        return array;
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        if (getColor()) {
            return new Image("/knightW.png", 80, 80, true, false);
        } else {
            return new Image("/knightB.png", 80, 80, true, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getType() {
        return "Knight";
    }
}