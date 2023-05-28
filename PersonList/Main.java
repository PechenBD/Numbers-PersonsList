import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long underAgeCount = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(underAgeCount);

        List<String> recruits = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() < 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println(recruits);

        List<String> workers = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> {
                    if (x.getSex() == Sex.WOMAN) {
                        return x.getAge() < 60;
                    } else {
                        return x.getAge() < 65;
                    }
                })
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println(workers);
    }
}