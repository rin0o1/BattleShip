package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.io.File;
import java.util.*;

public class cTableSection extends Table implements iTableSection {

        //get those values from a setting file
        private final float squareHeight;
        private final float squareWidth;
        private final int columsNum;
        private final int rowsNum;
        private cWeaponHandler weaponHandler;
        private final ArrayList<cTableObject> tableObjects;
        private final String shipsTexturesPath;
        private Stage stage;
        private cGameView gameView;
        //test MAIN ONLINE
        public cTableSection (cGameView gameView) {
            super();
            squareHeight=50;
            squareWidth=50;
            columsNum=10;
            rowsNum=10;
            tableObjects= new ArrayList<cTableObject>();
            shipsTexturesPath= "C:\\Users\\Admin\\OneDrive - Brunel University London\\Courses\\CS1701-GroupProject\\GameDevelopment\\BattleShip\\core\\assets\\ships";

            weaponHandler= new cWeaponHandler(this);

            this.gameView=gameView;

        }
        public void updateShipList(cShip ship, boolean value){
                gameView.updateShipList(ship, value);
        }
        @Override
        public void initSection(Stage stage){
                this.stage=stage;

                buildTable();
                displayShips();
        }
        //this method is called as loop from cGameView class
        public void renderSection(SpriteBatch batch, float deltaTime){
                weaponHandler.renderAnimations(batch,deltaTime);
        }
        @Override
        public void buildTable() {

                float startHeight=Gdx.graphics.getHeight()+200;
                float startWidth=Gdx.graphics.getWidth();

                weaponHandler.createWeaponList(startWidth, startHeight);

                for(int h=0; h<columsNum; h++)
                {

                        float y = (startHeight / 2
                                - (squareHeight*columsNum)/2) + h*squareHeight;

                        for (int i = 0; i < rowsNum; i++) {

                                float x = (startWidth / 2
                                        - (squareWidth*rowsNum)/2) + i*squareWidth;

                                String name =createSquareNameFromCoordinates(i,h);

                                final cSquare square=new cSquare
                                        (
                                                squareWidth,
                                                squareHeight,
                                        new float[]{x, y},
                                        new int[] {i,h},
                                        name
                                );

                                square.getBody().addListener(new InputListener(){

                                        public void touchUp(
                                                InputEvent event,
                                                float offsetX,
                                                float offsetY,
                                                int pointer,
                                                int button)
                                        {
                                                onSquareTouchUp(
                                                        square
                                                );
                                        }

                                        public boolean touchDown(
                                                InputEvent event,
                                                float offsetX,
                                                float offsetY,
                                                int pointer,
                                                int button)
                                        {
                                                onSquareTouchDown(
                                                       square
                                                );
                                                return true;
                                        }
                                });
                                stage.addActor(square.getBody());
                                this.add(square);
                        }
                }
        }
        private void onSquareTouchUp(cSquare square)
        {
                square.squareTouchUp();
        }
        private boolean onSquareTouchDown(cSquare square) {
                square.squareTouchDown();
                weaponHandler.weaponHandler(square);
                return true;
        }
        public void updateScore(double score){
                gameView.updateScore(score, true);
        }
        @Override
        public void displayShips() {

                System.out.println("Displaying ships...");
                ArrayList<String> names= cHelper.getFilesFromFolderPath(
                        shipsTexturesPath
                );
                Collections.reverse(names);
                for (String shipTexurePath: names)
                {

                        File f= new File(shipTexurePath);
                        String fileName=f.getName();
                        String [] splitName= fileName.split("_");
                        //getting ship inf
                        String shipName= splitName[0];
                        int shipLength=Integer.valueOf(splitName[1]);
                        Texture texture= new Texture(new FileHandle(f));
                        Image imgIcon=new Image(texture);

                        cShip ship= new cShip(shipName, shipLength,imgIcon);

                        boolean isDropped=false;

                        //while the ship is not placed on the table
                        while(!isDropped)
                        {
                                int [] res= getPositionAndOrientationFromShipLength(shipLength);
                                objectOrientation orientation= (res[4]<=0) ?
                                        objectOrientation.HORIZONTAL:
                                        objectOrientation.VERTICAL;

                                ArrayList<cSquare> squaresInterested;
                                squaresInterested=getRangeOfSquaresFromCoordinates(
                                        new int[]{res[0], res[1]},
                                        new int[]{res[2],res[3]},
                                        orientation
                                );

                                if(!areSquareBusy(squaresInterested)) {

                                        cSquare startSquare = squaresInterested.get(0);
                                        float [] pos= startSquare.getPosition();
                                        float[] startSquarePosition = new float[2];

                                        startSquarePosition[0]=pos[0];
                                        startSquarePosition[1]=pos[1];

                                        startSquarePosition[1]+=(orientation==objectOrientation.HORIZONTAL)? squareHeight :0;

                                        ship.dropShipOnTable(
                                                texture,
                                                startSquarePosition,
                                                orientation,
                                                shipLength * squareHeight,
                                                squaresInterested
                                        );

                                        gameView.updateShipList(ship,false);

                                        for (cSquare square :
                                                squaresInterested)
                                        {
                                                square.placeObject(ship);
                                        }

                                        isDropped = true;
                                }
                        }

                        //when hit show the ship

                        //stage.addActor(ship);
                        tableObjects.add(ship);
                        System.out.println("Ships displayed");
                }
        }
        private int [] getPositionAndOrientationFromShipLength(int shipLength) {
                int [] startCoordinates= new int[2];
                int [] endCoordinates= new int[2];

                int start_x=-1;
                int start_y=-1;
                int end_x=-1;
                int end_y=-1;

                Random random= new Random();
                int orientationNum=random.nextInt(2);


                objectOrientation orientation= (orientationNum<=0) ?
                        objectOrientation.HORIZONTAL:
                        objectOrientation.VERTICAL;


                int shipLengthForTable=shipLength-1;

                if(orientation==objectOrientation.HORIZONTAL){
                        int rangeFor_x= (columsNum-1)-shipLengthForTable;
                        int rangeFor_y= rowsNum-1 ;
                        start_x=random.nextInt(rangeFor_x);
                        start_y=random.nextInt(rangeFor_y)+1;
                        end_x=start_x+shipLengthForTable;
                        end_y=start_y;

                        System.out.println("START X "+ start_x);
                        System.out.println("START Y "+ start_y);
                        System.out.println("LENGTH" + shipLength);
                        System.out.println("---------------------------------------------------");
                }

                else if(orientation==objectOrientation.VERTICAL)
                {
                        int rangeFor_y= (rowsNum-1)-shipLengthForTable;
                        int rangeFor_x=columsNum-1;
                        start_y=random.nextInt(rangeFor_y);
                        start_x=random.nextInt(rangeFor_x);
                        end_y=start_y+shipLengthForTable;
                        end_x=start_x;

                        System.out.println("START X "+ start_x);
                        System.out.println("START Y "+ start_y);
                        System.out.println("LENGTH" + shipLength);
                        System.out.println("---------------------------------------------------");
                }
                else {return null;}

                int [] res= new int[5];
                res[0]=start_x;
                res[1]=start_y;
                res[2]=end_x;
                res[3]=end_y;
                res[4]=orientationNum;

                return  res;
        }
        public String createSquareNameFromCoordinates(int x, int y) {
                return String.valueOf(x).concat("_").concat(String.valueOf(y));
        }
        private ArrayList<cSquare> getRangeOfSquaresFromCoordinates(int[] startCoordinates, int [] endCoordinates, objectOrientation orientation) {
                ArrayList<cSquare> result= new ArrayList<>();


                if(orientation==objectOrientation.HORIZONTAL){
                        int start_x=startCoordinates[0];
                        int end_x=endCoordinates[0];
                        for (int i=start_x;i<=end_x; i++){
                                String name=createSquareNameFromCoordinates(
                                        i,
                                        startCoordinates[1]
                                );
                                result.add(getSquareFromName(name));
                        }
                }

                else if(orientation==objectOrientation.VERTICAL){
                        int start_y=startCoordinates[1];
                        int end_y=endCoordinates[1];
                        for (int i=start_y;i<=end_y; i++){
                                String name=createSquareNameFromCoordinates(
                                        startCoordinates[0],
                                        i);
                                result.add(getSquareFromName(name));
                        }
                }

                return result;
        }
        public Actor getActorFromName(String name){
                return findActor(name);
        }
        private cSquare getSquareFromName(String name){
                return findActor(name);
        }
        private boolean areSquareBusy(ArrayList<cSquare> squares){
                for (cSquare s:
                     squares) {
                        if(s.isBusy()){return  true;}
                }
                return  false;
        }
        public Stage getStage(){return this.stage;}
        public int getColumsNum(){return this.columsNum;}
        public int getRowsNum(){return this.rowsNum;}
        public float[] getSquareDimensions(){return new float[]{squareWidth,squareHeight};}
        @Override
        public void showMessage(String message) {

        }
        @Override
        public void draw(Batch batch, float parentAlpha) {
                super.draw(batch, parentAlpha);
                for (cTableObject o:
                     tableObjects) {
                        o.draw(batch,parentAlpha);
                }
        }
}
