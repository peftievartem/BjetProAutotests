package bjetpro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credentials extends Properties {

    public Credentials(String fileName) throws IOException{
        try (FileInputStream fis = new FileInputStream(fileName)) {
            this.load(fis);
        }

    }
}
