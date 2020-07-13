package ru.codeinside.generics.wildcards;

import ru.codeinside.generics.Describable;

public class Dog extends Pet implements Describable {
    public Dog() {
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public void whoami() {
        System.out.println("Я себек. " + super.name());
    }

    @Override
    public void describe() {
        this.whoami();
    }
}
