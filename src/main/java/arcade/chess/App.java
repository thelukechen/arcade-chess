package arcade.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Executes a {@code Chess} game.
 */
public class App extends Application {

    private Board board;
    private Dock white;
    private Dock black;

    @Override
    public void start(Stage stage) {

        VBox vbox = new VBox();

        board = new Board();
        board.setApp(this);
        white = new Dock(true);
        white.setApp(this);
        black = new Dock(false);
        black.setApp(this);

        vbox.getChildren().addAll(black, board, white);

        Scene scene = new Scene(vbox);
        stage.setTitle("Chess Application");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public Board getBoard() {
        return this.board;
    }

    public Dock getWhite() {
        return this.white;
    }

    public Dock getBlack() {
        return this.black;
    }

    public static void main(String[] args ) {
        launch();
    }

    /**
     * Adds the {@code Promotion} object into the {@code Board} stack object.
     * @param promotion the {@code Promotion} object
     */
    public void createPromotion(Promotion promotion) {
        this.board.getStack().getChildren().add(promotion);
    }
}
