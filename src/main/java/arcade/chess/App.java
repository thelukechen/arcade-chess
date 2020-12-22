package arcade.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Executes a {@code Chess} game.
 */
public class App extends Application {

    private Board board;
    private Dock white;
    private Dock black;
    private MenuBar bar;
    private Menu game;
    private ForceStop forceStop;

    @Override
    public void start(Stage stage) {

        VBox vbox = new VBox();
        board = new Board();
        board.setApp(this);
        white = new Dock(true);
        white.setApp(this);
        black = new Dock(false);
        black.setApp(this);
//        Welcome welcome = new Welcome();
//        welcome.setApp(this);
//        this.getBoard().getStack().getChildren().add(welcome);

        bar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> System.exit(0));
        MenuItem about = new MenuItem("About");
        file.getItems().addAll(about, quit);
        game = new Menu("Game");
        bar.getMenus().addAll(file, game);
        MenuItem draw = new MenuItem("Draw");
        MenuItem resign = new MenuItem("Resign");
        game.getItems().addAll(draw, resign);
        draw.setOnAction(e -> {
            forceStop = new ForceStop(this.getBoard().getColor() == 1, true);
            this.getBoard().getStack().getChildren().add(forceStop);
            forceStop.setApp(this);
        });
        resign.setOnAction(e -> {
            forceStop = new ForceStop(this.getBoard().getColor() == -1, false);
            this.getBoard().getStack().getChildren().add(forceStop);
            forceStop.setApp(this);
        });

        vbox.getChildren().addAll(bar, black, board, white);

        Scene scene = new Scene(vbox);
        stage.setTitle("Chess Application");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Returns the {@code ForceStop} object
     * @return the {@code ForceStop} object
     */
    public ForceStop getForceStop() {
        return forceStop;
    }

    /**
     * Returns the {@code Board}.
     * @return the {@code Board}
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Returns the white {@code Dock}.
     * @return the white {@code Dock}
     */
    public Dock getWhite() {
        return this.white;
    }

    /**
     * Returns the black {@code Dock}.
     * @return the black {@code Dock}
     */
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

    public MenuBar getBar() {
        return bar;
    }

    public Menu getGame() {
        return game;
    }
}
