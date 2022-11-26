package uni.fmi.bachelors.lolitemsbuilder;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ItemsCollection {


    public static ArrayList<Item> getItemCollection() throws IOException, JSONException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Item> itemsCollection = new ArrayList<>();
        String apiURLDesc = "https://ddragon.leagueoflegends.com/cdn/12.22.1/data/en_US/item.json";
        String apiURLIcon = "https://ddragon.leagueoflegends.com/cdn/12.22.1/img/item/";
        JSONObject jsonObject=null;
        JSONObject data = null;
        String json = "";

        OkHttpClient cli = new OkHttpClient();
        Request req = new Request.Builder()
                .url(apiURLDesc)
                .get()
                .build();

        try(Response response = cli.newCall(req).execute())
        {
            json = response.body().string();
            jsonObject = new JSONObject(json);

            data = jsonObject.getJSONObject("data");


            Iterator<String> keys = data.keys();

            while(keys.hasNext()) {
                String key = keys.next();

                if (data.get(key) instanceof JSONObject) {
                    Item itemToAdd = new Item();
                    itemToAdd.ItemID= Integer.parseInt(key);

                    JSONObject item = data.getJSONObject(key);
                    itemToAdd.ItemName = item.get("name").toString();
                    itemToAdd.ItemDesc = item.get("plaintext").toString();

                    JSONObject price  = item.getJSONObject("gold");
                    itemToAdd.Cost = Integer.parseInt(price.get("total").toString());
                    itemToAdd.ItemImagePath = apiURLIcon + key + ".png";

                    itemsCollection.add(itemToAdd);
                }

            }
        }
        catch (Exception e)
        {
            Log.e("exception",e.getMessage());
            e.printStackTrace();
        };
        return itemsCollection;
    }


}
