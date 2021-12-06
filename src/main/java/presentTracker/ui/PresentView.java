package presentTracker.ui;

import presentTracker.dto.Present;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PresentView {

    private UserIo io;

    public PresentView(UserIo io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Presents");
        io.print("2. Create New Presents");
        io.print("3. Edit a Present");
        io.print("4. View a Present");
        io.print("5. Remove a Present");
        io.print("6. Search for a Present");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public Present getNewPresentInfo() {
        String presentId = io.readString("Please enter Present ID");
        String name = io.readString("Please enter the present name");
        String description = io.readString("Please enter the present description");
        int price = io.readInt("Please enter the price");
        Present present = new Present();
        present.setPresentId(presentId);
        present.setName(name);
        present.setDescription(description);
        present.setPrice(price);

        return present;
    }

    public void displayCreatePresentBanner() {
        io.print("=== Create Present ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Present successfully created.  Please hit enter to continue");
    }

    public void displayPresentList(List<Present> presentList) {
        for (Present present : presentList) {
            String presentInfo = String.format("%s %s %s %s ",
                    present.getPresentId(),
                    present.getName(),
                    present.getDescription(),
                    present.getPrice());
            io.print(presentInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Presents ===");
    }

    public void displayDisplayPresentBanner() {
        io.print("=== Display Present ===");
    }

    public int getPresentIndex() {
        return io.readInt("please enter present index:(Tip: The list starts from 0. e.g if theres 4 values in the list, the 1st index will be 0 and the 4th will be 3)");
    }

    public void displayPresent(Optional<Present> present) {
        if (present.isPresent()) {
            io.print(present.get().getPresentId());
            io.print(present.get().getName());
            io.print(present.get().getDescription());
            io.print(String.valueOf(present.get().getPrice()));
            io.print("");
        } else {
            io.print("No such present.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemovePresentBanner() {
        io.print("=== Remove Present ===");
    }

    public void displayRemoveResult(Optional<Present> presentRecord) {
        if (presentRecord.isPresent()) {
            io.print("Present successfully removed.");
        } else {
            io.print("No such Present.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public Optional<Present> editPresentInfo(Optional<Present> present) {
        String presentId = io.readString("Please enter Present Id. (Current details are as follows: " + present.get().getPresentId() + ").");
        String name = io.readString("Please enter the  Present name. (Current details are as follows: " + present.get().getName() + ").");
        String description = io.readString("Please enter the present description. (Current details are as follows: " + present.get().getDescription() + ").");
        int price = io.readInt("Please enter the price of the present. The current details are as follows: " + present.get().getPrice());
        present.get().setPresentId(presentId);
        present.get().setName(name);
        present.get().setDescription(description);
        present.get().setPrice(price);

        return present;

    }

    public void displayEditBanner() {
        io.print("=== Edit Presents ===");
    }

    public void displayEditPresentBanner() {
        io.print("=== Present successfully edited ===");
    }

    public void displaySearchBanner() {
        io.print("=== Search Presents ===");
    }

    public void searchPresent(List<Present> presentList) {
        UserIOConsoleImpl io = new UserIOConsoleImpl();
        String searchPresent = io.readString("Please search here: ");
        List<Present> sorted = presentList.stream()
                .sorted(Comparator.comparing(Present::getName))
                .collect(Collectors.toList());

        for (Present currentPresent : sorted) {
            if (searchPresent.equalsIgnoreCase(currentPresent.getName())) {
                String presentInfo = String.format("%s %s %s %s ",
                        currentPresent.getPresentId(),
                        currentPresent.getName(),
                        currentPresent.getDescription(),
                        currentPresent.getPrice());

                io.print(presentInfo);
//            } else{
//                io.print("No search results found");
//            }
            }
        }
    }
}


