// Serviço para calcular a Fatura (Invoice)

import java.time.Duration;

public class RentalService {
    
    private Double pricePerHour;
    private Double pricePerDay;

    // Associação com BrazilTaxService
    //private BrazilTaxService taxService;

    // Com a interface, cria dependência com TaxService
    private TaxService taxService;

    public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
        // Caso fosse instanciado aqui o this.taxService = new BrazilTaxService() e retirado o TaxService do parâmetro, teríamos um forte acoplamento
    }

    // Gerar fatura para oobjeo CarRental

    public void processInvoice(CarRental carRental) {

        // Calculo diferença

        double minutes = Duration.between(carRental.getStart(),carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;

        if (hours <= 12) {
            basicPayment = pricePerHour * Math.ceil(hours);  // função ceil para arredodar para cima.
        }
        else {
            basicPayment = pricePerDay * Math.ceil(hours / 24.0);
        }

        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
