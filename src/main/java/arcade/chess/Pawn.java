package arcade.chess;

import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Application of a {@code Pawn} chess piece.
 * Includes {@code justDoubleMoved} and {@code enPassant}
 * for the en passant functionality.
 */
public class Pawn extends Piece {

    private boolean justDoubleMoved = false;
    private int enPassant = -1;

    /**
     * Constructs a {@code Piece} object with a certain color
     * and coordinates.
     *
     * @param a the color
     * @param i the x-coordinate
     * @param j the y-coordinate
     */
    public Pawn(boolean a, int i, int j) {
        super();
        setColor((byte) (a ? -1 : 1));
        setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Pawn} can move up 2, move up 1,
     * take diagonally left, take diagonally right, or
     * is able to en passant.
     */
    public void possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        int coordinate;
        if (isFirstMove()) { //double move
            coordinate = 10 * getX() + (getY() + (2 * getColor()));
            if (lookVertical(true) > 2) {
                list.add(coordinate);
            }
        }
        coordinate = 10 * getX() + (getY() + getColor());
        if (lookVertical(true) > 0) { //up one
            if (getSquare().getBoard().squareArr[coordinate / 10][coordinate % 10].getPiece().getColor() == 0) {
                list.add(coordinate);
            }
        }
        addD(false, true, list); //right take
        addD(false, false, list); //left take
        addEnPassant(list); //en passant
        this.setPossibleMoves(list);
    }

    /**
     * Adds if the {@code Pawn} object has the en passant functionality.
     *
     * @param list the arraylist in {@code possibleMoves}
     */
    public void addEnPassant(ArrayList<Integer> list) {
        for (int i = -1; i <= 1; i++) {
            if (i == 0) {
                continue;
            }
            boolean right = i == -1;
            if (lookHorizontal(right) == 1) {
                Piece piece = getSquare().getBoard().squareArr[getX() + i * getColor()][getY()].getPiece();
                if ((piece.getColor() * -1 == getColor()) && piece.getType() == 'P') {
                    Pawn pawn = (Pawn) piece;
                    if (pawn.isJustDoubleMoved()) {
                        list.add(10 * (getX() + i * getColor()) + (getY() + getColor()));
                        setEnPassant(10 * (getX() + i * getColor()) + (getY() + getColor()));
                    }
                }
            }
        }
    }

    /**
     * Returns in the {@code Pawn} has just double moved.
     * @return true if it just double moved, false otherwise
     */
    public boolean isJustDoubleMoved() {
        return justDoubleMoved;
    }

    /**
     * Sets the value of {@code justDoubleMoved} in the {@code Pawn}.
     * @param justDoubleMoved if {@code target} is null, sets {@code justDoubleMoved}
     *                        to this value
     */
    public void setJustDoubleMoved(boolean justDoubleMoved) {
        this.justDoubleMoved = justDoubleMoved;
    }

    /**
     * Returns the en passant coordinates.
     *
     * @return the en passant coordinates
     */
    public int getEnPassant() {
        return this.enPassant;
    }

    /**
     * Sets the {@code enPassant} value of the {@code Pawn}.
     *
     * @param enPassant the specified value
     */
    public void setEnPassant(int enPassant) {
        if (enPassant == -1) {
            setJustDoubleMoved(false);
        }
        this.enPassant = enPassant;
    }

    /**
     * {@inheritDoc}
     */
    public char getType() {
        return 'P';
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        if (getColor() == -1) {
            return new Image("/pawnW.png", 80, 80, true, false);
        } else {
            return new Image("/pawnB.png", 80, 80, true, false);
        }
    }
}
