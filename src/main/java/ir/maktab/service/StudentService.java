package ir.maktab.service;

import ir.maktab.model.entity.Admin;
import ir.maktab.model.entity.Student;
import ir.maktab.model.entity.User;
import ir.maktab.model.entity.VerificationToken;
import ir.maktab.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Transactional
    public Student save(User user) {
        Student student = new Student(user);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public boolean sendMail(Student student) {
        return userService.sendTo(student);
    }

public List<Student> createStudentListFromUser(List<User> users){
    List<Student> students =new ArrayList<>();
        for (User user:
         users) {
        Student student =new Student(user);
        students.add(student);
    }
        return students;
}

public Student getStudentById(Long id){
      return   studentRepository.findById(id);
}
}
