package arcade.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Executes a {@code Chess} game.
 */
public class App extends Application {

    Board board;
    VBox vbox;
    Stage stage;
    Dock white;
    Dock black;

    @Override
    public void start(Stage stage) {

        this.stage = stage;
        vbox = new VBox();
        board = new Board();
        board.setApp(this);

        white = new Dock(true);
        white.setApp(this);
        black = new Dock(false);
        black.setApp(this);

        vbox.getChildren().addAll(black, board, white);

        Scene scene = new Scene(vbox);
        this.stage.setTitle("Chess Application");
        this.stage.setResizable(true);
        this.stage.setScene(scene);
        this.stage.sizeToScene();
        this.stage.show();
    }

    public static void main(String[] args ) {
        launch();
    }

    /**
     * Adds the {@code Promotion} object into the {@code Board} stack object.
     * @param promotion the {@code Promotion} object
     */
    public void createPromotion(Promotion promotion) {
        this.board.getBoard().getChildren().add(promotion);
    }
}
