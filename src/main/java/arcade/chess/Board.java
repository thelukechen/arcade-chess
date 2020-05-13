package arcade.chess;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Board extends GridPane {

    StackPane[][] array = new StackPane[8][8];

    public Board() {
        super();

        final int size = 8;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                StackPane index = new StackPane();
                Color color;
                if ((i + j) % 2 == 0) {
                    color = Color.rgb(255,222,173);
                } else {
                    color = Color.rgb(205,133,63);
                }
                index.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                index.getChildren().add((new Label(" ")));
                array[i][j] = index;
                Board.setHgrow(index, Priority.ALWAYS);
                index.setMinSize(50, 50);
                this.add(array[i][j], i, j);

                //Starting Board
                if (j == 1) {
                    Pawn pawn = new Pawn(i, j, false);
                    array[i][j].getChildren().add(new Label(pawn.getImage()));
                }
                if (j == 6) {
                    Pawn pawn = new Pawn(i, j, true);
                    array[i][j].getChildren().add(new Label(pawn.getImage()));
                }
                if ((i == 0 || i == 7) && j == 0) {
                    Rook rook = new Rook(i, j, false);
                    array[i][j].getChildren().add(new Label(rook.getImage()));
                }
                if ((i == 0 || i == 7) && j == 7) {
                    Rook rook = new Rook(i, j, true);
                    array[i][j].getChildren().add(new Label(rook.getImage()));
                }
                if ((i == 1 || i == 6) && j == 0) {
                    Knight knight = new Knight(i, j, false);
                    array[i][j].getChildren().add(new Label(knight.getImage()));
                }
                if ((i == 1 || i == 6) && j == 7) {
                    Knight knight = new Knight(i, j, true);
                    array[i][j].getChildren().add(new Label(knight.getImage()));
                }
                if ((i == 2 || i == 5) && j == 0) {
                    Bishop knight = new Bishop(i, j, false);
                    array[i][j].getChildren().add(new Label(knight.getImage()));
                }
                if ((i == 2 || i == 5) && j == 7) {
                    Bishop knight = new Bishop(i, j, true);
                    array[i][j].getChildren().add(new Label(knight.getImage()));
                }
                if (i == 3 && j == 0) {
                    Queen queen = new Queen(i, j, false);
                    array[i][j].getChildren().add(new Label(queen.getImage()));
                }
                if (i == 3 && j == 7) {
                    Queen queen = new Queen(i, j, true);
                    array[i][j].getChildren().add(new Label(queen.getImage()));
                }
                if (i == 4 && j == 0) {
                    King queen = new King(i, j, false);
                    array[i][j].getChildren().add(new Label(queen.getImage()));
                }
                if (i == 4 && j == 7) {
                    King queen = new King(i, j, true);
                    array[i][j].getChildren().add(new Label(queen.getImage()));
                }
            }
        }
        this.setMinSize(50, 50);
        this.setHeight(this.getWidth());
    }
}
