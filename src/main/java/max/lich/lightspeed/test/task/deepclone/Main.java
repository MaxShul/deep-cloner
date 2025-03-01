package max.lich.lightspeed.test.task.deepclone;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeepCloner deepCloner = new DeepCloner();
        Person person = new Person("Bob", 30, List.of());
        person.setFavouriteMovies(new String[]{"The matrix", "Dark knight", "Dune. Part 1"});
        System.out.println("The source person: " + person);

        Person personClone = deepCloner.deepClone(person);

        System.out.println("The clone of a person: " + personClone);

    }
}