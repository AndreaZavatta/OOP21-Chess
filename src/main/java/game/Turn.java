package game;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pair.Pair;
import piece.utils.Side;
import user.User;
@JsonDeserialize(as = TurnImpl.class)
interface Turn extends Serializable {

    int getTurn();

    void turnIncrement();

    Side getUserTurn();

    Pair<User, Side> getPairByColor(Side color);

    Side getOppositeColor(Side color);
}
