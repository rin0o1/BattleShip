package com.battleship.game;

import java.util.ArrayList;

public interface iShip {

     void dropShipOnTable(
            float [] coordinates,
            objectOrientation Orientation,
            ArrayList<cSquare> mySquare,
            float height);

     void shipSunk();

     void shipHit(float [] coordinates);

     boolean isSunk();
}
