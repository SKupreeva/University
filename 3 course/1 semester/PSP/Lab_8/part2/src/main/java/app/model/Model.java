package app.model;

import app.entities.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = new Model();

    private List<Result> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(Result result) {
        model.add(result);
    }

    public List<Double> list() {
        return model.stream()
                .map(Result::getRes)
                .collect(Collectors.toList());
    }
}
