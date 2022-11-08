package pl.edu.pw.infstos.szsdsr.users.service;

import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.users.domain.AppUser;
import pl.edu.pw.infstos.szsdsr.users.repository.AppUserRepository;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepo;

    public AppUserService(AppUserRepository appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    //todo change types to dto
    public AppUser create(AppUser appUser) {
        return appUserRepo.save(appUser);
    }

}
