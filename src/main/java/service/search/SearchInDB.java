package service.search;

import java.io.IOException;

public interface SearchInDB {
    public void search(Integer column, String str) throws IOException;
}
