package ru.codeinside.generics.extend;

import ru.codeinside.generics.wildcards.Animal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Zoo<T extends Animal> {
    private final List<T> animalsInZoo = new ArrayList<>();

    public Zoo() {
    }

    public Zoo(Collection<T> startPets) {
        addAllAnimals(startPets);
    }

    public void addAnimal(T t) {
        animalsInZoo.add(t);
    }

    public boolean addAllAnimals(Collection<T> c) {
        return this.animalsInZoo.addAll(c);
    }

    public void printAllAnimals() {
        for (T t : animalsInZoo) {
            t.describe();
        }
    }
}
