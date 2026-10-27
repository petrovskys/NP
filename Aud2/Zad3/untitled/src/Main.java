import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Function<String,Integer> stringLength = str -> str.length();
        System.out.println("Length of string: " + stringLength.apply("Hello"));
        BiFunction<Integer,Integer,Integer> addition = (a,b) -> a+b;
        System.out.println("Addition of integers 1 and 2: " + addition.apply(1,2));
        Predicate<Integer> iseven = a -> a%2==0;
        System.out.println("The number 1 is even: "+iseven.test(1));
        System.out.println("The number 2 is even: "+iseven.test(2));
        Consumer<String> printer = str -> System.out.println(str);
        printer.accept("Hello");
        printer.accept("World");
        Supplier<Long> time = () -> System.currentTimeMillis();
        printer.accept(time.get().toString());
    }
}