package Lambda;

import java.util.function.Function;

public class FinalVariable {
    public static void main(String[] args){

        double taxrate=0.18;

        Function<Integer,Integer> func = (salary)-> (int) (salary*taxrate);

        System.out.println(func.apply(50000));


    }
}
