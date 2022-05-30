package gui.board;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import game.Game;
import game.GameImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import pair.Pair;
import piece.utils.Numbers;
import piece.utils.Position;
import piece.utils.Side;
import pieces.Piece;
import user.User;
import user.UserController;

/**
 * Controller class for Board.fxml.
 *
 */
public class BoardController {
    @FXML
    private Pane pane = new Pane();
    @FXML
    private Pane anchorPane = new Pane();
    @FXML
    private Pane bottomPane = new Pane();
    @FXML
    private Pane leftPane = new Pane();
    @FXML
    private Text blackPlayer = new Text();
    @FXML
    private Text whitePlayer = new Text();
    @FXML
    private ImageView blackPlayerImage = new ImageView();
    @FXML
    private ImageView whitePlayerImage = new ImageView();
    private Game match;
    private final Map<Position, Rectangle> mapPositionRectangle = new HashMap<>();
    private final Map<GuiPiece, Position> mapGuiPiecePosition = new HashMap<>();
    //probabilmente ti serve una mappa pezzo-rettangolo oppure rettangolo-pezzo (la seconda probably)
    private UserController whiteUser;
    private UserController blackUser;
    private double lastX;
    private double lastY;
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
    public void createPlayers(final UserController whiteUser, final UserController blackUser) {
        this.whiteUser = whiteUser;
        this.blackUser = blackUser;
        Pair<User, Side> usr1 = new Pair<User, Side>(whiteUser.getUser(), Side.WHITE);
        Pair<User, Side> usr2 = new Pair<User, Side>(blackUser.getUser(), Side.BLACK);
        this.match = new GameImpl(new Pair<User, Side>(whiteUser.getUser(), Side.WHITE),
                                new Pair<User, Side>(blackUser.getUser(), Side.BLACK));
        this.initialize();
    }

    private void initialize() {
        this.createChessboard();
        this.createGuiPiece();
        this.createBoxes();
        this.createPlayers();
        anchorPane.setStyle("-fx-background-color: #2F4F4F");
    }

    @FXML
    void askForDraw(ActionEvent event) {
        //TODO
    }

    private void createPlayers(){
        this.blackPlayer.setText(blackUser.getName());
        this.whitePlayer.setText(whiteUser.getName());
        blackPlayer.setId("textPlayersBoard");
        this.blackPlayerImage.setImage(blackUser.getImage());
        this.whitePlayerImage.setImage(whiteUser.getImage());
    }

    private void createBoxes(){
        for(int i = 0; i < WIDTH; i++){
            final Text leftText = new Text(String.valueOf(8-i));
            final Text bottomText = new Text(Character.toString(65 + i));
            leftText.setY(TILE_SIZE * i + TILE_SIZE / 2.0);
            leftText.setX(5);
            bottomText.setX(TILE_SIZE * i + TILE_SIZE / 2.0);
            bottomText.setY(15);
            setTextOptions(leftText);
            setTextOptions(bottomText);
            bottomPane.getChildren().add(bottomText);
            leftPane.getChildren().add(leftText);
        }
    }

    private void setTextOptions(final Text text) {
        text.setStyle("-fx-font: 18 arial;");
        text.setFill(Color.WHITE);
    }

    private void createGuiPiece() {
        final GuiPiece guiPiece = new GuiPiece(TILE_SIZE, TILE_SIZE, "/pieces/images/blackPawn.png");
        final Rectangle guiPieceRectangle = guiPiece.getRectangle();
        lastX = Numbers.FOUR;
        lastY = Numbers.FOUR;
        guiPiece.setX(lastX);
        guiPiece.setY(lastY);
        mapGuiPiecePosition.put(guiPiece, Position.createNumericPosition((int) lastX, (int) lastY));
        guiPieceRectangle.setOnMouseDragged(x -> dragged(x, guiPieceRectangle));
        guiPieceRectangle.setOnMouseReleased(x -> released(guiPiece));
        guiPieceRectangle.setOnMouseEntered(x -> setEffect(Color.RED, guiPieceRectangle));
        guiPieceRectangle.setOnMouseExited(x -> removeEffect(guiPieceRectangle));
        pane.getChildren().add(guiPiece.getRectangle());
    }

    private void createChessboard() {
        int count = 0;
        for (int i = 0; i < WIDTH; i++) {
            count++;
            for (int j = 0; j < HEIGHT; j++) {
                final Rectangle chessBoardRectangle = new Rectangle(i * TILE_SIZE, j * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE);
                mapPositionRectangle.put(Position.createNumericPosition(i, j), chessBoardRectangle);
                if (count % 2 == 0) {
                    chessBoardRectangle.setFill(Color.valueOf("#feb"));
                } else {
                    chessBoardRectangle.setFill(Color.valueOf("#582"));
                }
                count++;
                chessBoardRectangle.setStroke(Color.BLACK);
                chessBoardRectangle.setOnMouseEntered(x -> setEffect(Color.YELLOW, chessBoardRectangle));
                chessBoardRectangle.setOnMouseExited(x -> removeEffect(chessBoardRectangle));
                pane.getChildren().add(chessBoardRectangle);
            }
        }
    }

    private void dragged(final MouseEvent event, final Rectangle guiPieceRectangle) {
        guiPieceRectangle.setX(event.getX() - (double) TILE_SIZE / 2);
        guiPieceRectangle.setY(event.getY() - (double) TILE_SIZE / 2);
    }

    private void released(final GuiPiece guiPiece) {
        final int x = (int) ((guiPiece.getRectangle().getX() + TILE_SIZE / 2) / TILE_SIZE);
        final int y = (int) ((guiPiece.getRectangle().getY() + TILE_SIZE / 2) / TILE_SIZE);
        final Position finalPosition = Position.createNumericPosition(x, y);
        if (mapPositionRectangle.containsKey(finalPosition)) {
            lastX = x;
            lastY = y;
            guiPiece.setX(lastX);
            guiPiece.setY(lastY);
            System.out.println(finalPosition);
            mapGuiPiecePosition.put(guiPiece, finalPosition);
        } else {
            System.out.println("Wrong position");
            guiPiece.setX(lastX);
            guiPiece.setY(lastY);
        }
        lightRectangle(finalPosition);
        pane.getChildren().remove(guiPiece.getRectangle());
    }

    private void lightRectangle(final Position finalPosition) {
        if (match.getPiecesList.getAllPieces().stream()
                .map(Piece::getPosition).collect(Collectors.toList()).contains(finalPosition)) {
            //mapPositionRectangle.get(finalPosition).setFill(Color.BLUE);
        }
    }

    private void setEffect(Color color, Rectangle rectangle) {
        InnerShadow shadow = new InnerShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setRadius(Numbers.SEVEN);
        shadow.setChoke(0.8);
        shadow.setColor(color);
        rectangle.setEffect(shadow);
    }

    private void removeEffect(Rectangle rectangle) {
        rectangle.setEffect(null);
    }
}
