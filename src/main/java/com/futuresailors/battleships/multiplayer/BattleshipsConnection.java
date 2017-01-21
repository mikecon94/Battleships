package com.futuresailors.battleships.multiplayer;

/**
 * Connection Interface.
 * @author Mike Conroy.
 */

public interface BattleshipsConnection {
    public void close();

    public void sendMessage(Object object);
}
