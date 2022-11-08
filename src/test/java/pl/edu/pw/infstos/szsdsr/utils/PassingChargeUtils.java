package pl.edu.pw.infstos.szsdsr.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import pl.edu.pw.infstos.szsdsr.charges.core.domain.Charge;
import pl.edu.pw.infstos.szsdsr.charges.passings.domain.PassingCharge;
import pl.edu.pw.infstos.szsdsr.charges.passings.repository.PassingChargeRepository;
import pl.edu.pw.infstos.szsdsr.charges.passings.service.PassingChargeService;
import pl.edu.pw.infstos.szsdsr.generated.models.PassingChargeDTO;
import pl.edu.pw.infstos.szsdsr.passings.Passing;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@TestComponent
public class PassingChargeUtils {

    @Autowired
    PassingChargeService passingChargeService;
    @Autowired
    PassingChargeRepository passingChargeRepo;
    @Autowired
    ObjectMapper objectMapper;

    public List<PassingChargeDTO> createPassingCharges(AppUser user, int chargeCount) {
        var rand = new Random(0);

        var charges = new ArrayList<PassingChargeDTO>();
        for (int j = 0; j < chargeCount; j++) {
            var passing = new Passing();
            var charge = new Charge(
                    user,
                    BigDecimal.valueOf(rand.nextDouble(150)));
            var passingCharge = new PassingCharge(charge, passing);

            passingCharge = passingChargeRepo.save(passingCharge);
            charges.add(objectMapper.convertValue(passingCharge, PassingChargeDTO.class));
        }

        return charges;
    }

}
