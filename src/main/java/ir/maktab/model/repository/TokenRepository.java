package ir.maktab.model.repository;

import ir.maktab.model.entity.VerificationToken;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface TokenRepository extends Repository<VerificationToken, Long> {

    void save(VerificationToken token);

    VerificationToken findByToken(String token);
}