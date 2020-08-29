package ir.maktab.model.repository;

import ir.maktab.model.entity.Teacher;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface TeacherRepository extends Repository<Teacher,Integer> {
    Teacher save(Teacher teacher);

}
