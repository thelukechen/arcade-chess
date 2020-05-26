package arcade.chess;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Application of a {@code Pawn} chess piece.
 */
public class Pawn extends Piece {

    private boolean justDoubleMoved;
    private int enPassant;

    public Pawn(boolean a, int i, int j) {
        super();

        this.setColor(a);
        this.setCoordinate(10 * i + j);
        this.justDoubleMoved = false;
        this.enPassant = -1;
    }

    public int[] possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        int coordinate;
        //first move
        if (isFirstMove()) {
            coordinate = 10 * getX() + (getY() + (2 * getSide()));
            if (lookUp() > 1 && isInGrid(coordinate)) {
                list.add(coordinate);
            }
        }
        //up one
        coordinate = 10 * getX() + (getY() + getSide());
        if (lookUp() > 0 && isInGrid(coordinate)) {
            list.add(coordinate);
        }
        //take diagonal left
        coordinate = 10 * (getX() + getSide()) + (getY() + getSide());
        if (lookNeg(true) == 0 && isInGrid(coordinate)) {
            list.add(coordinate);
        }
        //take diagonal right
        coordinate = 10 * (getX() - getSide()) + (getY() + getSide());
        if (lookPos(true) == 0 && isInGrid(coordinate)) {
            list.add(coordinate);
        }
        //en passant
        Piece piece;
        if (lookLeft() == 0 && isInGrid((10 * (getX() + getSide())) + getY())) {
            piece = getSquare().getBoard().squareArr[getX() + getSide()][getY()].getPiece();
            if ((piece.getColor() != getColor()) && piece.getType().equals("Pawn")) {
                Pawn pawn = (Pawn) piece;
                if (pawn.isJustDoubleMoved()) {
                    list.add(10 * (getX() + getSide()) + (getY() + getSide()));
                    setEnPassant(10 * (getX() + getSide()) + (getY() + getSide()));
                }

            }
        }
        if (lookRight() == 0 && isInGrid((10 * (getX() - getSide())) + getY())) {
            piece = getSquare().getBoard().squareArr[getX() - getSide()][getY()].getPiece();
            if ((piece.getColor() != getColor()) && piece.getType().equals("Pawn")) {
                Pawn pawn = (Pawn) piece;
                if (pawn.isJustDoubleMoved()) {
                    list.add(10 * (getX() - getSide()) + (getY() + getSide()));
                    setEnPassant(10 * (getX() - getSide()) + (getY() + getSide()));
                }

            }
        }

        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public boolean isJustDoubleMoved() {
        return justDoubleMoved;
    }

    public void setJustDoubleMoved(Square target, boolean justDoubleMoved) {
        if (target != null) {
            if (target.getCoordinate() == 10 * getX() + (getY() + (2 * getSide()))) {
                this.justDoubleMoved = true;
            }
        } else {
            this.justDoubleMoved = justDoubleMoved;
        }
    }

    public int getEnPassant() {
        return this.enPassant;
    }

    public void setEnPassant(int enPassant) {
        this.enPassant = enPassant;
    }

    public String getType() {
        return "Pawn";
    }

    public Image image() {
        if (getColor()) {
            return new Image("/pawnW.png");
        } else {
            return new Image("/pawnB.png");
        }
    }
}
