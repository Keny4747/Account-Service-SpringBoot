package account.repository;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class BreachedPasswordsRepository {
    List<String> passwords = Arrays.asList(
            "PasswordForJanuary", "PasswordForFebruary", "PasswordForMarch", "PasswordForApril",
            "PasswordForMay", "PasswordForJune", "PasswordForJuly", "PasswordForAugust",
            "PasswordForSeptember", "PasswordForOctober", "PasswordForNovember", "PasswordForDecember");


    public List<String> getAllPasswords(){
        return this.passwords;
    }

}
