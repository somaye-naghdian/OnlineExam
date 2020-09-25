package ir.maktab.service;

import ir.maktab.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthenticationService {

    public boolean isUserInformationTrue(User user) {
        return checkNameAndFamily(user.getName(), user.getFamily()) ;
//                &&
//                checkMail(user.getEmail()) &&
//                checkPassword(user.getPassword());
    }

    public boolean checkNameAndFamily(String name, String family) {
        if (name.length() > 20 && family.length() > 20) {
            return false;
        }
        return true;
    }

}
