package pl.edu.pw.infstos.szsdsr.appshared.generators;

import org.springframework.stereotype.Component;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;
import pl.edu.pw.infstos.szsdsr.users.service.AppUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AppUserGenerator extends AbstractGenerator<AppUser> {
    private final AppUserService appUserService;

    public AppUserGenerator(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    protected List<AppUser> generateDefaultImpl() {
        return List.of(createBob(), createAlis());
    }

    @Override
    public AppUser generateRandom() {
        return new AppUser(randomANCIIString(rand.nextInt(10)));
    }

    public AppUser createBob() {
        var bob = new AppUser("bob");
        return appUserService.create(bob);
    }

    public AppUser createAlis() {
        var alice = new AppUser("alice");
        return appUserService.create(alice);
    }

}
