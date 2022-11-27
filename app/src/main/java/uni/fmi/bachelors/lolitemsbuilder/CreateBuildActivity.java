package uni.fmi.bachelors.lolitemsbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CreateBuildActivity extends AppCompatActivity {

    ImageView champImgV,item1ImgV,item2ImgV,item3ImgV,item4ImgV,item5ImgV,item6ImgV;
    EditText buildNameET;
    TextView test;
    Button createBtn;

    ScrollView scrv;
    LinearLayout gal;


    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_build);

        String url = getIntent().getExtras().getString("champUri");
        String champName = getIntent().getExtras().getString("champName");

        champImgV=findViewById(R.id.championCImage);
        item1ImgV=findViewById(R.id.item1CImage);
        item2ImgV=findViewById(R.id.item2CImage);
        item3ImgV=findViewById(R.id.item3CImage);
        item4ImgV=findViewById(R.id.item4CImage);
        item5ImgV=findViewById(R.id.item5CImage);
        item6ImgV=findViewById(R.id.item6CImage);

        buildNameET=findViewById(R.id.buildName);

        scrv = findViewById(R.id.SCRVItems);
        gal = findViewById(R.id.itemsGalleryC);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view;

        ArrayList<ImageView> itemImages = new ArrayList<>(6);



        itemImages.add(item1ImgV);
        itemImages.add(item2ImgV);
        itemImages.add(item3ImgV);
        itemImages.add(item4ImgV);
        itemImages.add(item5ImgV);
        itemImages.add(item6ImgV);

        ArrayList<String> itemIDS = new ArrayList<String>();

        for (int i=0;i<6;i++){
            itemIDS.add(null);

        }

        for(int i=0;i<6;i++)
        {
            int finalI = i;
            itemImages.get(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                            itemIDS.set(finalI,null);
                            Glide.with(getApplicationContext()).load("0").into( itemImages.get(finalI));
                        }
            });
        }

        createBtn = findViewById(R.id.createBuildBtn);
        dbHandler = new DBHandler(CreateBuildActivity.this);

        Glide.with(this).load(url).into(champImgV);


        for(Item item :dbHandler.readAllItems())
        {
            view = inflater.inflate(R.layout.item,gal,false);
            ImageView itemImg = (ImageView) view.findViewById(R.id.ItemImageV);
            TextView itemName = view.findViewById(R.id.ItemNameTV);
            TextView itemDesc = view.findViewById(R.id.DescriptionTV);
            TextView itemCost = view.findViewById(R.id.CostTV);

            Uri uri = Uri.parse(item.ItemImagePath);

            Glide.with(this).load(uri).into(itemImg);
            itemImg.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    for(int i=0;i<6;i++) //itemImages
                    {
                        if(itemImages.get(i).getDrawable()==null)
                        {
                            Glide.with(getApplicationContext()).load(item.ItemImagePath).into(itemImages.get(i));
                            itemIDS.set(i,item.ItemID+"");
                            break;
                        }
                    }
                }
            });

            itemName.setText(item.ItemName);
            itemDesc.setText(item.ItemDesc);
            itemCost.setText("Price: "+item.Cost+" gold");

            gal.addView(view);

        }

        createBtn.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Build build= new Build(0,
                        buildNameET.getText().toString(),
                        champName,
                        Integer.parseInt(itemIDS.get(0)),
                        Integer.parseInt(itemIDS.get(1)),
                        Integer.parseInt(itemIDS.get(2)),
                        Integer.parseInt(itemIDS.get(3)),
                        Integer.parseInt(itemIDS.get(4)),
                        Integer.parseInt(itemIDS.get(5)));

                dbHandler.addBuild(build);


                Intent i = new Intent(getApplicationContext(),BuildsActivity.class);
                startActivity(i);
            }
        }
        ));

    }
}