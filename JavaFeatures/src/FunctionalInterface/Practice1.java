package FunctionalInterface;
@FunctionalInterface
interface Practice1 {

    double calculate(double amount);

    default void printReciept(){
        System.out.println("Payment processed");
    }

    static void info(){
        System.out.println("currency - INR");
    }
}
class Main{
    public static void main(String[] args){

        Practice1 lam= n-> n * 1.10;

        System.out.println(lam.calculate(1000));
        lam.printReciept();
        Practice1.info();
    }
}
