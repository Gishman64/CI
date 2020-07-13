package ru.codeinside.generics.extend;

import ru.codeinside.generics.Describable;

public class Galaxy implements Describable {
    private final String name;

    public Galaxy(String name) {
        this.name = name;
    }

    @Override
    public void describe() {
        System.out.println("This is galaxy of " + name());
    }

    public String name() {
        return this.name;
    }
}
