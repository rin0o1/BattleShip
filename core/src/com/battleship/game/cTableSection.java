package com.battleship.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import jdk.internal.joptsimple.internal.Strings;

import java.awt.image.ImageFilter;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;


public class cTableSection extends Table implements iTableSection {

        private LinkedList<Explosion> explosionList;
        private Texture explosionTexture;

        private SpriteBatch batch;
        private float deltaTime;

        private float squareHeight;
        private float squareWidth;
        private int columsNum;
        private int rowsNum;


        private ArrayList<cTableObject> tableObjects;

        private String shipsTexturesPath;
        private int countOfShot;
        private Random random;



        public cTableSection ()
        {
            super();

            setTouchable(Touchable.enabled);
            squareHeight=50;
            squareWidth=50;
            columsNum=8;
            rowsNum=8;
            tableObjects= new ArrayList<cTableObject>();
            shipsTexturesPath= "C:\\Users\\Admin\\OneDrive - Brunel University London\\Courses\\CS1701-GroupProject\\GameDevelopment\\BattleShip\\core\\assets\\ships";
            random= new Random();
            /*
            explosionTexture = new Texture("explosion.png");
            explosionList= new LinkedList<>();
            explosionList.add(new Explosion(
                        explosionTexture,
                        new Rectangle(200,220,30,30),
                    1f
            ));*/

        }

        @Override
        public void initSection(){
                buildTable();
                displayShips();
        }

        public void renderSection(SpriteBatch batch, float deltaTime){
                this.batch=batch;
                this.deltaTime =deltaTime;
                //updateAndRenderExplosions();
        }

      /*  private void updateAndRenderExplosions() {
                ListIterator<Explosion> explosionListIterator = explosionList.listIterator();
                while (explosionListIterator.hasNext()) {
                        Explosion explosion = explosionListIterator.next();
                        explosion.update(deltaTime);
                        explosion.draw(batch);
                }
        }*/

        @Override
        public void buildTable() {
                float x;
                float y;

                float width=squareWidth;
                float height=squareHeight;
                String name="";

                for(int h=0; h<columsNum; h++) {

                        y = (Gdx.graphics.getHeight() / 2
                                - (height*columsNum)/2) + h*height;

                        for (int i = 0; i < rowsNum; i++) {

                                x = (Gdx.graphics.getWidth() / 2
                                        - (width*rowsNum)/2) + i*width;

                                name=createSquareNameFromCoordinates(i,h);

                                cSquare square=new cSquare(
                                        width,
                                        height,
                                        new float[]{x, y},
                                        name
                                );

                                this.add(square);
                        }
                }

        }

        @Override
        public void displayShips()
        {
                System.out.println("Displaying ships...");
                ArrayList<String> names= cHelper.getFilesFromFolderPath(
                        shipsTexturesPath
                );

                //getting textures from ships folder

                for (String shipTexurePath:
                     names) {

                        File f= new File(shipTexurePath);
                        String fileName=f.getName();
                        String [] splitName= fileName.split("_");

                        //getting ship inf
                        String shipName= splitName[0];
                        int length=Integer.valueOf(splitName[1]);

                        Texture texture= new Texture(
                                new FileHandle(f)
                        );

                        cShip ship= new cShip(
                                texture,
                                shipName,
                                length
                        );

                        tableObjects.add(ship);
                }


                ArrayList<cShip> ships=getShipsFromTableObjects();

                //dropping ships on table
                for (cShip s:
                        ships)
                {
                       boolean isDropped=false;

                       while(!isDropped){

                               int [] startCoordinates= new int[2];
                               int [] endCoordinates= new int[2];
                               int shipLength=s.getLength();

                               int start_x=-1;
                               int start_y=-1;
                               int end_x=-1;
                               int end_y=-1;
                               int orientationNum=random.nextInt(2);


                               objectOrientation orientation= (orientationNum<=0) ?
                                       objectOrientation.HORIZONTAL:
                                       objectOrientation.VERTICAL;

                               if(orientation==objectOrientation.HORIZONTAL){
                                       int range=(columsNum-shipLength);
                                       start_x=random.nextInt(range-1);
                                       start_y=random.nextInt(rowsNum);
                                       end_x=start_x+shipLength;
                                       end_y=start_y;
                               }

                               else if(orientation==objectOrientation.VERTICAL){
                                        int range=(rowsNum-shipLength);
                                        start_y=random.nextInt(range-1);
                                        start_x=random.nextInt(columsNum);
                                        end_y=start_y+shipLength;
                                        end_x=start_x;
                               }

                               startCoordinates[0]=start_x;
                               startCoordinates[1]=start_y;
                               endCoordinates[0]=end_x;
                               endCoordinates[1]=end_y;

                               ArrayList<cSquare> squaresInterested=getRangeOfSquaresFromCoordinates(
                                       startCoordinates,
                                       endCoordinates,
                                       orientation
                               );

                               //there are some squares which are not available
                               if(areSquareBusy(squaresInterested)){continue;}

                               cSquare startSquare= squaresInterested.get(0);
                               float[] startSquareCoordinates=startSquare.getCoordinates();
                               float square_x=startSquareCoordinates[0];
                               float square_y=startSquareCoordinates[1];

                               s.dropShipOnTable(
                                       new float[] {square_x,square_y},
                                       orientation,
                                       squaresInterested,
                                       shipLength*squareHeight
                               );

                               for (cSquare square:
                                    squaresInterested) {
                                       square.placeObject(square);
                               }

                               isDropped=true;
                       }
                }
                System.out.println("Ships displayed");

        }

        private String createSquareNameFromCoordinates(
                int x,
                int y
        )
        {
                return String.valueOf(x).concat("_").concat(String.valueOf(y));
        }

        private ArrayList<cSquare> getRangeOfSquaresFromCoordinates(
                int[] startCoordinates,
                int [] endCoordinates,
                objectOrientation orientation
        )
        {
                ArrayList<cSquare> result= new ArrayList<>();

                if(orientation==objectOrientation.HORIZONTAL){
                        int start_x=startCoordinates[0];
                        int end_x=endCoordinates[0];
                        for (int i=start_x;i<end_x; i++){
                                String name=createSquareNameFromCoordinates(i,startCoordinates[1]);
                                result.add(getSquareFromName(name));
                        }
                }

                else if(orientation==objectOrientation.VERTICAL){
                        int start_y=startCoordinates[1];
                        int end_y=endCoordinates[1];
                        for (int i=start_y;i<end_y; i++){
                                String name=createSquareNameFromCoordinates(startCoordinates[0],i);
                                result.add(getSquareFromName(name));
                        }
                }

                return result;
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


        private ArrayList<cShip> getShipsFromTableObjects(){
                ArrayList<cShip> ships= new ArrayList<>();
                for (cTableObject o:
                     tableObjects) {
                        if (o instanceof cShip){
                                ships.add((cShip) o);
                        }
                }
                return   ships;
        }

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

                //onPlayerClick
}