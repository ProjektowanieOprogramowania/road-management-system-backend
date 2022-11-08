package pl.edu.pw.infstos.szsdsr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.edu.pw.infstos.szsdsr.charges.core.service.ChargeService;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.PassingChargeService;
import pl.edu.pw.infstos.szsdsr.generated.models.*;
import pl.edu.pw.infstos.szsdsr.localization.services.LocalizationService;
import pl.edu.pw.infstos.szsdsr.passings.services.PassingService;
import pl.edu.pw.infstos.szsdsr.penalties.services.PenaltyService;
import pl.edu.pw.infstos.szsdsr.tariffs.services.TariffService;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;
import pl.edu.pw.infstos.szsdsr.users.service.AppUserService;
import pl.edu.pw.infstos.szsdsr.vehicle.services.VehicleService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(@Autowired AppUserService appUserService,
                                      @Autowired TariffService tariffService,
                                      @Autowired PenaltyService penaltyService,
                                      @Autowired PassingService passingService,
                                      @Autowired LocalizationService localizationService,
                                      @Autowired VehicleService vehicleService,
                                      @Autowired ChargeService chargeService,
                                      @Autowired PassingChargeService passingChargeService) {
        return args -> {
            AppUser user1 = new AppUser("JanKowalski");
            UUID user1Uuid = UUID.fromString("4d312962-5bbf-11ed-9b6a-0242ac120002");
            user1.setUuid(user1Uuid);
            user1 = appUserService.create(user1);

            PassingDTO passing1 = new PassingDTO();
            passing1.setDateTime(LocalDateTime.of(2022, 10, 15, 15, 55, 7));
            LocalizationDTO localization1 = new LocalizationDTO();
            localization1.setLatitude("52°25'18.0\"N");
            localization1.setLongitude("21°13'21.3\"E");
            localization1 = localizationService.addLocalization(localization1);
            passing1.setLocalization(localization1);
            passing1.setPayable(true);
            VehicleDTO vehicle1 = new VehicleDTO();
            vehicle1.setRegistrationNumber("WA6642E");
            vehicle1.setVehicleType("car");
            vehicle1.setMake("Skoda");
            vehicle1 = vehicleService.addVehicle(vehicle1);
            passing1.setVehicle(vehicle1);
            passing1 = passingService.addPassing(passing1);

            ChargeDTO charge1 = new ChargeDTO();
            charge1.setAmount(19.50);
            charge1.setPaid(false);
            charge1 = chargeService.addCharge(charge1);

            PassingChargeDTO passingCharge1 = new PassingChargeDTO();
            passingCharge1.setPassing(passing1);
            passingCharge1.setCharge(charge1);
            passingCharge1 = passingChargeService.addPassingCharge(passingCharge1);

            PenaltyChargeDTO penalty1 = new PenaltyChargeDTO();
            penalty1.setPassing(passing1);
            penalty1.setUserId(user1Uuid);
            penalty1.setCharge(charge1);
            penalty1.setPaid(false);
            penalty1.setDescription("Kara za nieopłacony przejazd");
            penalty1 = penaltyService.addPenalty(penalty1);

            TariffDTO tariff1 = new TariffDTO();
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
            tariff1 = tariffService.addTariff(tariff1);
        };
    }

}
