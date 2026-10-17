package Lambda;

@FunctionalInterface
interface Lambda {
    int taxrate=100;
    int calculate(int a, int b);
}

class Main {
    public static void main(String[] args) {

        Lambda addition = (a, b) -> a + b;
        Lambda subtraction = (a, b) -> a - b;
        Lambda division = (a, b) -> a / b;


        System.out.println(addition.calculate(2, 4));
        System.out.println(subtraction.calculate(4, 6));
        System.out.println(division.calculate(6, 3));
    }
}
