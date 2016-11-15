package com.futuresailors.battleships.ai;

import java.awt.Point;

/**
 * Interface for the AI classes to implement. Defines the two methods that the AI classes require.
 * 
 * @author Michael Conroy
 */
public interface AI {

    /**
     * The Ships will be given to the AI on the constructor. This method will then choose where to
     * place them on the grid.
     */
    void placeShips();

    /**
     * Allows the AI to choose a location to drop a bomb.
     * 
     * @return Location the bomb was dropped.
     */
    Point takeMove();
}
