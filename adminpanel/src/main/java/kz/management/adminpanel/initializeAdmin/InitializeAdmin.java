//package kz.management.adminpanel.initializeAdmin;
//
//import kz.management.adminpanel.model.enums.Role;
//import kz.management.adminpanel.repo.UserRepository;
//import kz.management.adminpanel.security.AuthenticationService;
//import kz.management.adminpanel.security.RegisterRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InitializeAdmin implements CommandLineRunner {
//
//   private AuthenticationService authenticationService;
//
//   @Autowired
//    public InitializeAdmin(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }
//
//    @Override
//    public void run(String... args) {
//        RegisterRequest admin = new RegisterRequest();
//        admin.setFirstName("John");
//        admin.setLastName("Titor");
//        admin.setEmail("titor@gmail.com");
//        admin.setPassword("admin");
//        admin.setRole(Role.ADMIN);
//       authenticationService.register(admin);
//    }
//}
