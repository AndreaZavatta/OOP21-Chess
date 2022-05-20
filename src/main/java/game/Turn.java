package game;

import piece.utils.Side;

interface Turn {

    int getTurn();

    void turnIncrement();

    Side getUserTurn();
}
