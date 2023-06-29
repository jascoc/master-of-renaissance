package it.polimi.ingsw.network.client.GUI;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.colourMarble.*;
import it.polimi.ingsw.network.client.Minimal.PlayerMinimal;
import it.polimi.ingsw.network.server.Controller;
import it.polimi.ingsw.network.server.LobbyManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import it.polimi.ingsw.network.client.Minimal.*;

import java.io.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static it.polimi.ingsw.GUIVariables.*;
import static it.polimi.ingsw.Variables.*;

//ImageView rende l'immagine cliccabile!!!
//Stackpane for overlapping images/buttons

/**
 * JavaFX App
 */
public class GUIView extends Application {

    private GUIHandler guiHandler;
    private GameModel gameModel;
    private GameInitialization gameInitialization;

    private DevelopmentCardMarketMinimal developmentCardMarketMinimal;
    private MarbleMarketMinimal marbleMarketMinimal;
    private PlayerMinimal thisPlayer;
    private PrintWriter toServer;
    private static Group setup;
    private static Group waitingLobby;
    private static Group discardLeaderCards;
    private static Group selectionResources;
    private static Group actualGamePanel;
    private static Group exit;
    private Stage stage;
    private Image redcross = loadImage(DESTROY);
    private ImageView quit = new ImageView(redcross);
    private Image askPlayers = loadImage(ASKER);
    private Image background = loadImage(FRONT_IMAGE);
    private Image imDone = loadImage(DONE);
    private ImageView button = new ImageView(imDone);
    private ImageView button2 = new ImageView(imDone);

    private Group root2 = new Group();
    private Group root3 = new Group();
    private Group root4 = new Group();
    //List<ImageView> GUILeaderCards = new ArrayList<>();







    public void start(Stage primaryStage) {

        /*Socket socket;
        try {
            socket = new Socket(LOCALHOST, 1234);

            guiHandler = new GUIHandler(socket);
        }
        catch (IOException e){
            Platform.exit();
            return;
        }

         */
        primaryStage.setTitle("Masters of Renaissance");
        //stage.setResizable(true);
        setStageClass(stage);


        //STATIC TEST FOR DIFFERENT STAGES

        /*
        deck1 = listDevelopmentCardToImgArray();
        deck2 = listDevelopmentCardToImgArray();
        deck3 = listDevelopmentCardToImgArray();
        deck4 = listDevelopmentCardToImgArray();
        deck5 = listDevelopmentCardToImgArray();
        deck6 = listDevelopmentCardToImgArray();
        deck7 = listDevelopmentCardToImgArray();
        deck8 = listDevelopmentCardToImgArray();
        deck9 = listDevelopmentCardToImgArray();
        deck10 = listDevelopmentCardToImgArray();
        deck11 = listDevelopmentCardToImgArray();
        deck12 = listDevelopmentCardToImgArray();

         */



        DevelopmentCardMinimal developmentCardMinimal1 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal2 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal3 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal4 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal5 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal6 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal7 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal8 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal9 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal10 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal11= new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal12 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal13 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal14 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal15 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal16 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal17 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal18 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal19 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal20 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal21 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal22 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal23 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal24 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal25 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal26 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal27 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal28 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal29 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal30 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal31 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal32 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal33 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal34 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal35 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal36 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal37 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal38 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal39 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal40 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal41 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal42 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal43 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal44 = new DevelopmentCardMinimal(2,YELLOW);
        DevelopmentCardMinimal developmentCardMinimal45 = new DevelopmentCardMinimal(2,BLUE);
        DevelopmentCardMinimal developmentCardMinimal46 = new DevelopmentCardMinimal(2,PURPLE);
        DevelopmentCardMinimal developmentCardMinimal47 = new DevelopmentCardMinimal(2,GREEN);
        DevelopmentCardMinimal developmentCardMinimal48 = new DevelopmentCardMinimal(2,YELLOW);



        ArrayList<DevelopmentCardMinimal> developmentCardMinimalArrayList = new ArrayList<>();
        ArrayList<DevelopmentCardMinimal> uselessDevelopmentCardMinimalArrayList = new ArrayList<>();

        developmentCardMinimalArrayList.add(developmentCardMinimal1);
        developmentCardMinimalArrayList.add(developmentCardMinimal2);


        LeaderCardMinimal leaderCardMinimal1 = new LeaderCardMinimal(EXTRA_SPACE_LEADER,SHIELD,3,COIN,uselessDevelopmentCardMinimalArrayList);
        LeaderCardMinimal leaderCardMinimal2 = new LeaderCardMinimal(EXTRA_SPACE_LEADER,SHIELD,3,SHIELD,uselessDevelopmentCardMinimalArrayList);
        LeaderCardMinimal leaderCardMinimal3 = new LeaderCardMinimal(EXTRA_SPACE_LEADER,SHIELD,3,SERVANT,uselessDevelopmentCardMinimalArrayList);
        LeaderCardMinimal leaderCardMinimal4 = new LeaderCardMinimal(EXTRA_SPACE_LEADER,SHIELD,3,"test",developmentCardMinimalArrayList);


        ArrayList<LeaderCardMinimal> leaderCardMinimalList = new ArrayList<>();

        leaderCardMinimalList.add(leaderCardMinimal1);
        leaderCardMinimalList.add(leaderCardMinimal2);
        leaderCardMinimalList.add(leaderCardMinimal3);
        leaderCardMinimalList.add(leaderCardMinimal4);

        PlayerMinimal playerMinimal1 = new PlayerMinimal("Michele",0,leaderCardMinimalList,true);
        PlayerMinimal playerMinimal2 = new PlayerMinimal("Jasco",1,leaderCardMinimalList,false);
        PlayerMinimal playerMinimal3 = new PlayerMinimal("Fabio",2,leaderCardMinimalList,false);
        PlayerMinimal playerMinimal4 = new PlayerMinimal("Valentina",3,leaderCardMinimalList,false);

        List<PlayerMinimal> playerMinimalList = new ArrayList<>();
        playerMinimalList.add(playerMinimal1);
        playerMinimalList.add(playerMinimal2);
        playerMinimalList.add(playerMinimal3);
        playerMinimalList.add(playerMinimal4);



        Group root0 = new Group();
        Group root1 = new Group();

        Canvas canvas = new Canvas(1024, 700);
        //Canvas discardLeaderCardsSelection = new Canvas(1024, 700);
        Canvas nameAndPLayersSelection = new Canvas(1024,700);
        Canvas waitingLobbySelection = new Canvas(1024,700);
        //Canvas resourcesSelection = new Canvas(1024,700);
        //Canvas lastCanvas = new Canvas(1024, 700);


        GraphicsContext gc = canvas.getGraphicsContext2D();

        //GraphicsContext gLeaderCards = discardLeaderCardsSelection.getGraphicsContext2D();
        GraphicsContext gNameAndPlayers = nameAndPLayersSelection.getGraphicsContext2D();
        GraphicsContext gWaitingLobby = waitingLobbySelection.getGraphicsContext2D();
        //GraphicsContext gSelectingResources = resourcesSelection.getGraphicsContext2D();
        //GraphicsContext gActualGame = lastCanvas.getGraphicsContext2D();
        //GraphicsContext gexit = onExit.getGraphicsContext2D();

        //Image background = loadImage(FRONT_IMAGE);
        //gNameAndPlayers.drawImage(background, 0, 0, 1024, 700);


        //gNameAndPlayers.drawImage(askPlayers, 300, 570, askPlayers.getWidth() / 2, askPlayers.getHeight() / 2);
        //gNameAndPlayers.drawImage(askPlayers, 500, 570, askPlayers.getWidth() / 5, askPlayers.getHeight() / 5);
        //listAskers.add(askPlayers);
        //listAskers.add(askPlayers);
        //gNameAndPlayers.setStroke(Color.WHITE);
        //gNameAndPlayers.setFont(Font.font("", FontWeight.SEMI_BOLD, 18));
        //gNameAndPlayers.strokeText(HOW_MANY_PLAYERS, 405, 600);



        //name&NPlayers selection setup
        gNameAndPlayers.drawImage(background, 0, 0, 1024, 700);
        gNameAndPlayers.setFill(Color.BLACK);
        gNameAndPlayers.setStroke(Color.BLACK);
        gNameAndPlayers.setFont(Font.font("", FontWeight.BLACK,  100));
        gNameAndPlayers.strokeText(TITLE, 20, 100);
        gNameAndPlayers.fillText(TITLE,20,100);


        insertQuestion(gNameAndPlayers, NAME  );
        insertQuestion(gNameAndPlayers, N_PLAYERS);
        //ImageView textNPlayers = insertQuestion(gNameAndPlayers, N_PLAYERS, listAskers);
        TextField textFieldName = new TextField();
        TextField textFieldNPlayers = new TextField();
        insertTextField(textFieldName, NAME);
        insertTextField(textFieldNPlayers, N_PLAYERS);
        //Image imDone = loadImage(DONE);
        //ImageView okButton = new ImageView(imDone);
        insertQuestion(gNameAndPlayers,BUTTON);
        createButton(button,850,660);
        //StackPane stackPane1 = new StackPane();





        //waitingLobby selection setup
        gWaitingLobby.drawImage(background, 0, 0, 1024, 700);
        createButton(button2,850,660);
        insertQuestion(gWaitingLobby,WAITING);

/*
        //discardLeaderCards selection Set up
        gameModel.initializeTheGame();

        gLeaderCards.drawImage(background, 0, 0, 1024, 700);
        insertQuestion(gLeaderCards,DISCARDLEADERCARDS);
        List<ImageView> discarded_cards = new ArrayList<>();
        List<ImageView> leaderCards = new ArrayList<>();
        List<LeaderCard> listOfLeaders = new ArrayList<>();
        List<ImageView> clickableLeaders = new ArrayList<>();
        //HashMap<ImageView, String> cardToPlayerName = new HashMap<>();

        LeaderCard leader0 = new ExtraProductionLeader("StoneProduction", 2, "newProduction", "Ciao1", "Stone");
        LeaderCard leader1 = new ExtraProductionLeader("StoneProduction", 2, "newProduction", "Ciao1", "Stone");
        LeaderCard leader2 = new ExtraProductionLeader("StoneProduction", 2, "newProduction", "Ciao1", "Stone");
        LeaderCard leader3 = new ExtraProductionLeader("StoneProduction", 2, "newProduction", "Ciao1", "Stone");



        for(int i=0 ; i < gameModel.getPlayerList().get(0).getLeaderCardList().size() ;i++) { //come faccio a dire alla gui che deve prendere le carte al giocatore che sta usando la gui in quel momento?
                clickableLeaders = leaderCardToListClickableCard(gameModel.getActivePlayer().getLeaderCardList().get(i),leaderCards);
        }

        for(int i=0 ; i < clickableLeaders.size(); i++) {
            createLeaderCards( i, i, clickableLeaders.get(i));
        }


*/

        //Discard LeaderCards selection Setup
        /*
        List<ImageView> GUILeaderCards = new ArrayList<>();
        for(int i=0 ; i<thisPlayer.getLeaderCards().size();i++) {
            leaderCardToGuiLeaderCard(thisPlayer.getLeaderCards().get(i),GUILeaderCards);
        }

         */
        List<ImageView> GUILeaderCards = new ArrayList<>();

        GUILeaderCards = leaderCardToGuiLeaderCard(leaderCardMinimalList);


        generateDiscardLeaderCardScene(GUILeaderCards,280,100,350,playerMinimal1);



        //Resources selection Setup
        List<String> resourceType = new ArrayList<>();
        List<ImageView> GUIResourcesType = new ArrayList<>();


        resourceType.add(COIN);
        resourceType.add(SHIELD);
        resourceType.add(STONE);
        resourceType.add(SERVANT);
        resourcesTypeToGUIResourcesType(resourceType,GUIResourcesType);
        generateSelectResourcesScene(GUIResourcesType,150,300,200,playerMinimal1,playerMinimalList);



        //Last Canvas Setup

        ArrayList<String> marketMarbles = new ArrayList<>();

        String marble1 = new White().toString();
        String marble2 = new Red().toString();
        String marble3 = new White().toString();
        String marble4 = new White().toString();
        String marble5 = new Purple().toString();
        String marble6 = new White().toString();
        String marble7 = new Purple().toString();
        String marble8 = new Yellow().toString();
        String marble9 = new Yellow().toString();
        String marble10 = new Grey().toString();
        String marble11 = new Blue().toString();
        String marble12 = new Grey().toString();
        String marble13 = new Blue().toString();

        marketMarbles.add(marble1);
        marketMarbles.add(marble2);
        marketMarbles.add(marble3);
        marketMarbles.add(marble4);
        marketMarbles.add(marble5);
        marketMarbles.add(marble6);
        marketMarbles.add(marble7);
        marketMarbles.add(marble8);
        marketMarbles.add(marble9);
        marketMarbles.add(marble10);
        marketMarbles.add(marble11);
        marketMarbles.add(marble12);
        marketMarbles.add(marble13);







        Image imgDeV1 = devCardToImg(developmentCardMinimal1);
        Image imgDeV2 = devCardToImg(developmentCardMinimal2);
        Image imgDeV3 = devCardToImg(developmentCardMinimal3);
        Image imgDeV4 = devCardToImg(developmentCardMinimal4);

        ArrayList<DevelopmentCardMinimal> arrayListDevCardMarket = new ArrayList<>();
        arrayListDevCardMarket.add(developmentCardMinimal1);
        arrayListDevCardMarket.add(developmentCardMinimal2);
        arrayListDevCardMarket.add(developmentCardMinimal3);
        arrayListDevCardMarket.add(developmentCardMinimal4);
        arrayListDevCardMarket.add(developmentCardMinimal5);
        arrayListDevCardMarket.add(developmentCardMinimal6);
        arrayListDevCardMarket.add(developmentCardMinimal7);
        arrayListDevCardMarket.add(developmentCardMinimal8);
        arrayListDevCardMarket.add(developmentCardMinimal9);
        arrayListDevCardMarket.add(developmentCardMinimal10);
        arrayListDevCardMarket.add(developmentCardMinimal11);
        arrayListDevCardMarket.add(developmentCardMinimal12);
        arrayListDevCardMarket.add(developmentCardMinimal13);
        arrayListDevCardMarket.add(developmentCardMinimal14);
        arrayListDevCardMarket.add(developmentCardMinimal15);
        arrayListDevCardMarket.add(developmentCardMinimal16);
        arrayListDevCardMarket.add(developmentCardMinimal17);
        arrayListDevCardMarket.add(developmentCardMinimal18);
        arrayListDevCardMarket.add(developmentCardMinimal19);
        arrayListDevCardMarket.add(developmentCardMinimal20);
        arrayListDevCardMarket.add(developmentCardMinimal21);
        arrayListDevCardMarket.add(developmentCardMinimal22);
        arrayListDevCardMarket.add(developmentCardMinimal23);
        arrayListDevCardMarket.add(developmentCardMinimal24);
        arrayListDevCardMarket.add(developmentCardMinimal25);
        arrayListDevCardMarket.add(developmentCardMinimal26);
        arrayListDevCardMarket.add(developmentCardMinimal27);
        arrayListDevCardMarket.add(developmentCardMinimal28);
        arrayListDevCardMarket.add(developmentCardMinimal29);
        arrayListDevCardMarket.add(developmentCardMinimal30);
        arrayListDevCardMarket.add(developmentCardMinimal31);
        arrayListDevCardMarket.add(developmentCardMinimal32);
        arrayListDevCardMarket.add(developmentCardMinimal33);
        arrayListDevCardMarket.add(developmentCardMinimal34);
        arrayListDevCardMarket.add(developmentCardMinimal35);
        arrayListDevCardMarket.add(developmentCardMinimal36);
        arrayListDevCardMarket.add(developmentCardMinimal37);
        arrayListDevCardMarket.add(developmentCardMinimal38);
        arrayListDevCardMarket.add(developmentCardMinimal39);
        arrayListDevCardMarket.add(developmentCardMinimal40);
        arrayListDevCardMarket.add(developmentCardMinimal41);
        arrayListDevCardMarket.add(developmentCardMinimal42);
        arrayListDevCardMarket.add(developmentCardMinimal43);
        arrayListDevCardMarket.add(developmentCardMinimal44);
        arrayListDevCardMarket.add(developmentCardMinimal45);
        arrayListDevCardMarket.add(developmentCardMinimal46);
        arrayListDevCardMarket.add(developmentCardMinimal47);
        arrayListDevCardMarket.add(developmentCardMinimal48);







        ArrayList<Image> deck1 = new ArrayList<>();
        deck1.add(imgDeV1);
        deck1.add(imgDeV2);
        deck1.add(imgDeV3);
        deck1.add(imgDeV4);

        //developmentCardMarketMinimal = new DevelopmentCardMarketMinimal();


        marbleMarketMinimal = generateMarketMarble(marketMarbles);
        developmentCardMarketMinimal = generateDevCardMarket(arrayListDevCardMarket);

        List<PlayerMinimal> listOfPlayers = new ArrayList<>();

        listOfPlayers.add(playerMinimal1);
        listOfPlayers.add(playerMinimal2);
        listOfPlayers.add(playerMinimal3);
        listOfPlayers.add(playerMinimal4);

        generateActualGamePanelScene(listOfPlayers,playerMinimal2,developmentCardMarketMinimal,marbleMarketMinimal);





        //stackPane.getChildren().addAll(okButton,root0);

        root0.getChildren().addAll(nameAndPLayersSelection,textFieldName,textFieldNPlayers, button);
        //stackPane1.getChildren().addAll(root0,okButton);
        root1.getChildren().addAll(waitingLobbySelection);
        //root2.getChildren().add(discardLeaderCardsSelection);
        //root3.getChildren().add(resourcesSelection);
        //stackPane.getChildren().addAll(okButton,root0);
        //drawShapes(gc);
        //drawCards(gc);
        //root0.getChildren().add(canvas);

        setup = root0;
        waitingLobby = root1;
        discardLeaderCards = root2;
        selectionResources = root3;
        actualGamePanel = root4;


        //Scene scene = new Scene(setup);
        //stage.setScene(scene);
        //stage.show();


        primaryStage.setScene(new Scene(actualGamePanel));
        primaryStage.show();




        button.setOnMouseEntered(mouseEvent -> root0.setCursor(Cursor.HAND));

        button.setOnMouseExited(mouseEvent -> root0.setCursor(Cursor.DEFAULT));

        button.setOnMouseClicked(mouseEvent -> {
            System.out.println( "setup" + " " + textFieldName.getText() + " " +  textFieldNPlayers.getText());
            //sendMessageToServer(Parser.getInstance().serializeMessage(new SetupGameMessage(Integer.valueOf(textFieldNPlayers.getText()),textFieldName.getText())));
            if(textFieldNPlayers.getText()=="1")
                setDiscardLeaderCardsStage();
            else
                setWaitingLobbyStage();
        });

        button2.setOnMouseClicked(mouseEvent -> {
            //System.out.println( "setup" + " " + textFieldName.getText() + " " +  textFieldNPlayers.getText());
            //sendMessageToServer(Parser.getInstance().serializeMessage(new SetupGameMessage(Integer.valueOf(textFieldNPlayers.getText()),textFieldName.getText())));
            setDiscardLeaderCardsStage();
        });
    }


    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240,
                ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},

                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);

    }


    private void drawImage(GraphicsContext gc,Image image,int coordinateX, int coordinateY) {
        //Image img = new Image( "Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png" );
        gc.drawImage(image, image.getHeight(), image.getWidth(), coordinateX, coordinateY);
    }




    private void createButton(ImageView imageView, double coord1, double coord2){
        imageView.setFitHeight(imageView.getImage().getHeight()/4);
        imageView.setFitWidth(imageView.getImage().getWidth()/4);
        imageView.fitHeightProperty();
        imageView.fitWidthProperty();
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setX(coord1);
        imageView.setY(coord2);
    }


    private void createUselessButton(ImageView imageView, int coord1, int coord2,double dim){
        imageView.setFitHeight(imageView.getImage().getHeight()/dim);
        imageView.setFitWidth(imageView.getImage().getWidth()/dim);
        imageView.fitHeightProperty();
        imageView.fitWidthProperty();
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setX(coord1);
        imageView.setY(coord2);


    }


    //private void createDynamicButtons (List<Image> imageList ){ }



    /**
     * This method create a banner with a question on it
     * @param graphicsContext is the graphic context of the canvas
     * @param string is to set the right question
     */

    private void insertQuestion(GraphicsContext graphicsContext, String string){

        //graphicsContext.drawImage(askPlayers, 200*(intSpace-1), 570, askPlayers.getWidth()/2, askPlayers.getHeight()/2);
        //graphicsContext.drawImage(askPlayers, 500, 570, askPlayers.getWidth()/5, askPlayers.getHeight()/2);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setFont(new Font("", 18));

        switch (string) {
            case NAME:
                graphicsContext.strokeText(INSERT_NAME, 250, 500);
                break;
            case N_PLAYERS:
                graphicsContext.strokeText(INSERT_NPLAYERS, 550, 500);
                break;
            case (BUTTON):
                graphicsContext.strokeText(OK, 910, 660);
                graphicsContext.fillText(OK, 910, 660);
                break;
            case WAITING:
                graphicsContext.setFont(new Font("", 22));
                graphicsContext.strokeText(WAITING_FOR, 750, 680);
                graphicsContext.fillText(WAITING_FOR, 750, 680);
                break;
            case DISCARD2L:
                graphicsContext.setFont(new Font("", 22));
                graphicsContext.strokeText(DISCARDLEADERCARDS, 400, 50);
                graphicsContext.fillText(DISCARDLEADERCARDS, 400, 50);
                break;

            case SEL_RESOURCES:
                graphicsContext.setFont(new Font("", 22));
                graphicsContext.fillText("Error", 400, 80);

                switch (thisPlayer.getPlayerRoundNumber()) {
                    case 0:
                        graphicsContext.strokeText("Since you are player number " + (thisPlayer.getPlayerRoundNumber() + 1) + "you can't choose extra resources.", 400, 80);
                        break;
                    case 1:
                        graphicsContext.strokeText("Since you are player number " + (thisPlayer.getPlayerRoundNumber() + 1) + "you can choose  1 extra resource.", 400, 80);
                        break;
                    case 2:
                        graphicsContext.strokeText("Since you are player number " + (thisPlayer.getPlayerRoundNumber() + 1) + "you can choose  1 extra resource.", 400, 80);
                        break;
                    case 3:
                        graphicsContext.strokeText("Since you are player number " + (thisPlayer.getPlayerRoundNumber() + 1) + "you can choose  2 extra resources.", 400, 80);
                        break;


                }
                break;
        }

    }



    private void insertQuestion(GraphicsContext graphicsContext, String string,List<PlayerMinimal> playerMinimalList){

        //graphicsContext.drawImage(askPlayers, 200*(intSpace-1), 570, askPlayers.getWidth()/2, askPlayers.getHeight()/2);
        //graphicsContext.drawImage(askPlayers, 500, 570, askPlayers.getWidth()/5, askPlayers.getHeight()/2);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setFont(new Font("", 18));

        switch (string) {
            case NAME:
                graphicsContext.strokeText(INSERT_NAME, 250, 500);
                break;
            case N_PLAYERS:
                graphicsContext.strokeText(INSERT_NPLAYERS, 550, 500);
                break;
            case (BUTTON):
                graphicsContext.strokeText(OK, 910, 660);
                graphicsContext.fillText(OK, 910, 660);
                break;
            case WAITING:
                graphicsContext.setFont(new Font("", 22));
                graphicsContext.strokeText(WAITING_FOR, 750, 680);
                graphicsContext.fillText(WAITING_FOR, 750, 680);
                break;
            case DISCARD2L:
                graphicsContext.setFont(new Font("", 22));
                graphicsContext.strokeText(DISCARDLEADERCARDS, 400, 50);
                graphicsContext.fillText(DISCARDLEADERCARDS, 400, 50);
            case SEL_RESOURCES:
                graphicsContext.setFont(new Font("", 30));
                //graphicsContext.fillText(DISCARDLEADERCARDS, 400, 80);
                switch (playerMinimalList.get(1).getPlayerRoundNumber()) {
                    case 0:
                        graphicsContext.strokeText("Since you are player number " +" " + (playerMinimalList.get(1).getPlayerRoundNumber() + 1) + " " + "you can't choose extra resources.", 110, 80);
                        graphicsContext.fillText("Since you are player number " +" " + (playerMinimalList.get(1).getPlayerRoundNumber() + 1) + " " + "you can't choose extra resources.", 110, 80);
                        break;
                    case 1:
                        graphicsContext.strokeText("Since you are player number " + " " +(playerMinimalList.get(1).getPlayerRoundNumber() + 1) +" " + "you can choose  1 extra resource.", 110, 80);
                        graphicsContext.fillText("Since you are player number " + " " +(playerMinimalList.get(1).getPlayerRoundNumber() + 1) +" " + "you can choose  1 extra resource.", 110, 80);
                        break;
                    case 2:
                        graphicsContext.strokeText("Since you are player number " + " " +(playerMinimalList.get(1).getPlayerRoundNumber() + 1) +" " + "you can choose  1 extra resource.", 110, 80);
                        graphicsContext.fillText("Since you are player number " + " " +(playerMinimalList.get(1).getPlayerRoundNumber() + 1) +" " + "you can choose  1 extra resource.", 110, 80);
                        break;
                    case 3:
                        graphicsContext.strokeText("Since you are player number " +" " + (playerMinimalList.get(1).getPlayerRoundNumber() + 1) +" " + "you can choose  2 extra resources.", 110, 80);
                        graphicsContext.fillText("Since you are player number " +" " + (playerMinimalList.get(1).getPlayerRoundNumber() + 1) +" " + "you can choose  2 extra resources.", 110, 80);
                        break;

                }
            case TURN :
                graphicsContext.setFont(new Font("", 20));
                if(playerMinimalList.get(0).isActive()){
                    graphicsContext.strokeText("It's" + playerMinimalList.get(0).getPlayerName() +" " + " turn " + " " , 850, 30);
                    graphicsContext.fillText("It's" + playerMinimalList.get(0).getPlayerName() +" " + " turn " + " " , 850, 30);
                }else if(playerMinimalList.get(1).isActive()){
                    graphicsContext.strokeText("It's your turn " + " " + playerMinimalList.get(1).getPlayerName(), 850, 30);
                    graphicsContext.fillText("It's your turn " + " " + playerMinimalList.get(1).getPlayerName(), 850, 30);

                }else if(playerMinimalList.get(2).isActive()){
                    graphicsContext.strokeText("It's" + playerMinimalList.get(2).getPlayerName() +" " + " turn " + " " , 850, 30);
                    graphicsContext.fillText("It's" + playerMinimalList.get(2).getPlayerName() +" " + " turn " + " " , 850, 30);
                }else if(playerMinimalList.get(3).isActive()){
                    graphicsContext.strokeText("It's" + playerMinimalList.get(3).getPlayerName() +" " + " turn " + " " , 850, 30);
                    graphicsContext.fillText("It's" + playerMinimalList.get(3).getPlayerName() +" " + " turn " + " " , 850, 30);
                }

                break;
        }





        /*ImageView text = new ImageView(askPlayers);
        text.setFitWidth(askPlayers.getWidth()/2);
        text.setFitHeight(askPlayers.getHeight()/2);
        text.setX((intSpace*100)+30);
        text.setY(510);


        return text;*/
    }






    /**
     * This method set all the condition for the textfield
     * @param textField is the textfield to set
     */
    private void insertTextField(TextField textField, String string){
        if(string.equals(NAME)) {
            textField.setAlignment(Pos.CENTER);
            textField.setTranslateX(247);
            textField.setPrefWidth(230);
            textField.setTranslateY(519);
            textField.setFont(new Font("", 15));
        }else if(string.equals(N_PLAYERS)){
            textField.setAlignment(Pos.CENTER);
            textField.setTranslateX(547);
            textField.setPrefWidth(230);
            textField.setTranslateY(519);
            textField.setFont(new Font("", 15));
        }
    }




    /**
     * This method create the quit button
     * @param imageView is the imageview of the quit
     */
    private void quitButton(ImageView imageView){
        imageView.setFitHeight(imageView.getImage().getHeight()/8);
        imageView.setFitWidth(imageView.getImage().getWidth()/8);
        imageView.fitHeightProperty();
        imageView.fitWidthProperty();
        imageView.setSmooth(true);
        imageView.setCache(true);

        imageView.setX(900);
        imageView.setY(30);
    }



    private void setStageClass(Stage stage){ this.stage = stage; }

    /**
     * This method load an image from resource folder
     * @param name is the name of the png file
     * @return the loaded image
     */
    private Image loadImage(String name){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(name);
        return new Image(inputStream);
    }

    /**
     * This method set the imageview in the canvas
     * @param imageView is the imageview to set
     * @param row is the row of the canvas
     * @param column is the column of the canvas
     */
    private void createLeaderCards( int row, int column, ImageView imageView){
        imageView.setFitWidth(imageView.getImage().getWidth()/6);
        imageView.setFitHeight(imageView.getImage().getHeight()/6);
        imageView.fitWidthProperty();
        imageView.fitHeightProperty();
        imageView.setSmooth(true);
        imageView.setCache(true);
        if(row == 0 && column==0) {
            imageView.setY(150);
            imageView.setX(100);
        } else if(row == 1 && column==1) {
            imageView.setY(150);
            imageView.setX(500);
        }else if(row == 2 && column==2) {
            imageView.setY(600);
            imageView.setX(100);
        }else if(row == 3 && column==3) {
            imageView.setY(600);
            imageView.setX(500);
        }

    }



    private List<ImageView> leaderCardToGuiLeaderCard(List<LeaderCardMinimal> leaderCardList){


        List<ImageView> clickableLeaderCards = new ArrayList<>();

        for(int i = 0 ; i<leaderCardList.size();i++) {
            if (leaderCardList.get(i).getRequiredResource().equals(COIN)) {
                Image leaderCardGUI = loadImage(COIN_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);


            } else if (leaderCardList.get(i).getRequiredResource().equals(SERVANT)) {
                Image leaderCardGUI = loadImage(SERVANT_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);


            } else if (leaderCardList.get(i).getRequiredResource().equals(SHIELD)) {
                Image leaderCardGUI = loadImage(SHIELD_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);


            } else if (leaderCardList.get(i).getRequiredResource().equals(STONE)) {
                Image leaderCardGUI = loadImage(STONE_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);


            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(YELLOW) &&
                    leaderCardList.get(i).getRequiredDevCards().get(1).getColour().equals(GREEN)) {
                Image leaderCardGUI = loadImage(YELLOW_GREEN_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);


            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(BLUE) &&
                    leaderCardList.get(i).getRequiredDevCards().get(1).getColour().equals(PURPLE)) {
                Image leaderCardGUI = loadImage(BLUE_PURPLE_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);


            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(GREEN) &&
                    leaderCardList.get(i).getRequiredDevCards().get(1).getColour().equals(BLUE)) {
                Image leaderCardGUI = loadImage(GREEN_BLUE_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);

            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(YELLOW) &&
                    leaderCardList.get(i).getRequiredDevCards().get(1).getColour().equals(PURPLE)) {
                Image leaderCardGUI = loadImage(YELLOW_PURPLE_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);

            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(YELLOW) &&
                    leaderCardList.get(i).getRequiredDevCards().get(1).getColour().equals(BLUE)) {
                Image leaderCardGUI = loadImage(YELLOW_BLUE_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);

            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(GREEN) &&
                    leaderCardList.get(i).getRequiredDevCards().get(1).getColour().equals(PURPLE)) {
                Image leaderCardGUI = loadImage(GREEN_PURPLE_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);

            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(BLUE) &&
                    leaderCardList.get(i).getRequiredDevCards().get(1).getColour().equals(YELLOW)) {
                Image leaderCardGUI = loadImage(BLUE_YELLOW_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);

            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(PURPLE) &&
                    leaderCardList.get(i).getRequiredDevCards().get(1).getColour().equals(GREEN)) {
                Image leaderCardGUI = loadImage(PURPLE_GREEN_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);

            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(YELLOW)) {
                Image leaderCardGUI = loadImage(YELLOW_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);

            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(BLUE)) {
                Image leaderCardGUI = loadImage(BLUE_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);

            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(PURPLE)) {
                Image leaderCardGUI = loadImage(PURPLE_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);

            } else if (leaderCardList.get(i).getRequiredDevCards().get(0).getColour().equals(GREEN)) {
                Image leaderCardGUI = loadImage(GREEN_LEADER);
                ImageView clickableLeaderCard = new ImageView(leaderCardGUI);
                clickableLeaderCards.add(clickableLeaderCard);


            }
        }

        return clickableLeaderCards;

    }


    private List<Image> leaderCardToImgLeaderCard(LeaderCardMinimal leaderCard){

        List<Image> imgLeaderCards = new ArrayList<>();

        if(leaderCard.getRequiredResource().equals(COIN)){
            Image leaderCardGUI = loadImage(COIN_LEADER);
            imgLeaderCards.add(leaderCardGUI);


        }else if (leaderCard.getRequiredResource().equals(SERVANT)){
            Image leaderCardGUI = loadImage(SERVANT_LEADER);
            imgLeaderCards.add(leaderCardGUI);


        }else if (leaderCard.getRequiredResource().equals(SHIELD)){
            Image leaderCardGUI = loadImage(SHIELD_LEADER);
            imgLeaderCards.add(leaderCardGUI);


        }else if (leaderCard.getRequiredResource().equals(STONE)){
            Image leaderCardGUI = loadImage(STONE_LEADER);
            imgLeaderCards.add(leaderCardGUI);


        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(YELLOW) &&
                leaderCard.getRequiredDevCards().get(1).getColour().equals(GREEN)){
            Image leaderCardGUI = loadImage(YELLOW_GREEN_LEADER);
            imgLeaderCards.add(leaderCardGUI);


        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(BLUE) &&
                leaderCard.getRequiredDevCards().get(1).getColour().equals(PURPLE)){
            Image leaderCardGUI = loadImage(BLUE_PURPLE_LEADER);
            imgLeaderCards.add(leaderCardGUI);


        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(GREEN) &&
                leaderCard.getRequiredDevCards().get(1).getColour().equals(BLUE)){
            Image leaderCardGUI = loadImage(GREEN_BLUE_LEADER);
            imgLeaderCards.add(leaderCardGUI);

        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(YELLOW) &&
                leaderCard.getRequiredDevCards().get(1).getColour().equals(PURPLE)){
            Image leaderCardGUI = loadImage(YELLOW_PURPLE_LEADER);
            imgLeaderCards.add(leaderCardGUI);

        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(YELLOW) &&
                leaderCard.getRequiredDevCards().get(1).getColour().equals(BLUE)){
            Image leaderCardGUI = loadImage(YELLOW_BLUE_LEADER);
            imgLeaderCards.add(leaderCardGUI);

        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(GREEN) &&
                leaderCard.getRequiredDevCards().get(1).getColour().equals(PURPLE)){
            Image leaderCardGUI = loadImage(GREEN_PURPLE_LEADER);
            imgLeaderCards.add(leaderCardGUI);

        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(BLUE) &&
                leaderCard.getRequiredDevCards().get(1).getColour().equals(YELLOW)){
            Image leaderCardGUI = loadImage(BLUE_YELLOW_LEADER);
            imgLeaderCards.add(leaderCardGUI);
        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(PURPLE) &&
                leaderCard.getRequiredDevCards().get(1).getColour().equals(GREEN)){
            Image leaderCardGUI = loadImage(PURPLE_GREEN_LEADER);
            imgLeaderCards.add(leaderCardGUI);

        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(YELLOW) ){
            Image leaderCardGUI = loadImage(YELLOW_LEADER);
            imgLeaderCards.add(leaderCardGUI);

        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(BLUE)){
            Image leaderCardGUI = loadImage(BLUE_LEADER);
            imgLeaderCards.add(leaderCardGUI);

        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(PURPLE)){
            Image leaderCardGUI = loadImage(PURPLE_LEADER);
            imgLeaderCards.add(leaderCardGUI);

        }else if (leaderCard.getRequiredDevCards().get(0).getColour().equals(GREEN)){
            Image leaderCardGUI = loadImage(GREEN_LEADER);
            imgLeaderCards.add(leaderCardGUI);


        }

        return imgLeaderCards;

    }





    private List<ImageView> resourcesTypeToGUIResourcesType(List<String> stringList,List<ImageView> GUIResources ){
        Image big_coin = loadImage(BIG_COIN);
        ImageView resource1 = new ImageView(big_coin);
        Image big_shield = loadImage(BIG_SHIELD);
        ImageView resource2 = new ImageView(big_shield);
        Image big_stone = loadImage(BIG_STONE);
        ImageView resource3 = new ImageView(big_stone);
        Image big_servant = loadImage(BIG_SERVANT);
        ImageView resource4 = new ImageView(big_servant);


        for(int i =0 ; i< stringList.size(); i++){
            if(stringList.get(i).equals(COIN)){
                GUIResources.add(resource1);
            }else if(stringList.get(i).equals(SHIELD)){
                GUIResources.add(resource2);
            }else if(stringList.get(i).equals(STONE)){
                GUIResources.add(resource3);
            }else if(stringList.get(i).equals(SERVANT)){
                GUIResources.add(resource4);
            }
        }

        return GUIResources;
    }


    private void initializeStructure() {
        ArrayList<Player> players = new ArrayList<>(4);
        LobbyManager.getLobbyInstance().setLobbyInstance();
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.get(0).setName("One");
        players.get(1).setName("Two");
        players.get(2).setName("Three");
        players.get(3).setName("Four");
        Controller controller = new Controller();
        controller.setGameModel(gameModel);
        players.get(0).setController(controller);
        players.get(1).setController(controller);
        players.get(2).setController(controller);
        players.get(3).setController(controller);
        gameModel.setPlayerList(players);
        gameModel.initializeTheGame();
    }

    /**
     * This method set the selectionName root as scene root
     */
    public void setSetupStage(){ stage.getScene().setRoot(setup); }


    public void setWaitingLobbyStage(){ stage.getScene().setRoot(waitingLobby); }


    public void setSelectionResourcesStage(){ stage.getScene().setRoot(selectionResources);}

    public void setDiscardLeaderCardsStage(){ stage.getScene().setRoot(discardLeaderCards);}


    public void setLastCanvasStage(){
        /*Platform.runLater(()  -> {
            if (!actualGamePanel.getChildren().contains(quit))
                actualGamePanel.getChildren().add(quit);
        });

         */

        stage.getScene().setRoot(actualGamePanel);


/*
        quit.setOnMouseEntered(mouseEvent ->  actualGamePanel.setCursor(Cursor.HAND));

        quit.setOnMouseExited(mouseEvent -> actualGamePanel.setCursor(Cursor.DEFAULT));

        quit.setOnMouseClicked(mouseEvent -> {
            GeneralStringRequestCommand generalStringRequestCommand = new GeneralStringRequestCommand(QUIT);
            commandGUIManager.manageCommand(generalStringRequestCommand);
        });

 */
    }



// prima di questo metodo devo usare leaderCardToGuiLeaderCard(); per trasformare una lista di leadercard del giocatore in lista di immagini
    public void generateDiscardLeaderCardScene ( List<ImageView> leaderCardList,int row, int column,int shift,PlayerMinimal playerMinimal){// row = 150 , column = 500 da provare
        Canvas discardLeaderCardsSelection = new Canvas(1024, 700);
        GraphicsContext gLeaderCards = discardLeaderCardsSelection.getGraphicsContext2D();
        gLeaderCards.drawImage(background, 0, 0, 1024, 700);
        insertQuestion(gLeaderCards,DISCARDLEADERCARDS);
        HashMap<ImageView,Integer> buttonToImagePosition = new HashMap<>();
        ArrayList<LeaderCardMinimal>leaderCardDiscarded = new ArrayList<>();
        ArrayList<String> resourcesChosen = null;
        ImageView button0 = new ImageView(imDone);
        ImageView button1= new ImageView(imDone);
        ImageView button2= new ImageView(imDone);
        ImageView button3= new ImageView(imDone);

        ImageView leaderGUI1 = leaderCardList.get(0);
        ImageView leaderGUI2 = leaderCardList.get(1);
        ImageView leaderGUI3 = leaderCardList.get(2);
        ImageView leaderGUI4 = leaderCardList.get(3);





        createUselessButton(leaderGUI1, row, column,4);

        createUselessButton(leaderGUI2, row + shift, column,4 );

        createUselessButton(leaderGUI3, row, column + shift,4 );

        createUselessButton(leaderGUI4, row + shift, column + shift,4);





        for(int i=0 ; i<leaderCardList.size() ; i++) {
            switch (i) {
                case 0:
                    createButton(button0 , leaderCardList.get(i).getX()-10,leaderCardList.get(i).getY()+180);
                    buttonToImagePosition.put(button0,i);
                    break;
                case 1:
                    createButton(button1 , leaderCardList.get(i).getX()-10,leaderCardList.get(i).getY()+180);
                    buttonToImagePosition.put(button1,i);
                    break;
                case 2:
                    createButton(button2 , leaderCardList.get(i).getX()-10,leaderCardList.get(i).getY()+180);
                    buttonToImagePosition.put(button2,i);
                    break;
                case 3:
                    createButton(button3 , leaderCardList.get(i).getX()-10,leaderCardList.get(i).getY()+180);
                    buttonToImagePosition.put(button3,i);
                    break;
            }

        }




/*
        for(int i=0 ; i<leaderCardList.size() ; i++) {
            switch (i) {
                case 0:
                    createButton(button,  row, column + 60  );
                    buttonList.add(button);
                    break;
                case 1:
                    createButton(button, row + shift , column + 60  );
                    buttonList.add(button);
                    break;
                case 2:
                    createButton(button, row , column + shift + 60  );
                    buttonList.add(button);
                    break;
                case 3:
                    createButton(button,  row + shift, column + shift + 60  );
                    buttonList.add(button);
                    break;
            }

        }

 */

        root2.getChildren().addAll(discardLeaderCardsSelection,button0,button1,button2,button3,leaderGUI1,leaderGUI2,
                leaderGUI3,leaderGUI4);





            button0.setOnMouseClicked(mouseEvent -> {
                System.out.println("discardLeader " + buttonToImagePosition.get(button0));
                leaderCardDiscarded.add(playerMinimal.getLeaderCards().get(0));

                if(leaderCardDiscarded.size() ==2){
                    /*
                    sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                            thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));

                     */
                    setSelectionResourcesStage();
                }

            });
            button1.setOnMouseClicked(mouseEvent -> {
                System.out.println("DiscardLeaderCard " + buttonToImagePosition.get(button1));
                leaderCardDiscarded.add(playerMinimal.getLeaderCards().get(1));

                if(leaderCardDiscarded.size() ==2){
                    /*
                    sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                            thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));

                     */
                    setSelectionResourcesStage();
                }

            });
            button2.setOnMouseClicked(mouseEvent -> {
                System.out.println("DiscardLeaderCard " + buttonToImagePosition.get(button2));
                leaderCardDiscarded.add(playerMinimal.getLeaderCards().get(2));

                if(leaderCardDiscarded.size() ==2){
                    /*
                    sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                            thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));

                     */
                    setSelectionResourcesStage();
                }

            });
            button3.setOnMouseClicked(mouseEvent -> {
                System.out.println("DiscardLeaderCard " + buttonToImagePosition.get(button3));
                leaderCardDiscarded.add(playerMinimal.getLeaderCards().get(3));

                if(leaderCardDiscarded.size() ==2){
                    /*
                    sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                            thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));

                     */
                    setSelectionResourcesStage();
                }


            });

            /*
        if (leaderCardList.size() ==2) {
            setSelectionResourcesStage();
            sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                    thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));
            leaderCardDiscarded.clear();


        }else System.out.println(ERROR);


        setDiscardLeaderCardsStage();

             */

    }




    public void generateSelectResourcesScene (List<ImageView> resourcesTypes,int row, int column, int shift,PlayerMinimal playerMinimal,List<PlayerMinimal> list){
        Canvas resourcesSelection = new Canvas(1024,700);
        GraphicsContext gSelectingResources = resourcesSelection.getGraphicsContext2D();
        gSelectingResources.drawImage(background, 0, 0, 1024, 700);
        insertQuestion(gSelectingResources,SEL_RESOURCES,list);


        HashMap<ImageView,String> buttonToResource = new HashMap<>();
        ArrayList<String> resourcesChosen = new ArrayList<>();
        ArrayList<LeaderCardMinimal> leaderCardDiscarded = null;
        ImageView button0 = new ImageView(imDone);
        ImageView button1= new ImageView(imDone);
        ImageView button2= new ImageView(imDone);
        ImageView button3= new ImageView(imDone);

        ImageView resourceGUI1 = resourcesTypes.get(0);
        ImageView resourceGUI2 = resourcesTypes.get(1);
        ImageView resourceGUI3 = resourcesTypes.get(2);
        ImageView resourceGUI4 = resourcesTypes.get(3);

/*
        if (playerMinimal.getPlayerRoundNumber()==0) //thisPlayer.getPlayerRoundNumber()==0
            setLastCanvasStage();

 */



        createUselessButton(resourceGUI1,  row, column,1.4);
        createUselessButton(resourceGUI2,  row = row + shift , column,1.4  );

        createUselessButton(resourceGUI3, row = row + shift, column,1.4  );
        createUselessButton(resourceGUI4,  row = row + shift , column,1.4 );



        for(int i=0 ; i<resourcesTypes.size() ; i++) {
            switch (i) {
                case 0:
                    createButton(button0 , resourcesTypes.get(i).getX()-10,resourcesTypes.get(i).getY()+120);
                    buttonToResource.put(button0,COIN);
                    break;
                case 1:
                    createButton(button1 , resourcesTypes.get(i).getX()-10,resourcesTypes.get(i).getY()+120);
                    buttonToResource.put(button1,SHIELD);
                    break;
                case 2:
                    createButton(button2 , resourcesTypes.get(i).getX()-10,resourcesTypes.get(i).getY()+120);
                    buttonToResource.put(button2,STONE);
                    break;
                case 3:
                    createButton(button3 , resourcesTypes.get(i).getX()-10,resourcesTypes.get(i).getY()+120);
                    buttonToResource.put(button3,SERVANT);
                    break;
            }

        }





        root3.getChildren().addAll(resourcesSelection,resourceGUI1,resourceGUI2,resourceGUI3,resourceGUI4,button0,button1,button2,button3);

        button0.setOnMouseClicked(mouseEvent -> {
            System.out.println("choose " + "co");
           //resourcesChosen.add(buttonToResource.get(button0));
            if(playerMinimal.getPlayerRoundNumber() == 1 || playerMinimal.getPlayerRoundNumber()==2 && resourcesChosen.size()==1){
                /*sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                        thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));

                 */

            }else if(playerMinimal.getPlayerRoundNumber()==3 && resourcesChosen.size()==2){
                 /*sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                        thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));

                 */

            }
        });
        button1.setOnMouseClicked(mouseEvent -> {
            System.out.println("choose " + "sh");
            //resourcesChosen.add(buttonToResource.get(button1));

            if(playerMinimal.getPlayerRoundNumber() == 1 || playerMinimal.getPlayerRoundNumber()==2 && resourcesChosen.size()==1){
                /*sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                        thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));
                        */
                setLastCanvasStage();
            }else if(playerMinimal.getPlayerRoundNumber()==3 && resourcesChosen.size()==2){
                 /*sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                        thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));
                        */
                setLastCanvasStage();
            }

        });
        button2.setOnMouseClicked(mouseEvent -> {
            System.out.println("choose " + "st");
            //resourcesChosen.add(buttonToResource.get(button2));

            if(playerMinimal.getPlayerRoundNumber() == 1 || playerMinimal.getPlayerRoundNumber()==2 && resourcesChosen.size()==1){
                /*sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                        thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));
                         */

            }else if(playerMinimal.getPlayerRoundNumber()==3 && resourcesChosen.size()==2){
                 /*sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                        thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));
                         */

            }

        });
        button3.setOnMouseClicked(mouseEvent -> {
            System.out.println("choose " + "se");
            //resourcesChosen.add(buttonToResource.get(button3));

            if(playerMinimal.getPlayerRoundNumber() == 1 || playerMinimal.getPlayerRoundNumber()==2 && resourcesChosen.size()==1){
                /*sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                        thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));
                        */

            }else if(playerMinimal.getPlayerRoundNumber()==3 && resourcesChosen.size()==2){
                 /*sendMessageToServer(Parser.getInstance().serializeMessage(new RoundZeroMessage(leaderCardDiscarded, resourcesChosen,
                        thisPlayer.getPlayerName(), thisPlayer.getPlayerRoundNumber())));
                        */

            }


        });

    }





    public void generateActualGamePanelScene ( List<PlayerMinimal> playerMinimalList,PlayerMinimal playerMinimal, DevelopmentCardMarketMinimal developmentCardMarketMinimal,MarbleMarketMinimal marbleMarketMinimal){
        Canvas lastCanvas = new Canvas(2024-300, 1150-300);
        GraphicsContext gActualGame = lastCanvas.getGraphicsContext2D();
        List<ImageView> leaderImageViewList ;
        Image last_background = loadImage(BW_PLAYER_BOARD);
        Image playerBoard = loadImage(COLOURED_PLAYER_BOARD);

        Button productionBase = new Button("ActiveBaseProd");
        productionBase.setPrefSize(120,PICO_BUTTON);
        productionBase.setLayoutX(740-200);
        productionBase.setLayoutY(550-100);
        Button productionD1 = new Button("ActiveProdD1");
        productionD1.setPrefSize(120,PICO_BUTTON);
        productionD1.setLayoutX(740-200);
        productionD1.setLayoutY(500-100);
        Button productionD2 = new Button("ActiveProdD2");
        productionD2.setPrefSize(120,PICO_BUTTON);
        productionD2.setLayoutX(900-200);
        productionD2.setLayoutY(500-100);
        Button productionD3 = new Button("ActiveProdD3");
        productionD3.setPrefSize(120,PICO_BUTTON);
        productionD3.setLayoutX(1060-200);
        productionD3.setLayoutY(500-100);
        Button productionL1 = new Button("ActiveProdL1");
        productionL1.setPrefSize(120,PICO_BUTTON);
        productionL1.setLayoutX(900-200);
        productionL1.setLayoutY(550-100);
        Button productionL2 = new Button("ActiveProdL2");
        productionL2.setPrefSize(120,PICO_BUTTON);
        productionL2.setLayoutX(1060-200);
        productionL2.setLayoutY(550-100);



        Button leaderCard00 = new Button();
        leaderCard00.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard00.setLayoutX(1512-200);
        leaderCard00.setLayoutY(470);
        Button leaderCard01 = new Button();
        leaderCard01.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard01.setLayoutX(1612-200);
        leaderCard01.setLayoutY(470);
        Button leaderCard02 = new Button();
        leaderCard02.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard02.setLayoutX(1712-200);
        leaderCard02.setLayoutY(470);
        Button leaderCard03 = new Button();
        leaderCard03.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard03.setLayoutX(1812-200);
        leaderCard03.setLayoutY(470);
        Button leaderCard10 = new Button();
        leaderCard10.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard10.setLayoutX(1512-200);
        leaderCard10.setLayoutY(295);
        Button leaderCard11 = new Button();
        leaderCard11.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard11.setLayoutX(1612-200);
        leaderCard11.setLayoutY(295);
        Button leaderCard12 = new Button();
        leaderCard12.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard12.setLayoutX(1712-200);
        leaderCard12.setLayoutY(295);
        Button leaderCard13 = new Button();
        leaderCard13.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard13.setLayoutX(1812-200);
        leaderCard13.setLayoutY(295);
        Button leaderCard20 = new Button();
        leaderCard20.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard20.setLayoutX(1512-200);
        leaderCard20.setLayoutY(145);
        Button leaderCard21 = new Button();
        leaderCard21.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard21.setLayoutX(1612-200);
        leaderCard21.setLayoutY(145);
        Button leaderCard22 = new Button();
        leaderCard22.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard22.setLayoutX(1712-200);
        leaderCard22.setLayoutY(145);
        Button leaderCard23 = new Button();
        leaderCard23.setPrefSize(PICO_BUTTON,PICO_BUTTON);
        leaderCard23.setLayoutX(1812-200);
        leaderCard23.setLayoutY(145);




        Button marbleColumn0 = new Button("^");
        marbleColumn0.setPrefSize(TINY_BUTTON,TINY_BUTTON);
        marbleColumn0.setLayoutX(40);
        marbleColumn0.setLayoutY(340);
        Button marbleColumn1 = new Button("^");
        marbleColumn1.setPrefSize(TINY_BUTTON,TINY_BUTTON);
        marbleColumn1.setLayoutX(130);
        marbleColumn1.setLayoutY(340);
        Button marbleColumn2 = new Button("^");
        marbleColumn2.setPrefSize(TINY_BUTTON,TINY_BUTTON);
        marbleColumn2.setLayoutX(230);
        marbleColumn2.setLayoutY(340);
        Button marbleColumn3 = new Button("^");
        marbleColumn3.setPrefSize(TINY_BUTTON,TINY_BUTTON);
        marbleColumn3.setLayoutX(320);
        marbleColumn3.setLayoutY(340);


        Button marbleRow0 = new Button("<-");
        marbleRow0.setPrefSize(35,TINY_BUTTON);
        marbleRow0.setLayoutX(410);
        marbleRow0.setLayoutY(100);
        Button marbleRow1 = new Button("<-");
        marbleRow1.setPrefSize(35,TINY_BUTTON);
        marbleRow1.setLayoutX(410);
        marbleRow1.setLayoutY(180);
        Button marbleRow2 = new Button("<-");
        marbleRow2.setPrefSize(35,TINY_BUTTON);
        marbleRow2.setLayoutX(410);
        marbleRow2.setLayoutY(260);





        Button moveTopToBottom = new Button("MoveTopToBot");
        moveTopToBottom.setPrefSize(120,PICO_BUTTON);
        moveTopToBottom.setLayoutX(300-200);
        moveTopToBottom.setLayoutY(550-100);
        Button moveTopToMiddle = new Button("MoveTopToMid");
        moveTopToMiddle.setPrefSize(120,PICO_BUTTON);
        moveTopToMiddle.setLayoutX(300-200);
        moveTopToMiddle.setLayoutY(500-100);
        Button moveMiddleToTop = new Button("MoveMidToTop");
        moveMiddleToTop.setPrefSize(120,PICO_BUTTON);
        moveMiddleToTop.setLayoutX(450-200);
        moveMiddleToTop.setLayoutY(500-100);
        Button moveMiddleToBottom = new Button("MoveMidToBot");
        moveMiddleToBottom.setPrefSize(120,PICO_BUTTON);
        moveMiddleToBottom.setLayoutX(450-200);
        moveMiddleToBottom.setLayoutY(550-100);
        Button moveBottomToTop = new Button("MoveBotToTop");
        moveBottomToTop.setPrefSize(120,PICO_BUTTON);
        moveBottomToTop.setLayoutX(590-200);
        moveBottomToTop.setLayoutY(550-100);
        Button moveBottomToMiddle = new Button("MoveBotToMid");
        moveBottomToMiddle.setPrefSize(120,PICO_BUTTON);
        moveBottomToMiddle.setLayoutX(590-200);
        moveBottomToMiddle.setLayoutY(500-100);


        Button endTurn = new Button(END_TURN);
        endTurn.setPrefSize(80,PICO_BUTTON);
        endTurn.setLayoutX(150);

        Button quit = new Button(QUIT);
        //endTurn.setPrefSize(150,PICO_BUTTON);


        Button discrdLeader1 = new Button(DISCARD_LEADER +"1");
        discrdLeader1.setPrefSize(100,PICO_BUTTON);
        discrdLeader1.setLayoutX(1300-200);
        discrdLeader1.setLayoutY(550-100);
        Button discrdLeader2 = new Button(DISCARD_LEADER +"2");
        discrdLeader2.setPrefSize(100,PICO_BUTTON);
        discrdLeader2.setLayoutX(1300-200);
        discrdLeader2.setLayoutY(500-100);





        gActualGame.drawImage(last_background, 0, 0, 2024-300, 1150-300);
        List<Button> activeLeaderButtons ;
        List<Button> actionPlayerButtons ;
        Button activeLeaderButton1;
        Button activeLeaderButton2;

        ImageView leaderCard1 ;
        ImageView leaderCard2;





        drawPlayerBoards(gActualGame,playerMinimalList,playerBoard);

        leaderImageViewList = drawPLayersLeaderCards(gActualGame,playerMinimalList,playerMinimal);
        leaderCard1 = leaderImageViewList.get(0);
        leaderCard2 = leaderImageViewList.get(1);



        activeLeaderButtons = drawLeadersPlayerButtons(leaderImageViewList);
        activeLeaderButton1 = activeLeaderButtons.get(0);
        activeLeaderButton2 = activeLeaderButtons.get(1);


        drawDevelopmentMarket(gActualGame,developmentCardMarketMinimal);

        drawMarketMarble(gActualGame,marbleMarketMinimal);

        //actionPlayerButtons = drawPlayerActionsButtons(playerMinimal);

        root4.getChildren().addAll(lastCanvas,  leaderCard1,leaderCard2,activeLeaderButton1,activeLeaderButton2,moveTopToBottom,
                moveTopToMiddle,moveMiddleToTop,moveMiddleToBottom,moveBottomToTop,moveBottomToMiddle,marbleColumn0,marbleColumn1,marbleColumn2,marbleColumn3,
                marbleRow0,marbleRow1,marbleRow2,leaderCard00,leaderCard01,leaderCard02,leaderCard03,leaderCard10,leaderCard11,leaderCard12,leaderCard13,leaderCard20,
                leaderCard21,leaderCard22,leaderCard23,productionBase,productionD1,productionD2,productionD3,productionL1,productionL2,endTurn,quit,discrdLeader1,discrdLeader2);


        marbleColumn0.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PICK_MARBLES+" " + COLUMN +" "+ "0");

        });
        marbleColumn1.setOnMouseClicked(mouseEvent -> {
            System.out.println(ACTION +" " + PICK_MARBLES+" " + COLUMN +" "+ "1");

        });
        marbleColumn2.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PICK_MARBLES+" " + COLUMN +" "+ "2");

        });
        marbleColumn3.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PICK_MARBLES+" " + COLUMN +" "+ "3");

        });
        marbleRow0.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PICK_MARBLES+" " + ROW +" "+ "0");

        });
        marbleRow1.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PICK_MARBLES+" " + ROW +" "+ "1");

        });
        marbleRow2.setOnMouseClicked(mouseEvent -> {
            System.out.println(ACTION +" " + PICK_MARBLES+" " + ROW +" "+ "2");

        });
        leaderCard00.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(0,0).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(0,0).getColour() + " " + "0");

        });
        leaderCard01.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(0,1).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(0,1).getColour() + " " + "1");

        });
        leaderCard02.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(0,2).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(0,2).getColour() + " " + "2");

        });
        leaderCard03.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(0,3).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(0,3).getColour() + " " + "3");

        });
        leaderCard10.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(1,0).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(1,0).getColour() + " " + "0");

        });
        leaderCard11.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(1,1).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(1,1).getColour() + " " + "1");

        });
        leaderCard12.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(1,2).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(1,2).getColour() + " " + "2");

        });
        leaderCard13.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(1,3).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(1,3).getColour() + " " + "3");

        });
        leaderCard20.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(2,0).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(2,0).getColour() + " " + "0");

        });
        leaderCard21.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(2,1).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(2,1).getColour() + " " + "1");

        });
        leaderCard22.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(2,22).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(2,2).getColour() + " " + "2");

        });
        leaderCard23.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + BUY_DEV_CARD+" " + developmentCardMarketMinimal.getCard(2,3).getLvl()
                    +" "+ developmentCardMarketMinimal.getCard(2,3).getColour() + " " + "3");

        });
        productionD1.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PRODUCE+" " +D1_PRODUCTION);
        });
        productionD2.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PRODUCE+" " +D2_PRODUCTION);
        });
        productionD3.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PRODUCE+" " +D3_PRODUCTION);
        });
        productionBase.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PRODUCE+" " +BASE_PRODUCTION);
        });
        productionL1.setOnMouseClicked(mouseEvent -> {
            System.out.println( ACTION +" " + PRODUCE+" " +L1_PRODUCTION);
        });
        productionL2.setOnMouseClicked(mouseEvent -> {
            System.out.println(ACTION +" " + PRODUCE+" " +L2_PRODUCTION);
        });
        moveTopToMiddle.setOnMouseClicked(mouseEvent -> {
            System.out.println( MOVE +" " + TOP+" " + "to " +" " + MIDDLE);
        });
        moveTopToBottom.setOnMouseClicked(mouseEvent -> {
            System.out.println( MOVE +" " + TOP+" " + "to " +" " + BOTTOM);
        });
        moveMiddleToTop.setOnMouseClicked(mouseEvent -> {
            System.out.println( MOVE +" " + MIDDLE+" " + "to " +" " + TOP);
        });
        moveMiddleToBottom.setOnMouseClicked(mouseEvent -> {
            System.out.println( MOVE +" " + MIDDLE+" " + "to " +" " + BOTTOM);
        });
        moveBottomToTop.setOnMouseClicked(mouseEvent -> {
            System.out.println( MOVE +" " + BOTTOM+" " + "to " +" " + TOP);
        });
        moveBottomToMiddle.setOnMouseClicked(mouseEvent -> {
            System.out.println( MOVE +" " + BOTTOM+" " + "to " +" " + MIDDLE);
        });
        leaderCard1.setOnMouseClicked(mouseEvent -> {
            System.out.println( LEADERCARD +" " + ACTIVATE+" " + playerMinimal.getLeaderCards().get(0));
        });
        leaderCard2.setOnMouseClicked(mouseEvent -> {
            System.out.println( LEADERCARD +" " + ACTIVATE+" " + playerMinimal.getLeaderCards().get(1));
        });
        endTurn.setOnMouseClicked(mouseEvent -> {
            System.out.println( END_TURN);
        });
        quit.setOnMouseClicked(mouseEvent -> {
            System.out.println( QUIT);
        });
        discrdLeader1.setOnMouseClicked(mouseEvent -> {
            System.out.println( DISCARD_LEADER + " " +"0" );
        });
        discrdLeader2.setOnMouseClicked(mouseEvent -> {
            System.out.println( DISCARD_LEADER + " " +"1" );
        });




    }








    public void drawPlayerBoards(GraphicsContext graphicsContext,List<PlayerMinimal> playersList,Image playerBoard){


        Image imgCross1 = loadImage(BLACK_CROSS);
        Image imgCross2 = loadImage(BLACK_CROSS);
        Image imgCross3 = loadImage(BLACK_CROSS);
        Image imgCross4 = loadImage(BLACK_CROSS);

        //ImageView cross = new ImageView(imgCross);


        drawPopeFavors(graphicsContext,playersList);


        insertQuestion(graphicsContext,TURN,playersList);

        switch (playersList.size()) {
            case 4:
                graphicsContext.drawImage(playerBoard,70,600-100,400-100,300-100);
                graphicsContext.drawImage(imgCross1,80,650-100,30,30);
                graphicsContext.drawImage(playerBoard,500,600-100,400-100,300-100);
                graphicsContext.drawImage(imgCross2,510,650-100,30,30);
                graphicsContext.drawImage(playerBoard,950,600-100,400-100,300-100);
                graphicsContext.drawImage(imgCross3,960,650-100,30,30);
                graphicsContext.drawImage(playerBoard,1400,600-100,400-100,300-100);
                graphicsContext.drawImage(imgCross4,1410,650-100,30,30);

                break;
            case 3:
                graphicsContext.drawImage(playerBoard,70,600-100,400-100,300-100);
                graphicsContext.drawImage(imgCross1,80,650-100,30,30);
                graphicsContext.drawImage(playerBoard,500,600-100,400-100,300-100);
                graphicsContext.drawImage(imgCross2,510,650-100,30,30);
                graphicsContext.drawImage(playerBoard,950,600-100,400-100,300-100);
                graphicsContext.drawImage(imgCross3,960,650-100,30,30);
                break;
            case 2:
                graphicsContext.drawImage(playerBoard,70,600-100,400-100,300-100);
                graphicsContext.drawImage(imgCross1,80,650-100,30,30);
                graphicsContext.drawImage(playerBoard,500,600-100,400,300);
                graphicsContext.drawImage(imgCross2,510,650-100,30,30);
                break;
            case 1:
                graphicsContext.drawImage(playerBoard,70,600-100,400-100,300-100);
                graphicsContext.drawImage(imgCross1,80,650-100,30,30);
                graphicsContext.drawImage(playerBoard,500,600-100,400,300-100);
                graphicsContext.drawImage(imgCross2,510,650-100,30,30);
                break;
        }

    }


    public void drawPopeFavors(GraphicsContext graphicsContext,List<PlayerMinimal> playersList){
        Image popeBackFavor2 = loadImage("pope_favor1_back.png");
        Image popeBackFavor3  = loadImage("pope_favor2_back.png");
        Image popeBackFavor4  = loadImage("pope_favor3_back.png");

        List<Image> popeBackFavorList = new ArrayList<>();

        popeBackFavorList.add(popeBackFavor2);
        popeBackFavorList.add(popeBackFavor3);
        popeBackFavorList.add(popeBackFavor4);

        switch (playersList.size()) {
            case 4:
                graphicsContext.drawImage(popeBackFavor2,70,600,100,100);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);
                graphicsContext.drawImage(popeBackFavor2,70,600,30,30);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);
                graphicsContext.drawImage(popeBackFavor2,70,600,30,30);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);
                graphicsContext.drawImage(popeBackFavor2,70,600,30,30);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);

                break;
            case 3:
                graphicsContext.drawImage(popeBackFavor2,70,600,30,30);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);
                graphicsContext.drawImage(popeBackFavor2,70,600,30,30);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);
                graphicsContext.drawImage(popeBackFavor2,70,600,30,30);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);
                break;
            case 2:
                graphicsContext.drawImage(popeBackFavor2,70,600,30,30);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);
                graphicsContext.drawImage(popeBackFavor2,70,600,30,30);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);
                break;
            case 1:
                graphicsContext.drawImage(popeBackFavor2,70,600,30,30);
                graphicsContext.drawImage(popeBackFavor3,80,650,30,30);
                graphicsContext.drawImage(popeBackFavor4,500,600,30,30);
                break;
        }



    }


    public List<ImageView> drawPLayersLeaderCards(GraphicsContext graphicsContext, List <PlayerMinimal> playerMinimalList,PlayerMinimal playerMinimal){
        Image back_leader = loadImage(BACK_LEADER);
        List<ImageView> imageViewList = new ArrayList<>();

        List<ImageView> imgLeader1 = new ArrayList<>();
        List<ImageView> imgLeader2 = new ArrayList<>();
        List<ImageView> imgLeader3 = new ArrayList<>();
        List<ImageView> imgLeader4 = new ArrayList<>();





        switch (playerMinimalList.size()) {
            case 1:
                imgLeader1 = leaderCardToGuiLeaderCard(playerMinimalList.get(0).getLeaderCards());
                break;
            case 2:
                if(playerMinimal.getPlayerRoundNumber() == 1) {
                    //imgLeader1 = leaderCardToGuiLeaderCard(playerMinimalList.get(0).getLeaderCards());
                    imgLeader2 = leaderCardToGuiLeaderCard(playerMinimalList.get(1).getLeaderCards());
                    graphicsContext.drawImage(back_leader,80,915-215,100,150);
                    graphicsContext.drawImage(back_leader,230,915-215,100,150);
                }else {
                    imgLeader1 = leaderCardToGuiLeaderCard(playerMinimalList.get(0).getLeaderCards());
                    graphicsContext.drawImage(back_leader,520,915-215,100,150);
                    graphicsContext.drawImage(back_leader,700,915-215,100,150);
                }
                break;
            case 3:
                if(playerMinimal.getPlayerRoundNumber() == 0) {
                    imgLeader1 = leaderCardToGuiLeaderCard(playerMinimalList.get(0).getLeaderCards());
                    graphicsContext.drawImage(back_leader,520,915-215,100,150);
                    graphicsContext.drawImage(back_leader,700,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1000,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1220-100,915-215,100,150);
                }else if(playerMinimal.getPlayerRoundNumber() == 1){
                    imgLeader2 = leaderCardToGuiLeaderCard(playerMinimalList.get(1).getLeaderCards());
                    graphicsContext.drawImage(back_leader,80,915-215,100,150);
                    graphicsContext.drawImage(back_leader,230,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1000,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1220-100,915-215,100,150);
                }else{
                    imgLeader3 = leaderCardToGuiLeaderCard(playerMinimalList.get(2).getLeaderCards());
                    graphicsContext.drawImage(back_leader,80,915-215,100,150);
                    graphicsContext.drawImage(back_leader,230,915-215,100,150);
                    graphicsContext.drawImage(back_leader,520,915-215,100,150);
                    graphicsContext.drawImage(back_leader,700,915-215,100,150);
                }
                break;
            case 4:
                if(playerMinimal.getPlayerRoundNumber() == 0) {
                    imgLeader1 = leaderCardToGuiLeaderCard(playerMinimalList.get(0).getLeaderCards());
                    graphicsContext.drawImage(back_leader,520,915-215,100,150);
                    graphicsContext.drawImage(back_leader,700,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1000,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1220-50,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1450-50,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1670-100,915-215,100,150);

                }else if(playerMinimal.getPlayerRoundNumber() == 1){
                    imgLeader2 = leaderCardToGuiLeaderCard(playerMinimalList.get(1).getLeaderCards());
                    graphicsContext.drawImage(back_leader,80,915-215,100,150);
                    graphicsContext.drawImage(back_leader,230,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1000,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1220-50,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1450-50,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1670-100,915-215,100,150);
                }else if(playerMinimal.getPlayerRoundNumber() == 2){
                    imgLeader3 = leaderCardToGuiLeaderCard(playerMinimalList.get(2).getLeaderCards());
                    graphicsContext.drawImage(back_leader,80,915-215,100,150);
                    graphicsContext.drawImage(back_leader,230,915-215,100,150);
                    graphicsContext.drawImage(back_leader,520,915-215,100,150);
                    graphicsContext.drawImage(back_leader,700,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1450-50,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1670-100,915-215,100,150);
                }else{
                    imgLeader4 = leaderCardToGuiLeaderCard(playerMinimalList.get(3).getLeaderCards());
                    graphicsContext.drawImage(back_leader,80,915-215,100,150);
                    graphicsContext.drawImage(back_leader,230,915-215,100,150);
                    graphicsContext.drawImage(back_leader,520,915-215,100,150);
                    graphicsContext.drawImage(back_leader,700,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1000,915-215,100,150);
                    graphicsContext.drawImage(back_leader,1220-80,915-215,100,150);

                }
                break;
        }






        switch (playerMinimalList.size()) {
            case 1:
                createUselessButton(imgLeader1.get(0), 80, 915-215, 6);
                createUselessButton(imgLeader1.get(1), 230, 915-215, 6);

                imageViewList.add(imgLeader1.get(0));
                imageViewList.add(imgLeader1.get(1));
                break;
            case 2:
                if(playerMinimal.getPlayerRoundNumber()==0){
                    createUselessButton(imgLeader1.get(0), 80, 915-215, 6);
                    createUselessButton(imgLeader1.get(1), 230, 915-215, 6);
                    imageViewList.add(imgLeader1.get(0));
                    imageViewList.add(imgLeader1.get(1));
                }else{
                    createUselessButton(imgLeader2.get(0), 520, 915-215, 6);
                    createUselessButton(imgLeader2.get(1), 700, 915-215, 6);
                    imageViewList.add(imgLeader2.get(0));
                    imageViewList.add(imgLeader2.get(1));
                }

                break;
            case 3:
                switch (playerMinimal.getPlayerRoundNumber()) {
                    case 0:
                        createUselessButton(imgLeader1.get(0), 80, 915-215, 6);
                        createUselessButton(imgLeader1.get(1), 230, 915-215, 6);
                        imageViewList.add(imgLeader1.get(0));
                        imageViewList.add(imgLeader1.get(1));
                        break;
                    case 1:
                        createUselessButton(imgLeader2.get(0), 520, 915-215, 6);
                        createUselessButton(imgLeader2.get(1), 700, 915-215, 6);
                        imageViewList.add(imgLeader2.get(0));
                        imageViewList.add(imgLeader2.get(1));
                        break;
                    case 2:
                        createUselessButton(imgLeader3.get(0), 1000, 915-215, 6);
                        createUselessButton(imgLeader3.get(1), 1220, 915-215, 6);
                        imageViewList.add(imgLeader3.get(0));
                        imageViewList.add(imgLeader3.get(1));
                        break;
                }

                break;
            case 4:
                switch (playerMinimal.getPlayerRoundNumber()) {
                    case 0:
                        createUselessButton(imgLeader1.get(0), 80, 915-215, 6);
                        createUselessButton(imgLeader1.get(1), 230, 915-215, 6);
                        imageViewList.add(imgLeader1.get(0));
                        imageViewList.add(imgLeader1.get(1));
                        break;
                    case 1:
                        createUselessButton(imgLeader2.get(0), 520, 915-215, 6);
                        createUselessButton(imgLeader2.get(1), 700, 915-215, 6);
                        imageViewList.add(imgLeader2.get(0));
                        imageViewList.add(imgLeader2.get(1));

                        break;
                    case 2:
                        createUselessButton(imgLeader3.get(0), 1000, 915-215, 6);
                        createUselessButton(imgLeader3.get(1), 1220, 915-215, 6);
                        imageViewList.add(imgLeader3.get(0));
                        imageViewList.add(imgLeader3.get(1));

                        break;
                    case 3:
                        createUselessButton(imgLeader4.get(0), 1450, 915-215, 6);
                        createUselessButton(imgLeader4.get(1), 1670, 915-215, 6);
                        imageViewList.add(imgLeader4.get(0));
                        imageViewList.add(imgLeader4.get(1));
                        break;
                }

                break;
        }
        return imageViewList;

    }




    public List<Button> drawLeadersPlayerButtons(List<ImageView> leaders){
        Button activeButton1 = new Button(ACTIVATE);
        Button activeButton2 = new Button(ACTIVATE);
        List<Button> buttonList = new ArrayList<>();

        activeButton1.setPrefSize(80,5);
        activeButton2.setPrefSize(80,5);

        activeButton1.setLayoutX(leaders.get(0).getX());
        activeButton1.setLayoutY(leaders.get(0).getY()+120);
        activeButton2.setLayoutX(leaders.get(1).getX());
        activeButton2.setLayoutY(leaders.get(1).getY()+120);



        buttonList.add(activeButton1);
        buttonList.add(activeButton2);

        return buttonList;

    }



    public void drawDevelopmentMarket(GraphicsContext gc,DevelopmentCardMarketMinimal developmentCardMarket){

        for(int j = 0; j < 3; j ++) {
            for (int i = 0; i < 4; i ++) {
                //developmentCardMarket.getCard(i,j) = new ArrayList<>(4);
                gc.drawImage(devCardToImg(developmentCardMarket.getCard(j,i)),1500-200+100*i,20+150*j,100,150);


            }
        }
    }




    public void drawMarketMarble(GraphicsContext gc , MarbleMarketMinimal marbleMarketMinimal){

        for(int j = 0; j < 3; j ++) {
            for (int i= 0; i < 4; i++) {
                gc.drawImage(marbleStringToImage(marbleMarketMinimal.getMarbleFromMarket(j, i)), 20 + 90 * i, 70 +90* j, 70, 70);
            }
        }

        gc.drawImage(marbleStringToImage(marbleMarketMinimal.getOutMarble()),400,0,70,70);


    }


    public MarbleMarketMinimal generateMarketMarble(ArrayList<String> marbles){

        MarbleMarketMinimal marbleMarketMinimal;

        marbleMarketMinimal = new MarbleMarketMinimal(marbles);

        return  marbleMarketMinimal;

    }


    public Image marbleStringToImage(String s){

        Image img = null;

        switch (s) {
            case WHITE:
                img = loadImage("whiteCircle.png");
                break;
            case RED:
                img = loadImage("redCircle (1).png");
                break;
            case BLUE:
                img = loadImage("blueCircle.png");
                break;
            case GREY:
                img = loadImage("circleBlack2.png");
                break;
            case PURPLE:

                img = loadImage("purpleCircle.png");
                break;
            case YELLOW:
                img = loadImage("yellowCircle1.png");
                break;
        }

        return img;
    }





    /*
    private List<Image> listDevelopmentCardToImgArray(List<DevelopmentCardMinimal> developmentCardMinimalList){


    }

     */


    private Image devCardToImg (DevelopmentCardMinimal developmentCardMinimal){

        Image imgDeVCard = null;

        if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==1){
             imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==1){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-2-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==1){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-3-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==1){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-4-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==2){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-5-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==2){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-6-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==2){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-7-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==2){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-8-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==3){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-9-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==3){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-10-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==3){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-11-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==3){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-12-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==4){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-13-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==4){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-14-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==4){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-15-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==4){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-16-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==5){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-17-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==5){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-18-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==5){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-19-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==5){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-20-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==6){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-21-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==6){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-22-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==6){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-23-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==6){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-24-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==7){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-25-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==7){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-26-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==7){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-27-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==7){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-28-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==8){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-29-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==8){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-30-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==8){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-31-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==8){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-32-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==9){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-33-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==9){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-34-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==9){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-35-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==9){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-36-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==10){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-37-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==10){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-38-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==10){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-39-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==10){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-40-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==11){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-41-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==11){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-42-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==11){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-43-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==11){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-44-1.png");
        }else if(developmentCardMinimal.getColour()==GREEN && developmentCardMinimal.getVictoryPoints()==12){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-45-1.png");
        }else if(developmentCardMinimal.getColour()==PURPLE && developmentCardMinimal.getVictoryPoints()==12){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-46-1.png");
        }else if(developmentCardMinimal.getColour()==BLUE && developmentCardMinimal.getVictoryPoints()==12){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-47-1.png");
        }else if(developmentCardMinimal.getColour()==YELLOW && developmentCardMinimal.getVictoryPoints()==12){
            imgDeVCard = loadImage("Masters of Renaissance_Cards_FRONT_3mmBleed_1-48-1.png");
        }
        return  imgDeVCard;
    }



    private DevelopmentCardMarketMinimal generateDevCardMarket(ArrayList<DevelopmentCardMinimal> arrayListDevCardMarket){

        DevelopmentCardMarketMinimal developmentCardMarket ;

        developmentCardMarket = new DevelopmentCardMarketMinimal(arrayListDevCardMarket);

        return developmentCardMarket;

    }








    /*public void setFinalStage(){
        Platform.runLater(()  -> {
            if (!selectionName.getChildren().contains(quit))
                selectionName.getChildren().add(quit);
        });

        stage.getScene().setRoot(selectionName);

        quit.setOnMouseEntered(mouseEvent ->  selectionName.setCursor(Cursor.HAND));

        quit.setOnMouseExited(mouseEvent -> selectionName.setCursor(Cursor.DEFAULT));

        quit.setOnMouseClicked(mouseEvent -> {
            GeneralStringRequestCommand generalStringRequestCommand = new GeneralStringRequestCommand(QUIT);
            commandGUIManager.manageCommand(generalStringRequestCommand);
        });
    }*/


    /**
     * Send the serialized message to the server.
     */
    public void sendMessageToServer(String message) { toServer.println(message); }




    public static void main(String[] args) /*throws IOException*/ {
        //Socket socket = new Socket(LOCALHOST, 60100);
        /*CommandGUIManager commandGUIManager = new CommandGUIManager(socket);
        DeliveryMessage deliveryMessage = commandGUIManager.getDeliveryMessage();
        new Thread(deliveryMessage::startReading).start();*/
        launch(args);
    }



//COME FAR PARTIRE LA GUI CON USANDO NETWORK COME CON LA CLI ; COME GESTIRE OGNI GUI COME SE FOSSE UN GIOCATORE DIVERSO ; ->IMPLEMETARE GUIHANDLER ENTRO LUNEDI/MARTEDI E ALLO STESSO TEMPO FARE I METODI PER MODIFICRE IL CLIENT DUTANTE LA PARTITA NEL LASTCANVAS -> MERCOLEDI E GIOVEDI DA USARE PER TESTING ??

}








