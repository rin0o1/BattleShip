package com.battleship.game;

public interface iSquare {

     void setState();

     void placeObject( cTableObject object);

     void removeCurrentObject();

     cTableObject isBusy();


}
