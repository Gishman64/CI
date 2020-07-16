package ru.codeinside.streams;

import ru.codeinside.generics.wildcards.Dog;
import ru.codeinside.generics.wildcards.Pet;
import ru.codeinside.util.PetGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamsSample {

    public static void main(String[] args) {
        final List<Pet> pets = PetGenerator.generateN(20);

        Stream<Pet> petStream = StreamSupport.stream(pets.spliterator(), false); // вариант декларации

        pets.stream() //вариант декларации
                .filter(pet -> pet instanceof Dog)
                .forEach(Pet::describe); //Consumer, терминальный метод, ничего не возвращает ( "-" позволяет мутации)

        petStream
                .filter(pet -> pet.name().contains("-"))
                /**
                 * Вариант коллектора, при котором нужно указать Suppler (поставщик) контейнера, здесь он создает List,
                 * И 2 BiConsumer для добавления объекта и объединения контейнеров
                 * */
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        pets.stream()
                .filter(pet -> !pet.name().contains("-"))
                /**
                 * Вариант с фабричным коллектором
                 */
                .collect(Collectors.toSet());

        /**
         * Stream предлагает возможности параллелизма из "коробки", для этого он использует Spliterator коллекции
         * на которой базируется. При параллельном режием Stream вызывает trySplit() и пробует разделиться если возможно.
         * Использую большое количество элементов коллекции, чтобы время выполнения явно различалось
         */
        var pets1 = PetGenerator.generateN(1_000_000);
        var petList = PetGenerator.generateN(1_000_000);
        Consumer<Pet> simpleAction = pet -> {
            //Для загрузки процессора
            StringBuilder sb = new StringBuilder(pet.name());
            sb.replace(3, sb.length(), String.valueOf(sb.toString().hashCode()));
        };

        Profiler<Runnable, String> runnerProfiler = (runner, str) -> {
            long start = System.currentTimeMillis();
            runner.run();
            long end = System.currentTimeMillis();
            System.out.print(str);
            printStatistics(start, end);
        };

        runnerProfiler.profile(() -> pets.stream().parallel().forEach(simpleAction), "parallel ");
        runnerProfiler.profile(() -> petList.stream().forEach(simpleAction), "sequential ");
    }

    /**
     * Печатает время выполненния метода
     * @param start выполнения
     * @param end выполнения
     */
    private static void printStatistics(long start, long end) {
        long delta = end - start;
        System.out.println(String.format("Заняло %d ms", delta));
    }

    /**
     * @param <R> блок кода, передаваемый в виде Runnable
     * @param <C> комментарий для профилирования (на всякий случай)
     */
    interface Profiler<R extends Runnable, C> {
        void profile(R runnable, C comment);
    }
}