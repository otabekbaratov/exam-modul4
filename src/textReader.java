import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class textReader {
    public static final Logger log =Logger.getLogger("textReader.class.getname");

    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler("access.txt");
            fileHandler.setLevel(Level.ALL);



        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }
}
