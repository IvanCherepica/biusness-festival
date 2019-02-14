import models.Festival;
import models.HotPoint;
import services.implementation.FestivalServiceImpl;
import services.implementation.HotPointServiceImpl;

public class DbTest {

    public void doTest(){

        Festival fest = FestivalServiceImpl.getInstance().getById(1);
        System.out.println(fest.getName());

        HotPoint hpoint = new HotPoint("имянарусском5555","описание","1","1",fest,"1",1);

        HotPointServiceImpl.getInstance().add(hpoint);

    }
}
