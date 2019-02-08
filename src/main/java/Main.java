import models.EventPoint;
import models.Festival;
import models.HotPoint;
import services.EventPoinService;
import services.EventPoinServiceImpl;
import services.FestivalServiceImpl;
import services.HotPointServiceImpl;

public class Main {
    public static void main(String[] args) {
        FestivalServiceImpl festivalService = FestivalServiceImpl.getInstance();


        long l1 = festivalService.add(new Festival("festival","festival1","festival2","festival3"));

        Festival festival = festivalService.getById(l1);

//        festival.setColor("12344");
//
//        festivalService.update(festival);
//
//        festival = festivalService.getByName("festival");


        //festivalService.remove(l1);

//        HotPointServiceImpl hotPointService = HotPointServiceImpl.getInstance();
////        Festival festival = new Festival("festival","festival1","festival2","festival3");
//        l1 = hotPointService.add(new HotPoint("HotPoint1",
//                "HotPoint2",
//                "HotPoint3",
//                "HotPoint4",
//                festival));
//
//        HotPoint hotPoint = hotPointService.getById(l1);
//
//        hotPoint.setDescription("dflaskdf;ashdfsahf");
//
//        hotPointService.update(hotPoint);
//
//
//        hotPoint = hotPointService.getByName("HotPoint1");
//
//        hotPointService.add(new HotPoint("HotPoint222",
//                "asdasd",
//                "dddddd",
//                "black",
//                festival));
//
//        hotPointService.remove(l1);

        EventPoinService eventPoinService = EventPoinServiceImpl.getInstance();

        l1 = eventPoinService.add(new EventPoint("EventPoint name","qweq","qweqe","qweqe", festival));

        EventPoint eventPoint = eventPoinService.getById(l1);

        eventPoint.setDescription("dflaskdf;ashdfsahf");

        eventPoinService.update(eventPoint);


        eventPoint = eventPoinService.getByName("HotPoint1");

        eventPoinService.remove(l1);

    }
}
