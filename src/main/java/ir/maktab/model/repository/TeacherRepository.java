package ir.maktab.model.repository;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Teacher;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface TeacherRepository extends Repository<Teacher,Integer> {

    Teacher save(Teacher teacher);

    List<Course> findByEmail(String email);

}
