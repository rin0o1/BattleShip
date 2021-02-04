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

        private LinkedList<Explosion> explosionList;
        private Texture explosionTexture;

        //get those values from a setting file
        private final float squareHeight;
        private final float squareWidth;
        private final int columsNum;
        private final int rowsNum;
        private int radarCount;

        private cWeapon weaponSelected;
        private Image weaponIconSelected;
        private ArrayList<cWeapon> weapons;
        private cWeaponHandler weaponHandler;

        private final ArrayList<cTableObject> tableObjects;

        private final String shipsTexturesPath;
        private final Random random;

        private Stage stage;
        private cGameView gameView;

        public cTableSection (cGameView gameView)
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

            weaponHandler= new cWeaponHandler(this);

            this.gameView=gameView;

            explosionTexture = new Texture("explosion.png");
            explosionList= new LinkedList<>();
        }

        public void updateShipList(cShip ship, boolean value){
                gameView.updateShipList(ship, value);
        }

        public void addExplosion(float locX, float locY){
                explosionList.add(new Explosion(
                        explosionTexture,
                        new Rectangle(
                                locX,
                                locY,
                                squareWidth,
                                squareHeight),
                        2f));
        }

        @Override
        public void initSection(Stage stage){
                this.stage=stage;
                initWeapon();
                //weaponHandler.initWeapon();
                buildTable();
                displayShips();

        }

        private void initWeapon() {
                cRadar r= new cRadar();
                cShot s= new cShot();
                weapons.add(s);
                weapons.add(r);
        }

        //this method is called as loop from cGameView class
        public void renderSection(SpriteBatch batch, float deltaTime){
                ListIterator<Explosion> explosionListIterator = explosionList.listIterator();
                //rendering explosions
                while (explosionListIterator.hasNext()) {
                        Explosion explosion = explosionListIterator.next();
                        explosion.update(deltaTime);
                        explosion.draw(batch);
                }
             //   cShip ship= getShipsFromTableObjects().get(0);
           //     ship.getBody().moveBy(ship.getX()+(1.5f), ship.getY());
         //       ship.getBody().draw(batch, deltaTime);

        }

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

                //designin
                for(int i=1; i<weapons.size()+1;i++)
                {
                        final cWeapon object=weapons.get(i-1);
                        final Image icon=object.getBody();

                        icon.setPosition(weaponWritten_x+80*i,weaponWritten_y-20);

                        icon.addListener(new InputListener(){
                                public void touchUp(
                                        InputEvent event,
                                        float offsetX,
                                        float offsetY,
                                        int pointer,
                                        int button)
                                {
                                      /*  icon.setSize(
                                                icon.getWidth()+20,
                                                icon.getHeight()+20);*/
                                        //weaponHandler.onWeaponSelected();
                                        onWeaponSelected(object,icon);
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
                        stage.addActor(icon);
                }

                for(int h=0; h<columsNum; h++)
                {

                        y = (startHeight / 2
                                - (height*columsNum)/2) + h*height;

                        for (int i = 0; i < rowsNum; i++) {

                                x = (startWidth / 2
                                        - (width*rowsNum)/2) + i*width;

                                name=createSquareNameFromCoordinates(i,h);

                                final cSquare square=new cSquare
                                        (
                                        width,
                                        height,
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

        private void onWeaponSelected(cWeapon weapon, Image icon) {
                //if is available
                weaponSelected =weapon;
                weaponIconSelected=icon;
                System.out.println("Weapon selected");
        }

        private void onSquareTouchUp(cSquare square)
        {
                square.squareTouchUp();
        }

        private boolean onSquareTouchDown(cSquare square) {
                square.squareTouchDown();
                weaponHandler(square);
                return true;
        }

        private void weaponHandler(cSquare square){
                if(weaponSelected==null){ System.out.println("Please choose a weapon"); return;}

                boolean isShot= weaponSelected instanceof cShot
                        && square.getIsAvailableForAction();
                if(isShot) { shotFunction(square); }

                boolean isRadarSelected= weaponSelected instanceof cRadar;
                if(isRadarSelected) { placeRadarFunction(square);}
        }

        private void placeRadarFunction(cSquare square) {
                int [] coordinates= square.getCoordinates();
                int square_X= coordinates[0];
                int square_Y= coordinates[1];
                if(!isRadarAvailable()){System.out.println("You used all radars available"); return ;}

                boolean canBePlaced=
                        square_X>0 &&
                                square_X <columsNum-1 &&
                                square_Y> 0 &&
                                square_Y< rowsNum-1;

                if(!canBePlaced){System.out.println("The radar cannot be placed here"); return ;}

                cRadar r=new cRadar(square);
                ArrayList<cSquare> squares=new ArrayList<>();
                ArrayList<String> squareCoordinatesRequired;
                squareCoordinatesRequired=r.getSquareCoordinatesRequired();

                for (String sC: squareCoordinatesRequired)
                {
                        String [] squareCoordinates=sC.split(",");
                        int s_x=Integer.valueOf(squareCoordinates[0]);
                        int s_y=Integer.valueOf(squareCoordinates[1]);

                        String fromCoordinatesToName= createSquareNameFromCoordinates(
                                s_x,
                                s_y
                        );
                        cSquare singleSquareRequired= findActor(fromCoordinatesToName);

                        squares.add(singleSquareRequired);
                }

                r.action(squares);
                radarCount-=1;
                System.out.println("Radar available "+ radarCount);
                weaponSelected=null;
                return;
        }

        private void shotFunction(cSquare square) {
                int points= weaponHandler.shotFunction(square);
                gameView.updateScore(points, true);
                weaponSelected=null;
        }

        private boolean isRadarAvailable()
        {
                return radarCount>0;
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

}
