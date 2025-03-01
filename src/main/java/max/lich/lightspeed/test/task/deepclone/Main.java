package max.lich.lightspeed.test.task.deepclone;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeepCloner deepCloner = new DeepCloner();
        Book bible = new Book("The Bible", "The Main book of Christianity", 1500, "Unknown");
        Book forWhomTheBellTolls = new Book(
                "For whom the bell tolls",
                "This is a novel about a young American soldier's experience " +
                        "during the Spanish Civil War and his struggle with duty, love, and death.",
                400, "Ernest Hemingway"
        );
        Book catcherInTheRye = new Book(
                "The catcher in the rye",
                "This is a novel about a disillusioned teenager, Holden Caulfield, " +
                        "who grapples with loneliness and the complexities of adulthood while navigating New York City",
                277, "J.D. Salinger"
        );

        Person person = new Person("Bob", 30,
                new String[]{"The matrix", "Dark knight", "Dune. Part 1"},
                List.of(bible, forWhomTheBellTolls, catcherInTheRye)
        );
        System.out.println("The source person: " + person);

        Person personClone = deepCloner.deepClone(person);

        System.out.println("The clone of a person: " + personClone);

    }
}