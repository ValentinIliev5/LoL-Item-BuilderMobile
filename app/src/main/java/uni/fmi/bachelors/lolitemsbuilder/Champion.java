package uni.fmi.bachelors.lolitemsbuilder;

public class Champion {
    int ChampionID;
    String Name;
    String ImagePath;

    public Champion(int championID,String name, String imagePath)
    {
        this.ChampionID=championID;
        this.Name=name;
        this.ImagePath=imagePath;
    }

}
