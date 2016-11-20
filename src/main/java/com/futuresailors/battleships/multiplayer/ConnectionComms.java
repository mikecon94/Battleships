package com.futuresailors.battleships.multiplayer;

public class ConnectionComms {
    public boolean connected;
    public boolean shipsPlaced;
    //This may seem ambiguous but whenever this object is sent across the network
    //The received knows "myTurn" refers to whether it is the opponents turn or not.
    public boolean myTurn;
}
