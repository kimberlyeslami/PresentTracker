package presentTracker.controller;

import presentTracker.dao.PresentDao;
import presentTracker.dao.PresentDaoException;
import presentTracker.dto.Present;
import presentTracker.ui.PresentView;
import presentTracker.ui.UserIOConsoleImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PresentController {
    private PresentView view;
    private PresentDao dao;

    public PresentController(PresentDao dao, PresentView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listPresents();
                        break;
                    case 2:
                        createPresent();
                        break;
                    case 3:
                        editPresent();
                        break;
                    case 4:
                        viewPresent();
                        break;
                    case 5:
                        removePresent();
                        break;
                    case 6:
                        searchPresent();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (IOException | PresentDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createPresent() throws PresentDaoException  {
        view.displayCreatePresentBanner();
        Present newPresent = view.getNewPresentInfo();
        dao.addPresent(newPresent);
        view.displayCreateSuccessBanner();
    }

    private void listPresents() throws PresentDaoException, IOException {
        view.displayDisplayAllBanner();
        List<Present> presentList = dao.getAllPresents();
        System.out.println(presentList.size());
        view.displayPresentList(presentList);
    }

    private void viewPresent() throws PresentDaoException {
        view.displayDisplayPresentBanner();
        int planeIndex = view.getPresentIndex();
        Optional<Present> present = dao.getPresent(planeIndex);
        view.displayPresent(present);
    }

    private void removePresent() throws PresentDaoException {
        view.displayRemovePresentBanner();
        int presentIndex = view.getPresentIndex();
        Optional<Present> removePresent = dao.removePresent(presentIndex);
        view.displayRemoveResult(removePresent);
    }

    private void editPresent() throws PresentDaoException  {
        view.displayEditBanner();
        int planeIndex = view.getPresentIndex();
        Optional<Present> present = dao.getPresent(planeIndex);

        if (present.isPresent()){
            Optional<Present> editFlights = view.editPresentInfo(present);
            dao.updatePresent(planeIndex, editFlights.get());
            view.displayEditPresentBanner();

        }else {
            view.displayErrorMessage("Present does not exist");
        }
    }


    private void searchPresent() throws PresentDaoException, IOException {
        view.displaySearchBanner();
        List<Present> presentList = dao.getAllPresents();
        view.searchPresent(presentList);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
