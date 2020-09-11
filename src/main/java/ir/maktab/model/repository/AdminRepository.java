package ir.maktab.model.repository;

import ir.maktab.model.entity.Admin;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface AdminRepository  extends Repository<Admin,Long> {

    Admin save(Admin admin);
}
