package game;

import java.io.Serializable;

import pair.Pair;
import piece.utils.Side;
import user.User;

interface Turn extends Serializable {

    int getTurn();

    void turnIncrement();

    Side getUserTurn();

    Pair<User, Side> getPairByColor(Side color);

    Side getOppositeColor(Side color);
}
