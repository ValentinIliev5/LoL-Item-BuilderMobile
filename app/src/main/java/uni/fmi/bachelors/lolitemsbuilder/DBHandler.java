package uni.fmi.bachelors.lolitemsbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {



    public DBHandler(Context context){
        super(context,"LoLItemBuilder",null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS ITEMS ("+
                "ID INTEGER PRIMARY KEY, "+
                "NAME TEXT NOT NULL, " +
                "DESCRIPTION TEXT NOT NULL, " +
                "COST INTEGER NOT NULL, " +
                "IMAGE TEXT NOT NULL) ";
        db.execSQL(query);

        query ="CREATE TABLE IF NOT EXISTS CHAMPIONS ("+
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "NAME TEXT NOT NULL, " +
                "IMAGE TEXT NOT NULL)";
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS BUILDS ("+
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "NAME TEXT NOT NULL, " +
                "CHAMPIONNAME TEXT , " +
                "ITEM1ID TEXT NOT NULL, " +
                "ITEM2ID TEXT NOT NULL, " +
                "ITEM3ID TEXT NOT NULL, " +
                "ITEM4ID TEXT NOT NULL, " +
                "ITEM5ID TEXT NOT NULL, " +
                "ITEM6ID TEXT NOT NULL) ";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS ITEMS");
        db.execSQL("DROP TABLE IF EXISTS CHAMPIONS");
        db.execSQL("DROP TABLE IF EXISTS BUILDS");
        onCreate(db);
    }

    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ID",item.ItemID);
        values.put("NAME",item.ItemName);
        values.put("DESCRIPTION",item.ItemDesc);
        values.put("COST",item.Cost);
        values.put("IMAGE",item.ItemImagePath);

        db.insert("ITEMS",null,values);

        db.close();
    }
    public void addChampion(Champion champion){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NAME",champion.Name);
        values.put("IMAGE",champion.ImagePath);
        db.insert("CHAMPIONS",null,values);

        db.close();
    }

    public void addBuild(Build build){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NAME",build.Name);
        values.put("CHAMPIONNAME",build.ChampName);
        values.put("ITEM1ID",build.FirstItemID);
        values.put("ITEM2ID",build.SecondItemID);
        values.put("ITEM3ID",build.ThirdItemID);
        values.put("ITEM4ID",build.FourthItemID);
        values.put("ITEM5ID",build.FifthItemID);
        values.put("ITEM6ID",build.SixthItemID);

        db.insert("BUILDS",null,values);

        db.close();
    }

    public ArrayList<Item> readAllItems(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM ITEMS",null);

        ArrayList<Item> items = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                items.add(new Item(Integer.parseInt(cursor.getString(0))
                        ,cursor.getString(1)
                        ,cursor.getString(2)
                        ,Integer.parseInt(cursor.getString(3))
                        ,cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }
    public ArrayList<Champion> readAllChampions(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM CHAMPIONS",null);

        ArrayList<Champion> champions = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                champions.add(new Champion(cursor.getString(1)
                        ,cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return champions;
    }
    public ArrayList<Build> readAllBuilds(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM BUILDS",null);

        ArrayList<Build> builds = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                builds.add(new Build(cursor.getInt(0),
                        cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getInt(3)
                        ,cursor.getInt(4)
                        ,cursor.getInt(5)
                        ,cursor.getInt(6)
                        ,cursor.getInt(7)
                        ,cursor.getInt(8))
                );

            } while (cursor.moveToNext());
        }
        cursor.close();
        return builds;
    }
    public void updateBuild(int ID,Build build){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME",build.Name);
        values.put("ITEM1ID",build.FirstItemID);
        values.put("ITEM2ID",build.SecondItemID);
        values.put("ITEM3ID",build.ThirdItemID);
        values.put("ITEM4ID",build.FourthItemID);
        values.put("ITEM5ID",build.FifthItemID);
        values.put("ITEM6ID",build.SixthItemID);

        db.update("BUILDS",values,"ID=?",new String[]{ID+""});
        db.close();
    }

    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("ITEMS","ID=?",new String[]{id+""});
    }
    public void deleteChampion(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("CHAMPIONS","ID=?",new String[]{id+""});
    }
    public void deleteBuild(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("BUILDS","ID=?",new String[]{id+""});
    }

    public void FillDB() throws IOException, JSONException {
        for(Item i:ItemsCollection.getItemCollection())
        {
            addItem(i);
        }
        for(Champion c :ChampionsCollection.getChampionsCollection())
        {
            addChampion(c);
        }

    }
}
