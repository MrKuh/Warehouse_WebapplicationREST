package at.htlkaindorf.warehouse_webapplicationrest;

import at.htlkaindorf.warehouse_webapplicationrest.beans.Pick;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class IOAccess {

    private static final String configFile = "config.csv";
    private static final String dataFile = "data.csv";

    public static Map<String, Integer> getConfig(){
        InputStream is = IOAccess.class.getClassLoader().getResourceAsStream(configFile);
        Map<String, Integer> config = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                .filter(line -> !line.isEmpty())
                .map(line -> line.split(":"))
                .collect(Collectors.toMap(line -> line[0],line -> Integer.valueOf(line[1])));
        return config;
    }

    public static List<Pick> getData(){
        InputStream iS = IOAccess.class.getClassLoader().getResourceAsStream(dataFile);
        List<Pick> picks = new BufferedReader(new InputStreamReader(iS, StandardCharsets.UTF_8)).lines().skip(1)
                .filter(line -> !line.isEmpty())
                .map(Pick::fromString)
                .collect(Collectors.toList());
        return picks;
    }


    public static void main(String[] args) {
        List<Pick> picks = WebDataBase.getInstance().getPicks(1);
        Map<String, Pick> data = new HashMap<String, Pick>();
        data.put("active", picks.get(0));
        data.put("next", picks.get(1));
        System.out.println(data);
    }




}
