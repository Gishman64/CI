package ru.codeinside.fifc;

import ru.codeinside.generics.wildcards.Cat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FIfcTest {
    private static List<Cat> cats = new ArrayList<>();

    public static void main(String[] args) {
        cats.add(new Cat("@flow"));
        cats.add(new Cat("@blog"));
        cats.add(new Cat("Dog1"));

        cats.forEach(Cat::describe);

        Function<String, Predicate<Cat>> tag = strTag -> cat -> cat.name().contains(strTag);

        Cat cat = getFirstBy(tag.apply("@")).orElseThrow(IllegalStateException::new);
        cat.describe();
        Function<String, Function<String, Supplier<Cat>>> catBuilder = name -> name2 -> () -> new Cat(name2);

        Cat cat1 = catBuilder.apply("сначала кот звался так").apply("@flow2").get();
        cat1.describe();
    }

    private static Optional<Cat> getFirstBy(Predicate<? super Cat> predicate) {
        return cats
                .stream()
                .filter(predicate)
                .findFirst();
    }
}
