package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ListName {
    public static void main(String[] args){

        List<String> names=new ArrayList<>();

        Consumer<String> lambo=name->names.add(name);
        lambo.accept("sum");
        System.out.println(names);


    }
}
