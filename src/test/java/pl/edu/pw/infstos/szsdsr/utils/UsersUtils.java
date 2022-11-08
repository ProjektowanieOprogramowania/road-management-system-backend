package pl.edu.pw.infstos.szsdsr.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;
import pl.edu.pw.infstos.szsdsr.users.service.AppUserService;

@TestComponent
public class UsersUtils {

    @Autowired
    AppUserService appUserService;

    public void createBob() {
        var bob = new AppUser("bob");
        appUserService.create(bob);
    }

    public void createAlis(AppUserService appUserService) {
        var alice = new AppUser("alice");
        appUserService.create(alice);
    }

}
