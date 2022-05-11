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
    @FXML
    private SVGPath abc;

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
        //        SVGPath svg = new SVGPath();
        //        svg.setContent("M150 0 L75 200 L225 200 Z");
        //        final Region svgShape = new Region();
        //        svgShape.setShape(svg);
        //        svgShape.setStyle("-fx-background-color: black;");
        //        Scene scene = new Scene(new StackPane(svgShape), 200, 200);
        //        pane.add(svgShape, TILE_SIZE, HEIGHT);
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
        //        SVGPath svg = new SVGPath();
        //        svg.setContent("M150 0 L75 200 L225 200 Z");
        //        final Region svgShape = new Region();
        //        svgShape.setShape(svg);
        //        svgShape.setStyle("-fx-background-color: black;");
        //        Scene scene = new Scene(new StackPane(svgShape), 200, 200);
        //        pane.add(svgShape, TILE_SIZE, HEIGHT);
        

    }

    private void updateView() {
        final List<Piece> l =  board.getAllPieces();
        tiles.forEach(x -> {
            if (l.stream().map(y -> y.getPosition()).collect(Collectors.toList()).contains(x.getPosition())) {
                final Piece p = l.stream().filter(a -> a.getPosition().equals(x.getPosition())).findFirst().get();
                if (p.getName().equals(Name.PAWN)) {
                    final ImagePattern i = new ImagePattern(new Image("/pieces/images/blackPawn.png"));
                    //x.setFill(i);
                }
            }
        });
    }

    private void printPos(final Tile t) {
        System.out.println(t.getPosition());
        t.setFill(Color.BEIGE);
        final String s = "M 802 1577 c -106 -39 -169 -155 -143 -265 l 12 -48 l -39 -41 c -106 -112 -125 -264 -49 -407 l 31 -58 l -29 -21 c -54 -37 -139 -132 -178 -198 c -58 -99 -98 -235 -104 -357 l -6 -102 l 583 0 l 583 0 l -6 88 c -16 234 -105 425 -256 546 l -59 48 l 28 45 c 81 127 63 300 -43 413 c -31 34 -32 36 -32 130 c 0 91 -1 97 -32 142 c -58 82 -168 118 -261 85 Z";
        abc.setScaleX(0.1);
        abc.setScaleY(0.1);
        abc.setContent(s);
        t.setClip(abc);
    }
}
