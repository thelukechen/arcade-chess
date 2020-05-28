package arcade.chess;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Application of a {@code Pawn} chess piece.
 * Includes {@code justDoubleMoved} and {@code enPassant}
 * for the en passant functionality.
 */
public class Pawn extends Piece {

    private boolean justDoubleMoved;
    private int enPassant;

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

        this.setSide(a ? -1 : 1);
        this.setCoordinate(10 * i + j);
        this.justDoubleMoved = false;
        this.enPassant = -1;
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code Pawn} can move up 2, move up 1,
     * take diagonally left, take diagonally right, or
     * is able to en passant.
     */
    public int[] possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        int coordinate;
        //Up 2
        if (isFirstMove()) {
            coordinate = 10 * getX() + (getY() + (2 * getSide()));
            if (lookVertical(true) > 2 && isInGrid(coordinate)) {
                list.add(coordinate);
            }
        }
        //Up 1
        coordinate = 10 * getX() + (getY() + getSide());
        if (lookVertical(true) > 1 && isInGrid(coordinate)) {
            list.add(coordinate);
        }
        //Diagonal Left Take
        coordinate = 10 * (getX() + getSide()) + (getY() + getSide());
        int side;
        if (lookDiagonal(true, false) == 1 && isInGrid(coordinate)) {
            side = getSquare().getBoard().squareArr[coordinate / 10][coordinate % 10].getPiece().getSide();
            if (side != this.getSide() && side != 0) {
                list.add(coordinate);
            }
        }
        //Diagonal Right Take
        coordinate = 10 * (getX() - getSide()) + (getY() + getSide());
        if (lookDiagonal(true, true) == 1 && isInGrid(coordinate)) {
            side = getSquare().getBoard().squareArr[coordinate / 10][coordinate % 10].getPiece().getSide();
            if (side != this.getSide() && side != 0) {
                list.add(coordinate);
            }
        }
        //En Passant
        addEnPassant(list);
        //Add elements to array
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * Adds if the {@code Pawn} object has the en passant functionality.
     *
     * @param list the arraylist in {@code possibleMoves}
     */
    public void addEnPassant(ArrayList<Integer> list) {
        Piece piece;
        if (lookHorizontal(false) == 1 && isInGrid((10 * (getX() + getSide())) + getY())) {
            piece = getSquare().getBoard().squareArr[getX() + getSide()][getY()].getPiece();
            if ((piece.getColor() != getColor()) && piece.getType().equals("Pawn")) {
                Pawn pawn = (Pawn) piece;
                if (pawn.isJustDoubleMoved()) {
                    list.add(10 * (getX() + getSide()) + (getY() + getSide()));
                    setEnPassant(10 * (getX() + getSide()) + (getY() + getSide()));
                }

            }
        }
        if (lookHorizontal(true) == 1 && isInGrid((10 * (getX() - getSide())) + getY())) {
            piece = getSquare().getBoard().squareArr[getX() - getSide()][getY()].getPiece();
            if ((piece.getColor() != getColor()) && piece.getType().equals("Pawn")) {
                Pawn pawn = (Pawn) piece;
                if (pawn.isJustDoubleMoved()) {
                    list.add(10 * (getX() - getSide()) + (getY() + getSide()));
                    setEnPassant(10 * (getX() - getSide()) + (getY() + getSide()));
                }

            }
        }
    }

    /**
     * Returns in the {@code Pawn} has just double moved.
     *
     * @return true if it just double moved, false otherwise
     */
    public boolean isJustDoubleMoved() {
        return justDoubleMoved;
    }

    /**
     * Sets the value of {@code justDoubleMoved} in the {@code Pawn}.
     *
     * @param target          the specified {@code Square} location
     * @param justDoubleMoved if {@code target} is null, sets {@code justDoubleMoved}
     *                        to this value
     */
    public void setJustDoubleMoved(Square target, boolean justDoubleMoved) {
        if (target != null) {
            if (target.getCoordinate() == 10 * getX() + (getY() + (2 * getSide()))) {
                this.justDoubleMoved = true;
            }
        } else {
            this.justDoubleMoved = justDoubleMoved;
        }
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
        this.enPassant = enPassant;
    }

    /**
     * {@inheritDoc}
     */
    public String getType() {
        return "Pawn";
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        if (getColor()) {
            return new Image("/pawnW.png");
        } else {
            return new Image("/pawnB.png");
        }
    }
}
