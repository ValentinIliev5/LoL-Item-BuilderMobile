package uni.fmi.bachelors.lolitemsbuilder;

public class Build {
    String Name;
    int FirstItemID;
    int SecondItemID;
    int ThirdItemID;
    int FourthItemID;
    int FifthItemID;
    int SixthItemID;

    public Build(String name,int first,int second,int third,int fourth,int fifth,int sixth){
        this.Name=name;
        this.FirstItemID=first;
        this.SecondItemID=second;
        this.ThirdItemID=third;
        this.FourthItemID = fourth;
        this.FifthItemID = fifth;
        this.SixthItemID=sixth;
    }
}
