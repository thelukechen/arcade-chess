package arcade.chess;

import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 * Application for a {@code TakenGrid} object.
 * Shows the taken {@code Pieces} from the two sides.
 */
public class TakenGrid extends TilePane {

    private App app;
    private final ImageView[] piecesTaken = new ImageView[15];
    private int count;

    /**
     * Constructs a {@code TakenGrid} object with
     * a maximum size of 15 for the 15 potential pieces.
     */
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

    /**
     * Adds the specified {@code Piece} to the {@code TakenGrid}.
     * @param piece the specified {@code Piece}
     */
    public void add(Piece piece) {
        piecesTaken[count].setImage(piece.image());
        count++;
    }

    /**
     * Sets the specified app to this.
     * @param app the specified app
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Returns the current app.
     * @return the app
     */
    public App getApp() {
        return this.app;
    }
}
