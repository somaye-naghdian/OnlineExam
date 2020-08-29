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

//    public boolean checkMail(String email) {
//        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    public boolean checkPassword(String password) {
//        String regex = "\\\\A(?=.*[A-Z])(?=.*\\\\d)[a-zA-Z0-9]{8,16}\\\\z";
//        //"(?=.*\\d)(?=.*[a-z]).{8,}"
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(password);
//        return matcher.matches();
//    }

}
