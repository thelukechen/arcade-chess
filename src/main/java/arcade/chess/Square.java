package arcade.chess;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Square extends StackPane {

    private final int x;
    private final int y;
    private final int coordinate;
    private final ImageView iv;
    private Piece piece;
    private Board board;

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
        for (int i = 0; i < this.getBoard().squareArr.length; i++) {
            for (int j = 0; j < this.getBoard().squareArr[i].length; j++) {
                if (this.getBoard().squareArr[i][j].getPiece().getType().equals("Pawn")) {
                    Pawn pawn = (Pawn) this.getBoard().squareArr[i][j].getPiece();
                    pawn.setJustDoubleMoved(null,false);
                }

            }
        }
        //en passant
        if (this.getPiece().getType().equals("Pawn")) {
            Pawn pawn = (Pawn) this.getPiece();
            pawn.setJustDoubleMoved(target, true);
            if (pawn.getEnPassant() > 0) {
                Square passantSquare = this.getBoard().squareArr[getX() + getPiece().getSide()][getY()];
                if (!passantSquare.getPiece().getType().equals("Pawn")) {
                    passantSquare = this.getBoard().squareArr[getX() - getPiece().getSide()][getY()];
                }
                if (pawn.getEnPassant() == target.getCoordinate() && passantSquare.getPiece().getColor()) {
                    this.getBoard().app.blackTakes.add(passantSquare.getPiece());
                } else {
                    this.getBoard().app.whiteTakes.add(passantSquare.getPiece());
                }
                passantSquare.setPiece(new Empty(passantSquare.getX(), passantSquare.getY()));
                pawn.setEnPassant(-1);
            }
        }
        //piece taken
        if (!target.getPiece().getType().equals("Empty")) {
            if (target.getPiece().getColor()) {
                getBoard().app.blackTakes.add(target.getPiece());
//                System.out.println(target.getPiece() + ",1 " + target.getPiece().getColor());
            } else {
                getBoard().app.whiteTakes.add(target.getPiece());
//                System.out.println(target.getPiece() + ",2 " + target.getPiece().getColor());
            }
        }
        //swap
        int temp = this.getCoordinate();
        this.getPiece().setCoordinate(10 * target.getX() + target.getY());
        target.setPiece(this.getPiece());
        target.getPiece().setFirstMove(false);
        this.setPiece(new Empty(temp / 10, temp % 10));
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        iv.setImage(this.piece.image());
    }

    public int getCoordinate() {
        return this.coordinate;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
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