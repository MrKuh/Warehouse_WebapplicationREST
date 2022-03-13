package at.htlkaindorf.warehouse_webapplicationrest.db;

import at.htlkaindorf.warehouse_webapplicationrest.beans.Pick;

import java.util.*;

public class WebDataBase {

    private static WebDataBase theInstance;

    private HashMap<Integer, ArrayList<Pick>> containerData;
    private ArrayList<Pick> picks;
    private boolean isNew;
    private int activeContainer;
    private int targetAmount;

    private WebDataBase() {
        containerData = new HashMap<Integer, ArrayList<Pick>>();
        targetAmount = IOAccess.getConfig().get("targetAmount");

        //init containerData
        List<Pick> allPicks = IOAccess.getData();
        ArrayList<Pick> pickList = new ArrayList<Pick>();
        int orderNumber = allPicks.get(0).getOrderNumber();
        int targetAmount = IOAccess.getConfig().get("targetAmount");
        int count = 1;
        for (Pick pick : allPicks) {
            if (orderNumber != pick.getOrderNumber() && count <= targetAmount) {
                containerData.put(count, pickList);
                pickList = new ArrayList<Pick>();
                count++;
            }
            orderNumber = pick.getOrderNumber();
            pickList.add(pick);
        }
        activeContainer = 1;
        isNew = false;
        picks = getPicksWithDestination(activeContainer, activeContainer);
    }

    public synchronized static WebDataBase getInstance() {
        if (theInstance == null) {
            theInstance = new WebDataBase();
        }
        return theInstance;
    }

    public ArrayList<Pick> getPicks(int index) {
        return containerData.get(index);
    }

    public ArrayList<Pick> getPicksWithDestination(int index, int destination) {
        ArrayList<Pick> pickList = new ArrayList(containerData.get(index));
        for (Pick pick : pickList) {
            pick.setDestination(destination);
        }
        return pickList;
    }

    public void getNextActiveContainer() {
        activeContainer++;
        if (activeContainer > targetAmount) {
            activeContainer = 1;
        }
    }

    public void replacePicks(int index, ArrayList<Pick> picks) {
        containerData.replace(index, picks);
    }

    public HashMap<Integer, ArrayList<Pick>> getContainerData() {
        return containerData;
    }

    public Map<String, Pick> getData() {
        Map<String, Pick> data = new HashMap<String, Pick>();
        if (picks.size() == 1) {
            data.put("active", picks.get(0));
            ArrayList<Pick> nextPicks = getPicksWithDestination(activeContainer + 1, activeContainer + 1);
            data.put("next", nextPicks.get(0));
        }
        if (picks.size() > 1) {
            data.put("active", picks.get(0));
            data.put("next", picks.get(1));
        }
        return data;
    }

    public Map<String, Pick> getNewData() {
        Map<String, Pick> data = new HashMap<String, Pick>();
        if (!picks.isEmpty()) {
            if (picks.size() >= 3) {
                if (isNew) {
                    data.put("active", picks.get(0));
                    data.put("next", picks.get(1));
                    isNew = false;

                } else {
                    data.put("active", picks.get(1));
                    data.put("next", picks.get(2));
                    picks.remove(0);
                }

            } else if (picks.size() == 2) {
                if (isNew) {
                    data.put("active", picks.get(0));
                    data.put("next", picks.get(1));
                    isNew = false;

                } else {
                    data.put("active", picks.get(1));
                    getNextActiveContainer();
                    picks = getPicksWithDestination(activeContainer, activeContainer);
                    data.put("next", picks.get(0));
                    isNew = true;

                }
            } else if (picks.size() == 1) {
                if (isNew) {
                    data.put("active", picks.get(0));
                    getNextActiveContainer();
                    picks = getPicksWithDestination(activeContainer, activeContainer);
                    data.put("next", picks.get(0));
                    isNew = true;

                } else {
                    getNextActiveContainer();
                    picks = getPicksWithDestination(activeContainer, activeContainer);
                    if (picks.size() > 1) {
                        data.put("active", picks.get(0));
                        data.put("next", picks.get(1));

                    } else {
                        data.put("active", picks.get(0));
                        getNextActiveContainer();
                        picks = getPicksWithDestination(activeContainer, activeContainer);
                        data.put("next", picks.get(0));
                        isNew = true;
                    }
                }
            }
        }
        return data;
    }
}

