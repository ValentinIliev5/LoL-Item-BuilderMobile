package uni.fmi.bachelors.lolitemsbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class ItemsListActivity extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout galleryItems;

    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        dbHandler = new DBHandler(ItemsListActivity.this);

        scrollView =findViewById(R.id.ItemsWrapper);
        galleryItems = findViewById(R.id.galleryItems);
        LayoutInflater inflater = LayoutInflater.from(this);

        ArrayList<Item> items = dbHandler.readAllItems();
        View view;



        for(Item item : items)
        {
            view = inflater.inflate(R.layout.item,galleryItems,false);
            ImageView itemImg = (ImageView) view.findViewById(R.id.ItemImageV);
            TextView itemName = view.findViewById(R.id.ItemNameTV);
            TextView itemDesc = view.findViewById(R.id.DescriptionTV);
            TextView itemCost = view.findViewById(R.id.CostTV);

            Uri uri = Uri.parse(item.ItemImagePath);

            Glide.with(this).load(uri).into(itemImg);

            itemName.setText(item.ItemName);
            itemDesc.setText(item.ItemDesc);
            itemCost.setText("Price: "+item.Cost+" gold");
            galleryItems.addView(view);

        }

    }
}

