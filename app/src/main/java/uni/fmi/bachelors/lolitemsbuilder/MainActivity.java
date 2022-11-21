package uni.fmi.bachelors.lolitemsbuilder;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hwTV = findViewById(R.id.testTV);
        tf = findViewById(R.id.editTextTextMultiLine);

        try {
            ArrayList<Item> collection = ItemsCollection.getItemCollection();
            String desc = ItemsCollection.getItemCollection().get(0).ItemDesc;
            hwTV.setText(desc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}