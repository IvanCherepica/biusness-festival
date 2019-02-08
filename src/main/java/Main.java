import models.Festival;
import models.HotPoint;
import services.FestivalServiceImpl;
import services.HotPointServiceImpl;

public class Main {
    public static void main(String[] args) {
        FestivalServiceImpl festivalService = FestivalServiceImpl.getInstance();


        long l1 = festivalService.add(new Festival("festival","festival1","festival2","festival3"));

        Festival festival = festivalService.getById(l1);

        festival.setColor("12344");

        festivalService.update(festival);

        festival = festivalService.getByName("festival");


        festivalService.remove(l1);

//        HotPointServiceImpl hotPointService = HotPointServiceImpl.getInstance();
//        Festival festival = new Festival("festival","festival1","festival2","festival3");
//        hotPointService.add(new HotPoint("HotPoint1",
//                "HotPoint2",
//                "HotPoint3",
//                "HotPoint4",
//                festival));
    }
}
