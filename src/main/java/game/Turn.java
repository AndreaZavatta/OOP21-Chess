package game;

import pair.Pair;
import piece.utils.Side;
import user.User;

interface Turn {

    int getTurn();

    void turnIncrement();

    Side getUserTurn();

    Pair<User, Side> getPairByColor(Side color);

    Side getOppositeColor(Side color);
}
