package bjetpro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credentials {

    private Properties creds;

    public Properties getCreds() {
        return creds;
    }

    public Credentials(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            creds = new Properties();
            creds.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
