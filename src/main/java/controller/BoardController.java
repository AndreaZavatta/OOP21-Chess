package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import controller.user.UserController;
import controller.utils.ColorSettings;
import controller.utils.PieceImagePath;
import model.timer.ChessTimer;
import model.timer.ChessTimerImpl;
import model.game.Game;
import model.game.GameImpl;
import model.timer.MatchDuration;
import model.timer.TimerPlayer;
import model.timer.TimerPlayerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tuple.Pair;
import model.piece.utils.Name;
import model.piece.utils.Numbers;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.user.User;

import static controller.BoardControllerUtils.getBackground;
import static controller.BoardControllerUtils.getKingOfTheOtherTurn;
import static controller.BoardControllerUtils.getKingOfThisTurn;
import static controller.BoardControllerUtils.removeEffect;
import static controller.BoardControllerUtils.removeEffects;
import static controller.BoardControllerUtils.setEffect;
import static controller.BoardControllerUtils.setEffectPlayerTurn;
import static controller.BoardControllerUtils.setTextOptions;

/**
 * Controller class for Board.fxml.
 */
public class BoardController {
    /**
     * The tile size.
     */
    public static final int TILE_SIZE = 600 / 8;
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private static final int TEXT_DISTANCE = 15;
    private static final double OPACITY = 0.4;
    private static final int RADIUS = 15;
    private static final int STROKEWIDTH = 17;
    private Game match;
    private final Map<Position, Rectangle> mapPositionRectangle = new HashMap<>();
    private final Map<Piece, GuiPiece> mapPieceToGuiPiece = new HashMap<>();
    private final Map<GuiPiece, Piece> mapGuiPieceToPiece = new HashMap<>();
    private UserController whiteUser;
    private UserController blackUser;
    private List<Circle> circles = new ArrayList<>();
    private final ControllerUtils contrUtil = new ControllerUtils();
    private ColorSettings theme = ColorSettings.CORAL;
    @FXML
    private Pane pane = new Pane();
    @FXML
    private Pane borderPane = new Pane();
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
    private ChessTimer chessTimer;
    @FXML
    private Label whiteTimer = new Label();
    @FXML
    private Label blackTimer = new Label();

    /**
     * Initialize the player's textarea and image with the relative text and image.
     * @param whiteUser the white user.
     * @param blackUser the black user.
     */
    public void initializePlayers(final UserController whiteUser, final UserController blackUser) {
        this.whiteUser = whiteUser;
        this.blackUser = blackUser;
        this.createPlayers();
        this.match = new GameImpl(new Pair<User, Side>(whiteUser.getUser(), Side.WHITE),
                new Pair<User, Side>(blackUser.getUser(), Side.BLACK));
        this.createGuiPieces();
        createTimer();
    }

    private void createTimer() {
        final TimerPlayer whitePlayer = new TimerPlayerImpl(whiteUser.getName(), whiteUser.getImage(),
                MatchDuration.TEN_MINUTES_MATCH.getTime(), match, Side.WHITE);
        final TimerPlayer blackPlayer = new TimerPlayerImpl(blackUser.getName(), whiteUser.getImage(),
                MatchDuration.TEN_MINUTES_MATCH.getTime(), match, Side.BLACK);

        chessTimer = new ChessTimerImpl(whitePlayer, blackPlayer, whiteTimer, blackTimer);
        chessTimer.setGameOverListener(loserPlayer -> {
            try {
                match.setWinner();
                quitGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        chessTimer.buildTimer();
    }

    @FXML
    void initialize() {
        this.createChessboard();
        this.createBoardInformation();
        borderPane.setBackground(getBackground(theme.getBackground()));
    }

    @FXML
    void askForDraw(final ActionEvent event) {
        contrUtil.createEndGameAlert(match.getUserSideTurn() + " ask for a draw!",
                "Do you want to accept the draw?",
                AlertType.CONFIRMATION);
        setHeader(contrUtil.getAlert());
        final Optional<ButtonType> yesBtn = contrUtil.getAlert().showAndWait();
        final ButtonType yes = yesBtn.orElse(ButtonType.CANCEL);

        if (yes.equals(ButtonType.OK)) {
            try {
                match.setDraw();
            } catch (IOException ioEx) {
                contrUtil.showAlert("Impossible create record of the game", AlertType.WARNING);
            }
            chessTimer.closeTimer();
            quitGame();
        }
    }

    @FXML
    void surrend() {
        contrUtil.createEndGameAlert("Surrend", "Do you want to surrend?",
                AlertType.CONFIRMATION);
        contrUtil.getAlert().setHeaderText(match.getUserSideTurn() + " is surrendering");
        final Optional<ButtonType> yesBtn = contrUtil.getAlert().showAndWait();
        final ButtonType yes = yesBtn.orElse(ButtonType.CANCEL);

        if (yes.equals(ButtonType.OK)) {
            try {
                match.setWinner();
                chessTimer.closeTimer();
                quitGame();
            } catch (IOException e) {
                contrUtil.showAlert("Impossible create record of the game", AlertType.WARNING);
            }
        }
    }

    private void createPlayers() {
        this.blackPlayer.setText(blackUser.getName());
        this.whitePlayer.setText(whiteUser.getName());
        setTextOptions(whitePlayer);
        this.blackPlayerImage.setImage(blackUser.getImage());
        this.whitePlayerImage.setImage(whiteUser.getImage());
        setTextOptions(blackPlayer);
        setEffectPlayerTurn(whitePlayer, blackPlayerImage);
    }

    private void createBoardInformation() {
        for (int i = 0; i < WIDTH; i++) {
            final Text leftText = new Text(String.valueOf(8 - i));
            final Text bottomText = new Text(Character.toString(65 + i));
            leftText.setY(TILE_SIZE * i + TILE_SIZE / 2.0);
            leftText.setX(Numbers.FIVE);
            bottomText.setX(TILE_SIZE * i + TILE_SIZE / 2.0);
            bottomText.setY(TEXT_DISTANCE);
            setTextOptions(leftText);
            setTextOptions(bottomText);
            bottomPane.setBackground(getBackground(theme.getBoxesColor()));
            bottomPane.getChildren().add(bottomText);
            leftPane.setBackground(getBackground(theme.getBoxesColor()));
            leftPane.getChildren().add(leftText);
        }
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

        mapPieceToGuiPiece.put(piece, guiPiece);
        mapGuiPieceToPiece.put(guiPiece, piece);
        guiPieceRectangle.setOnMouseDragged(x -> dragged(x, guiPieceRectangle));
        guiPieceRectangle.setOnMouseReleased(x -> released(guiPiece));
        guiPieceRectangle.setOnMouseEntered(x -> setEffect(theme.getPieceEffect(), guiPieceRectangle));
        guiPieceRectangle.setOnMouseExited(x -> removeEffect(guiPieceRectangle, this.match, this.mapGuiPieceToPiece));
        guiPieceRectangle.setOnMousePressed(x -> showPossiblePositions(guiPiece));
        pane.getChildren().add(guiPiece.getRectangle());
    }

    private void createChessboard() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final Rectangle chessBoardRectangle = new Rectangle(i * TILE_SIZE, j * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE);
                mapPositionRectangle.put(Position.createNumericPosition(i, j), chessBoardRectangle);
                if ((i + j) % 2 == 0) {
                    chessBoardRectangle.setFill(theme.getR1());
                } else {
                    chessBoardRectangle.setFill(theme.getR2());
                }
                chessBoardRectangle.setStroke(Color.BLACK);
                chessBoardRectangle.setOnMouseEntered(x -> 
                setEffect(theme.getRectangleEffect(), chessBoardRectangle));
                chessBoardRectangle.
                setOnMouseExited(x -> removeEffect(chessBoardRectangle, this.match, this.mapGuiPieceToPiece));
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
                updateGui();
                return;
            } catch (IOException ioEx) {
                contrUtil.showAlert("Impossible create record of the game", AlertType.WARNING);
            }
            updatePlayers();
            updateGui();
            if (checkPieceOnPosition(finalPos)) {
                removePiece(finalPos);
            }
            isInCheck();
            pane.getChildren().removeAll(circles);
            if (match.isGameFinished()) {
                chessTimer.closeTimer();
                quitGame();
            }
            if (match.checkPromotion().isPresent()) {
                selectPromotion(mapGuiPieceToPiece.get(guiPiece));
            }
        } else {
            updateGui();
        }
    }

    private void isInCheck() {
        if (match.isInCheck()) {
            setEffect(theme.getPieceEffect(), getKingOfThisTurn(this.match, this.mapGuiPieceToPiece).getRectangle());
        } else {
            removeEffect(getKingOfTheOtherTurn(this.match, this.mapGuiPieceToPiece).getRectangle(),
                    this.match, this.mapGuiPieceToPiece);
        }
    }

    private void quitGame() {
        contrUtil.createEndGameAlert("Game ended", "Press ok to go back to main menu",
                AlertType.INFORMATION);
        setHeader(contrUtil.getAlert());
        final Optional<ButtonType> result = contrUtil.getAlert().showAndWait();
        final ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    private void selectPromotion(final Piece oldPiece) {
        final Stage promotionStage = new Stage();
        final Button queen = contrUtil.createImageButton(PieceImagePath.QUEEN.getPath(match.getOppositeColor(match.getUserSideTurn())));
        queen.setOnMouseClicked(x -> setClick(Name.QUEEN, promotionStage, oldPiece));
        final Button rook = contrUtil.createImageButton(PieceImagePath.ROOK.getPath(match.getOppositeColor(match.getUserSideTurn())));
        rook.setOnMouseClicked(x ->  setClick(Name.ROOK, promotionStage, oldPiece));
        final Button bishop = contrUtil.createImageButton(PieceImagePath.BISHOP.getPath(match.getOppositeColor(match.getUserSideTurn())));
        bishop.setOnMouseClicked(x -> setClick(Name.BISHOP, promotionStage, oldPiece));
        final Button knight = contrUtil.createImageButton(PieceImagePath.KNIGHT.getPath(match.getOppositeColor(match.getUserSideTurn())));
        knight.setOnMouseClicked(x -> setClick(Name.KNIGHT, promotionStage, oldPiece));
        final VBox window = new VBox(10);
        window.getChildren().addAll(queen, rook, bishop, knight);
        window.setAlignment(Pos.CENTER);
        final Scene promotionScene = new Scene(window);
        promotionStage.initModality(Modality.APPLICATION_MODAL);
        promotionStage.setOnCloseRequest(x -> x.consume());
        promotionStage.setScene(promotionScene);
        promotionStage.show();
    }

    private void setHeader(final Alert alert) {
        match.getWinner().ifPresentOrElse(x -> alert.setHeaderText("Player name '" 
                + match.getWinner().get().getX() 
                + "' side '"
                + match.getWinner().get().getY() 
                + "' won!!"), () -> alert.setHeaderText("It's a draw!"));
    }

    private void backToMainMenu() {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/layouts/MainMenu.fxml"));
            final Parent root = (Parent) loader.load();
            final Stage stage = new Stage();
            stage.setTitle("MENU");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node) (whitePlayer)).getScene().getWindow().hide();
        } catch (IOException e) {
            System.out.println(e.getMessage());
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

    private boolean checkPieceOnPosition(final Position pos) {
        return mapPieceToGuiPiece.keySet().stream().anyMatch(x -> x.getPosition().equals(pos) && x.getSide().equals(match.getUserSideTurn()));
    }

    private void updateGui() {
        mapPieceToGuiPiece.entrySet().forEach(x -> updatePositionOnGuiPiece(x.getKey().getPosition(), x.getValue()));
    }

    private void updatePlayers() {
        if (match.getUserSideTurn().equals(Side.BLACK)) {
            setEffectPlayerTurn(blackPlayer, whitePlayerImage);
            removeEffects(whitePlayer, blackPlayerImage);
        } else {
            setEffectPlayerTurn(whitePlayer, blackPlayerImage);
            removeEffects(blackPlayer, whitePlayerImage);
        }
    }

    private void removePiece(final Position finalPos) {
        final GuiPiece deadGuiPiece = mapPieceToGuiPiece.entrySet().stream()
                .filter(p -> p.getKey().getPosition().equals(finalPos))
                .filter(p -> p.getKey().getSide().equals(match.getUserSideTurn()))
                .findFirst()
                .get()
                .getValue();
        final Piece deadPiece = mapGuiPieceToPiece.get(deadGuiPiece);
        mapPieceRemove(deadPiece, deadGuiPiece);
    }

    private void updatePromotion(final Piece oldPiece, final Piece newPiece) {
        final GuiPiece oldGuiPiece = mapPieceToGuiPiece.get(oldPiece);
        mapPieceRemove(oldPiece, oldGuiPiece);
        createGuiPiece(newPiece);
    }

    private void mapPieceRemove(final Piece piece, final GuiPiece guiPiece) {
        mapGuiPieceToPiece.remove(guiPiece);
        mapPieceToGuiPiece.remove(piece);
        pane.getChildren().remove(guiPiece.getRectangle());
    }

    private void setClick(final Name namePiece, final Stage currentStage, final Piece oldPiece) {
        updatePromotion(oldPiece, match.promotion(namePiece));
        currentStage.close();
    }
}
