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

    /**
     * Moves the {@code Piece} on the {@code Square} to the target
     * {@code Square}. Checks for en passant and calls {@link TakenGrid}
     * methods if {@code Piece} is taken.
     * @param target the target {@code Square}
     */
    public void moveTo(Square target) {
        //check double move
        if (!checkDoubleMoved(target)) {
            //en passant
            if (!checkEnPassant(target)) {
                setAllDoubleMovedFalse();
            }
        }
        //piece taken
        if (!target.getPiece().getType().equals("Empty")) {
            if (target.getPiece().getColor()) {
                getBoard().app.blackTakes.add(target.getPiece());
            } else {
                getBoard().app.whiteTakes.add(target.getPiece());
            }
        }
        //swap
        int temp = this.getCoordinate();
        this.getPiece().setCoordinate(10 * target.getX() + target.getY());
        target.setPiece(this.getPiece());
        //promotion
        if (checkPromotion(target)) {
            this.getBoard().app.setScene(new Promotion(getPiece().getColor(), this.getPiece().getCoordinate(), this.getBoard().app));
        }
        target.getPiece().setFirstMove(false);
        this.setPiece(new Empty(temp / 10, temp % 10));
    }

    /**
     * Checks if the {@code Pawn} object can perform a promotion.
     * @param target the target {@code Square} location after promotion.
     * @return true if promotion executed
     */
    public boolean checkPromotion(Square target) {
        return this.getPiece().getType().equals("Pawn") && (target.getY() == 0 || target.getY() == 7);
    }

    /**
     * Checks if the {@code Pawn} object can perform en passant.
     * Adds the opposing pawn to the corresponding {@code TakenGrid}.
     * @param target the target {@code Square} location after en passant
     * @return true if en passant executed
     */
    public boolean checkEnPassant(Square target) {
        if (this.getPiece().getType().equals("Pawn")) {
            Pawn pawn = (Pawn) this.getPiece();
            if (pawn.getEnPassant() == target.getCoordinate()) {
                Square passantSquare = getBoard().squareArr[target.getX()][target.getY() - getPiece().getSide()];
                if (pawn.getEnPassant() == target.getCoordinate() && passantSquare.getPiece().getColor()) {
                    this.getBoard().app.blackTakes.add(passantSquare.getPiece());
                } else {
                    this.getBoard().app.whiteTakes.add(passantSquare.getPiece());
                }
                passantSquare.setPiece(new Empty(passantSquare.getX(), passantSquare.getY()));
                pawn.setEnPassant(-1);
                pawn.setJustDoubleMoved(false);
                return true;
            }
        }
        return false;
    }

    /** Checks if the {@code Pawn} object just double moved.
     * @param target the target {@code Square} location
     * @return true if {@code Pawn} just double moved.
     */
    public boolean checkDoubleMoved(Square target) {
        if (this.getPiece().getType().equals("Pawn")) {
            if (this.getX() == target.getX() && target.getY() == this.getY() + (2 * this.getPiece().getSide())) {
                Pawn pawn = (Pawn) this.getPiece();
                pawn.setJustDoubleMoved(true);
                return true;
            }
        }
        return false;
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

    /**
     * Returns the current x-coordinate.
     * @return the x-coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the current y-coordinate.
     * @return the y-coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets every {@code Pawn}'s {@code justDoubleMoved} to false
     * to negate the potential of en passant after the next move.
     */
    public void setAllDoubleMovedFalse() {
        for (int i = 0; i < this.getBoard().squareArr.length; i++) {
            for (int j = 0; j < this.getBoard().squareArr[i].length; j++) {
                if (this.getBoard().squareArr[i][j].getPiece().getType().equals("Pawn")) {
                    Pawn pawn = (Pawn) this.getBoard().squareArr[i][j].getPiece();
                    pawn.setJustDoubleMoved(false);
                }

            }
        }
    }

    /**
     * Creates and immediately starts a new daemon thread that executes
     * {@code target.run()}. This method, which may be called from any thread,
     * will return immediately its the caller.
     * @param target the object whose {@code run} method is invoked when this
     *               thread is started
     */
    public static void runNow(Runnable target) {
        Thread t = new Thread(target);
        t.setDaemon(true);
        t.start();
    }
}