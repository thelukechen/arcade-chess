package arcade.chess.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Application of the {@code Dock} object for each side,
 * housing the description, timer, and taken pieces.
 */
public class Dock extends HBox {

    private App app;
    private final TakenGrid taken;

    /**
     * Constructs a {@code Dock} object with the side image,
     * description, timer, and taken pieces.
     * @param color true if white, false if black
     */
    public Dock(boolean color) {
        ImageView iv = new ImageView();
        Label pic = new Label();
        Label text = new Label();
        VBox vbox = new VBox();
        if (color) {
            iv.setImage(App.loadImage("/pieces/white/pawnW.png", 80, 80, true, false));
            text.setText("White");
            this.setAlignment(Pos.TOP_LEFT);
            taken = new TakenGrid(true);

        } else {
            iv.setImage(App.loadImage("/pieces/black/pawnB.png", 80, 80, true, false));
            text.setText("Black");
            taken = new TakenGrid(false);
            this.setAlignment(Pos.BOTTOM_LEFT);
        }
        taken.setApp(app);
        pic.setGraphic(iv);
        pic.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        text.setTextFill(Color.WHITE);
        //vbox.getChildren().addAll(text, new Text("Timer"), taken);
        vbox.getChildren().addAll(text, taken);
        vbox.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(pic, vbox);
        this.setPadding(new Insets(15));
        Color background = Color.rgb(105, 105, 105);
        this.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Sets the {@code App) object to use methods within it.}
     * @param app the specified {@code App} object
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Returns the {@code TakenGrid} object.
     * @return the {@code TakenGrid} object
     */
    public TakenGrid getTaken() {
        return taken;
    }
}
