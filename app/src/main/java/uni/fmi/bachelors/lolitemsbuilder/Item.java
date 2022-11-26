package uni.fmi.bachelors.lolitemsbuilder;

public class Item {
    int ItemID;
    String ItemName;
    String ItemDesc;
    int Cost;
    String ItemImagePath;

    public Item (int itemID,String itemName,String itemDesc,int cost,String itemImagePath)
    {
        this.ItemID=itemID;
        this.ItemName=itemName;
        this.ItemDesc = itemDesc;
        this.Cost= cost;
        this.ItemImagePath = itemImagePath;
    }
    public Item(){

    }
}
