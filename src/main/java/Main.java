import models.Festival;
import services.FestivalServiceImpl;

public class Main {
    public static void main(String[] args) {
        FestivalServiceImpl festivalService = FestivalServiceImpl.getInstance();


        festivalService.add(new Festival("festival","festival1","festival2","festival3"));

    }
}
