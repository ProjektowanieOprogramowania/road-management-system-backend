package pl.edu.pw.infstos.szsdsr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.edu.pw.infstos.szsdsr.appshared.generators.AppUserGenerator;
import pl.edu.pw.infstos.szsdsr.charges.core.service.ChargeService;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.PassingChargeService;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.SubscriptionService;
import pl.edu.pw.infstos.szsdsr.charges.penalties.services.PenaltyService;
import pl.edu.pw.infstos.szsdsr.datacollection.camerastreams.services.CameraStreamService;
import pl.edu.pw.infstos.szsdsr.datacollection.sensors.core.services.SensorService;
import pl.edu.pw.infstos.szsdsr.driving.passings.services.PassingService;
import pl.edu.pw.infstos.szsdsr.driving.vehicle.services.VehicleService;
import pl.edu.pw.infstos.szsdsr.generated.models.*;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.services.AuctionOfferService;
import pl.edu.pw.infstos.szsdsr.infrastructure.auctions.services.AuctionService;
import pl.edu.pw.infstos.szsdsr.localization.services.LocalizationService;
import pl.edu.pw.infstos.szsdsr.roads.services.RoadNodeService;
import pl.edu.pw.infstos.szsdsr.roads.services.RoadSegmentService;
import pl.edu.pw.infstos.szsdsr.roads.services.RoadService;
import pl.edu.pw.infstos.szsdsr.tariffs.services.TariffService;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;
import pl.edu.pw.infstos.szsdsr.users.service.AppUserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class DataGenerator {

    private final AppUserGenerator appUserGenerator;

    public DataGenerator(AppUserGenerator appUserGenerator) {
        this.appUserGenerator = appUserGenerator;
    }

    @Bean
    public CommandLineRunner loadData(@Autowired AppUserService appUserService,
                                      @Autowired TariffService tariffService,
                                      @Autowired PenaltyService penaltyService,
                                      @Autowired PassingService passingService,
                                      @Autowired LocalizationService localizationService,
                                      @Autowired VehicleService vehicleService,
                                      @Autowired ChargeService chargeService,
                                      @Autowired PassingChargeService passingChargeService,
                                      @Autowired RoadService roadService,
                                      @Autowired SubscriptionService subscriptionService,
                                      @Autowired CameraStreamService cameraStreamService,
                                      @Autowired SensorService sensorService,
                                      @Autowired RoadNodeService roadNodeService,
                                      @Autowired RoadSegmentService roadSegmentService,
                                      @Autowired AuctionService auctionService,
                                      @Autowired AuctionOfferService auctionOfferService) {
        return args -> {
            AppUser userWithPassing = new AppUser("JanKowalski");
            UUID userWithPassingUuid = UUID.fromString("4d312962-5bbf-11ed-9b6a-0242ac120002");
            userWithPassing.setUuid(userWithPassingUuid);
            userWithPassing = appUserService.create(userWithPassing);

            AppUser userWithSubscription = new AppUser("Bob");
            UUID userWithSubscriptionUuid = UUID.fromString("cc2cb489-146c-4482-b290-1b4d00220b08");
            userWithSubscription.setUuid(userWithSubscriptionUuid);
            userWithSubscription = appUserService.create(userWithSubscription);

            AppUser userWithAuctionOffer1 = new AppUser("PawelNowak");
            UUID userWithAuctionOfferUuid1 = UUID.fromString("777d2a36-c835-4f09-a7aa-22fd709f4968");
            userWithAuctionOffer1.setUuid(userWithAuctionOfferUuid1);
            userWithAuctionOffer1.setCompanyName("Firma sp. z o.o.");
            userWithAuctionOffer1 = appUserService.create(userWithAuctionOffer1);

            AppUser userWithAuctionOffer2 = new AppUser("krzysztofibisz");
            UUID userWithAuctionOfferUuid2 = UUID.fromString("7049caf9-4803-4e47-9cd0-8cc08e59aa30");
            userWithAuctionOffer2.setUuid(userWithAuctionOfferUuid2);
            userWithAuctionOffer2.setCompanyName("Usługi drogowe sp. j.");
            userWithAuctionOffer2 = appUserService.create(userWithAuctionOffer2);

            PassingDTO passing1 = new PassingDTO();
            passing1.setDateTime(LocalDateTime.of(2022, 10, 15, 15, 55, 7));
            LocalizationDTO localization1 = new LocalizationDTO();
            localization1.setLatitude(52.142154);
            localization1.setLongitude(19.482726);
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
            charge1.setUserId(userWithPassingUuid);
            charge1.setChargeType(String.valueOf(ChargeTypeDTO.PASSING_CHARGE));
            charge1.setDescription("Opłata za przejazd bramką A1");
            charge1.setAmount(19.50);
            charge1.setPaid(false);
            charge1 = chargeService.addCharge(charge1);

            PassingChargeDTO passingCharge1 = new PassingChargeDTO();
            passingCharge1.setPassing(passing1);
            passingCharge1.setCharge(charge1);
            passingCharge1 = passingChargeService.addPassingCharge(passingCharge1);

            PenaltyChargeDTO penalty1 = new PenaltyChargeDTO();
            penalty1.setPassing(passing1);
            ChargeDTO chargeForPenalty1 = new ChargeDTO();
            chargeForPenalty1.setUserId(userWithPassingUuid);
            chargeForPenalty1.setChargeType(String.valueOf(ChargeTypeDTO.PENALTY_CHARGE));
            chargeForPenalty1.setDescription("Opłata karna za nieopłacenie opłaty za przejazd");
            chargeForPenalty1.setAmount(975.00);
            chargeForPenalty1.setPaid(false);
            chargeForPenalty1 = chargeService.addCharge(chargeForPenalty1);
            penalty1.setCharge(chargeForPenalty1);
            penalty1.setPaid(false);
            penalty1.setDescription("Kara za nieopłacony przejazd bramką A1 (50-krotność)");
            penalty1 = penaltyService.addPenalty(penalty1);

            RoadDTO a1 = new RoadDTO();
            a1.setName("A1");
            a1.setSubscriptionPriceForOneDay(4.99);
            a1 = roadService.addRoad(a1);

            RoadDTO a2 = new RoadDTO();
            a2.setName("A2");
            a2.setSubscriptionPriceForOneDay(6.99);
            a2 = roadService.addRoad(a2);

            RoadDTO a4 = new RoadDTO();
            a4.setName("A4");
            a4.setSubscriptionPriceForOneDay(3.65);
            a4 = roadService.addRoad(a4);

            SubscriptionDTO subscription1 = new SubscriptionDTO();
            subscription1.setSubscriberId(userWithSubscriptionUuid);
            subscription1.setSubscriptionFrom(LocalDate.now().minusDays(10));
            subscription1.setSubscriptionTo(LocalDate.now().plusDays(20));
            subscription1.setRoadsIds(List.of(a1.getId(), a2.getId()));
            ChargeDTO chargeForSubscription1 = new ChargeDTO();
            chargeForSubscription1.setUserId(userWithSubscriptionUuid);
            chargeForSubscription1.setChargeType(String.valueOf(ChargeTypeDTO.SUBSCRIPTION_CHARGE));
            chargeForSubscription1.setDescription("Opłata za abonament [A1/A2]");
            chargeForSubscription1.setAmount(49.99);
            chargeForSubscription1.setPaid(false);
            chargeForSubscription1 = chargeService.addCharge(chargeForSubscription1);
            subscription1.setCharge(chargeForSubscription1);
            subscription1 = subscriptionService.addSubscription(subscription1);

            TariffDTO tariff1 = new TariffDTO();
            tariff1.setActive(true);
            tariff1.setName("Taryfikator opłat za przejazd autostradą A2");
            Map<String, Double> prices1 = new HashMap<>();
            prices1.put(VehicleTypeDTO.MOTORCYCLE.getValue(), 0.05);
            prices1.put(VehicleTypeDTO.CAR.getValue(), 0.10);
            prices1.put(VehicleTypeDTO.TRUCK.getValue(), 0.15);
            prices1.put(VehicleTypeDTO.OTHER.getValue(), 0.15);
            tariff1.setPrices(prices1);
            tariff1 = tariffService.addTariff(tariff1);

            CameraStreamDTO cameraStream1 = new CameraStreamDTO();
            cameraStream1.setProtocol(CameraStreamProtocolDTO.HTTP);
            cameraStream1.setUrl("https://y.com.sb/embed/crPl0ITIkS0");
            cameraStream1.setVoivodeship(VoivodeshipDTO.MAZOWIECKIE);
            LocalizationDTO cameraStream1Localization = new LocalizationDTO();
            cameraStream1Localization.setLatitude(52.232222);
            cameraStream1Localization.setLongitude(21.008333);
            cameraStream1Localization = localizationService.addLocalization(cameraStream1Localization);
            cameraStream1.setLocalization(cameraStream1Localization);
            cameraStream1.setName("Warszawa, ul. Marszałkowska");
            cameraStreamService.addCameraStream(cameraStream1);

            SensorDTO sensor1 = new SensorDTO();
            LocalizationDTO sensor1Localization = new LocalizationDTO();
            sensor1Localization.setLatitude(52.232222);
            sensor1Localization.setLongitude(21.008333);
            sensor1Localization = localizationService.addLocalization(sensor1Localization);
            sensor1.setLocalization(sensor1Localization);
            sensor1.setName("Warszawa, ul. Marszałkowska - natężenie ruchu");
            sensor1.setEnabled(true);
            sensor1.setVoivodeship(VoivodeshipDTO.MAZOWIECKIE);
            sensor1.setSensorType(SensorTypeDTO.TRAFFIC);
            sensor1.setSerialNumber("12345678");
            sensorService.addSensor(sensor1);

            RoadNodeDTO swiecko = new RoadNodeDTO();
            swiecko.setName("Świecko");
            LocalizationDTO swieckoLocalization = new LocalizationDTO();
            swieckoLocalization.setLatitude(52.324000);
            swieckoLocalization.setLongitude(14.618806);
            swieckoLocalization = localizationService.addLocalization(swieckoLocalization);
            swiecko.setLocalization(swieckoLocalization);
            swiecko = roadNodeService.addRoadNode(swiecko);

            RoadNodeDTO poznan = new RoadNodeDTO();
            poznan.setName("Poznań");
            LocalizationDTO poznanLocalization = new LocalizationDTO();
            poznanLocalization.setLatitude(52.353861);
            poznanLocalization.setLongitude(16.903778);
            poznanLocalization = localizationService.addLocalization(poznanLocalization);
            poznan.setLocalization(poznanLocalization);
            poznan = roadNodeService.addRoadNode(poznan);

            RoadNodeDTO lodz = new RoadNodeDTO();
            lodz.setName("Łódź");
            LocalizationDTO lodzLocalization = new LocalizationDTO();
            lodzLocalization.setLatitude(51.909750);
            lodzLocalization.setLongitude(19.439806);
            lodzLocalization = localizationService.addLocalization(lodzLocalization);
            lodz.setLocalization(lodzLocalization);
            lodz = roadNodeService.addRoadNode(lodz);

            RoadNodeDTO warszawa = new RoadNodeDTO();
            warszawa.setName("Warszawa");
            LocalizationDTO warszawaLocalization = new LocalizationDTO();
            warszawaLocalization.setLatitude(52.230389);
            warszawaLocalization.setLongitude(21.000194);
            warszawaLocalization = localizationService.addLocalization(warszawaLocalization);
            warszawa.setLocalization(warszawaLocalization);
            warszawa = roadNodeService.addRoadNode(warszawa);

            RoadNodeDTO siedlce = new RoadNodeDTO();
            siedlce.setName("Siedlce");
            LocalizationDTO siedlceLocalization = new LocalizationDTO();
            siedlceLocalization.setLatitude(52.171583);
            siedlceLocalization.setLongitude(22.220722);
            siedlceLocalization = localizationService.addLocalization(siedlceLocalization);
            siedlce.setLocalization(siedlceLocalization);
            siedlce = roadNodeService.addRoadNode(siedlce);

            RoadSegmentDTO swieckoPoznanSegment = new RoadSegmentDTO();
            swieckoPoznanSegment.setStartNode(swiecko);
            swieckoPoznanSegment.setEndNode(poznan);
            swieckoPoznanSegment = roadSegmentService.addRoadSegment(swieckoPoznanSegment);

            RoadSegmentDTO poznanLodzSegment = new RoadSegmentDTO();
            poznanLodzSegment.setStartNode(poznan);
            poznanLodzSegment.setEndNode(lodz);
            poznanLodzSegment.setTariff(tariff1);
            poznanLodzSegment = roadSegmentService.addRoadSegment(poznanLodzSegment);

            RoadSegmentDTO lodzWarszawaSegment = new RoadSegmentDTO();
            lodzWarszawaSegment.setStartNode(lodz);
            lodzWarszawaSegment.setEndNode(warszawa);
            lodzWarszawaSegment = roadSegmentService.addRoadSegment(lodzWarszawaSegment);

            RoadSegmentDTO warszawaSiedlceSegment = new RoadSegmentDTO();
            warszawaSiedlceSegment.setStartNode(warszawa);
            warszawaSiedlceSegment.setEndNode(siedlce);
            warszawaSiedlceSegment = roadSegmentService.addRoadSegment(warszawaSiedlceSegment);

            a2.setSegments(List.of(swieckoPoznanSegment, poznanLodzSegment, lodzWarszawaSegment, warszawaSiedlceSegment));
            roadService.updateRoad(a2);

            AuctionDTO auction1 = new AuctionDTO();
            auction1.setName("Przetarg na prace konserwatorskie obwodnicy Warszawy");
            auction1.setDescription("Opis przetargu na prace konserwatorskie obwodnicy Warszawy.");
            auction1.setLocalization(warszawaLocalization);
            auction1.setNumber(107);
            auction1.setDueDate(createTimestamp(LocalDateTime.of(2022, 12, 30, 23, 59, 59)));
            auction1.setIsOpen(true);
            auction1.setStaringPrice(100000.0);

            auction1 = auctionService.createAuction(auction1);

            AuctionOfferDTO auctionOffer1 = new AuctionOfferDTO();
            auctionOffer1.setAmount(95000.0);
            auctionOffer1.setCurrency("pln");
            auctionOffer1.setUserId(userWithAuctionOfferUuid1);
            auctionOffer1.setCompanyName(userWithAuctionOffer1.getCompanyName());
            auctionOffer1.setAuctionId(auction1.getId());
            auctionOffer1.setScore(95000);
            auctionOffer1 = auctionOfferService.createOffer(auctionOffer1);
        };
    }

    public static long createTimestamp(LocalDateTime ldt) {
        ZoneId zoneId = ZoneId.systemDefault();
        return ldt.atZone(zoneId).toEpochSecond();
    }

}
