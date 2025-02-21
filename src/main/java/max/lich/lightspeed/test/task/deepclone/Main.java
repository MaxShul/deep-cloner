package max.lich.lightspeed.test.task.deepclone;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeepCloner deepCloner = new DeepCloner();
        Person person = new Person("Bob", 30, List.of("The Bible", "For whom the bell tolls", "Catcher in the rye"));
        Person personClone = deepCloner.deepClone(person);

        System.out.println("The clone of a person: " + personClone);

    }
}