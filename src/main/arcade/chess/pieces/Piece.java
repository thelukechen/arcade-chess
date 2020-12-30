package arcade.chess.pieces;

import arcade.chess.gui.Square;
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Abstract class for all {@code Piece} objects.
 * All {@code Piece} objects have {@code firstMove},
 * a {@code coordinate}, a specified {@code Square} object, and
 * a corresponding {@code Color} to its {@code side} value.
 */
public abstract class Piece {

    private boolean firstMove = true;
    private int x;
    private int y;
    private int coordinate;
    private Square square;
    private byte color;
    private int[] possibleMoves;
    private King king;
    private King opponent;

    /**
     * Returns the image of the {@code Piece} object with color.
     * @return the {@code Piece} image
     */
    public abstract Image image();

    /**
     * Returns the {@code String} of the {@code Piece} object.
     * @return the type of {@code Piece}
     */
    public abstract char getType();

    /**
     * Returns an integer array of coordinates
     * of all possible moves currently available.
     */
    public abstract void possibleMoves();

    /**
     * Finds possible moves of a {@code Piece}.
     * @return true if there is a possible move, false otherwise
     */
    public boolean getPossibleMoves() {
        possibleMoves();
        return possibleMoves.length != 0;
    }

    /**
     * Sets the integer array of possible moves for the {@code Piece}.
     * @param list the specified integer arraylist
     */
    public void setPossibleMoves(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        this.possibleMoves = array;
    }

    /**
     * Returns if the following piece is capable of
     * moving to the specified target {@code Square}.
     * @param target the specified {@code Square} object location
     * @return true if allowed to make move
     */
    public boolean isPossibleMove(Square target) {
        for (int e : possibleMoves) {
            if (target.getCoordinate() == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Number of squares vertical of the piece that have an {@code Empty} piece
     * or opponent piece.
     *
     * @param up true for up, false for down
     * @return value
     */
    public int lookVertical(boolean up) {
        int value = 0;
        int upOrDown = up ? 1 : -1;
        for (int i = 1; i < 8; i++) {
            try {
                Square square = this.getSquare().getBoard().getSquareArr()[getX()][getY() + (upOrDown * (i * color))];
                if (!isInGrid(square.getCoordinate())) {
                    return value;
                } else if (square.getPiece().getType() == 'E') {
                    value++;
                } else if (square.getPiece().getColor() != this.getColor()) {
                    value++;
                    return value;
                } else {
                    return value;
                }
            } catch (IndexOutOfBoundsException ignored) {
                return value;
            }
        }
        return value;
    }

    /**
     * Number of squares horizontal of the piece that have an {@code Empty} piece
     * or an opponent piece.
     *
     * @param right true for right, false for left
     * @return value
     */
    public int lookHorizontal(boolean right) {
        int value = 0;
        int leftOrRight = right ? -1 : 1;
        for (int i = 1; i < 8; i++) {
            try {
                Square square = this.getSquare().getBoard().getSquareArr()[getX() + (leftOrRight * (i * color))][getY()];
                if (!isInGrid(square.getCoordinate())) {
                    return value;
                } else if (square.getPiece().getType() == 'E') {
                    value++;
                } else if (square.getPiece().getColor() != this.getColor()) {
                    value++;
                    return value;
                } else {
                    return value;
                }
            } catch (IndexOutOfBoundsException ignored) {
                return value;
            }
        }
        return value;
    }

    /**
     * Number of squares along the diagonals with an {@code Empty}
     * or opponent {@code Piece}..
     *
     * @param up true for above, false for below
     * @param right true for right, false for left
     * @return value
     */
    public int lookDiagonal(boolean up, boolean right) {
        int value = 0;
        int rightOrLeft = right ? 1 : -1;
        for (int i = 1; i < 8; i++) {
            try {
                Square square;
                if (up) {
                    square = this.getSquare().getBoard().getSquareArr()[getX() - (rightOrLeft * (i * color))][getY() + (i * color)];
                } else {
                    rightOrLeft = right ? -1 : 1;
                    square = this.getSquare().getBoard().getSquareArr()[getX() + (rightOrLeft * (i * color))][getY() - (i * color)];
                }
                if (!isInGrid(square.getCoordinate())) {
                    return value;
                } else if (square.getPiece().getType() == 'E') {
                    value++;
                } else if (square.getPiece().getColor() != this.getColor()) {
                    value++;
                    return value;
                } else {
                    return value;
                }
            } catch (IndexOutOfBoundsException ignored) {
                return value;
            }
        }
        return value;
    }

    /**
     * Decides if the {@code Piece} is a valid move. Shows if the {@code Piece}
     * is in the grid and not exposing the {@code King} to check.
     * @param coordinate the coordinate
     * @return true if moves if allowed, false otherwise
     */
    public boolean isValidMove(int coordinate) {
        if (isInGrid(coordinate) && !ifOwnKingInCheck(coordinate)) {
            return getSquare().getBoard().getSquareArr()[coordinate / 10][coordinate % 10].getPiece().getColor() != this.getColor();
        }
        return false;
    }

    /**
     * Checks if the {@code Piece} moving to the {@code coordinate} causes its
     * {@code King} to be in check.
     * @param coordinate the coordinate
     * @return true if own {@code King} is in check
     */
    public boolean ifOwnKingInCheck(int coordinate) {
        Piece target = getSquare().getBoard().getSquareArr()[coordinate / 10][coordinate % 10].getPiece();
        int first = this.coordinate;
        getSquare().getBoard().getSquareArr()[coordinate / 10][coordinate % 10].setPiece(this);
        this.setCoordinate(coordinate);
        if (this.king.isChecked()) {
            getSquare().getBoard().getSquareArr()[coordinate / 10][coordinate % 10].setPiece(target);
            this.setCoordinate(first);
            return true;
        } else {
            getSquare().getBoard().getSquareArr()[coordinate / 10][coordinate % 10].setPiece(target);
            this.setCoordinate(first);
            return false;
        }
    }

    /**
     * Adds the diagonal possible moves of that {@code Piece}.
     * @param up true if up, false if down
     * @param right true if right, false if left
     * @param list the list of possible moves
     */
    public void addD(boolean up, boolean right, ArrayList<Integer> list) {
        int counter1 = right ? -1 : 1;
        if (getType() == 'P') { //pawn take
            int coordinate = 10 * (getX() + (counter1 * getColor())) + (getY() + getColor());
            if (lookDiagonal(true, right) == 1) {
                if (getSquare().getBoard().getSquareArr()[coordinate / 10][coordinate % 10].getPiece().getColor() * -1 == getColor()) {
                    if (isValidMove(coordinate)) {
                        list.add(coordinate);
                    }
                }
            }
        } else {
            int counter2 = up ? 1 : -1;
            if (getType() == 'K') { //king
                if (lookDiagonal(up, right) > 0) {
                    if (isValidMove(10 * (getX() + (counter1) * getColor()) + (getY() + (counter2) * getColor()))) {
                        list.add(10 * (getX() + (counter1) * getColor()) + (getY() + (counter2) * getColor()));
                    }
                }
            } else { //every other piece
                for (int i = 1; i <= lookDiagonal(up, right); i++) {
                    if (isValidMove(10 * (getX() + (counter1) * (i * getColor())) + (getY() + (counter2) * (i * getColor())))) {
                        list.add(10 * (getX() + (counter1) * (i * getColor())) + (getY() + (counter2) * (i * getColor())));
                    }
                }
            }
        }
    }

    /**
     * Adds the vertical possible moves of that {@code Piece}.
     * @param up true if up, false if down
     * @param list the list of possible moves
     */
    public void addV(boolean up, ArrayList<Integer> list) {
        int counter = up ? 1 : -1;
        if (getType() == 'K') { //king
            if (lookVertical(up) > 0) {
                if (isValidMove(10 * getX() + (getY() + (counter) * getColor()))) {
                    list.add(10 * getX() + (getY() + (counter) * getColor()));
                }
            }
        } else { //every other piece
            for (int i = 1; i <= lookVertical(up); i++) {
                if (isValidMove(10 * getX() + (getY() + (counter) * (i * getColor())))) {
                    list.add(10 * getX() + (getY() + (counter) * (i * getColor())));
                }
            }
        }
    }

    /**
     * Adds the horizontal possible moves of that {@code Piece}.
     *
     * @param right true if right, false if left
     * @param list the list of possible moves
     */
    public void addH(boolean right, ArrayList<Integer> list) {
        int counter = right ? -1 : 1;
        if (getType() == 'K') { //king
            if (lookHorizontal(right) > 0) {
                if (isValidMove(10 * (getX() + (counter) * getColor()) + getY())) {
                    list.add(10 * (getX() + (counter) * getColor()) + getY());
                }
            }
        } else {
            for (int i= 1; i <= lookHorizontal(right); i++) {
                if (isValidMove(10 * (getX() + (counter) * (i * getColor())) + getY())) {
                    list.add(10 * (getX() + (counter) * (i * getColor())) + getY());
                }
            }
        }
    }

    /**
     * Checks if the given coordinates exist on the chess board.
     * @param coordinate the specified coordinate
     * @return if the coordinate is on the board
     */
    public boolean isInGrid(int coordinate) {
        int[] invalidNums = new int[] {8, 9, 18, 19, 28, 29, 38, 39, 48, 49, 58, 59, 68, 69};
        for (Integer e : invalidNums) {
            if (e == coordinate) {
                return false;
            }
        }
        return coordinate >= 0 && coordinate <= 77;
    }

    /**
     * Returns the {@code color} value of the {@code Piece}.
     * @return the color
     */
    public byte getColor() {
        return this.color;
    }

    /**
     * Sets the {@code color} value of the {@code Piece}.
     * @param color the specified {@code side} value
     */
    public void setColor(byte color) {
        this.color = color;
    }

    /**
     * Sets the {@code Square} of the current {@code Piece}.
     * @param square the specified {@code Square} object
     */
    public void setSquare(Square square) {
        this.square = square;
    }

    /**
     * Returns the {@code Square} object where the {@code Piece} is located.
     * @return the specific {@code Square}
     */
    public Square getSquare() {
        return square;
    }

    /**
     * Returns the coordinates in a two digit number
     * @return X value in the tens digit, Y value in the ones digit.
     */
    public int getCoordinate() {
        return coordinate;
    }

    /**
     * Sets the new coordinates of the {@code Piece}.
     * Also changes the values of {@code x} and {@code y}.
     * @param coordinate the specified coordinates
     */
    public void setCoordinate(int coordinate) {
        this.setX(coordinate / 10);
        this.setY(coordinate % 10);
        this.coordinate = coordinate;
    }

    /**
     * Returns the x-coordinate of the {@code Piece}.
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the {@code Piece}.
     * @param x the specified x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the {@code Piece}.
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the {@code Piece}.
     * @param y the specified y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns if the {@code Piece} has not moved yet.
     * @return true if not moved yet, otherwise false
     */
    public boolean isFirstMove() {
        return firstMove;
    }

    /**
     * Sets the {@code firstMove} value.
     * @param firstMove the specified value
     */
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public King getKing() {
        return this.king;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public King getOpponent() {
        return this.opponent;
    }

    public void setOpponent(King opponent) {
        this.opponent = opponent;
    }
}
