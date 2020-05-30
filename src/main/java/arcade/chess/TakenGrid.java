package arcade.chess;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Application for a {@code TakenGrid} object.
 * Shows the taken {@code Pieces} from the two sides.
 */
public class TakenGrid extends GridPane {

    private App app;
    private ArrayList<Piece> piecesTaken = new ArrayList<>();
    private int count;
    private final boolean color;
    private Label value;
    HashMap<String, Integer> pieceValues;

    /**
     * Constructs a {@code TakenGrid} object with
     * a maximum size of 15 for the 15 potential pieces.
     */
    public TakenGrid(boolean color) {
        this.color = color;
        pieceValues = new HashMap<>();
        pieceValues.put("Pawn", 1);
        pieceValues.put("Knight", 3);
        pieceValues.put("Bishop", 3);
        pieceValues.put("Rook", 5);
        pieceValues.put("Queen", 9);
        value = new Label("");
        value.setTextFill(Color.WHITE);
        value.setPrefSize(40, 40);
        value.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 12));
        this.add(value, 0, 0);
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
        ImageView iv = new ImageView(piece.image());
        iv.setFitHeight(40);
        iv.setFitWidth(40);
        piecesTaken.add(piece);
        System.out.println("array length" + piecesTaken.size());
        this.getChildren().remove(count);
        this.add(iv, count, 0);
        this.add(value, count + 1, 0);
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

    public ArrayList<Piece> getPiecesTaken() {
        return this.piecesTaken;
    }

    public void updateDifference(TakenGrid other) {
        int thisDock = 0;
        int otherDock = 0;
        for (Piece e : this.piecesTaken) {
            thisDock += pieceValues.get(e.getType());
        }
        for (Piece e : other.piecesTaken) {
            otherDock += pieceValues.get(e.getType());
        }
        if (thisDock > otherDock) {
            this.value.setText(" +" + (thisDock - otherDock));
            other.value.setText("");
        } else if (otherDock > thisDock) {
            other.value.setText(" +" + (otherDock - thisDock));
            this.value.setText("");
        } else {
            value.setText("");
            other.value.setText("");
        }
    }
}
