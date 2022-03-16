package at.htlkaindorf.warehouse_webapplicationrest.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.MalformedURLException;
import java.net.URL;

@Data
@AllArgsConstructor
public class Pick {
    //Auftragsnummer;Quellbehälter;Anzahl;Marke;ProduktName;Farbe;Groeße;Beschreibung;Pic URL
    private int orderNumber;
    private int destination;
    private int source;
    private int amount;
    private String productBrand;
    private String productName;
    private String productColor;
    private String productSize;
    private String picURL;

    public static Pick fromString(String text){
        String[] split = text.split(";");
        return new Pick(
                Integer.parseInt(split[0]),
                -1,
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2]),
                split[3],
                split[4],
                split[5],
                split[6],
                "");
    }
}
