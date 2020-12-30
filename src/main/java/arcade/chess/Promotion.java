package arcade.chess;

import arcade.chess.pieces.Bishop;
import arcade.chess.pieces.Knight;
import arcade.chess.pieces.Queen;
import arcade.chess.pieces.Rook;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Application of the {@code Promotion} object for pawns.
 */
public class Promotion extends HBox {

    private final App app;
    private final int coordinates;
    private final Piece[] pieces = new Piece[4];

    /**
     * Constructs a {@code Promotion} object that displays the 4 available pieces.
     * @param a the color of the pawn being promoted
     * @param coordinates the coordinates of the final position
     * @param app the app
     */
    public Promotion(boolean a, int coordinates, App app) {
        super();
        this.app = app;
        this.coordinates = coordinates;
        pieces[0] = new Queen(a, coordinates / 10, coordinates % 10);
        pieces[1] = new Rook(a, coordinates / 10, coordinates % 10);
        pieces[2] = new Knight(a, coordinates / 10, coordinates % 10);
        pieces[3] = new Bishop(a, coordinates / 10, coordinates % 10);
        Button[] buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(pieces[i].getType() + "");
            buttons[i].setGraphic(new ImageView(pieces[i].image()));
            buttons[i].setOnAction(this::updatePawn);
            buttons[i].setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            buttons[i].setPadding(new Insets(15));
        }
        GridPane grid = new GridPane();
        for (int i = 0; i < 2; i++) {
            grid.add(buttons[i], i, 0);
            grid.add(buttons[i + 2], i, 1);
        }
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);

        this.getChildren().add(grid);
        this.setAlignment(Pos.CENTER);
        Color background = Color.rgb(105, 105, 105);
        this.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setMaxSize(280, 280);
    }

    /**
     * Updates the {@code Pawn} to the chosen {@code Piece}.
     * Also removes the {@code Promotion} object from view.
     * @param ae the {@code ActionEvent}
     */
    public void updatePawn(ActionEvent ae) {
        Button button = (Button) ae.getSource();
        for (Piece e : pieces) {
            if (e.getType() == button.getText().charAt(0)) {
                Square square = this.app.getBoard().squareArr[coordinates / 10][coordinates % 10];
                e.setKing(square.getPiece().getKing());
                e.setOpponent(square.getPiece().getOpponent());
                square.setPiece(e);
                square.getPiece().setSquare(square);
                break;
            }
        }
        this.app.getBoard().getStack().getChildren().remove(1);
    }
}
