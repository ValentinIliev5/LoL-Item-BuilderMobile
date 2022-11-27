package uni.fmi.bachelors.lolitemsbuilder;

public class Build {
    int ID;
    String Name;
    String ChampName;
    int FirstItemID;
    int SecondItemID;
    int ThirdItemID;
    int FourthItemID;
    int FifthItemID;
    int SixthItemID;

    public Build(int id,String name,String champName,int first,int second,int third,int fourth,int fifth,int sixth){
        this.ID=id;
        this.Name=name;
        this.ChampName=champName;
        this.FirstItemID=first;
        this.SecondItemID=second;
        this.ThirdItemID=third;
        this.FourthItemID = fourth;
        this.FifthItemID = fifth;
        this.SixthItemID=sixth;
    }
}
