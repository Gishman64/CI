package ru.codeinside.generics.wildcards;

import lombok.Builder;
import ru.codeinside.generics.Describable;

@Builder
public class Cat extends Pet implements Describable {

    public Cat() {
    }

    public Cat(String name) {
        super(name);
    }

    @Override
    public void whoami() {
        System.out.println("Я катц. " + super.name());
    }

    @Override
    public void describe() {
        this.whoami();
    }
}
