package arcade.chess;

import javafx.scene.image.Image;

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
    private int side;
    private boolean color;

    /**
     * Returns the image of the {@code Piece} object with color.
     * @return the {@code Piece} image
     */
    public abstract Image image();

    /**
     * Returns the {@code String} of the {@code Piece} object.
     * @return the type of {@code Piece}
     */
    public abstract String getType();

    /**
     * Returns an integer array of coordinates
     * of all possible moves currently available.
     * @return the list of all possible moves
     */
    public abstract int[] possibleMoves();

    /**
     * Returns if the following piece is capable of
     * moving to the specified target {@code Square}.
     * @param target the specified {@code Square} object location
     * @return true if allowed to make move
     */
    public boolean isValidMove(Square target) {
        for (int e : this.possibleMoves()) {
            if (target.getCoordinate() == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Number of squares vertical of the piece that have an {@code Empty} piece.
     *
     * @param up true for up, false for down
     * @return value
     */
    public int lookVertical(boolean up) {
        int value = 0;
        int upOrDown = up ? 1 : -1;
        for (int i = 1; i < 8; i++) {
            try {
                Square square = this.getSquare().getBoard().squareArr[getX()][getY() + (upOrDown * (i * side))];
                if (square.getPiece().getType().equals("Empty")) {
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
     * Number of squares horizontal of the piece that have an {@code Empty} piece.
     *
     * @param right true for right, false for left
     * @return value
     */
    public int lookHorizontal(boolean right) {
        int value = 0;
        int leftOrRight = right ? -1 : 1;
        for (int i = 1; i < 8; i++) {
            try {
                Square square = this.getSquare().getBoard().squareArr[getX() + (leftOrRight * (i * side))][getY()];
                if (square.getPiece().getType().equals("Empty")) {
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
     * Number of squares along the diagonals.
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
                    square = this.getSquare().getBoard().squareArr[getX() - (rightOrLeft * (i * side))][getY() + (i * side)];
                } else {
                    rightOrLeft = right ? -1 : 1;
                    square = this.getSquare().getBoard().squareArr[getX() + (rightOrLeft * (i * side))][getY() - (i * side)];
                }
                if (square.getPiece().getType().equals("Empty")) {
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
     * Returns the color of the chess {@code Piece}.
     * @return true if white, false if black
     */
    public boolean getColor() {
        return this.color;
    }

    /**
     * Sets the color of the {@code Piece}.
     * Also sets the side value: -1 for white, 1 for black.
     * @param color the specified {@code Piece} color
     */
    public void setColor(boolean color) {
        this.color = color;
        side = color ? -1 : 1;
    }

    /**
     * Returns the {@code side} value.
     * @return the side
     */
    public int getSide() {
        return this.side;
    }

    /**
     * Sets the side value.
     * @param side the specified {@code side} value
     */
    public void setSide(int side) {
        this.color = side == -1;
        this.side = side;
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
     * @return the speific {@code Square}
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
     * @param coord the specified coordinates
     */
    public void setCoordinate(int coord) {
        this.setX(coord / 10);
        this.setY(coord % 10);
        this.coordinate = coord;
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
}
