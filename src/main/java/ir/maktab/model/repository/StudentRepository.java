package ir.maktab.model.repository;

import ir.maktab.model.entity.Student;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface StudentRepository extends Repository<Student,Integer> {

    Student save(Student student);
}