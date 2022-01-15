package exercise01.application;

import exercise01.entities.Department;
import exercise01.entities.HourContract;
import exercise01.entities.Worker;
import exercise01.entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Ler os dados de um trabalhador com N contratos (N fornecido pelo usuário). Depois, solicitar
 * do usuário um mês e mostrar qual foi o salário do funcionário nesse mês.
 */
public class Application {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdtf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Nome do Departamento: ");
        String department = sc.nextLine();
        System.out.println("Dados do Trabalhador:");
        System.out.print("Nome: ");
        String name = sc.nextLine();
        System.out.print("Level: ");
        String level = sc.next();
        System.out.print("Salário Base: ");
        double baseSalary = sc.nextDouble();
        Worker worker = new Worker(name, WorkerLevel.valueOf(level), baseSalary, new Department(department));

        System.out.print("Quantos contratos serão para esse trabalhador? ");
        int n = sc.nextInt();

        for (int i = 1 ; i <= n; i++ ){
            System.out.println("Contrato #" + i);
            System.out.print("Data (DD/MM/YYYY): ");
            Date dateContract = sdtf.parse(sc.next());
            System.out.print("Valor por hora: ");
            double valuePerHour = sc.nextDouble();
            System.out.print("Duração (Horas): ");
            int hours = sc.nextInt();
            worker.addContract(new HourContract(dateContract, valuePerHour, hours));
        }

        System.out.print("\nMês e ano para calcular o salário (MM/AAAA): ");
        String dateYear = sc.next();
        Integer month = Integer.parseInt(dateYear.substring(0,2));
        Integer year = Integer.parseInt(dateYear.substring(3));

        System.out.println("Nome: " + worker.getName());
        System.out.println("Departamento: " + worker.getDepartment().getName());
        System.out.println("Salário em " + dateYear + ": R$ " + String.format("%.2f", worker.income(year, month)));

        sc.close();

    }
}
