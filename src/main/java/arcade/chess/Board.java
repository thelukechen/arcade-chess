package arcade.chess;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
    private boolean firstClick = false;
    private Square squareClicked;
    private int whoseTurn = -1;

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
                    //white queen
                    square = new Square(i, j, new King(true, i, j));
                } else if (j == 0) {
                    //black queen
                    square = new Square(i, j, new King(false, i, j));
                }  else {
                    //empty squares
                    square = new Square(i, j, new Empty(i, j));
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
        Color background = Color.rgb(139,69,19);
        this.setCenter(stack);

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
     * MouseEvent handler for clicking on the chess {@code Pieces}.
     * Also includes game logic and calls the {@link Square} {@code moveTo}
     * method.
     * @param e the mouse event
     */
    public void click(MouseEvent e) {
        if (!firstClick) {
            // first click
            squareClicked = (Square) e.getSource();
            if (whoseTurn != squareClicked.getPiece().getSide() && !squareClicked.getPiece().getType().equals("Empty")) {
                if (whoseTurn == -1) {
                    System.out.println("It is white's turn.");
                } else {
                    System.out.println("It is blacks's turn.");
                }
            } else if (squareClicked.getPiece().getType().equals("Empty")) {
                firstClick = false;
            } else if (squareClicked.getPiece().possibleMoves().length == 0) {
                System.out.println("No Moves Available");
            } else {
                firstClick = true;
            }
        } else {
            // second click
            Square target = (Square) e.getSource();
            if (squareClicked.getPiece().getSide() == target.getPiece().getSide()) {
                squareClicked = target;
                squareClicked.getPiece().possibleMoves();
            } else if (squareClicked.getCoordinate() == target.getCoordinate()) { //for if u click on the same piece
                firstClick = false;
            } else if (squareClicked.getPiece().isValidMove(target)) {
                squareClicked.moveTo(target);
                whoseTurn = whoseTurn == -1 ? 1 : -1;
                firstClick = false;
            } else if (squareClicked.getCoordinate() != target.getCoordinate()) { //for if u click on the same piece
                System.out.println("Invalid Move");
                firstClick = false;
            }
        }
    }

    /**
     * Sets the specified app to this
     * @param app the specified app
     */
    public void setApp(App app) {
        this.app = app;
    }

    public boolean getWhoseTurn() {
        return whoseTurn == -1;
    }
}
