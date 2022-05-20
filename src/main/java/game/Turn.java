package game;

import piece.utils.Side;
import user.User;

interface Turn {

    int getTurn();

    void turnIncrement();

    Side getUserTurn();

    User getUserByColor(Side color);

    Side getOppositeColor(Side color);
}
