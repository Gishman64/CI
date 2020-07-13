package ru.codeinside.generics.extend;

import ru.codeinside.generics.wildcards.Animal;
import ru.codeinside.generics.wildcards.Cat;
import ru.codeinside.generics.wildcards.Dog;

import java.util.ArrayList;
import java.util.List;

public class ExtendsTest {
    public static void main(String[] args) {
        Zoo<Animal> standardZoo = new Zoo<>();
        standardZoo.printAllAnimals();
        List<Animal> animals = new ArrayList<>();
        animals.add(new Cat("cat1"));
        animals.add(new Dog("dog2"));

        standardZoo.addAllAnimals(animals);
        standardZoo.printAllAnimals();

        Galaxy galaxy = new Galaxy("Alpha Centaur");
        ZooInSpace<Animal, Galaxy> alphaCentaurZoo = new ZooInSpace<>(galaxy, animals);
        alphaCentaurZoo.describe();
    }
}
