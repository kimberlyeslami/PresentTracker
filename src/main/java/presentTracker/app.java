package presentTracker;

import presentTracker.controller.PresentController;
import presentTracker.dao.PresentDao;
import presentTracker.dao.PresentDaoFileImpl;
import presentTracker.ui.PresentView;
import presentTracker.ui.UserIOConsoleImpl;
import presentTracker.ui.UserIo;

import java.io.IOException;

public class app {
    public static void main(String[] args) throws IOException {
        UserIo myIo = new UserIOConsoleImpl();
        PresentView myView = new PresentView(myIo);
        PresentDao myDao = new PresentDaoFileImpl();
       PresentController controller =
                new PresentController(myDao, myView);
        controller.run();
    }
}
