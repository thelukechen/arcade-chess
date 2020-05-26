package arcade.chess;

import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TakenGrid extends TilePane {

    private App app;
    private final ImageView[] piecesTaken = new ImageView[15];
    private int count;

    public TakenGrid() {
        for (int i = 0; i < 15; i++) {
            ImageView iv = new ImageView();
            iv.setFitHeight(50);
            iv.setFitWidth(50);
            piecesTaken[i] = iv;
            this.getChildren().add(piecesTaken[i]);
        }
        count = 0;
    }

    public void add(Piece piece) {
        piecesTaken[count].setImage(piece.image());
        count++;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public App getApp() {
        return this.app;
    }
}
