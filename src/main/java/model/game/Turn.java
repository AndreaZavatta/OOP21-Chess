package model.game;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import model.tuple.Pair;
import model.piece.utils.Side;
import model.user.User;
@JsonDeserialize(as = TurnImpl.class)
interface Turn {

    int getTurn();

    void turnIncrement();

    Side getUserTurn();

    Pair<User, Side> getPairByColor(Side color);

    Side getOppositeColor(Side color);

    Pair<User, User> getUsers();
}
