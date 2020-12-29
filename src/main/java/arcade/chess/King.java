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
        setColor((byte) (a ? -1 : 1));
        setCoordinate(10 * i + j);
    }

    /**
     * {@inheritDoc}
     * Finds if the {@code King} can move one step
     * vertically, horizontally, and diagonally.
     */
    public void possibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        addV(true, list);
        addV(false, list);
        addH(true, list);
        addH(false, list);
        addD(true, true, list);
        addD(false, false, list);
        addD(true, false, list);
        addD(false, true, list);
        addCastle(list);
        this.setPossibleMoves(list);
    }

    /**
     * Adds the {@code King}'s movement if a castle if allowed.
     * @param list the list of possible moves
     */
    public void addCastle(ArrayList<Integer> list) {
        if (isFirstMove()) {
            if (getColor() == -1) {
                if (lookHorizontal(true) >= 2 && getSquare().getBoard().squareArr[7][7].getPiece().isFirstMove()) {
                    if (isValidMove(57) && isValidMove(67)) {
                        list.add(67);
                    }
                }
                if (lookHorizontal(false) >= 2 && getSquare().getBoard().squareArr[0][7].getPiece().isFirstMove()) {
                    if (isValidMove(27) && isValidMove(37)) {
                        list.add(27);
                    }
                }
            } else {
                if (lookHorizontal(true) >= 2 && getSquare().getBoard().squareArr[0][0].getPiece().isFirstMove()) {
                    if (isValidMove(20) && isValidMove(30)) {
                        list.add(20);
                    }
                }
                if (lookHorizontal(false) >= 2 && getSquare().getBoard().squareArr[7][0].getPiece().isFirstMove()) {
                    if (isValidMove(50) && isValidMove(60)) {
                        list.add(60);
                    }
                }
            }
        }
    }

    /**
     * Checks if the {@code King} is in check before and after a move.
     * @return true if the King is in check, false otherwise
     */
    public boolean isChecked() {
        boolean bool = true;
        for (int i = -1; i <= 1; i++) { //-1 for down and right, 1 for up and left
            if (i != 0) {
                if (i == 1) {
                bool = false;
                }
                Piece piece = getSquare().getBoard().squareArr[getX()][getY() + i * getColor() * lookVertical(!bool)].getPiece();
                if (piece.getType() == 'R' || piece.getType() == 'Q') {
                    return true;
                }
                piece = getSquare().getBoard().squareArr[getX() + i * getColor() * lookHorizontal(bool)][getY()].getPiece();
                if (piece.getType() == 'R' || piece.getType() == 'Q') {
                    return true;
                }
            }
        }
        int[] one = new int[] {-1, 1};
        for (Integer i : one) { //(-1,-1) up left. (-1,1) bot left. (1,-1) up right. (1,1) bot right
            for (Integer j : one) {
                boolean right = i == -1;
                boolean up = j == 1;
                Piece piece = getSquare().getBoard().squareArr[getX() + i * getColor() * lookDiagonal(up, right)][getY() + j * getColor() * lookDiagonal(up, right)].getPiece();
                if (piece.getType() == 'B' || piece.getType() == 'Q') {
                    return true;
                } else if (piece.getType() == 'P' && lookDiagonal(true, right) == 1) { //pawn check
                    return true;
                }
            }
        }
        for (Integer e : one) { //knights
            for (Integer i : one) {
                //all Ls
                int coordinate = 10 * (getX() - (e * getColor())) + (getY() + (i * (2 * getColor())));
                if (isInGrid(coordinate)) {
                    Piece piece = getSquare().getBoard().squareArr[coordinate / 10][coordinate % 10].getPiece();
                    if (piece.getColor() != getColor() && piece.getType() == 'N') {
                        return true;
                    }
                }
                //all guns
                coordinate = 10 * (getX() - (e * (2 * getColor()))) + (getY() + (i * getColor()));
                if (isInGrid(coordinate)) {
                    Piece piece = getSquare().getBoard().squareArr[coordinate / 10][coordinate % 10].getPiece();
                    if (piece.getColor() != getColor() && piece.getType() == 'N') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Image image() {
        if (getColor() == -1) {
            return new Image("/kingW.png", 80, 80, true, false);

        } else {
            return new Image("/kingB.png", 80, 80, true, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public char getType() {
        return 'K';
    }
}