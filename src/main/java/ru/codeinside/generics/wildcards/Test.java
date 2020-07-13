package ru.codeinside.generics.wildcards;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Pet());
        animalList.add(new Dog());
        animalList.add(new Cat());

        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("first"));
        pets.add(new Pet("fist"));

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat("pem"));
        catList.add(new Cat("kek"));

        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog("bob"));
        dogList.add(new Dog("noname"));
        //добавляем объектов
        checkAll(animalList);
        checkWithoutCat(animalList);
        checkWithoutCat(dogList);
        //checkWithoutCat(catList);//ошибка
        checkWithoutCat(pets);
        System.out.println("----------------------------------------");
        System.out.println(dogList.getClass()); //type erasure
    }

    public static void checkAll(List<? extends Animal> c) {
        for (Animal a : c) {
            a.whoami(); //Upper-Bounded wildcard
            /*В метод могут передаваться вся иерархия наследования Animal сверху-вниз*/
        }
    }

    public static void checkWithoutCat(List<? super Dog> c) {
        for (Object o : c) {
            ((Animal) o).whoami(); //Lower-Bounded wildcard
            /*В метод может передаваться каждый предок Dog снизу-вверх, тем самым можно отсеять
             * класс Cat, который находится на том же уровне наследования*/
        }
    }
}
