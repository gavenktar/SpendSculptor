package by.kirylarol.spendsculptor.model;

import by.kirylarol.spendsculptor.repos.Position;
import com.google.gson.*;
import org.springframework.security.core.parameters.P;

import javax.swing.text.Element;
import java.math.BigDecimal;
import java.util.*;

public class JsonStringIntoInternalParser {

    JsonArray object = null;

    public JsonStringIntoInternalParser firstParseStageAfterHttp(String jsonString){
        Gson gson = new Gson();
        JsonObject rawObject = gson.fromJson(jsonString, JsonObject.class);
        JsonArray object = rawObject.get("result").getAsJsonArray().get(0).getAsJsonObject().get("prediction").getAsJsonArray();
        return this;
    }

    public JsonStringIntoInternalParser firstParseStageRaw(String jsonString){
        Gson gson = new Gson();
        object = gson.fromJson(jsonString, JsonArray.class);
        return this;
    }
    public Collection<Position> parse() throws Exception {
        if (object == null){
            throw new Exception("You must have a firstParseStage");
        }
        JsonObject dateObject = null;
        JsonObject tableObject= null;
        JsonObject totalAmountObject = null;
        for (var elem : object){
            JsonObject elemObject = (JsonObject)elem;
            if (elemObject.has("label")){
                String label = elemObject.get("label").getAsString();
                if (label.equals("Total_Amount")){
                    totalAmountObject = elemObject;
                }
                if (label.equals("Date")){
                    dateObject = elemObject;
                }
                if (label.equals("table")){
                    tableObject = elemObject;
                }
            }
        }
        if (totalAmountObject != null) {
            String price = totalAmountObject.get("ocr_text").getAsString();
            price = price.replace(',', '.');
        }
        if (dateObject != null) {
            String date = dateObject.get("ocr_text").getAsString();
        }
        if (tableObject != null) {
            JsonArray table = tableObject.get("cells").getAsJsonArray();

            Map<Integer, Position> map = new HashMap<>();
            for (var elem : table) {
                JsonObject jsonObject = elem.getAsJsonObject();
                String label = null;
                if (jsonObject.has("label")) {
                    label = jsonObject.get("label").getAsString();
                }
                if (label.equals("Description") || label.equals("Price") || label.equals("Line_Amount")) {
                    Integer key = jsonObject.get("row").getAsInt();
                    if (!map.containsKey(key)) {
                        map.put(key, new Position());
                    }
                    var entry = map.get(key);
                    if (label.equals("Description")) {
                        String description = jsonObject.get("text").getAsString();
                        entry.setName(description);
                    } else {
                        String priceofEntity = null;
                        try {
                            priceofEntity = jsonObject.get("text").getAsString().replace(',', '.');
                            entry.setPrice(new BigDecimal(priceofEntity));
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(priceofEntity);
                        }
                    }
                }
            }
            return map.values();
        }
    }
}
