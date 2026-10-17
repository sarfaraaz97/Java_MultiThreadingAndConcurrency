package Lambda;

public class EmployeeService {

    private String name="Employee";

    void Execute(){
        Runnable task=()->{
            System.out.println(this.name);
        };

        task.run();
    }
    public  static void main(String[] args){
        EmployeeService obj=new EmployeeService();
        obj.Execute();
    }
}

