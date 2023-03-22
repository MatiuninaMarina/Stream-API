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

        Long youngPerson = persons.stream().filter(person -> person.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних: " + youngPerson);
        List menSoldiers = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge()>18 && person.getAge() <27)
                .map(p -> p.getFamily()).collect(Collectors.toList());
        System.out.println("Список фамилий призывников: " + menSoldiers);
        List ableToWork = persons.stream()
                .filter(person -> ((person.getSex() == Sex.MAN && person.getAge()>18 && person.getAge() <65) || (person.getSex() == Sex.WOMAN && person.getAge()>18 && person.getAge() <60)) && person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(p -> p.getFamily())).collect(Collectors.toList());
        System.out.println("Cписок потенциально работоспособных людей с высшим образованием: " + ableToWork);

    }
}