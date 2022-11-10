package pl.edu.pw.infstos.szsdsr.appshared.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractGenerator<T> implements Generator<T> {

    protected Random rand = new Random(0);
    protected Boolean isDefaultGenerated = false;
    protected List<T> defaultCache = new ArrayList<>();

    @Override
    public List<T> generateDefault() {
        if (isDefaultGenerated) {
            return defaultCache;
        }
        defaultCache = generateDefaultImpl();
        isDefaultGenerated = true;
        return defaultCache;
    }

    public void setRandom(Random rand) {
        this.rand = rand;
    }

    protected abstract List<T> generateDefaultImpl();

    protected String randomANCIIString(int len) {
        var builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(rand.nextInt('A', 'z'+1));
        }
        return builder.toString();
    }
}
