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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class cTableSection extends Table implements iTableSection {

        private LinkedList<Explosion> explosionList;
        private Texture explosionTexture;

        private SpriteBatch batch;
        private float deltaTime;

        private float squareHeight;
        private float squareWidth;

        private ArrayList<cTableObject> tableObjects;

        private String shipsTexturesPath;

        private int countOfShot;



        public cTableSection ()
        {
            super();

            setTouchable(Touchable.enabled);

            squareHeight=50;
            squareWidth=50;

            tableObjects= new ArrayList<cTableObject>();
            explosionTexture = new Texture("explosion.png");

            shipsTexturesPath= "C:\\Users\\Admin\\OneDrive - Brunel University London\\Courses\\CS1701-GroupProject\\GameDevelopment\\BattleShip\\core\\assets\\ships";

            /*explosionList= new LinkedList<>();
            explosionList.add(new Explosion(
                        explosionTexture,
                        new Rectangle(200,220,30,30),
                    1f
            ));*/

        /*    shipTest= new cShip(
                    shipsTexturesPath,
                    "Length 3",
                    3,
                    new float[]{squareWidth, squareHeight}
            );

            shipTest.dropShipOnTable(
                    new float[] {150,200},
                    0,
                    new int[]{1,2},
                    3*50
            );*/


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
                int columsNum=8;
                int rowsNum=8;
                float width=squareWidth;
                float height=squareHeight;
                String name="";

                for(int h=0; h<columsNum; h++) {

                        y = (Gdx.graphics.getHeight() / 2
                                - (height*columsNum)/2) + h*height;

                        for (int i = 0; i < rowsNum; i++) {

                                x = (Gdx.graphics.getWidth() / 2
                                        - (width*rowsNum)/2) + i*width;

                                name=String.valueOf(x) +
                                        "_"
                                        + String.valueOf(y);

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
                ArrayList<String> names= cHelper.getFilesFromFolderPath(
                        shipsTexturesPath
                );

                //getting textures from ships folder

                for (String shipTexurePath:
                     names) {
                        String fileName=shipTexurePath.replace(
                                shipsTexturesPath,
                                ""
                        );
                        System.out.println(fileName);
                        String [] splitName= fileName.split("_");


                        //creating ships
                        String shipName= splitName[0];
                        int length=Integer.valueOf(splitName[1]);
                        File f= new File(shipTexurePath);
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

                for (cShip s:
                     getShipsFromTableObjects()) {
                        //dropping ships on table
                }

        }


        private ArrayList<cShip> getShipsFromTableObjects(){
                ArrayList<cShip> ships= new ArrayList<>();
                for (cTableObject o:
                     tableObjects) {
                        if (o instanceof cSquare){
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
                //shipTest.draw(batch, parentAlpha);
        }

                //onPlayerClick
}