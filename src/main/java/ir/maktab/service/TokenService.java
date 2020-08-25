package ir.maktab.service;

import ir.maktab.model.repository.TokenRepository;
import ir.maktab.model.entity.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void save(VerificationToken verificationToken) {
        tokenRepository.save(verificationToken);
    }

    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
