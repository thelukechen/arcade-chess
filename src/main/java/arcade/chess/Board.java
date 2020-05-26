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

    public Board() {
        super();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Square square = null;
                if (j == 6) {
                    //white pawns
                    square = new Square(i, j, new Pawn(true, i, j));
                } else if (j == 1) {
                    //black pawns
                    square = new Square(i, j, new Pawn(false, i, j));
                } else {
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
            if (squareClicked.getPiece().getType().equals("Empty")) {
                firstClick = false;
            } else if (squareClicked.getPiece().possibleMoves().length == 0) {
                System.out.println("No Moves Available");
            } else {
                firstClick = true;
            }
//            System.out.println(squareClicked.getPiece().getType() + ", " + squareClicked.getPiece().getColor());
//          System.out.println(squareClicked.getPiece().getX() + ", " + squareClicked.getPiece().getY());
//          System.out.println(squareClicked.getPiece().getCoordinate());
        } else {
            // second click
            Square target = (Square) e.getSource();
            if (squareClicked.getPiece().isValidMove(target)) {
                squareClicked.moveTo(target);
            } else if (squareClicked.getCoordinate() != target.getCoordinate()) {
                System.out.println("Invalid Move");
            }
            firstClick = false;
//            System.out.println(target.getPiece().getType() + ", " + target.getPiece().getColor());
//          System.out.println(target.getPiece().getX() + ", " + target.getPiece().getY());
//          System.out.println(target.getPiece().getCoordinate());

        }
    }

    public void setApp(App app) {
        this.app = app;
    }
}
