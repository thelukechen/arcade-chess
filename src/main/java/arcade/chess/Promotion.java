package arcade.chess;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Promotion extends GridPane {

    private final App app;
    private final int coordinates;
    private final Piece[] pieces = new Piece[4];

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
            buttons[i] = new Button(pieces[i].getType());
            buttons[i].setGraphic(new ImageView(pieces[i].image()));
            buttons[i].setOnAction(this::updatePawn);
        }

        for (int i = 0; i < 2; i++) {
            this.add(buttons[i], i, 0);
            this.add(buttons[i + 2], i, 1);
        }
    }
    //put promotion in center of screen

    public void updatePawn(ActionEvent ae) {
        Button button = (Button) ae.getSource();
        for (Piece e : pieces) {
            if (e.getType().equals(button.getText())) {
                Square square = this.app.board.squareArr[coordinates / 10][coordinates % 10];
                square.setPiece(e);
                square.getPiece().setSquare(square);
                break;
            }
        }
        this.app.stack.getChildren().remove(1);
    }
}
