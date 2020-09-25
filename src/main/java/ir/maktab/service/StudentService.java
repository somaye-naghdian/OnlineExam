package ir.maktab.service;

import ir.maktab.model.entity.*;
import ir.maktab.model.repository.ExamRepository;
import ir.maktab.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private MultipleChoiceQuestionService mCQuestionService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ExamRepository examRepository;

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Transactional
    public Student addNewUser(User user) throws Exception {
        if (userService.findUserByEmail(user.getEmail()) != null) {
            throw new Exception("A user has already registered with this email");
        }
        Student student = new Student(user);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public boolean sendMail(Student student) {
        return userService.sendTo(student);
    }

    public List<Student> createStudentListFromUser(List<User> users) {
        List<Student> students = new ArrayList<>();
        for (User user :
                users) {
            Student student = new Student(user);
            students.add(student);
        }
        return students;
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Map<Exam, Double> getStudentScoreEachExam(Long id) throws Exception {
        Student student = getStudentById(id);
        if (student.getScores().isEmpty()) {
            throw new Exception("not found ");
        } else {
            return student.getScores();
        }
    }
    @Transactional
    public void save(Student student){
        studentRepository.save(student);
    }
    public Map<Student, Double> getStudentScores(List<Student> students, Long id) {
        Map<Student, Double> studentScoreMap = null;
        for (Student student :
                students) {
            Map<Exam, Double> scores = student.getScores();
            for (Exam exam :
                    scores.keySet()) {
                if (exam.getId().equals(id)) {
                    studentScoreMap.put(student, student.getScores().get(exam));
                }
            }
        }
        return studentScoreMap;
    }
}
