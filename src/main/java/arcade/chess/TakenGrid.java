package arcade.chess;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Application for a {@code TakenGrid} object.
 * Shows the taken {@code Pieces} from the two sides.
 */
public class TakenGrid extends GridPane {

    private App app;
    private final ImageView[] piecesTaken = new ImageView[15];
    private int count;
    private final boolean color;

    /**
     * Constructs a {@code TakenGrid} object with
     * a maximum size of 15 for the 15 potential pieces.
     */
    public TakenGrid(boolean color) {
        this.color = color;
        for (int i = 0; i < 15; i++) {
            ImageView iv = new ImageView();
            iv.setFitHeight(40);
            iv.setFitWidth(40);
            piecesTaken[i] = iv;
            this.add(piecesTaken[i], i, 0);
        }
        count = 0;
        this.setAlignment(Pos.BASELINE_LEFT);
    }

    /**
     * Returns the color of the {@code TakenGrid} object.
     * @return true if white, false if black
     */
    public boolean getColor() {
        return this.color;
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
