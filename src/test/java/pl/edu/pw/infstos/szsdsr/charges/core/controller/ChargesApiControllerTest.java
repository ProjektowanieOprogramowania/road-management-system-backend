package pl.edu.pw.infstos.szsdsr.charges.core.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.edu.pw.infstos.szsdsr.generated.api.ChargesApi;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ChargesApiControllerTest {

    @Autowired
    ChargesApi chargesApi;


    @Test
    public void testIfReturnsCharge() {

    }

}