package interfaces;

import java.io.IOException;
import java.util.List;

public interface IFileRepository<T> {

    void save(List<T> entities) throws IOException;

    List<T> load() throws IOException, ClassNotFoundException;

    boolean exists();

    boolean delete();
}
