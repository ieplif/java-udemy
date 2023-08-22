// SEM INTERFACE

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Formatação para a data
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");

        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno (dd/MM/yyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        // Instanciando CarRental
        CarRental cr = new CarRental(start, finish, new Vehicle(carModel)); //carModel é uma string quando digitado, tem q ser instanciado em Vehicle

        System.out.print("Entre com o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Entre com o preço por dia: ");
        double pricePerDay = sc.nextDouble();

        // Instanciando o RentalService

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService()); // informa somente aqui, faz o upcasting de TaxService
                                                                                                            // Injeção de dependência por meio de construtor

        rentalService.processInvoice(cr);

        System.out.println("FATURA: ");
        System.out.println("Pagamento básico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " + String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));



        sc.close();
    }
}
