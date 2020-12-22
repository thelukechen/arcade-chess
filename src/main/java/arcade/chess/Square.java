package arcade.chess;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Application of a {@code Square} for each square in
 * the {@code Board}.
 */
public class Square extends StackPane {

    private final int x;
    private final int y;
    private final int coordinate;
    private final ImageView iv;
    private Piece piece;
    private Board board;

    /**
     * Constructs a {@code Square} object with a set of
     * coordinates and a {@code Piece}. Also sets the square color
     * and size.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param piece the specified {@code Piece}
     */
    public Square(int x, int y, Piece piece) {
        super();

        this.x = x;
        this.y = y;
        this.piece = piece;
        this.piece.setSquare(this);
        this.coordinate = 10 * x + y;

        Color color;
        if ((x + y) % 2 == 0) {
            color = Color.rgb(255,222,173);
        } else {
            color = Color.rgb(205,133,63);
        }

        iv = new ImageView(this.piece.image());
        this.getChildren().add(iv);
        this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setMinSize(100, 100);
    }

    public void moveTo(Square target) {
        checkTaken(checkPawn(target));
        this.piece.setFirstMove(false);
        this.piece.setCoordinate(target.coordinate);
        this.piece.setSquare(target);
        target.setPiece(this.piece);
        this.setPiece(new Empty(x, y));
    }

    public Square checkPawn(Square target) {
        if (piece.getType() == 'P') {
            if (target.y == 0 || target.y == 7) { //promotion
                board.app.createPromotion(new Promotion(piece.getColor() == -1, target.coordinate, board.app));
            } else {
                Pawn pawn = (Pawn) piece;
                if (x == target.x && y + (2 * piece.getColor()) == target.y) { //double move
                    pawn.setJustDoubleMoved(true);
                } else {
                    pawn.setJustDoubleMoved(false);
                    if (pawn.getEnPassant() == target.coordinate) { //enpassant
                        pawn.setEnPassant(-1);
                        return board.squareArr[target.x][target.y - piece.getColor()];
                    }
                }
            }
        }
        return target;
    }

    public void checkTaken(Square taken) {
        if (taken.getPiece().getColor() == -1) {
            board.app.getBlack().getTaken().add(taken.getPiece());
            board.app.getBlack().getTaken().updateDifference(board.app.getWhite().getTaken());
            taken.setPiece(new Empty(taken.x, taken.y)); //remove enpassant piece
        } else if (taken.getPiece().getColor() == 1) {
            board.app.getWhite().getTaken().add(taken.getPiece());
            board.app.getWhite().getTaken().updateDifference(board.app.getBlack().getTaken());
            taken.setPiece(new Empty(taken.x, taken.y));
        }
    }

    /**
     * Sets the specified {@code Board} to this.
     * @param board the specified board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Returns the current {@code Board}.
     * @return the {@code Board}
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Returns the current {@code Piece}.
     * @return the {@code Piece}
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets the specified {@code Piece} to this.
     * @param piece the specified {@code Piece}
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        iv.setImage(this.piece.image());
    }

    /**
     * Returns the current coordinates of the {@code Square}.
     * @return the coordinates: x in the tens digit, y in the ones digit.
     */
    public int getCoordinate() {
        return this.coordinate;
    }
}