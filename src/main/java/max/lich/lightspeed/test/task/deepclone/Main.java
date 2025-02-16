package max.lich.lightspeed.test.task.deepclone;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeepCloner deepCloner = new DeepCloner();
        Man man = new Man("Bob", 30, List.of("The Bible", "For whom the bell tolls", "Catcher in the rye"));
        Man manClone = deepCloner.deepClone(man);

        System.out.println(manClone);

    }
}