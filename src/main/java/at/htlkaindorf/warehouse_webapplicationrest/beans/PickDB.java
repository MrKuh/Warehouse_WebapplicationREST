package at.htlkaindorf.warehouse_webapplicationrest.beans;

import jakarta.xml.bind.JAXB;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PickDB {

    private static PickDB theInstance;

    private String xmlFile;
    private List<Pick> pickList;

    private PickDB() {
        pickList = new ArrayList<Pick>();
    }

    public synchronized static PickDB getInstance() {
        if(theInstance == null) {
            theInstance = new PickDB();
        }
        return theInstance;
    }


    public List<Pick> getPickList() {
        return pickList;
    }

    //public void loadXMLData() {
    //    List = JAXB.unmarshal(xmlFile, ProductList.class);
    //}

    //public void saveXMLData() {
    //    File file = new File(xmlFile);
    //    JAXB.marshal(List, file);
    //}

    //public void setXmlFile(String xmlFile) {
    //    this.xmlFile = xmlFile;
    //}


}

