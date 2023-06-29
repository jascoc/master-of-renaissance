package it.polimi.ingsw.model;

import it.polimi.ingsw.changes.WarehouseChanges;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;
import it.polimi.ingsw.network.server.Controller;

import java.util.ArrayList;

/**
 * This Class represents Warehouse
 */
public class Warehouse implements Cloneable{

    private Resource[] top = new Resource[1];
    private Resource[] middle = new Resource[2];
    private Resource[] bottom = new Resource[3];
    private boolean insertable = true;

    private Player player;

    public Resource[] getTop() { return top; }
    public Resource[] getBottom() { return bottom; }
    public Resource[] getMiddle() { return middle; }

    public Warehouse() { }

    public Warehouse(Player player) {
        this.player = player;
    }

    /**
     * used only for testing
     */
    public void setBottom(Resource resource) {
        this.bottom[1] = resource;
    }

    /**
     * to set changes in the network
     */
    public void change(GameModel gameModel) {
        ArrayList<String> warehouseList = new ArrayList<>(6);
        if(getTop()[0]==null) { warehouseList.add("empty"); }
        else { warehouseList.add(getTop()[0].toString()); }

        if(getMiddle()[0]==null) { warehouseList.add("empty"); }
        else { warehouseList.add(getMiddle()[0].toString()); }

        if(getMiddle()[1]==null) { warehouseList.add("empty"); }
        else { warehouseList.add(getMiddle()[1].toString()); }

        if(getBottom()[0]==null) { warehouseList.add("empty"); }
        else { warehouseList.add(getBottom()[0].toString()); }

        if(getBottom()[1]==null) { warehouseList.add("empty"); }
        else { warehouseList.add(getBottom()[1].toString()); }

        if(getBottom()[2]==null) { warehouseList.add("empty"); }
        else { warehouseList.add(getBottom()[2].toString()); }

        WarehouseChanges changes = new WarehouseChanges(gameModel.getActivePlayer().getRoundPosition(), warehouseList, player.getName());
        for (Controller controller: gameModel.getControllers()) { controller.notify(changes); }
    }

    /**
     * used to know if the resource is in the top, middle or bottom
     * @param gameModel
     * @param resource
     * @param position
     */
    public void chooseLocation(GameModel gameModel, Resource resource, int position) {
        if(position == 0) { addResourceTop(gameModel, resource); }
        else if(position == 1) { addResourceMiddle(gameModel, resource); }
        else if(position == 2) { addResourceBottom(gameModel, resource); }
    }

    /**
     * converts the resources in the warehouse in a string then put them in a array list
     * @return
     */
    public ArrayList<String> getResourcesString() {
        ArrayList<String> resources = new ArrayList<>(6);
        for(Resource res : warehouseToList()) {
            if(res != null) { resources.add(res.toString()); }
            else { resources.add("Null"); }
        }
        return resources;
    }

    /**
     * add the resource to the top of the warehouse
     * @param gameModel
     * @param resource
     */
    public void addResourceTop(GameModel gameModel, Resource resource) {
        if (top[0] == null) {
            if (bottom[0] != null) {
                if (bottom[0].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (bottom[1] != null) {
                if (bottom[1].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (bottom[2] != null) {
                if (bottom[2].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (middle[0] != null) {
                if (middle[0].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (middle[1] != null) {
                if (middle[1].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (insertable) {
                top[0] = resource;
                change(gameModel);
            }
        }

        else{
            insertable = true;
            for (Player otherPlayer : gameModel.getPlayerList()){
                if(!otherPlayer.isActivePlayer()){
                    gameModel.moveFaithTrack(1, otherPlayer);
                }
            }
        }

    }

    /**
     * add the resource to the middle of the warehouse
     * @param gameModel
     * @param resource
     */
    public void addResourceMiddle(GameModel gameModel, Resource resource){
        if(middle[0]==null){
            if (bottom[0]!=null){
                if(bottom[0].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (bottom[1]!=null){
                if(bottom[1].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (bottom[2]!=null) {
                if (bottom[2].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (top[0]!=null){
                if(top[0].toString().equals(resource.toString()))
                    insertable = false;
            }
            if(middle[1]!=null){
                if(!middle[1].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (insertable){
                middle[0]= resource;
                change(gameModel);
            }
            else{
                insertable=true;
                for (Player otherPlayer : gameModel.getPlayerList()){
                    if(!otherPlayer.isActivePlayer()){
                        gameModel.moveFaithTrack(1,otherPlayer);
                        //otherPlayer.activePopeFavorTails();
                    }
                }

            }
        }
        else if(middle[1]==null) {
                if (middle[0].toString().equals(resource.toString())) {
                    if (bottom[0] != null) {
                        if (bottom[0].toString().equals(resource.toString()))
                            insertable = false;
                    }
                    if (bottom[1] != null) {
                        if (bottom[1].toString().equals(resource.toString()))
                            insertable = false;
                    }
                    if (bottom[2] != null) {
                        if (bottom[2].toString().equals(resource.toString()))
                            insertable = false;
                    }
                    if (top[0] != null) {
                        if (top[0].toString().equals(resource.toString()))
                            insertable = false;
                    }
                    if (insertable){
                        middle[1]= resource;
                        change(gameModel);
                    }
                    else{
                        insertable=true;
                        for (Player otherPlayer : gameModel.getPlayerList()){
                            if(!otherPlayer.isActivePlayer()){
                                gameModel.moveFaithTrack(1,otherPlayer);
                                //otherPlayer.activePopeFavorTails();
                            }
                        }
                    }
                }
        }
    }

    /**
     * add the resource to the bottom of the warehouse
     * @param gameModel
     * @param resource
     */
    public void addResourceBottom(GameModel gameModel, Resource resource){
        if(bottom[0]==null){
            if (middle[1]!=null) {
                if (middle[1].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (top[0]!=null){
                if(top[0].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (middle[0]!=null){
                if(middle[0].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (bottom[1]!=null) {
                if (!bottom[1].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (bottom[2]!=null) {
                if (!bottom[2].toString().equals(resource.toString()))
                    insertable = false;
            }
            if (insertable){
                bottom[0]= resource;
                change(gameModel);
            }
            else{
                insertable=true;
                for (Player otherPlayer : gameModel.getPlayerList()) {
                    if (!otherPlayer.isActivePlayer()) {
                        gameModel.moveFaithTrack(1, otherPlayer);
                    }
                }
            }
        }
        else if(bottom[1]==null) {
            if (bottom[0].toString().equals(resource.toString())) {
                    if (middle[1]!=null) {
                        if (middle[1].toString().equals(resource.toString()))
                            insertable = false;
                    }
                    if (top[0]!=null){
                        if(top[0].toString().equals(resource.toString()))
                            insertable = false;
                    }
                    if (middle[0]!=null){
                        if(middle[0].toString().equals(resource.toString()))
                            insertable = false;
                    }
                    if (bottom[2]!=null) {
                        if (!bottom[2].toString().equals(resource.toString()))
                            insertable = false;
                    }
                    if (insertable){
                        bottom[1]= resource;
                        change(gameModel);
                    }
                    else{
                        insertable=true;
                        for (Player otherPlayer : gameModel.getPlayerList()){
                            if(!otherPlayer.isActivePlayer()){
                                gameModel.moveFaithTrack(1,otherPlayer);
                            }
                        }
                    }
            }
        }
        else if(bottom[2]==null) {
            if (bottom[0].toString().equals(resource.toString()) && bottom[1].toString().equals(resource.toString())) {
                if (middle[1]!=null) {
                    if (middle[1].toString().equals(resource.toString()))
                        insertable = false;
                }
                if (top[0]!=null){
                    if(top[0].toString().equals(resource.toString()))
                        insertable = false;
                }
                if (middle[0]!=null){
                    if(middle[0].toString().equals(resource.toString()))
                        insertable = false;
                }
                if (insertable){
                    bottom[2]= resource;
                    change(gameModel);
                }
                else{
                    insertable=true;
                    for (Player otherPlayer : gameModel.getPlayerList()){
                        if(!otherPlayer.isActivePlayer()){
                            gameModel.moveFaithTrack(1,otherPlayer);
                        }
                    }
                }
            }
        }
    }

    /**
     * find the resource by the name of it
     * @param resource
     * @return true if it's found
     */
    public boolean findResourceByName(Resource resource){
        if(top[0]!=null){
            if(top[0].toString().equals(resource.toString()))
                return true;
        }
        if(middle[0]!=null){
            if(middle[0].toString().equals(resource.toString()))
                return true;
        }
        if(middle[1]!=null){
            if(middle[1].toString().equals(resource.toString()))
                return true;
        }
        if(bottom[0]!=null){
            if(bottom[0].toString().equals(resource.toString()))
                return true;
        }
        if(bottom[1]!=null){
            if(bottom[1].toString().equals(resource.toString()))
                return true;
        }
        if(bottom[2]!=null){
            if(bottom[2].toString().equals(resource.toString()))
                return true;
        }
        return false;
    }

    /**
     * @return an array of boolean true if there is any, if not false
     */
    public boolean[] hasResources() {
        boolean[] hasResources = new boolean[3];
        if(getTop()[0] != null) { hasResources[0] = true; }
        for(int i = 0; i < getMiddle().length; i ++){ if(getMiddle()[i] != null) { hasResources[1] = true; break; } }
        for(int i = 0; i < getBottom().length; i ++){ if(getBottom()[i] != null) { hasResources[2] = true; break; } }

        return hasResources;
    }

    /**
     * remove resource by name (String)
     * @param resource
     * @param gameModel
     */
    public void removeResourceByName(Resource resource, GameModel gameModel){
        if(top[0]!=null){
            if(top[0].toString().equals(resource.toString())) {
                top[0]=null;
                change(gameModel);
                return;
            }
        }
        if(middle[0]!=null){
            if(middle[0].toString().equals(resource.toString())){
                middle[0]=null;
                change(gameModel);
                return;
            }
        }
        if(middle[1]!=null){
            if(middle[1].toString().equals(resource.toString())){
                middle[1]=null;
                change(gameModel);
                return;
            }
        }
        if(bottom[0]!=null){
            if(bottom[0].toString().equals(resource.toString())){
                bottom[0]=null;
                change(gameModel);
                return;
            }
        }
        if(bottom[1]!=null){
            if(bottom[1].toString().equals(resource.toString())){
                bottom[1]=null;
                change(gameModel);
                return;
            }
        }
        if(bottom[2]!=null){
            if(bottom[2].toString().equals(resource.toString())){
                bottom[2]=null;
                change(gameModel);
                return;
            }
        }
    }

    /**
     * Remove resource from the warehouse
     * @param resource
     */
    public void removeResource(Resource resource){
        if(top[0]==resource)
            top[0]=null;
        else if(middle[0]==resource)
            middle[0]=null;
        else if(middle[1]==resource)
            middle[1]=null;
        else if(bottom[0]==resource)
            bottom[0]=null;
        else if(bottom[1]==resource)
            bottom[1]=null;
        else if(bottom[2]==resource)
            bottom[2]=null;
    }

    /**
     * find the resource and return the position
     * @param resource
     * @return
     */
    public int findResource(Resource resource) {
        if(top[0]==resource)
            return 0;
        else if(middle[0]==resource)
            return 1;
        else if(middle[1]==resource)
            return 2;
        else if(bottom[0]==resource)
            return 3;
        else if(bottom[1]==resource)
            return 4;
        else if(bottom[2]==resource)
            return 5;
        else
        {
            System.out.println("Resource not found");
            return -1;
        }
    }

    /**
     * returns the number of specified resources in the warehouse
     * @param resource
     * @return
     */
    public int numberOfResource(Resource resource) {
        int amount = 0;

        if(top[0] != null) { if(top[0].toString().equals(resource.toString())) { amount++; } }
        if(middle[0] != null) { if(middle[0].toString().equals(resource.toString())) { amount++; } }
        if(middle[1] != null) { if(middle[1].toString().equals(resource.toString())) { amount++; } }
        if(bottom[0] != null) { if(bottom[0].toString().equals(resource.toString())) { amount++; } }
        if(bottom[1] != null) { if(bottom[1].toString().equals(resource.toString())) { amount++; } }
        if(bottom[2] != null) { if(bottom[2].toString().equals(resource.toString())) { amount++; } }

        return amount;
    }

    /**
     * returns the number of resources in the warehouse
     * @return
     */
    public int numberOfResourceInWareHouse() {
        int amount = 0;
        amount += numberOfResource(new Coin());
        amount += numberOfResource(new Servant());
        amount += numberOfResource(new Shield());
        amount += numberOfResource(new Stone());

        return amount;
    }

    /**
     * converts the warehouse into a array list
     * @return
     */
    public ArrayList<Resource> warehouseToList() {
        ArrayList<Resource> resourceList = new ArrayList<>(6);
        resourceList.add(top[0]);
        resourceList.add(middle[0]);
        resourceList.add(middle[1]);
        resourceList.add(bottom[0]);
        resourceList.add(bottom[1]);
        resourceList.add(bottom[2]);

        return resourceList;
    }


    /**
     * used to move the resource to the top of the warehouse
     * @param resource
     */
    public void moveToTop(Resource resource){
        if (top[0]==null){
            removeResource(resource);
            top[0]=resource;
        }
        else if(top[0]!=null){
            if(findResource(resource)==1 && middle[1] == null)
            {
                removeResource(resource);
                middle[0]=top[0];
                top[0]=resource;

            }
            else if(findResource(resource)==2 && middle[0]== null)
            {
                removeResource(resource);
                middle[1]=top[0];
                top[0]=resource;

            }
            //CAMBIATO QUIIII
            else if(findResource(resource)==3&& bottom[1]==null && bottom[2]==null)
            {
                removeResource(resource);
                bottom[0]=top[0];
                top[0]=resource;
            }
            else if(findResource(resource)==4 && bottom[0]==null && bottom[2]==null)
            {
                removeResource(resource);
                bottom[1]=top[0];
                top[0]=resource;

            }
            else if(findResource(resource)==5 && bottom[0]==null && bottom[1]==null)
            {
                removeResource(resource);
                bottom[2]=top[0];
                top[0]=resource;
            }
            else
                System.out.println("Can't move to Top!!");
        }
    }

    /**
     * used to move the resource from top to the bottom of the warehouse
     * @param resource
     */
    public void moveFromTopToBottom(Resource resource) {
        if (top[0] == resource) {
            /**
             * case bottom empty
             */
            if (bottom[0] == null && bottom[1] == null && bottom[2] == null) {
                removeResource(resource);
                bottom[0] = resource;
            }
            /**
             * case bottom has 1 resource
             */
            else if ((bottom[0] != null && bottom[1] == null && bottom[2] == null) ||
                    (bottom[1] != null && bottom[0] == null && bottom[2] == null) ||
                    (bottom[2] != null && bottom[0] == null && bottom[1] == null)) {
                for (int i = 0; i < 3; i++) {
                    if (bottom[i] != null) {
                        removeResource(resource);
                        top[0] = bottom[i];
                        bottom[i] = resource;
                    }
                }
            }
            /**
             * case bottom has 2 resource and middle is free
             */
            else if (middle[0] == null && middle[1] == null) {
                top[0] = null;
                /**
                 * move 2 resource from bottom to middle first and then put the resource from top to bottom
                 */
                if (bottom[0] != null && bottom[1] != null && bottom[2] == null) {
                    removeResource(resource);
                    middle[0] = bottom[0];
                    middle[1] = bottom[1];
                    bottom[0] = resource;
                    bottom[1] = null;
                } else if (bottom[0] != null && bottom[1] == null && bottom[2] != null) {
                    removeResource(resource);
                    middle[0] = bottom[0];
                    middle[1] = bottom[2];
                    bottom[0] = resource;
                    bottom[2] = null;
                } else if ((bottom[0] == null && bottom[1] != null && bottom[2] != null)) {
                    removeResource(resource);
                    middle[0] = bottom[1];
                    middle[1] = bottom[2];
                    bottom[1] = resource;
                    bottom[2] = null;
                }
            }
        }
    }

    /**
     *case were i have only 1 resource in the middle
     */
    public void moveFromMiddleToBottom(Resource resource) {
        if ((middle[0] == resource&&middle[1] == null) || (middle[0] == null&&middle[1] == resource)) {
            /**
             * case bottom empty
             */
            if (bottom[0] == null && bottom[1] == null && bottom[2] == null) {
                removeResource(resource);
                bottom[0] = resource;
            }
            /**
             * case bottom has 1 resource
             */
            else if ((bottom[0] != null && bottom[1] == null && bottom[2] == null) ||
                    (bottom[1] != null && bottom[0] == null && bottom[2] == null) ||
                    (bottom[2] != null && bottom[0] == null && bottom[1] == null)) {
                for (int i = 0; i < 3; i++) {
                    if (bottom[i] != null) {
                        for (int j = 0; j < 2; j++) {
                            if (middle[j] != null) {
                                removeResource(resource);
                                middle[j] = bottom[i];
                                bottom[i] = resource;
                            }
                        }
                    }
                }
            }
            /**
             * cases where bottom has 2 resources
             */
            else if (bottom[0] != null && bottom[1] != null && bottom[2] == null) {
                removeResource(resource);
                middle[0] = bottom[0];
                middle[1] = bottom[1];
                bottom[0] = resource;
                bottom[1] = null;
            } else if (bottom[0] != null && bottom[1] == null && bottom[2] != null) {
                removeResource(resource);
                middle[0] = bottom[0];
                middle[1] = bottom[2];
                bottom[0] = resource;
                bottom[2] = null;
            } else if ((bottom[0] == null && bottom[1] != null && bottom[2] != null)) {
                removeResource(resource);
                middle[0] = bottom[1];
                middle[1] = bottom[2];
                bottom[0] = resource;
                bottom[1] = null;
                bottom[2] = null;
            }
        }
    }

    /**
     *case were i have 2 resources in the middle
     */
    public void moveFromMiddleToBottom(Resource[] resource){
        if(middle[0]==resource[0]&&middle[1]==resource[1]) {
            /**
             * case bottom empty
             */
            if (bottom[0] == null && bottom[1] == null && bottom[2] == null) {
                bottom[0] = middle[0];
                bottom[1] = middle[1];
                middle[0]=null;
                middle[1]=null;
            }
            /**
             * case bottom has 1 resource
             */
            else if ((bottom[0] != null && bottom[1] == null && bottom[2] == null) ||
                    (bottom[1] != null && bottom[0] == null && bottom[2] == null) ||
                    (bottom[2] != null && bottom[0] == null && bottom[1] == null)) {
                if (bottom[0] != null) {
                    Resource temp = bottom[0];
                    bottom[0] = middle[0];
                    bottom[1] = middle[1];
                    middle[0] = temp;
                    middle[1] = null;
                } else if (bottom[1] != null) {
                    Resource temp = bottom[1];
                    bottom[0] = middle[0];
                    bottom[1] = middle[1];
                    middle[0] = temp;
                    middle[1] = null;
                } else if (bottom[2] != null) {
                    bottom[0] = middle[0];
                    bottom[1] = middle[1];
                    middle[0] = bottom[2];
                    middle[1] = null;
                }
            }
            /**
             * cases where bottom has 2 resources
             */
            else if (bottom[0] != null && bottom[1] != null && bottom[2] == null) {
                Resource temp1 = middle[0];
                Resource temp2 = middle[1];
                middle[0] = bottom[0];
                middle[1] = bottom[1];
                bottom[0] = temp1;
                bottom[1] = temp2;
            } else if (bottom[0] != null && bottom[1] == null && bottom[2] != null) {
                Resource temp1 = middle[0];
                Resource temp2 = middle[1];
                middle[0] = bottom[0];
                middle[1] = bottom[2];
                bottom[0] = temp1;
                bottom[1] = temp2;
                bottom[2] = null;
            } else if ((bottom[0] == null && bottom[1] != null && bottom[2] != null)) {
                Resource temp1 = middle[0];
                Resource temp2 = middle[1];
                middle[0] = bottom[1];
                middle[1] = bottom[2];
                bottom[0] = temp1;
                bottom[1] = temp2;
                bottom[2] = null;
            }
        }
    }

    /**
     * used to move the resource from top to the middle of the warehouse
     * @param resource
     */
    public void moveFromTopToMiddle(Resource resource) {
        if (top[0] == resource) {
            /**
             * case middle is empty
             */
            if (middle[0] == null && middle[1] == null) {
                removeResource(resource);
                middle[0] = resource;
            }
            /**
             * case middle has 1 resource
             */
            else if (middle[0] != null && middle[1] == null) {
                removeResource(resource);
                top[0] = middle[0];
                middle[0] = resource;
            } else if (middle[0] == null && middle[1] != null) {
                removeResource(resource);
                top[0] = middle[1];
                middle[0] = resource;
                middle[1] = null;
            }
            /**
             * case middle has 2 resource and bottom empty
             */
            else if (bottom[0] == null && bottom[1] == null && bottom[2] == null && middle[0] != null && middle[1] != null) {
                removeResource(resource);
                bottom[0] = middle[0];
                bottom[1] = middle[1];
                middle[0] = resource;
                middle[1] = null;
            }
        }
    }

    /**
     *case when bottom has 1 resource
     */
    public void moveFromBottomToMiddle(Resource resource){
        /**
         * cases when the resource in the bottom is in [0]
         */
        if((bottom[0]==resource&&bottom[1]==null&&bottom[2]==null)){
            /**
             * case when middle is empty
             */
            if(middle[0]==null&&middle[1]==null){
                removeResource(resource);
                middle[0]=resource;
            }
            /**
             * cases when middle has 1 resource
             */
            else if (middle[0] != null && middle[1] == null) {
                removeResource(resource);
                bottom[0] = middle[0];
                middle[0] = resource;
            }
            else if (middle[0] == null && middle[1] != null) {
                removeResource(resource);
                bottom[0] = middle[1];
                middle[0] = resource;
                middle[1] = null;
            }
            /**
             * case when middle has 2 resource
             */
            else if (middle[0]!=null&&middle[1]!=null){
                removeResource(resource);
                bottom[0]=middle[0];
                bottom[1]=middle[1];
                middle[0]=resource;
                middle[1]=null;
            }
        }
        /**
         * cases when the resource in the bottom is in [1]
         */
        else if(bottom[0]==null&&bottom[1]==resource&&bottom[2]==null){
            /**
             * case when middle is empty
             */
            if(middle[0]==null&&middle[1]==null){
                removeResource(resource);
                middle[0]=resource;
            }
            /**
             * cases when middle has 1 resource
             */
            else if (middle[0] != null && middle[1] == null) {
                removeResource(resource);
                bottom[0] = middle[0];
                middle[0] = resource;
            }
            else if (middle[0] == null && middle[1] != null) {
                removeResource(resource);
                bottom[0] = middle[1];
                middle[0] = resource;
                middle[1] = null;
            }
            /**
             * case when middle has 2 resource
             */
            else if (middle[0]!=null&&middle[1]!=null){
                removeResource(resource);
                bottom[0]=middle[0];
                bottom[1]=middle[1];
                middle[0]=resource;
                middle[1]=null;
            }
        }
        /**
         * cases when the resource in the bottom is in [2]
         */
        else if(bottom[0]==null&&bottom[1]==null&&bottom[2]==resource){
            /**
             * case when middle is empty
             */
            if(middle[0]==null&&middle[1]==null){
                removeResource(resource);
                middle[0]=resource;
            }
            /**
             * cases when middle has 1 resource
             */
            else if (middle[0] != null && middle[1] == null) {
                removeResource(resource);
                bottom[2] = middle[0];
                middle[0] = resource;
            }
            else if (middle[0] == null && middle[1] != null) {
                removeResource(resource);
                bottom[0] = middle[1];
                middle[0] = resource;
                middle[1] = null;
            }
            /**
             * case when middle has 2 resource
             */
            else if (middle[0]!=null&&middle[1]!=null){
                removeResource(resource);
                bottom[0]=middle[0];
                bottom[1]=middle[1];
                middle[0]=resource;
                middle[1]=null;
            }
        }
    }

    /**
     * case when bottom has 2 resources
     */
    public void moveFromBottomToMiddle(Resource[] resource){
        /**
         * cases when 2 resources are in [0] and [1]
         */
        if(bottom[0]==resource[0]&&bottom[1]==resource[1]&&bottom[2]==null){
            /**
             * case when middle is empty
             */
            if(middle[0]==null&&middle[1]==null){
                middle[0] = bottom[0];
                middle[1] = bottom[1];
                bottom[0] = null;
                bottom[1] = null;
            }
            /**
             * cases when middle has 1 resource
             */
            else if (middle[0]!=null&&middle[1]==null){
                Resource temp = middle[0];
                middle[0] = bottom[0];
                middle[1] = bottom[1];
                bottom[0] = temp;
                bottom[1] = null;
            }
            else if (middle[0]==null&&middle[1]!=null){
                Resource temp = middle[1];
                middle[0] = bottom[0];
                middle[1] = bottom[1];
                bottom[0] = temp;
                bottom[1] = null;
            }
            /**
             * case middle has 2 resources
             */
            else if(middle[0]!=null&&middle[1]!=null){
                Resource temp1 = middle[0];
                Resource temp2 = middle[1];
                middle[0] = bottom[0];
                middle[1] = bottom[1];
                bottom[0] = temp1;
                bottom[1] = temp2;
            }
        }
        /**
         * cases when 2 resources are in [0] and [2]
         */
        else if(bottom[0]==resource[0]&&bottom[1]==null&&bottom[2]==resource[2]){
            /**
             * case when middle is empty
             */
            if(middle[0]==null&&middle[1]==null){
                middle[0] = bottom[0];
                middle[1] = bottom[2];
                bottom[0] = null;
                bottom[2] = null;
            }
            /**
             * cases when middle has 1 resource
             */
            else if (middle[0]!=null&&middle[1]==null){
                Resource temp = middle[0];
                middle[0] = bottom[0];
                middle[1] = bottom[2];
                bottom[0] = temp;
                bottom[2] = null;
            }
            else if (middle[0]==null&&middle[1]!=null){
                Resource temp = middle[1];
                middle[0] = bottom[0];
                middle[1] = bottom[2];
                bottom[0] = temp;
                bottom[2] = null;
            }
            /**
             * case middle has 2 resources
             */
            else if(middle[0]!=null&&middle[1]!=null){
                Resource temp1 = middle[0];
                Resource temp2 = middle[1];
                middle[0] = bottom[0];
                middle[1] = bottom[2];
                bottom[0] = temp1;
                bottom[1] = temp2;
                bottom[2] = null;
            }
        }
        /**
         * cases when 2 resources are in [1] and [2]
         */
        else if(bottom[0]==null&&bottom[1]==resource[1]&&bottom[2]==resource[2]){
            /**
             * case when middle is empty
             */
            if(middle[0]==null&&middle[1]==null){
                middle[0] = bottom[1];
                middle[1] = bottom[2];
                bottom[1] = null;
                bottom[2] = null;
            }
            /**
             * cases when middle has 1 resource
             */
            else if (middle[0]!=null&&middle[1]==null){
                Resource temp = middle[0];
                middle[0] = bottom[1];
                middle[1] = bottom[2];
                bottom[0] = temp;
                bottom[1] = null;
                bottom[2] = null;
            }
            else if (middle[0]==null&&middle[1]!=null){
                Resource temp = middle[1];
                middle[0] = bottom[1];
                middle[1] = bottom[2];
                bottom[0] = temp;
                bottom[1] = null;
                bottom[2] = null;
            }
            /**
             * case middle has 2 resources
             */
            else if(middle[0]!=null && middle[1]!=null){
                Resource temp1 = middle[0];
                Resource temp2 = middle[1];
                middle[0] = bottom[1];
                middle[1] = bottom[2];
                bottom[0] = temp1;
                bottom[1] = temp2;
                bottom[2] = null;
            }
        }
    }

    /**
     * @return number of the resource in the top
     */
    public int getElemTop() {
        int i=0;
        if(top[0] == null)
            return 0;
        else return i ++;
    }

    /**
     * @return number of the resource in the middle
     */
    public int getElemMiddle() {
        int middleElem = 0 ;
         for(int i = middle.length-1 ; i==0 ;  i--){
             if(middle[i]!= null){
                 middleElem++ ;

             }
         }
         return middleElem;
    }

    /**
     * @return number of the resource in the bottom
     */
    public int getElemBottom() {
        int bottomElem = 0 ;
        for(int i = bottom.length-1 ; i==0 ;  i--){
            if(bottom[i]!= null){
                bottomElem++ ;

            }
        }
        return bottomElem;
    }

    /**
     * method used to clone the object if needed
     * @return
     * @throws CloneNotSupportedException
     */
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * used to move the resource in the place specified
     * @param from
     * @param to
     * @param gameModel
     */
    public void moveResource (String from, String to, GameModel gameModel) {
        if(to.equals("top") && from.equals("middle")){
            if(middle[0]!=null){
                moveToTop(middle[0]);
            } else if(middle[1]!=null){
                moveToTop(middle[1]);
            }
        } else if(to.equals("top") && from.equals("bottom")){
            if(bottom[0]!= null){
                moveToTop(bottom[0]);
            } else if(bottom[1]!=null){
                moveToTop(bottom[1]);
            } else if(bottom[2]!=null){
                moveToTop(bottom[2]);
            }
        } else if(to.equals("middle") && from.equals("top")) {
            moveFromTopToMiddle(top[0]);
        } else if(to.equals("middle") && from.equals("bottom")){
            if((bottom[0]!=null&&bottom[1]==null&&bottom[2]==null)){
                moveFromBottomToMiddle(bottom[0]);
            } else if((bottom[0]==null&&bottom[1]!=null&&bottom[2]==null)) {
                moveFromBottomToMiddle(bottom[1]);
            } else if((bottom[0]==null&&bottom[1]==null&&bottom[2]!=null)) {
                moveFromBottomToMiddle(bottom[2]);
            } else if((bottom[0]!=null&&bottom[1]!=null&&bottom[2]==null)||(bottom[0]!=null&&bottom[1]==null&&bottom[2]!=null)||(bottom[0]==null&&bottom[1]!=null&&bottom[2]!=null)) {
                moveFromBottomToMiddle(bottom);
            }
        } else if(to.equals("bottom") && from.equals("top")) {
            moveFromTopToBottom(top[0]);
        } else if(to.equals("bottom") && from.equals("middle")) {
            if(middle[0]!=null&&middle[1]==null) {
                moveFromMiddleToBottom(middle[0]);
            } else if(middle[0]==null&&middle[1]!=null) {
                moveFromMiddleToBottom(middle[1]);
            } else if(middle[0]!=null && middle[1]!=null) {
                moveFromMiddleToBottom(middle);
            }
        }
        change(gameModel);
    }
}