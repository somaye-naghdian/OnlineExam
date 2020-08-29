package ir.maktab.service;

import ir.maktab.model.repository.TokenRepository;
import ir.maktab.model.entity.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

@Service
public class VerificationTokenService {

    TokenRepository tokenRepository;

    @Autowired
    public VerificationTokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Transactional
    public void save(VerificationToken verificationToken) {
        tokenRepository.save(verificationToken);

    }

    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
