package uni.fmi.bachelors.lolitemsbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TextView hwTV;
    EditText tf;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hwTV = findViewById(R.id.testTV);
        tf = findViewById(R.id.editTextTextMultiLine);
        dbHandler = new DBHandler(MainActivity.this);
        try {
            dbHandler.FillDB();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hwTV.setText(dbHandler.readAllItems().get(3).ItemDesc);
    }
}