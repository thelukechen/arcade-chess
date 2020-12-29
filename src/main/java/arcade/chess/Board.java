package arcade.chess;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Application to construct a {@code Board} with Mouse Click
 * functionality.
 */
public class Board extends BorderPane {

    private final StackPane stack;
    private boolean firstClick = true;
    private byte color = -1;
    private Square source;

    App app;
    Square[][] squareArr = new Square[8][8];

    /**
     * Constructs an {@code Board} object with all the
     * chess pieces in place. Also sets up game logic and mouse
     * functionality.
     */
    public Board() {
        super();
        GridPane grid = new GridPane();
        stack = new StackPane();
        King whiteK = new King(true, 4, 7);
        King blackK = new King(false, 4, 0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square;
                if (j == 6) {
                    //white pawns
                    square = new Square(i, j, new Pawn(true, i, j));
                } else if (j == 1) {
                    //black pawns
                    square = new Square(i, j, new Pawn(false, i, j));
                } else if (j == 7 && (i == 0 || i == 7)) {
                    //white rooks
                    square = new Square(i, j, new Rook(true, i, j));
                } else if (j == 0 && (i == 0 || i == 7)) {
                    //black rooks
                    square = new Square(i, j, new Rook(false, i, j));
                } else if (j == 7 && (i == 1 || i == 6)) {
                    //white knights
                    square = new Square(i, j, new Knight(true, i, j));
                } else if (j == 0 && (i == 1 || i == 6)) {
                    //black knights
                    square = new Square(i, j, new Knight(false, i, j));
                } else if (j == 7 && (i == 2 || i == 5)) {
                    //white bishops
                    square = new Square(i, j, new Bishop(true, i, j));
                } else if (j == 0 && (i == 2 || i == 5)) {
                    //black bishops
                    square = new Square(i, j, new Bishop(false, i, j));
                } else if (j == 7 && i == 3) {
                    //white queen
                    square = new Square(i, j, new Queen(true, i, j));
                } else if (j == 0 && i == 3) {
                    //black queen
                    square = new Square(i, j, new Queen(false, i, j));
                } else if (j == 7) {
                    //white king
                    square = new Square(i, j, whiteK);
                } else if (j == 0) {
                    //black king
                    square = new Square(i, j, blackK);
                }  else {
                    //empty squares
                    square = new Square(i, j, new Empty(i, j));
                }
                if (square.getPiece().getColor() == -1) { //set kings
                    square.getPiece().setKing(whiteK);
                    square.getPiece().setOpponent(blackK);
                } else if (square.getPiece().getColor() == 1) {
                    square.getPiece().setKing(blackK);
                    square.getPiece().setOpponent(whiteK);
                }
                square.setBoard(this);
                squareArr[i][j] = square;
                squareArr[i][j].setOnMouseClicked(this::click);
                grid.add(squareArr[i][j], i, j);
            }
        }
        stack.getChildren().add(grid);
        stack.setMinSize(50, 50);
        //board.setHeight(this.getWidth());
        this.setCenter(stack);

        restOfBoard();
    }

    /**
     * MouseEvent handler for clicking on the chess {@code Pieces}.
     * Also includes game logic and calls the {@link Square} {@code moveTo}
     * method.
     * @param e the mouse event
     */
    public void click(MouseEvent e) {
        if (firstClick) {
            source = (Square) e.getSource();
            if (source.getPiece().getColor() == color) {
                if (source.getPiece().getPossibleMoves()) {
                    firstClick = false;
                } else {
                    System.out.println("No moves available for " + source.getPiece().getType() + " on " + source.getCoordinate());
                }
            } else if (source.getPiece().getColor() - color == 2) {
                System.out.println("It is white's turn.");
            } else if (source.getPiece().getColor() - color == -2) {
                System.out.println("It is black's turn.");
            }
        } else {
            Square target = (Square) e.getSource();
            if (color == target.getPiece().getColor()) {
                source = target;
                if (!source.getPiece().getPossibleMoves()) {
                    System.out.println("No moves available for " + source.getPiece().getType() + " on " + source.getCoordinate());
                }
            } else {
                if (source.getPiece().isPossibleMove(target)) {
                    source.moveTo(target);
                    color = (byte) (color == 1 ? -1 : 1);
                } else {
                    System.out.println("Invalid Move.");
                }
                firstClick = true;
            }
        }
    }

    /**
     * Creates the rest of the {@code Board} GUI.
     */
    public void restOfBoard() {
        Color background = Color.rgb(139,69,19);
        HBox top = new HBox(new Label());
        top.setPadding(new Insets(6));
        top.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setTop(top);
        //bottom letters
        GridPane letters = new GridPane();
        letters.setHgap(93);
        HBox bot = new HBox(letters);
        bot.setAlignment(Pos.CENTER);
        bot.setPadding(new Insets(6));
        char character = 'a';
        for (int i = 0; i < 8; i++) {
            Text text = new Text(Character.toString(character));
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
            text.setFill(Color.WHITE);
            letters.add(text, i, 0);
            character+=1;
        }
        bot.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setBottom(bot);
        VBox rit = new VBox(new Label("    "));
        rit.setPadding(new Insets(6));
        rit.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setRight(rit);
        //left numbers
        GridPane numbers = new GridPane();
        numbers.setVgap(82);
        VBox lef = new VBox(numbers);
        lef.setPadding(new Insets(6));
        lef.setAlignment(Pos.CENTER);
        for (int i = 0; i < 8; i++) {
            Text text = new Text(Integer.toString(i));
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
            text.setFill(Color.WHITE);
            numbers.add(text, 0, i);
        }
        lef.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setLeft(lef);
    }

    /**
     * Returns the {@code StackPane} board to add more functionality,
     * such as promotion.
     * @return the {@code StackPane} object
     */
    public StackPane getStack() {
        return stack;
    }

    /**
     * Sets the specified app to this
     * @param app the specified app
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Returns the color of the current {@code Board}.
     * @return the color of the Board
     */
    public byte getColor() {
        return color;
    }
}
