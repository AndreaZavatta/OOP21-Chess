package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.Game;
import game.GameImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import pair.Pair;
import model.piece.utils.Numbers;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import user.User;
import user.UserController;

/**
 * Controller class for Board.fxml.
 */
public class BoardController {

    private static final int TEXT_DISTANCE = 15;
    private static final double OPACITY = 0.4;
    private static final int RADIUS = 15;
    private static final int STROKEWIDTH = 17;

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
    private final Map<Position, GuiPiece> mapGuiPiecePosition = new HashMap<>();
    private final Map<GuiPiece, Piece> mapGuiPieceToPiece = new HashMap<>();
    private UserController whiteUser;
    private UserController blackUser;
    private List<Circle> circles = new ArrayList<>();
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
    /**
     * Initialize the player's textarea and image with the relative text and image.
     * @param whiteUser the white user.
     * @param blackUser the black user.
     */
    public void initializePlayers(final UserController whiteUser, final UserController blackUser) {
        this.whiteUser = whiteUser;
        this.blackUser = blackUser;
        this.initializePlayers();
        this.match = new GameImpl(new Pair<User, Side>(whiteUser.getUser(), Side.WHITE),
                new Pair<User, Side>(blackUser.getUser(), Side.BLACK));
        this.createGuiPieces();
    }
    @FXML
    void initialize() {
        this.createChessboard();
        this.createBoxes();
        anchorPane.setStyle("-fx-background-color: #2F4F4F");
    }

    @FXML
    void askForDraw(final ActionEvent event) {
        //TODO
    }

    private void initializePlayers() {
        this.blackPlayer.setText(blackUser.getName());
        this.whitePlayer.setText(whiteUser.getName());
        blackPlayer.setId("textPlayersBoard");
        this.blackPlayerImage.setImage(blackUser.getImage());
        this.whitePlayerImage.setImage(whiteUser.getImage());
    }

    private void createBoxes() {
        for (int i = 0; i < WIDTH; i++) {
            final Text leftText = new Text(String.valueOf(8 - i));
            final Text bottomText = new Text(Character.toString(65 + i));
            leftText.setY(TILE_SIZE * i + TILE_SIZE / 2.0);
            leftText.setX(Numbers.FIVE);
            bottomText.setX(TILE_SIZE * i + TILE_SIZE / 2.0);
            bottomText.setY(TEXT_DISTANCE);
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

    private void createGuiPieces() {
        match.getPiecesList().stream().forEach(x -> createGuiPiece(x));
    }

    private void createGuiPiece(final Piece piece) {
        final GuiPieceFactory guiPieceFct = new GuiPieceFactoryImpl();
        final GuiPiece guiPiece = guiPieceFct.createGuiPiece(piece);
        final Rectangle guiPieceRectangle = guiPiece.getRectangle();
        final Position piecePos = piece.getPosition();
        guiPiece.setX(piecePos.getX());
        guiPiece.setY(piecePos.getY());

        mapGuiPiecePosition.put(piecePos, guiPiece);
        mapGuiPieceToPiece.put(guiPiece, piece);
        guiPieceRectangle.setOnMouseDragged(x -> dragged(x, guiPieceRectangle));
        guiPieceRectangle.setOnMouseReleased(x -> released(guiPiece));
        guiPieceRectangle.setOnMouseEntered(x -> BoardControllerUtils.setEffect(Color.RED, guiPieceRectangle));
        guiPieceRectangle.setOnMouseExited(x -> removeEffect(guiPieceRectangle));
        guiPieceRectangle.setOnMousePressed(x -> showPossiblePositions(guiPiece));
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
                    chessBoardRectangle.setFill(Color.valueOf("#582"));
                } else {
                    chessBoardRectangle.setFill(Color.valueOf("#feb"));
                }
                count++;
                chessBoardRectangle.setStroke(Color.BLACK);
                chessBoardRectangle.setOnMouseEntered(x -> BoardControllerUtils.setEffect(Color.YELLOW, chessBoardRectangle));
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
        final Position finalPos = Position.createNumericPosition(x, y);
        final Position firstPos = mapGuiPieceToPiece.get(guiPiece).getPosition();
        if (mapPositionRectangle.containsKey(finalPos)) {
            try {
                match.nextMove(firstPos, finalPos);
            } catch (IllegalArgumentException e) {
                updatePositionOnGuiPiece(firstPos, guiPiece);
                return;
            }
            updatePositionOnGuiPiece(finalPos, guiPiece);
            if (mapGuiPiecePosition.containsKey(finalPos)) {
                final GuiPiece deadPiece = mapGuiPiecePosition.get(finalPos);
                mapGuiPieceToPiece.remove(deadPiece);
                pane.getChildren().remove(deadPiece.getRectangle());
            }
            if (match.isInCheck()) {
                BoardControllerUtils.setEffect(Color.RED, BoardControllerUtils.getKingOfThisTurn(this.match, this.mapGuiPieceToPiece).getRectangle());
                System.out.println("scacco");
            } else {
                this.removeEffect(BoardControllerUtils.getKingOfTheOtherTurn(this.match, this.mapGuiPieceToPiece).getRectangle());
            }
            //            if (match.isCastling(mapGuiPieceToPiece.get(guiPiece), firstPos)) {
            //
            //            }
            mapGuiPiecePosition.put(finalPos, guiPiece);
            mapGuiPiecePosition.remove(firstPos);
            pane.getChildren().removeAll(circles);
            if (match.isGameFinished()) {
                System.out.println("Game Over");
            }
        } else {
            updatePositionOnGuiPiece(firstPos, guiPiece);
        }
    }

//    private void setEffect(final Color color, final Rectangle rectangle) {
//        final InnerShadow shadow = new InnerShadow();
//        shadow.setBlurType(BlurType.GAUSSIAN);
//        shadow.setRadius(Numbers.SEVEN);
//        shadow.setChoke(CHOKE_VALUE);
//        shadow.setColor(color);
//        rectangle.setEffect(shadow);
//    }

    private void removeEffect(final Rectangle rectangle) {
        if (rectangle.equals(BoardControllerUtils.getKingOfThisTurn(match, mapGuiPieceToPiece).getRectangle()) 
                && match.isInCheck()) {

        } else {
            rectangle.setEffect(null);
        }
    }

    private void showPossiblePositions(final GuiPiece guiPiece) {
        pane.getChildren().removeAll(circles);
        circles.clear();
        final List<Position> possiblePositions = match.getPossiblePiecePositions(mapGuiPieceToPiece.get(guiPiece));
        if (mapGuiPieceToPiece.get(guiPiece).getSide().equals(match.getUserSideTurn())) {
            updateCirclesList(possiblePositions);
            pane.getChildren().addAll(circles);
        }
    }

    private void updateCirclesList(final List<Position> possiblePositions) {
        possiblePositions.forEach(x -> {
            final Circle newCircle = new Circle(TILE_SIZE / 2 + TILE_SIZE * x.getX(),
                    TILE_SIZE / 2 + TILE_SIZE * x.getY(),
                    RADIUS);
            if (match.getPiecesList().stream().anyMatch(p -> p.getPosition().equals(x))) {
                newCircle.setRadius(TILE_SIZE / 2);
                newCircle.setStroke(Color.BLACK);
                newCircle.setStrokeWidth(TILE_SIZE / STROKEWIDTH);
                newCircle.setFill(Color.TRANSPARENT);
            } else {
                newCircle.setFill(Color.BLACK);
            }
            newCircle.setOpacity(OPACITY);
            circles.add(newCircle);
        });
    }

    private void updatePositionOnGuiPiece(final Position pos, final GuiPiece guiPiece) {
        guiPiece.setX(pos.getX());
        guiPiece.setY(pos.getY());
    }


}
