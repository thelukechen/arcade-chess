package arcade.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Executes a {@code Chess} game.
 *
 */
public class App extends Application {

    Board board;
    TakenGrid whiteTakes;
    TakenGrid blackTakes;

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        board = new Board();
        board.setApp(this);
        whiteTakes = new TakenGrid();
        whiteTakes.setApp(this);
        blackTakes = new TakenGrid();
        blackTakes.setApp(this);
        vbox.getChildren().addAll(blackTakes, board, whiteTakes);

        Scene scene = new Scene(vbox);
        stage.setTitle("Chess Application");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args ) {
        launch();
    }
}
