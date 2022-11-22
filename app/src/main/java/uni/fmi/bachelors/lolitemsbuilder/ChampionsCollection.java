package uni.fmi.bachelors.lolitemsbuilder;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChampionsCollection {
    public static ArrayList<Champion> getChampionsCollection() throws IOException, JSONException
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Champion> championsCollection = new ArrayList<>();
        String apiURLDesc = "https://ddragon.leagueoflegends.com/cdn/12.22.1/data/en_US/champion.json";
        String apiURLIcon = "https://ddragon.leagueoflegends.com/cdn/12.22.1/img/champion/";//Name+.png
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
                Champion championToAdd = new Champion();
                championToAdd.Name=key;
                championToAdd.ImagePath=apiURLIcon+key+".png";

                championsCollection.add(championToAdd);
            }
        }
        catch (Exception e)
        {
            Log.e("exception",e.getMessage());
            e.printStackTrace();
        };
        return championsCollection;

    }
}
