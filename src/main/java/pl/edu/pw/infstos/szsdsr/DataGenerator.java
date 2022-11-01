package pl.edu.pw.infstos.szsdsr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.edu.pw.infstos.szsdsr.payments.passing.Passing;
import pl.edu.pw.infstos.szsdsr.payments.passing.PassingService;
import pl.edu.pw.infstos.szsdsr.payments.penalty.Penalty;
import pl.edu.pw.infstos.szsdsr.payments.penalty.PenaltyService;
import pl.edu.pw.infstos.szsdsr.payments.tariff.TariffService;

import java.time.LocalDateTime;

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
        };
    }

}
