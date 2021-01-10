package com.battleship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.io.File;
import java.util.*;

public class cTableSection extends Table implements iTableSection {

        private LinkedList<Explosion> explosionList;
        private Texture explosionTexture;

        private SpriteBatch batch;
        private float deltaTime;

        private final float squareHeight;
        private final float squareWidth;
        private final int columsNum;
        private final int rowsNum;
        private final int radarCount;


        private cWeapon weaponSelected;

        private final ArrayList<cTableObject> tableObjects;

        private final String shipsTexturesPath;
        private int countOfShot;
        private final Random random;

        private ArrayList<cWeapon> weapons;

        private Stage stage;

        public cTableSection ()
        {
            super();

            setTouchable(Touchable.enabled);
            setFillParent(true);
            squareHeight=50;
            squareWidth=50;
            columsNum=10;
            rowsNum=10;
            tableObjects= new ArrayList<cTableObject>();
            shipsTexturesPath= "C:\\Users\\Admin\\OneDrive - Brunel University London\\Courses\\CS1701-GroupProject\\GameDevelopment\\BattleShip\\core\\assets\\ships";
            random= new Random();
            radarCount=4;
            weapons= new ArrayList<>();
            weaponSelected=null;
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
        public void initSection(Stage stage){
                this.stage=stage;
                initWeapon();
                buildTable();
                displayShips();

        }

        private void initWeapon()
        {
                cRadar r= new cRadar();
                cShot s= new cShot();
                weapons.add(s);
                weapons.add(r);
        }

        //this method is called as loop from cGameView class
        public void renderSection(SpriteBatch batch, float deltaTime){
                this.batch=batch;
                this.deltaTime =deltaTime;

             //   cShip ship= getShipsFromTableObjects().get(0);
           //     ship.getBody().moveBy(ship.getX()+(1.5f), ship.getY());
         //       ship.getBody().draw(batch, deltaTime);

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

                float startHeight=Gdx.graphics.getHeight()+200;
                float startWidth=Gdx.graphics.getWidth();
                float width=squareWidth;
                float height=squareHeight;
                String name="";

                //add weapons
                TextButton weaponWritten=new TextButton("WEAPONs",
                        cBattleShip.gameSkin);
                float weaponWritten_x=(startWidth / 2 - (width*rowsNum)/2);
                float weaponWritten_y=(startHeight / 2 - (height*columsNum)/2)-50;
                weaponWritten.setBounds(
                        weaponWritten_x,
                        weaponWritten_y,
                        0,
                        0);
                stage.addActor(weaponWritten);

                for(int i=1; i<weapons.size()+1;i++)
                {
                        final cWeapon object=weapons.get(i-1);
                        Image image=object.getBody();
                        image.setPosition(weaponWritten_x+80*i,weaponWritten_y-20);

                        image.addListener(new InputListener(){
                                public void touchUp(
                                        InputEvent event,
                                        float offsetX,
                                        float offsetY,
                                        int pointer,
                                        int button)
                                {
                                        onWeaponSelected(object);
                                }

                                public boolean touchDown(
                                        InputEvent event,
                                        float offsetX,
                                        float offsetY,
                                        int pointer,
                                        int button)
                                {
                                        return true;
                                }
                        });
                        stage.addActor(image);
                }

                for(int h=0; h<columsNum; h++)
                {

                        y = (startHeight / 2
                                - (height*columsNum)/2) + h*height;

                        for (int i = 0; i < rowsNum; i++) {

                                x = (startWidth / 2
                                        - (width*rowsNum)/2) + i*width;

                                name=createSquareNameFromCoordinates(i,h);
                                System.out.println("-------------------------");
                                System.out.println(name);
                                System.out.println("Y " + y);
                                System.out.println("x " + x);
                                System.out.println("-------------------------");

                                final cSquare square=new cSquare
                                        (
                                        width,
                                        height,
                                        new float[]{x, y},
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


        private void onWeaponSelected(cWeapon weapon)
        {
                //if is available
                Image image= weapon.getBody();
                image.setSize(image.getWidth()+20,image.getHeight()+20);

                weaponSelected =weapon;
                System.out.println("Weapon selected");
        }

        private void onSquareTouchUp(cSquare square)
        {
                System.out.println("cccc");
                square.squareTouchUp();
                if(weaponSelected !=null){

                        weaponSelected.action();
                        weaponSelected.getBody().setSize(
                                weaponSelected.getBody().getWidth()-20,
                                weaponSelected.getBody().getHeight()-20
                        );
                        weaponSelected=null;
                }


        }

        private boolean onSquareTouchDown(cSquare square)
        {
                System.out.println("ddd");
                square.squareTouchDown();

                return  true;
        }

        @Override
        public void displayShips() {

                System.out.println("Displaying ships...");
                ArrayList<String> names= cHelper.getFilesFromFolderPath(
                        shipsTexturesPath
                );

                //getting textures from ships folder

                Collections.reverse(names);

                for (String shipTexurePath: names)
                {

                        File f= new File(shipTexurePath);
                        String fileName=f.getName();
                        String [] splitName= fileName.split("_");

                        //getting ship inf
                        String shipName= splitName[0];
                        int shipLength=Integer.valueOf(splitName[1]);

                        Texture texture= new Texture(
                                new FileHandle(f)
                        );

                        cShip ship= new cShip(
                                texture,
                                shipName,
                                shipLength
                        );

                        boolean isDropped=false;
                        int count=0;

                        //while the ship is not placed on the table
                        while(!isDropped)
                        {
                                count++;
                                System.out.println("---------------------------------------------------");
                                System.out.println("COUNT "+ count);

                                int [] startCoordinates= new int[2];
                                int [] endCoordinates= new int[2];


                                int start_x=-1;
                                int start_y=-1;
                                int end_x=-1;
                                int end_y=-1;
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
                                        end_x=start_x+shipLength;
                                        end_y=start_y;

                                        System.out.println("START X "+ start_x);
                                        System.out.println("START Y "+ start_y);
                                        System.out.println("LENGTH" + shipLength);
                                        System.out.println("---------------------------------------------------");
                                }

                                else if(orientation==objectOrientation.VERTICAL){

                                        int rangeFor_y= (rowsNum-1)-shipLengthForTable;
                                        int rangeFor_x=columsNum-1;
                                        start_y=random.nextInt(rangeFor_y);
                                        start_x=random.nextInt(rangeFor_x);
                                        end_y=start_y+shipLength;
                                        end_x=start_x;

                                        System.out.println("START X "+ start_x);
                                        System.out.println("START Y "+ start_y);
                                        System.out.println("LENGTH" + shipLength);
                                        System.out.println("---------------------------------------------------");
                                }
                                else {return ;}

                                startCoordinates[0]=start_x;
                                startCoordinates[1]=start_y;
                                endCoordinates[0]=end_x;
                                endCoordinates[1]=end_y;

                                ArrayList<cSquare> squaresInterested;
                                squaresInterested=getRangeOfSquaresFromCoordinates(
                                        startCoordinates,
                                        endCoordinates,
                                        orientation
                                );

                                //there are some squares whhich are already busy from ships
                                if(!areSquareBusy(squaresInterested)) {

                                        cSquare startSquare = squaresInterested.get(0);
                                        float[] startSquareCoordinates = startSquare.getCoordinates();

                                        ship.dropShipOnTable(
                                                startSquareCoordinates,
                                                orientation,
                                                squaresInterested,
                                                shipLength * squareHeight
                                        );

                                        for (cSquare square :
                                                squaresInterested) {
                                                square.placeObject(ship);
                                        }

                                        isDropped = true;

                                }
                        }
                        stage.addActor(ship);
                        tableObjects.add(ship);
                        System.out.println("Ships displayed");
                }

        }

        private String createSquareNameFromCoordinates(
                int x,
                int y
        ) {
                return String.valueOf(x).concat("_").concat(String.valueOf(y));
        }

        private ArrayList<cSquare> getRangeOfSquaresFromCoordinates(
                int[] startCoordinates,
                int [] endCoordinates,
                objectOrientation orientation
        ) {
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
