package com.futuresailors.battleships.multiplayer;

public interface BattleshipsConnection {
    public void close();

    public void sendMessage(Object object);
}
