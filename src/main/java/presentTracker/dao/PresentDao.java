package presentTracker.dao;

import presentTracker.dto.Present;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PresentDao {
    Present addPresent(Present present)
            throws PresentDaoException;

    Present updatePresent(int index, Present present)
            throws PresentDaoException;

    List<Present> getAllPresents()
            throws PresentDaoException, IOException;


    Optional<Present> getPresent(int index)
            throws PresentDaoException;


    Optional<Present> removePresent(int index)
            throws PresentDaoException;
}

