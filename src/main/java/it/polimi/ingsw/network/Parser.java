package it.polimi.ingsw.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.changes.*;
import it.polimi.ingsw.network.messages.*;

public class Parser {

    private static Parser parser = null;
    private Gson gsonMessages;
    private Gson gsonChanges;

    private Parser() {}

    public static Parser getInstance() {
        if(parser == null){
            parser = new Parser();
            parser.setParserMessages();
            parser.setParserChanges();
        }
        return parser;
    }

    private void setParserMessages() {
        RuntimeTypeAdapterFactory<Message> messageAdapter = RuntimeTypeAdapterFactory.of(Message.class)
                .registerSubtype(ActiveProductionMessage.class).registerSubtype(BuyDevCardMessage.class)
                .registerSubtype(ChooseLeaderMessage.class).registerSubtype(PickResourcesMessage.class)
                .registerSubtype(RoundZeroMessage.class).registerSubtype(SingleMessageFromClient.class)
                ./*registerSubtype(SingleMessageFromServer.class)*/registerSubtype(SetupGameMessage.class)
                .registerSubtype(MoveResourcesMessage.class).registerSubtype(EndTurnMessage.class);

        gsonMessages = new GsonBuilder().registerTypeAdapterFactory(messageAdapter).create();
    }

    private void setParserChanges() {
        RuntimeTypeAdapterFactory<Changes> changesAdapter = RuntimeTypeAdapterFactory.of(Changes.class)
                .registerSubtype(DevelopmentCardStructureChanges.class).registerSubtype(FaithTrackChanges.class)
                .registerSubtype(LeaderCardChanges.class).registerSubtype(MarbleMarketChanges.class)
                .registerSubtype(PlayerDevelopmentCardChanges.class).registerSubtype(StrongboxChanges.class)
                .registerSubtype(WarehouseChanges.class).registerSubtype(EndTurnChanges.class)
                .registerSubtype(GameStartedChanges.class).registerSubtype(PlayerWaitingChanges.class)
                .registerSubtype(ErrorChanges.class).registerSubtype(WinnerChanges.class)
                .registerSubtype(LastRoundsChanges.class).registerSubtype(EndSinglePlayerTurnChanges.class)
                .registerSubtype(ExtraSpaceLeaderChanges.class).registerSubtype(NickNameTakenChanges.class)
                .registerSubtype(SinglePlayerWinnerChanges.class).registerSubtype(ConnectionPlayerChanges.class)
                .registerSubtype(ReconnectedClientChanges.class).registerSubtype(VictoryPointsChanges.class);

        gsonChanges = new GsonBuilder().registerTypeAdapterFactory(changesAdapter).create();
    }


    public Message deserializeMessage(String stringMessage) { return gsonMessages.fromJson(stringMessage, Message.class); }

    public String serializeMessage(Message jsonMessage) { return gsonMessages.toJson(jsonMessage, Message.class); }

    public Changes deserializeChanges(String stringChanges) { return gsonChanges.fromJson(stringChanges, Changes.class);}

    public String serializeChanges(Changes changes) { return gsonChanges.toJson(changes, Changes.class); }

}
