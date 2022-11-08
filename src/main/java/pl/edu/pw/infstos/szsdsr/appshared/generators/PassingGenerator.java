package pl.edu.pw.infstos.szsdsr.appshared.generators;

import org.springframework.stereotype.Component;
import pl.edu.pw.infstos.szsdsr.passings.Passing;

import java.util.List;

@Component
public class PassingGenerator extends AbstractGenerator<Passing> {

    @Override
    protected List<Passing> generateDefaultImpl() {
        return null;
    }

    @Override
    public Passing generateRandom() {
        var passing = new Passing();
        return passing;
    }
}
