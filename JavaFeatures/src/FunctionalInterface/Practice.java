package FunctionalInterface;

import java.util.Locale;
import java.util.function.*;

public class Practice {
    public static void main(String[] args){

        Predicate<Integer> prac1=n->n%2==0;
        System.out.println(prac1.test(40));

        Function<String,String> prac2=(len)-> String.valueOf(len.length());
        System.out.println(prac2.apply("sum"));

        Consumer<String> name=nam-> System.out.println(nam.toUpperCase());
        name.accept("Sumanth");

        BiFunction<Integer,Double,Double> totalSalary=
                (n1,n2 )->n1+n2;

        System.out.println(totalSalary.apply(2000,0.18));


        UnaryOperator<String> unary=n->n.toLowerCase().trim();

        System.out.println(unary.apply(" sumanTH "));


        BinaryOperator<Integer  >binaryOperator=(a,b)->{
            if(a>b){
                return a;
            }
            return b;
        };

        System.out.println(binaryOperator.apply(3,5));



    }
}
