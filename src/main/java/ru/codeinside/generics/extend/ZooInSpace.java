package ru.codeinside.generics.extend;

import ru.codeinside.generics.Describable;
import ru.codeinside.generics.wildcards.Animal;

import java.util.List;

public class ZooInSpace<T extends Animal & Describable, G extends Describable> extends Zoo<T> implements Describable {
    private final G galaxy;

    public ZooInSpace(G galaxy, List<T> startPets) {
        super(startPets);
        this.galaxy = galaxy;
    }

    @Override
    public void describe() {
        super.printAllAnimals();
        galaxy.describe();
    }
}
