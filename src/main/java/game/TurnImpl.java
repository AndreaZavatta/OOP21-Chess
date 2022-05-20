package game;

import piece.utils.Side;

class TurnImpl implements Turn {
    private int turn = 1;

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
}
