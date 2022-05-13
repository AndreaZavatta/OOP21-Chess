package gui.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import piece.utils.Name;
import pieces.Piece;

/**
 * Controller class for Board.fxml.
 *
 */
public class BoardController {
    @FXML
    private GridPane pane;

    private final List<Tile> tiles = new ArrayList<>();
    private final ChessboardFactory factory = new ChessboardFactoryImpl();
    private final Chessboard board = factory.createNormalCB();
    /**
     * The tile size.
     */
    public static final int TILE_SIZE = 600 / 8;
    /**
     * The width of the board.
     */
    public static final int WIDTH = 8;
    /**
     * The height of the board.
     */
    public static final int HEIGHT = 8;

    @FXML
    void initialize() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final Tile t = new Tile(i, j);
                tiles.add(t);
                t.setOnMousePressed(e -> printPos(t));
                t.setStroke(Color.BLACK);
                t.setFill(Color.RED);
                pane.add(t, i, j);
            }
        }
        this.updateView();
    }

    private void updateView() {
        final Circle c = new Circle();
        final List<Piece> l =  board.getAllPieces();
        tiles.forEach(x -> {
            if (l.stream().map(y -> y.getPosition()).collect(Collectors.toList()).contains(x.getPosition())) {
                final Piece p = l.stream().filter(a -> a.getPosition().equals(x.getPosition())).findFirst().get();
                if (p.getName().equals(Name.PAWN)) {
                    //final ImagePattern i = new ImagePattern(new Image("/pieces/images/blackPawn.png"));
                    //final ImageView im = new ImageView("/pieces/images/blackPawn.png");
                    final Image im = new Image("/pieces/images/blackPawn.png");
                    c.setFill(new ImagePattern(im));
                    c.setStroke(Color.BLACK);
                    c.prefHeight(10);
                    c.prefWidth(10);
                    c.setOnMouseClicked(e -> System.out.println("a"));
                    //c.set
                    //x.setFill(c);
                    //im.setViewport();
                    //x.setClip(c);
                }
            }
        });
        pane.getChildren().add(c);
    }

    private void printPos(final Tile t) {
        System.out.println(t.getPosition());
        t.setFill(Color.BEIGE);
    }
}
