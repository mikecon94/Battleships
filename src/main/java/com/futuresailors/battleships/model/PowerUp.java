package com.futuresailors.battleships.model;

/**
 * This Interface defines the structure of PowerUps in the game
 * 
 * @author Joe Baldwin
 */
public interface PowerUp {

    public void select();

    public void use();

    public String getDesc();

}
