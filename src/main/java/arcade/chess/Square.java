package arcade.chess;

import arcade.chess.pieces.Empty;
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
     * Executes the move of a {@code Piece} to a target {@code Square}.
     * @param target the target {@code Square}
     */
    public void moveTo(Square target) {
        checkTaken(checkPawn(target));
        checkCastle(target);
        this.piece.setFirstMove(false);
        this.piece.setCoordinate(target.coordinate);
        this.piece.setSquare(target);
        target.setPiece(this.piece);
        this.setPiece(new Empty(x, y));
        if (target.getPiece().getOpponent().isChecked()) {
            if (ifCheckMate(target)) {
                System.out.println("Checkmate");
            } else {
                System.out.println("Check");
            }
        }
    }

    /**
     * Checks if the {@code King} just performed a castle.
     * Moves the rook accordingly.
     * @param target the target {@code Square}
     */
    public void checkCastle(Square target) {
        if (this.getPiece().getType() == 'K') {
            if (target.coordinate - this.coordinate == 20 || target.coordinate - this.coordinate == -20) {
                if (target.coordinate == 67) {
                    getBoard().squareArr[5][7].setPiece(getBoard().squareArr[7][7].getPiece());
                    getBoard().squareArr[5][7].getPiece().setFirstMove(false);
                    getBoard().squareArr[5][7].getPiece().setCoordinate(57);
                    getBoard().squareArr[5][7].getPiece().setSquare(getBoard().squareArr[5][7]);
                    getBoard().squareArr[7][7].setPiece(new Empty(7, 7));
                } else if (target.coordinate == 27) {
                    getBoard().squareArr[3][7].setPiece(getBoard().squareArr[0][7].getPiece());
                    getBoard().squareArr[3][7].getPiece().setFirstMove(false);
                    getBoard().squareArr[3][7].getPiece().setCoordinate(37);
                    getBoard().squareArr[3][7].getPiece().setSquare(getBoard().squareArr[3][7]);
                    getBoard().squareArr[0][7].setPiece(new Empty(0, 7));
                } else if (target.coordinate == 20) {
                    getBoard().squareArr[3][0].setPiece(getBoard().squareArr[0][0].getPiece());
                    getBoard().squareArr[3][0].getPiece().setFirstMove(false);
                    getBoard().squareArr[3][0].getPiece().setCoordinate(30);
                    getBoard().squareArr[3][0].getPiece().setSquare(getBoard().squareArr[3][0]);
                    getBoard().squareArr[0][0].setPiece(new Empty(0, 0));
                } else if (target.coordinate == 60) {
                    getBoard().squareArr[5][0].setPiece(getBoard().squareArr[7][0].getPiece());
                    getBoard().squareArr[5][0].getPiece().setFirstMove(false);
                    getBoard().squareArr[5][0].getPiece().setCoordinate(50);
                    getBoard().squareArr[5][0].getPiece().setSquare(getBoard().squareArr[5][0]);
                    getBoard().squareArr[7][0].setPiece(new Empty(7, 0));
                }
            }
        }
    }

    /**
     * Checks if the move checkmated the opponent.
     * @param target the target {@code Square}
     * @return true if checkmate, false otherwise
     */
    public boolean ifCheckMate(Square target) {
        int numOpposingPieces = 0;
        int numNoMoves = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getBoard().squareArr[i][j].getPiece();
                if (piece.getColor() * -1 == target.piece.getColor()) {
                    numOpposingPieces++;
                    if (!piece.getPossibleMoves()) {
                        numNoMoves++;
                    }
                }
            }
        }
        return numOpposingPieces == numNoMoves;
    }

    /**
     * Checks for pawn functionality of promotions, double moves, and en passants.
     * @param target the target {@code Square}
     * @return the {@code Square} of a possible taken piece.
     */
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
                    if (pawn.getEnPassant() == target.coordinate) { //en passant
                        pawn.setEnPassant(-1);
                        return board.squareArr[target.x][target.y - piece.getColor()];
                    }
                }
            }
        }
        return target;
    }

    /**
     * Checks if the move execution called for a {@code Piece}
     * to be taken. Also updates the respective {@code TakenGrid}.
     * @param taken the {@code Square} with a taken piece
     */
    public void checkTaken(Square taken) {
        if (taken.getPiece().getColor() == -1) {
            board.app.getBlack().getTaken().add(taken.getPiece());
            board.app.getBlack().getTaken().updateDifference(board.app.getWhite().getTaken());
            taken.setPiece(new Empty(taken.x, taken.y)); //remove en passant piece
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