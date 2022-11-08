package pl.edu.pw.infstos.szsdsr.appshared.generators;

import java.util.List;
import java.util.Random;

public interface Generator<T> {

    List<T> generateDefault();

    T generateRandom();

    void setRandom(Random rand);

}
