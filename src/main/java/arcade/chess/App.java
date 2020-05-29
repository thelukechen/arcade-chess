package arcade.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Executes a {@code Chess} game.
 */
public class App extends Application {

    Board board;
    TakenGrid whiteTakes;
    TakenGrid blackTakes;
    VBox vbox;
    Stage stage;

    StackPane stack;
    Promotion promotion;


    @Override
    public void start(Stage stage) {
        this.stage = stage;
        vbox = new VBox();
        board = new Board();
        board.setApp(this);
        whiteTakes = new TakenGrid();
        whiteTakes.setApp(this);
        blackTakes = new TakenGrid();
        blackTakes.setApp(this);
        stack = new StackPane(board);
        vbox.getChildren().addAll(blackTakes, stack, whiteTakes);


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

    public void setScene(Promotion promotion) {
        this.stack.getChildren().add(promotion);
    }
}
