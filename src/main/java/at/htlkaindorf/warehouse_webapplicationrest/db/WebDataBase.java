package at.htlkaindorf.warehouse_webapplicationrest.db;

import at.htlkaindorf.warehouse_webapplicationrest.beans.Pick;

import java.util.*;


public class WebDataBase {

    private static WebDataBase theInstance;

    private List<Pick> currentPicks;
    private int targetAmount;
    private int currentOrderNumber;
    private int destination;
    private int position;
    private boolean skipFirst;
    private boolean skipedBefore;
    private boolean summary;


    private WebDataBase() {
        //init destinations
        currentPicks = IOAccess.getData();
        targetAmount = IOAccess.getConfig().get("targetAmount");
        currentOrderNumber = currentPicks.get(0).getOrderNumber();
        destination = 1;

        for (Pick pick : currentPicks) {
            if (pick.getOrderNumber() == currentOrderNumber) {
                pick.setDestination(destination);
            } else {
                destination++;
                if (destination > targetAmount) {
                    destination = 1;
                }
                currentOrderNumber = pick.getOrderNumber();
                pick.setDestination(destination);
            }
        }
        //init data loop
        position = 0;
        skipFirst = false;
        skipedBefore = false;
        summary = true;
        currentOrderNumber = 0;
    }

    public synchronized static WebDataBase getInstance() {
        if (theInstance == null) {
            theInstance = new WebDataBase();
        }
        return theInstance;
    }

    public List<Pick> getPicks() {
        return currentPicks;
    }

    public List<Pick> getLastPick() {
        List<Pick> data = new ArrayList<>();
        if (!skipedBefore && position != 0) {
            position--;
        }
        //data.put("active", currentPicks.get(position));
        //data.put("next", currentPicks.get(position + 1));
        data.add(currentPicks.get(position));
        data.add(currentPicks.get(position + 1));

        skipedBefore = true;
        return data;
    }


    public List<Pick> getData() {
        List<Pick> data = new ArrayList<>();
        if (skipFirst) {
            //data.put("active", currentPicks.get(currentPicks.size() - 1));
            //data.put("next", currentPicks.get(position));
            data.add(currentPicks.get(currentPicks.size() - 1));
            data.add(currentPicks.get(position));
        } else {
            //data.put("active", currentPicks.get(position));
            //data.put("next", currentPicks.get(position + 1));
            data.add(currentPicks.get(position));
            data.add(currentPicks.get(position + 1));
        }
        return data;
    }

    public List<Pick> getNewData() {
        List<Pick> data = new ArrayList<>();
        skipedBefore = false;

        if (!skipFirst) {
            position++;
/*
            if (summary) {
                data = checkOrderComplete();
                if (data.size() > 0) {
                    summary = false;
                    position--;
                    return data;
                } else {
                    summary = true;
                }
            }

 */
        } else {
            skipFirst = false;
        }

        //GET new data
        if (position == currentPicks.size() - 1) {
            //data.put("active", currentPicks.get(position));
            data.add(currentPicks.get(position));
            position = 0;
            skipFirst = true;
            //data.put("next", currentPicks.get(position));
            data.add(currentPicks.get(position));
        } else {
            //data.put("active", currentPicks.get(position));
            //data.put("next", currentPicks.get(position + 1));
            data.add(currentPicks.get(position));
            data.add(currentPicks.get(position + 1));
        }
        return data;
    }

    public void cancelOrderData() {
        List<Pick> toMovePicks = new ArrayList<>();
        int currentIndex = position;

        toMovePicks.add(currentPicks.get(currentIndex));

        int moveOrderNumber = toMovePicks.get(0).getOrderNumber();
        int moveDestination = toMovePicks.get(0).getDestination();

        currentIndex++;
        while (currentPicks.get(currentIndex).getOrderNumber() == moveOrderNumber) {
            toMovePicks.add(0, currentPicks.remove(currentIndex));
        }
        //currentOrderNumber = currentPicks.get(currentIndex).getOrderNumber();

        while (currentPicks.get(currentIndex).getDestination() != moveDestination && currentIndex <= currentPicks.size()) {
            currentIndex++;
        }

        for (Pick insertPick : toMovePicks) {
            currentPicks.add(currentIndex, insertPick);
        }
    }

    public List<Pick> getSummary() {
        List<Pick> data = new ArrayList<>();
        if (summary && position > 0 && currentPicks.get(position + 1).getOrderNumber() != currentPicks.get(position).getOrderNumber()) {
            int completeOrderNumber = currentPicks.get(position).getOrderNumber();

            for (int i = 0; i < currentPicks.size(); i++) {
                if (currentPicks.get(i).getOrderNumber() == completeOrderNumber) {
                    data.add(currentPicks.get(i));
                }
            }
            summary = false;
        }
        return data;
    }

    public void setSummary() {
        summary = true;
    }
}

