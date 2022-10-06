package bjetpro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ParseJson<T> {

    private Stream<T> data;

    public ParseJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> userData = new ArrayList<>();
        try {
            userData = mapper.readValue(new File(path), new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.data = userData.stream();
    }

    public Stream<T> ParseJson0(String path) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> userData = new ArrayList<>();
        try {
            userData = mapper.readValue(new File(path), new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData.stream();
    }

    public Stream<employeesFullName> ParseJson1(String path) {
        ObjectMapper mapper = new ObjectMapper();
        List<employeesFullName> userData = new ArrayList<>();
        try {
            userData = mapper.readValue(new File(path), new TypeReference<List<employeesFullName>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData.stream();
    }
    public Stream<T> getData() {
        return data;
    }
}
