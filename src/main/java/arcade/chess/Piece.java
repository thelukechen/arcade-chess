package arcade.chess;

import javafx.scene.image.Image;

public abstract class Piece {

    private boolean firstMove = true;
    private int x;
    private int y;
    private int coordinate;
    private Square square;
    private int side;
    private boolean color;

    public abstract Image image();
    public abstract String getType();
    public abstract int[] possibleMoves();

    public boolean isValidMove(Square target) {
        for (int e : this.possibleMoves()) {
            if (target.getCoordinate() == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Number of squares above the piece that have an {@code Empty} piece.
     *
     * @return value
     */
    public int lookUp() {
        int value = 0;
        for (int i = 1; i < 8; i++) {
            try {
                if (this.getSquare().getBoard().squareArr[getX()][getY() + (i * side)].getPiece().getType().equals("Empty")) {
                    value++;
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
     * Number of squares right of the piece that have an {@code Empty} piece.
     *
     * @return value
     */
    public int lookRight() {
        int value = 0;
        for (int i = 1; i < 8; i++) {
            try {
                if (this.getSquare().getBoard().squareArr[getX() - (i * side)][getY()].getPiece().getType().equals("Empty")) {
                    value++;
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
     * Number of squares left of the piece that have an {@code Empty} piece.
     *
     * @return value
     */
    public int lookLeft() {
        int value = 0;
        for (int i = 1; i < 8; i++) {
            try {
                if (this.getSquare().getBoard().squareArr[getX() + (i * side)][getY()].getPiece().getType().equals("Empty")) {
                    value++;
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
     * Number of squares along the negative diagonal
     *
     * @param up for above or below the square
     * @return value
     */
    public int lookNeg(boolean up) {
        int value = 0;
        for (int i = 1; i < 8; i++) {
            try {
                if (up) {
                    if (this.getSquare().getBoard().squareArr[getX() + (i * side)][getY() + (i * side)].getPiece().getType().equals("Empty")) {
                        value++;
                    } else {
                        return value;
                    }
                } else {
                    if (this.getSquare().getBoard().squareArr[getX() - (i * side)][getY() - (i * side)].getPiece().getType().equals("Empty")) {
                        value++;
                    } else {
                        return value;
                    }
                }
            } catch (IndexOutOfBoundsException ignored) {
                return value;
            }
        }
        return value;
    }

    /**
     * Number of squares along the positive diagonal
     *
     * @param up for above or below the square
     * @return value
     */
    public int lookPos(boolean up) {
        int value = 0;
        for (int i = 1; i < 8; i++) {
            try {
                if (up) {
                    if (this.getSquare().getBoard().squareArr[getX() - (i * side)][getY() + (i * side)].getPiece().getType().equals("Empty")) {
                        value++;
                    } else {
                        return value;
                    }
                } else {
                    if (this.getSquare().getBoard().squareArr[getX() + (i * side)][getY() - (i * side)].getPiece().getType().equals("Empty")) {
                        value++;
                    } else {
                        return value;
                    }
                }
            } catch (IndexOutOfBoundsException ignored) {
                return value;
            }
        }
        return value;
    }

    public boolean isInGrid(int coordinate) {
        return coordinate > 0 && coordinate < 77;
    }

    public boolean getColor() {
        return this.color;
    }

    public void setColor(boolean color) {
        this.color = color;
        side = color ? -1 : 1;
    }

    public int getSide() {
        return this.side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coord) {
        this.setX(coord / 10);
        this.setY(coord % 10);
        this.coordinate = coord;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}
