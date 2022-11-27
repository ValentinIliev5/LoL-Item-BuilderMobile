package uni.fmi.bachelors.lolitemsbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChampionsListActivity extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout galleryChampions;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions_list);

        dbHandler = new DBHandler(ChampionsListActivity.this);

        scrollView= findViewById(R.id.ChampionsWrapper);
        galleryChampions = findViewById(R.id.galleryChampions);

        LayoutInflater inflater = LayoutInflater.from(this);

        ArrayList<Champion> champions = dbHandler.readAllChampions();
        View view;

        for(Champion champ : champions)
        {
            view = inflater.inflate(R.layout.champion,galleryChampions,false);
            ImageView championImg = (ImageView) view.findViewById(R.id.championImgV);
            TextView championName = view.findViewById(R.id.championName);

            Uri uri = Uri.parse(champ.ImagePath);

            Glide.with(this).load(uri).into(championImg);
            championImg.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Intent intent=new Intent(getApplicationContext(),CreateBuildActivity.class);
                    intent.putExtra("champUri",champ.ImagePath);
                    intent.putExtra("champName",champ.Name);
                    startActivity(intent);
                }
            });
            championName.setText(champ.Name);
            galleryChampions.addView(view);

        }

    }
}