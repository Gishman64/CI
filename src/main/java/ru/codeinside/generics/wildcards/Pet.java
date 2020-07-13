package ru.codeinside.generics.wildcards;

public class Pet implements Animal {

    private String name = "noname";

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }

    @Override
    public void whoami() {
        System.out.println("Просто пет." + this.name());
    }

    public void rename(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    @Override
    public void describe() {
        whoami();
    }
}
