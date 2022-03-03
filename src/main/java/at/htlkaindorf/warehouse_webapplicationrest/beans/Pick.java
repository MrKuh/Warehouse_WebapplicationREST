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
    private URL picURL;
    private boolean last;

    public static Pick fromString(String text){
        String[] split = text.split(";");
        URL help = null;
        try {
            help = new URL("https://firmen.wko.at/dietmar-wajand/k%C3%A4rnten/Library/img/firmenlogo-ersatz.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new Pick(
                Integer.parseInt(split[0]),
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2]),
                Integer.parseInt(split[3]),
                split[4],
                split[5],
                split[6],
                split[7],
                help/*new URL(split[7])*/,
                false);
    }
}
