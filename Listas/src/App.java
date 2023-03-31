import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.print("How many employees will be registred? ");
        int n = sc.nextInt();
        
        List<Employee> list = new ArrayList<>();

        for (int i=0; i<n; i++) {

            System.out.println();
            System.out.println("Employee #" + (i + 1) + ":");
            System.out.print("Id: ");
            Integer id = sc.nextInt();
            System.out.print("Name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Salary: ");
            Double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, salary);

            list.add(emp);
        }

        System.out.print("Enter the employee id that will have salary increase: ");
        int idsalary = sc.nextInt();


        // Substituindo a função criada para encontrar o elemento
        Employee emp = list.stream().filter(x -> x.getId() == idsalary).findFirst().orElse(null);
        //Integer pos = position(list, idsalary);
        
        if (emp == null) {
            System.out.print("This id does not exist!");
        
        }
        else {
            System.out.println("Enter the percentege: ");
            double percent = sc.nextDouble();
            emp.increaseSalary(percent);
        }

        System.out.println();
        System.out.println("List of employees: ");
        for (Employee e : list) {
            System.out.println(e);
        }

        sc.close();
    }

    // função auxiliar para procurar elemento na lista
    public static Integer position(List<Employee> list, int id) {
        for (int i=0; i < list.size(); i++) {
            if (list.get(i).getId() == id){
                return i;
            }
        }
        return null; // elemento não encontrado
    }
}
