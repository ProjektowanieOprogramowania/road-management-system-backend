package pl.edu.pw.infstos.szsdsr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.edu.pw.infstos.szsdsr.payments.passing.Passing;
import pl.edu.pw.infstos.szsdsr.payments.passing.PassingService;
import pl.edu.pw.infstos.szsdsr.payments.penalty.Penalty;
import pl.edu.pw.infstos.szsdsr.payments.penalty.PenaltyService;
import pl.edu.pw.infstos.szsdsr.payments.tariff.Tariff;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(@Autowired TariffService tariffService,
                                      @Autowired PenaltyService penaltyService,
                                      @Autowired PassingService passingService) {
        return args -> {
            Passing passing1 = new Passing();
            passing1.setDateTime(LocalDateTime.of(2022, 10, 15, 15, 55, 7));
            passing1.setLocalization("52°25'18.0\"N 21°13'21.3\"E");
            passing1.setPayable(true);
            passing1.setVehicle("WA6642E");
            passingService.addPassing(passing1);

            Penalty penalty1 = new Penalty();
            penalty1.setPassing(passing1);
            penalty1.setUserId(123L);
            penalty1.setPaid(true);
            penalty1.setDescription("Kara za nieopłacony przejazd");
            penaltyService.addPenalty(penalty1);

            Tariff tariff1 = new Tariff();
            tariff1.setActive(true);
            tariff1.setName("Taryfikator opłat za przejazd autostradami");
            Map<String, Double> prices1 = new HashMap<>();
            prices1.put("A1/A2, Motocykle", 0.05);
            prices1.put("A1/A2, Osobowe", 0.10);
            prices1.put("A1/A2, Ciężarowe", 0.15);
            prices1.put("A4, Motocykle", 0.07);
            prices1.put("A4, Osobowe", 0.12);
            prices1.put("A4, Ciężarowe", 0.17);
            tariff1.setPrices(prices1);
            tariffService.addTariff(tariff1);
        };
    }

}
