package presentTracker.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import presentTracker.dto.Present;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class PresentDaoFileImpl implements PresentDao {
    private final ArrayList<Present> presentArrayList;

    public PresentDaoFileImpl() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Present[] presents = mapper.readValue(new File("src/main/resources/presents.json"), Present[].class);
        this.presentArrayList = new ArrayList<>(Arrays.asList(presents));
    }

    @Override
    public Present addPresent(Present present) {
        presentArrayList.add(present);
        return present;
    }

    public Present updatePresent(int presentId, Present present) {
        presentArrayList.set(presentId, present);
        return present;
    }

    @Override
    public List<Present> getAllPresents(){
        return this.presentArrayList;
    }

    @Override
    public Optional<Present> getPresent(int index) {
        return Optional.of(this.presentArrayList.get(index));
    }

    @Override
    public Optional<Present> removePresent(int index) {
        Optional<Present> removedPresent = Optional.of(this.presentArrayList.get(index));
        if (removedPresent.isPresent()) {
            this.presentArrayList.remove(index);
        }
        return removedPresent;
    }


}
