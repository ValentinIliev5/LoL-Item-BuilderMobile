package uni.fmi.bachelors.lolitemsbuilder;

public class Item {
    int ItemID;
    String ItemName;
    String ItemDesc;
    int Cost;
    String ItemImagePath;
    Boolean IsMythic;

    public Item (int itemID,String itemName,String itemDesc,int cost,String itemImagePath,Boolean isMythic)
    {
        this.ItemID=itemID;
        this.ItemName=itemName;
        this.ItemDesc = itemDesc;
        this.Cost= cost;
        this.ItemImagePath = itemImagePath;
        this.IsMythic = isMythic;
    }
    public Item(){

    }
}
