package com.battleship.game;

public interface iShip {

     void dropShipOnTable(
            float [] coordinates,
            int Orientation,
            int [] squareId,
            float height);

     void shipSunk();

     void shipHit(float [] coordinates);

     boolean isSunk();
}
