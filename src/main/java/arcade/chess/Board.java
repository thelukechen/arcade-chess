package arcade.chess;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Board extends GridPane {

    App app;
    Square[][] squareArr = new Square[8][8];
    final int size = 8;
    boolean firstClick = false;
    Square squareClicked;
    int whoseTurn = -1;

    public Board() {
        super();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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

                this.add(squareArr[i][j], i, j);
                Board.setHgrow(square, Priority.ALWAYS);
            }
        }
        this.setMinSize(50, 50);
        this.setHeight(this.getWidth());
    }

    public void click(MouseEvent e) {
        if (!firstClick) {
            // first click
            squareClicked = (Square) e.getSource();
            if (whoseTurn != squareClicked.getPiece().getSide()) {
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
            if (squareClicked.getPiece().isValidMove(target)) {
                squareClicked.moveTo(target);
                whoseTurn = whoseTurn == -1 ? 1 : -1;
                firstClick = false;
            } else if (squareClicked.getPiece().getSide() == target.getPiece().getSide()) {
                squareClicked = target;
            } else if (squareClicked.getCoordinate() != target.getCoordinate()) { //for if u click on the same piece
                System.out.println(squareClicked.getCoordinate());
                System.out.println(target.getCoordinate());
                System.out.println("Invalid Move");
                firstClick = false;
            }
        }
    }

    public void setApp(App app) {
        this.app = app;
    }
}
