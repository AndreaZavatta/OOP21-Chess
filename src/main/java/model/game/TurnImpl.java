package model.game;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import tuple.Pair;
import model.piece.utils.Side;
import model.user.User;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class TurnImpl implements Turn {

    private static final long serialVersionUID = -8909627808592630582L;
    private int turn;
    private final Pair<User, Side> player1;
    private final Pair<User, Side> player2;

    TurnImpl(final Pair<User, Side> player1, final Pair<User, Side> player2) {
        super();
        this.turn = 1;
        this.player1 = player1;
        this.player2 = player2;
    }


    @Override
    public int getTurn() {
        return this.turn;
    }

    @JsonIgnore
    @Override
    public Side getUserTurn() {
        return Math.abs(turn % 2) == 1 ? Side.WHITE : Side.BLACK;
    }

    @Override
    public void turnIncrement() {
        this.turn++;
    }

    @Override
    public Pair<User, Side> getPairByColor(final Side color) {
        return player1.getY().equals(color) 
                        ? player1 
                        : player2;
    }

    @Override
    public Side getOppositeColor(final Side color) {
        return color.equals(Side.WHITE) 
                        ? Side.BLACK
                        : Side.WHITE;
    }

    @JsonIgnore
    @Override
    public Pair<User, User> getUsers() {
        return new Pair<>(player1.getX(), player2.getX());
    }
}
