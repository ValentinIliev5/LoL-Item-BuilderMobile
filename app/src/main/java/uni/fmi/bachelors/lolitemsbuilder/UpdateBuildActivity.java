package uni.fmi.bachelors.lolitemsbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UpdateBuildActivity extends AppCompatActivity {

    DBHandler dbHandler;

    ImageView champImgV,item1ImgV,item2ImgV,item3ImgV,item4ImgV,item5ImgV,item6ImgV;
    EditText buildNameET;
    Button updateBtn,deleteBtn;
    TextView uTest;

    ScrollView scrv;
    LinearLayout gal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_build);

        int buildId = getIntent().getExtras().getInt("buildID");
        String api = "https://ddragon.leagueoflegends.com/cdn/12.22.1/img/item/";//+png;
        String apiC = "https://ddragon.leagueoflegends.com/cdn/12.22.1/img/champion/";//+png

        dbHandler = new DBHandler(UpdateBuildActivity.this);

        Build buildToEdit= null;

        for (Build b: dbHandler.readAllBuilds())
        {
            if(b.ID==buildId) buildToEdit=b;
        }

        champImgV=findViewById(R.id.chUpdate);
        item1ImgV=findViewById(R.id.i1Update);
        item2ImgV=findViewById(R.id.i2Update);
        item3ImgV=findViewById(R.id.i3Update);
        item4ImgV=findViewById(R.id.i4Update);
        item5ImgV=findViewById(R.id.i5Update);
        item6ImgV=findViewById(R.id.i6Update);

        uTest = findViewById(R.id.updateBTV);

        uTest.setText(buildId+"");
        buildNameET = findViewById(R.id.updateNameET);

        updateBtn = findViewById(R.id.updateButton);
        deleteBtn = findViewById(R.id.deleteButton);

        scrv = findViewById(R.id.uisv);
        gal = findViewById(R.id.uisvGallery);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view;

        ArrayList<ImageView> itemImages = new ArrayList<>(6);

        itemImages.add(item1ImgV);
        itemImages.add(item2ImgV);
        itemImages.add(item3ImgV);
        itemImages.add(item4ImgV);
        itemImages.add(item5ImgV);
        itemImages.add(item6ImgV);

        ArrayList<Integer> itemIDS = new ArrayList<>();

        itemIDS.add(buildToEdit.FirstItemID);
        itemIDS.add(buildToEdit.SecondItemID);
        itemIDS.add(buildToEdit.ThirdItemID);
        itemIDS.add(buildToEdit.FourthItemID);
        itemIDS.add(buildToEdit.FifthItemID);
        itemIDS.add(buildToEdit.SixthItemID);

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

        Glide.with(this)
                .load(apiC+ buildToEdit.ChampName+".png")
                .into(champImgV);
        Glide.with(this)
                .load(api+ buildToEdit.FirstItemID+".png")
                .into(item1ImgV);
        Glide.with(this)
                .load(api+ buildToEdit.SecondItemID+".png")
                .into(item2ImgV);
        Glide.with(this)
                .load(api+ buildToEdit.ThirdItemID+".png")
                .into(item3ImgV);
        Glide.with(this)
                .load(api+ buildToEdit.FourthItemID+".png")
                .into(item4ImgV);
        Glide.with(this)
                .load(api+ buildToEdit.FifthItemID+".png")
                .into(item5ImgV);
        Glide.with(this)
                .load(api+ buildToEdit.SixthItemID+".png")
                .into(item6ImgV);

        buildNameET.setText(buildToEdit.Name);


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
                            itemIDS.set(i,item.ItemID);

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

        Build finalBuildToEdit = buildToEdit;
        updateBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dbHandler.updateBuild(buildId,
                        new Build(buildId,
                            buildNameET.getText().toString(),
                            finalBuildToEdit.ChampName,
                            itemIDS.get(0),
                            itemIDS.get(1),
                            itemIDS.get(2),
                            itemIDS.get(3),
                            itemIDS.get(4),
                            itemIDS.get(5))
                );
                Intent i = new Intent(getApplicationContext(),BuildsActivity.class);
                startActivity(i);
            }
        }
        ));
        deleteBtn.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dbHandler.deleteBuild(buildId);
                Intent i = new Intent(getApplicationContext(),BuildsActivity.class);
                startActivity(i);
            }
        }
        ));
    }
}