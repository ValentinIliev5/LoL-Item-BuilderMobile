package uni.fmi.bachelors.lolitemsbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BuildsActivity extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout galleryBuilds;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builds);


        dbHandler= new DBHandler(BuildsActivity.this);

        scrollView= findViewById(R.id.buildsWrapper);
        galleryBuilds = findViewById(R.id.buildsGallery);


        LayoutInflater inflater = LayoutInflater.from(this);

        ArrayList<Build> builds = dbHandler.readAllBuilds();

        View view;

        for(Build build: builds)
        {
            view = inflater.inflate(R.layout.build,galleryBuilds,false);

            ImageView img1 = (ImageView) view.findViewById(R.id.item1IV);
            ImageView img2 = (ImageView) view.findViewById(R.id.item2IV);
            ImageView img3 = (ImageView) view.findViewById(R.id.item3IV);
            ImageView img4 = (ImageView) view.findViewById(R.id.item4IV);
            ImageView img5 = (ImageView) view.findViewById(R.id.item5IV);
            ImageView img6 = (ImageView) view.findViewById(R.id.item6IV);

            ImageView champIcon = (ImageView) view.findViewById(R.id.bChampIV);


            String api = "https://ddragon.leagueoflegends.com/cdn/12.22.1/img/item/";//+.png
            String apiC = "https://ddragon.leagueoflegends.com/cdn/12.22.1/img/champion/";//+png

            Glide.with(this).load(api+build.FirstItemID+".png").into(img1);
            Glide.with(this).load(api+build.SecondItemID+".png").into(img2);
            Glide.with(this).load(api+build.ThirdItemID+".png").into(img3);
            Glide.with(this).load(api+build.FourthItemID+".png").into(img4);
            Glide.with(this).load(api+build.FifthItemID+".png").into(img5);
            Glide.with(this).load(api+build.SixthItemID+".png").into(img6);
            Glide.with(this).load(apiC+build.ChampName+".png").into(champIcon);

            champIcon.setOnClickListener((new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dbHandler.deleteBuild(build.ID);
                    finish();
                    startActivity(getIntent());
                }
            }
            ));

            TextView buildName = view.findViewById(R.id.buildNameTV);
            buildName.setText(build.Name);

            galleryBuilds.addView(view);

        }
    }
}