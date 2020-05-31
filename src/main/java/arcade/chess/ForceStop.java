package arcade.chess;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
 * Application of a {@code ForceStope} object to
 * add draw/stalemate and resignation functionality.
 */
public class ForceStop extends VBox {

    private App app;
    private final Button yes = new Button("Yes");
    private final Button no = new Button("No");
    private final Label request = new Label();
    private final HBox buttons;
    private final boolean color;
    private final ImageView iv;

    /**
     * Constructs a {@code ForceStop} object for draws and resignations.
     * @param color the color of whose turn it is
     * @param draw true for draw, false for resign
     */
    public ForceStop(boolean color, boolean draw) {
        iv = new ImageView();
        buttons = new HBox();
        this.color = color;
        if (draw) {
            if (color) {
                iv.setImage(new Image("/pawnW.png"));
                request.setText("White has requested a draw.\nBlack, do you accept?");
            } else {
                iv.setImage(new Image("/pawnB.png"));
                request.setText("Black has requested a draw.\nWhite, do you accept?");
            }
            yes.setOnAction(this::setStalemate);
        } else {
            if (color) {
                iv.setImage(new Image("/pawnW.png"));
            } else {
                iv.setImage(new Image("/pawnB.png"));
            }
            request.setText("You have requested to resign.\nAre you sure?");
            yes.setOnAction(this::setResign);
        }
        request.setContentDisplay(ContentDisplay.TOP);
        request.setGraphic(iv);
        request.setTextFill(Color.WHITE);
        request.setPadding(new Insets(10));
        request.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        no.setOnAction(this::remove);
        buttons.getChildren().addAll(yes, no);
        buttons.setAlignment(Pos.BASELINE_CENTER);
        buttons.setPadding(new Insets(10));
        buttons.setSpacing(25);
        yes.setPadding(new Insets(8));
        yes.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        no.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        no.setPadding(new Insets(8));
        yes.setPrefSize(75, 10);
        no.setPrefSize(75, 10);
        this.getChildren().addAll(request, buttons);
        this.setAlignment(Pos.CENTER);
        Color background = Color.rgb(105, 105, 105);
        this.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setMaxSize(280, 280);
        this.setSpacing(10);
    }

    /**
     * Removes the {@code ForceStop} object from the {@code Board} stackpane.
     * @param ae the {@code ActionEvent}
     */
    public void remove(ActionEvent ae) {
        this.app.getBoard().getStack().getChildren().remove(this.app.getForceStop());
    }

    /**
     * Sets the final {@code ForceStop} VBox to show game is over.
     * @param ae the {@code ActionEvent}
     */
    public void setResign(ActionEvent ae) {
        if (color) {
            request.setText("Game Over. Black wins.");
            iv.setImage(new Image("kingB.png"));
        } else {
            request.setText("Game Over. White wins.");
            iv.setImage(new Image("kingW.png"));
        }
        this.buttons.getChildren().remove(yes);
        no.setText("Okay");
    }

    /**
     * Sets the final {@code ForceStop} VBox to show a draw has occurred.
     * @param ae the {@code ActionEvent}
     */
    public void setStalemate(ActionEvent ae) {
        request.setText("Stalemate.");
        this.buttons.getChildren().remove(yes);
        no.setText("Okay");
    }

    /**
     * Sets the {@code App} to this.
     * @param app the specified app
     */
    public void setApp(App app) {
        this.app = app;
    }
}
