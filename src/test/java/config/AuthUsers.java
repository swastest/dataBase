package config;


import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/auth.properties")
public interface AuthUsers extends Config {
    String loginAdmin();
    String passAdmin();
    String tokenAdmin();

    String loginClient();
    String passClient();
    String tokenClient();
    String firstNameClient();
    String lastNameClient();
    String cityClient();
    String verificationPhoneClient();


    String loginExecutor();
    String passExecutor();
    String tokenExecutor();
}
