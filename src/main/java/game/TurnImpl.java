package game;

import pair.Pair;
import piece.utils.Side;
import user.User;

class TurnImpl implements Turn {
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

    @Override
    public Side getUserTurn() {
        return turn % 2 == 1 ? Side.WHITE : Side.BLACK;
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
}
