package org.der.weg.zum.java.profi.StreamAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestStreamAPI {

    private static class TestPerson{
        private String name;
        private LocalDate date;
        private int age;

        private String city;

        public TestPerson(String name, LocalDate date,String city) {
            this.name = name;
            this.date = date;
            this.age = Period.between(date,LocalDate.now()).getYears();
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public LocalDate getDate() {
            return date;
        }

        public int getAge() {
            return age;
        }

        public String getCity() {
            return city;
        }

        public boolean isAdult(){
            return age >= 18;
        }

        @Override
        public String toString() {
            return "TestPerson{" +
                    "name='" + name + '\'' +
                    ", date=" + date +
                    ", age=" + age +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {


        //Intermediate Steps / Vorbereitungsschritte
        /*
        Zustandslos:
        * filter() -> Predicate<T> alle Elemente, welche die predicate Bedingung erfüllen, bleiben erhalten.
        * map() -> Function<T,R> Transformiert die elemente entsprechend.
        * flatMaps() -> Bildet verschachtelte Streams auf einem Stream ab
        * peek() → eine Aktion wird auf jedem Element ausgeführt.

        Zustands behaftet
        * distinct() -> Entfernt alle doppelten Elemente anhand der equals(Object) Methode
        * sorted() -> Sortiert die Elemente mithilfe der Comparator<T> Schnittstelle
        * limit() -> begrenzt die Anzahl der Elemente des Streams auf den übergebenen Wert. ShortCircuit
        * skip() überspringt die anzahl der Elemente.
        * */

        // Terminal Operations Verarbeitungsereignisse
        /*
        * forEach() -> Eine Aktion wird auf jedem Element ausgeführt.
        * toArray() -> Überträgt die Elemente aus dem Stream in ein Array.
        * collect() -> Überträgt die Stream-Elemente in eine Collection und berechnet, anzahl min, max etc.
        * reduce() -> reduziert die Elemente eines Streams mit entsprechendem Kriterium.
        * min() -> Comparator<T> Ermittelt das kleinste Element des Streams anhand eines Kriteriums.
        * max() -> Comparator<T> Ermittelt das größte Element des Streams anhand eines Kriteriums.
        * count() -> Zählt die Anzahl der Elemente eines Streams.
        * anyMatch() -> Predicate<T>  überprüft, ob mindestens ein Element das entsprechende Kriterium besitzt.
        * allMatch() -> Predicate<T> Überprüft, ob alle Elemente eines Streams dem Predicate entsprechen. ShortCircuit
        * noneMatch() -> Predicate<T> überprüft, ob kein Element dem entsprechenden Kriterium entspricht. ShortCircuit
        * findFirst() -> liefert das Erste zutreffende Element eines Stream.
        * findAny() -> findet das erste beliebige Element zurück, welches auf das Kriterium zutrifft.
        *
        * */

       // testFilter();
       // testMap();
       // testFlatMap();
       // testPeek();
       // testDistinctAndSorted();
       // skipOrLimit();
        testToArray();

    }


    private static final String[] names = {"Paula","Micheal","Thomas","Dieter","Johnson","Angelo","Luis","NietNiet","Carl","James","Roxana","Julia"
    ,"Maraike","Josephine"};

    private static String getRandomName(){
        return names[new Random().nextInt(names.length)];
    }

    private static final String [] cities = {"Berlin","New York","Tokio","Düsseldorf","Hannover","Los Angeles","Sao Paulo","Moskau","Madrid","Lissabon"
            ,"Rom","Leipzig","Hamburg","Munich","Unknown"};

    private static String getRandomCity(){
        return cities[ new Random().nextInt(cities.length)];
    }

    private static LocalDate getRandomDate(){
        int randomYear =  (int) ((Math.random() * (2020 - 1940)) + 1940);
        int randomMonth =  (int) ((Math.random() * (12 - 10)) + 1);
        int randomDay =  (int) ((Math.random() * (28 - 1)) + 1);
        return LocalDate.of(randomYear,randomMonth,randomDay);
    }

    public static List<TestPerson> createTestPersons(int amount){
        final List<TestPerson> personsList = new ArrayList<>();

        for(int i = 0;i < amount;i++){
            personsList.add(new TestPerson(getRandomName(), getRandomDate(), getRandomCity()));
        }
        return personsList;
    }

    public static void testFilter(){
        final Stream<TestPerson> adults = createTestPersons(100).stream().filter(testPerson -> testPerson.getAge() >= 18);
        adults.forEach(System.out::println);


        System.out.println();
        System.out.println("Persons in Tokio:");
        final Stream<TestPerson> adultsInTokio = createTestPersons(200).stream()
                .filter(testPerson -> testPerson.getAge() >= 10)
                .filter(testPerson -> testPerson.getCity().equalsIgnoreCase(cities[2]));

        adultsInTokio.forEach(System.out::println);

    }

    public static Stream<Integer> createIntStream(){
        return Stream.of(7,1,4,3,7,2,6,5,7,9,8);
    }

    public static void testMap(){
        List<TestPerson> persons = createTestPersons(200);
        final Stream<Integer> adultsAgesOverEighteen = persons.stream().map(TestPerson::getAge).filter(age -> age >= 18);
        adultsAgesOverEighteen.forEach(System.out::println);

        System.out.println();

        final Stream<String> adultsNamesOverEighteen = persons.stream()
                .filter(testPerson -> testPerson.getAge() >= 18)
                .filter(testPerson -> testPerson.getAge()<= 30)
                .map(TestPerson::getName);

        adultsNamesOverEighteen.forEach(System.out::println);
    }

    public static void testFlatMap() throws IOException {
        final Path exampleFile = Paths.get("src/main/resources/" + "Test/Example.txt");

        final var contents = Files.readAllLines(exampleFile);

        final Stream<String> words = contents.stream().flatMap(line -> Stream.of(line.split(" ")));

        final Predicate<String> isShortWord = word -> word.length() <= 3;
        final Predicate<String> notIsShortWord = isShortWord.negate();

        final var ignorableWords = Arrays.asList("this","these","them");
        final Predicate<String> isIgnorableWord = word -> ignorableWords.contains(word.toLowerCase());
        final Predicate<String> isSignificantWord = isIgnorableWord.negate();

        final Stream<String> filteredContent = words.map(String::trim)
                .filter(notIsShortWord)
                .filter(isSignificantWord);

        //filteredContent.forEach(it -> System.out.print(it + ", ")); -> Stream has already been operated upon

        final Function<String,String> removePunctuationsMarks = str -> {
            if(str.endsWith(".")|| str.endsWith(";") || str.endsWith("!")){
                return str.substring(0,str.length() -1);
            }
            return str;
        };

        final Stream<String> mapped = filteredContent.map(removePunctuationsMarks);
        final Stream<String> sorted = mapped.sorted(String.CASE_INSENSITIVE_ORDER);

        sorted.forEach(it -> System.out.print(it + ", "));

    }

    public static void testPeek(){
        List<TestPerson> people = createTestPersons(200);
        people.stream().peek(System.out::println)
                .filter(TestPerson::isAdult)
                .peek(System.out::println)
                .map(TestPerson::getName)
                .peek(System.out::println)
                .filter(name -> name.startsWith("Mi"))
                .peek(System.out::println)
                .map(String::toUpperCase);

        System.out.print("Löst die Verarbeitung aus: Protokollierung jeden Schrittes -> Filter 'Mi': ");

        people.forEach(System.out::println);

    }

    public static void testDistinctAndSorted(){
        final Stream<Integer> distinct = createIntStream().distinct();
        final Stream<Integer> sorted = createIntStream().sorted();
        final Stream<Integer> sortedAndDistinct = createIntStream().sorted().distinct();

        System.out.println("distinct: " + distinct.toList());
        System.out.println("sorted: " + sorted.toList());
        System.out.println("sortedAndDistinct: " + sortedAndDistinct.toList());
    }

    public static void skipOrLimit(){
        final Stream<Integer> values1 = Stream.of(1,2,3,4,5,6,7);
        final Stream<Integer> values2 = Stream.of(1,2,3,4,5,6,7);

        System.out.println("skip(3).limit(4)");
        values1.skip(3).limit(4).forEach(num -> System.out.println(num + " "));
        System.out.println("limit(4).skip(3");
        values2.limit(4).skip(3).forEach(num -> System.out.println(num + " "));
    }

    public static void testToArray(){
        final Random random = new Random();
        final Supplier<Float> randomSupplier = () -> random.nextFloat() * 100;

        final Object[] randomNumbers = Stream.generate(randomSupplier).limit(7).toArray();

        System.out.println(Arrays.toString(randomNumbers));

        System.out.println("Element Type: " + randomNumbers[0].getClass());

        final int[] intRandom = Stream.generate(randomSupplier).limit(7).mapToInt(val -> val.intValue()).toArray();
        System.out.println(Arrays.toString(intRandom));
    }


    public static void testFilterMapReduce(){
        final List<TestPerson> people = createTestPersons(200);

    }


}
