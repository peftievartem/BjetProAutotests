package bjetpro.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ParseJson {

    private static final Path resourceDirectory = Paths.get("src", "test", "resources");

    public static <T> Stream<Arguments> getStream(String fileName, Class<T> cl) {

        File file = new File(resourceDirectory.toString() + "/" + fileName);

        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType =
                mapper.getTypeFactory().constructCollectionType(ArrayList.class, cl);

        List<T> userData = new ArrayList<>();
        try {
            userData = mapper.readValue(file, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Arguments> result = new ArrayList<>();
        userData.forEach(element -> result.add(Arguments.of(element)));
        return result.stream();
    }
}
