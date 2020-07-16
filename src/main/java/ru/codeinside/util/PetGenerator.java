package ru.codeinside.util;

import ru.codeinside.generics.wildcards.Cat;
import ru.codeinside.generics.wildcards.Dog;
import ru.codeinside.generics.wildcards.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetGenerator {
    private static Random r = new Random(15431);

    public static Pet generate() {
        var val = r.nextInt();
        Pet pet;
        if (val % 2 == 0) {
            pet = new Dog("dog" + val);
        } else {
            pet = new Cat("cat" + val);
        }
        return pet;
    }

    public static List<Pet> generateN(int number) {
        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            pets.add(generate());
        }
        return pets;
    }
}
