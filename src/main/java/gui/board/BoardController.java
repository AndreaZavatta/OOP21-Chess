package gui.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import piece.utils.Position;
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
    public static final int TILE_SIZE = 600 / 8;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    @FXML
    void initialize() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final Tile t = new Tile(j, i);
                tiles.add(t);
                t.setOnMousePressed(e -> getPos(t));
                t.setStroke(Color.BLACK);
                t.setFill(Color.ALICEBLUE);
                pane.add(t, i, j);
            }
        }
        this.updateView();
    }

    private void updateView() {
        final List<Piece> l =  board.getAllPieces();
        tiles.forEach(x -> {
            if (l.stream().map(y -> y.getPosition()).collect(Collectors.toList()).contains(x.getPosition())) {
                getPos(x);
            }
        });
    }

    private void getPos(final Tile t) {
        System.out.println(t.getPosition());
        t.setFill(Color.BEIGE);
    }
}
