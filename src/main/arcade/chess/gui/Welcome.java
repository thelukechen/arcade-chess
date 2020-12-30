package arcade.chess.gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
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
 * Application for the {@code Welcome} screen.
 */
public class Welcome extends VBox {

    private App app;
    private final Label banner;
    private final ImageView arcade;
    private final Label game;
    private final ImageView chess;
    private final HBox button;
    private final Button enter;
    private final ImageView iv = new ImageView();
    private HBox empty = new HBox(iv);
    private Button yes;
    private Button no;

    /**
     * Constructs the initial stage of the {@code Welcome} object.
     */
    public Welcome() {
        empty.setPrefSize(80, 80);
        banner = new Label("Welcome to the Arcade");
        banner.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        banner.setTextFill(Color.WHITE);
        arcade = new ImageView(App.loadImage("/application/arcade.png", 200, 200, true, true));
        game = new Label("You have chosen chess.");
        game.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        game.setTextFill(Color.WHITE);
        chess = new ImageView(App.loadImage("/application/chess.png", 150, 150, false, false));
        ImageView enterIv = new ImageView(App.loadImage("/application/enter.png", 50, 50, true, true));
        button = new HBox();
        button.setPadding(new Insets(20));
        enter = new Button("Enter");
        button.getChildren().add(enter);
        button.setAlignment(Pos.BOTTOM_RIGHT);
        enter.setGraphic(enterIv);
        enter.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.getChildren().addAll(banner, arcade, empty, game, chess, button);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(50));
        this.setMaxSize(650, 650);
        Color background = Color.rgb(105, 105, 105);
        this.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        enter.setOnAction(this::clockScreen);
    }

    /**
     * After the Enter {@code Button}, asks user to use a clock or not.
     * @param ae the specified {@code ActionEvent}
     */
    public void clockScreen(ActionEvent ae) {
        yes = new Button("Yes");
        no = new Button("No");
        HBox hbox = new HBox(yes, no);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().removeAll(banner, arcade, empty, game, chess, button);
        game.setText("Would you like a clock?");
        ImageView clock = new ImageView(App.loadImage("/application/clock.png", 80, 80 , true, false));
        this.getChildren().addAll(chess, empty, game, clock, hbox, button);
        enter.setOnAction(this::remove);
    }

    /**
     * Removes the {@code Welcome} object from the screen.
     * @param ae the specified {@code ActionEvent}
     */
    public void remove(ActionEvent ae) {
        this.app.getBoard().getStack().getChildren().remove(this);
    }

    /**
     * Sets the {@code App} to this.
     * @param app the specified {@code App}
     */
    public void setApp(App app) {
        this.app = app;
    }
}
