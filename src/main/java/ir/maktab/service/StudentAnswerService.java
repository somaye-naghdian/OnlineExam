package ir.maktab.service;

import ir.maktab.model.entity.*;
import ir.maktab.model.repository.StudentAnswerRepository;
import ir.maktab.util.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StudentAnswerService {

    private static Logger logger = LogManager.getLogger(StudentAnswerService.class);
    @Autowired
    private StudentAnswerRepository studentAnswerRepository;
    @Autowired
    private ExamService examService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private Mapper mapper;

    @Transactional
    public StudentAnswer save(StudentAnswer studentAnswer) {
        return studentAnswerRepository.save(studentAnswer);
    }

    public StudentAnswer getStudentAnswer(Long id) {
        return studentAnswerRepository.findById(id);
    }

    public List<StudentAnswer> getAllStudentAnswer() {
        return studentAnswerRepository.findAll();
    }

    public String getStudentAnswerByQuestion(Question question, Long examId, Student student) {
        List<StudentAnswer> allStudentAnswer = studentAnswerRepository.getStudentAnswerByStudentId(student.getId());
        Exam exam = examService.getExamById(examId);
        for (StudentAnswer studentAnswer :
                allStudentAnswer) {
            if (studentAnswer.getExam().equals(exam) && studentAnswer.getQuestion().equals(question)
                    && studentAnswer.getStudent().equals(student)) {
                return studentAnswer.getAnswer();
            }
        }
        return null;
    }


    @Transactional
    public Map<Exam, Double> studentExamResult(Long studentId, String courseTitle) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.findCourseByTitle(courseTitle);
        List<Exam> exams = course.getExamList();
        Map<Exam, Double> examScore = new HashMap<>();
        logger.info("student get scores");
        for (Exam studentExam :
                exams) {
            List<StudentAnswer> studentAnswers = studentAnswerRepository.getStudentAnswerByExamAndStudent(studentExam, student);
            examScore.put(studentExam, calculateEachStudentAnswer(studentAnswers));
            student.getScores().put(studentExam, calculateEachStudentAnswer(studentAnswers));
        }
        studentService.save(student);
        return examScore;
    }

    public Map<String, Double> getStudentDescriptiveAnswer(Long studentId, Long examId) {
        List<StudentAnswer> studentAnswers = studentAnswerRepository.getStudentAnswers(studentId, examId);
        Exam exam = examService.getExamById(examId);
        Map<Question, Double> scoreEachQuestion = exam.getScoreEachQuestion();
        List<String> answers = new ArrayList<>();
        Map<String, Double> answerScore = new HashMap<>();
        for (StudentAnswer studentAnswer :
                studentAnswers) {
            if (studentAnswer.getQuestion() instanceof DescriptiveQuestion) {
                answers.add(studentAnswer.getAnswer());
                Question question = studentAnswer.getQuestion();
                Double score = scoreEachQuestion.get(question.getId());
                answerScore.put(studentAnswer.getAnswer(), score);
            }
        }
        return answerScore;
    }

    @Transactional
    public boolean addDescriptiveScore(Long studentId, Long examId, Double score) throws Exception {
        List<StudentAnswer> studentAnswers = studentAnswerRepository.getStudentAnswers(studentId, examId);
        Exam exam = examService.getExamById(examId);
        Map<Question, Double> scoreEachQuestion = exam.getScoreEachQuestion();

        for (StudentAnswer studentAnswer :
                studentAnswers) {
            Question question = studentAnswer.getQuestion();
            if (question instanceof DescriptiveQuestion) {
                if ((studentAnswer.getScore() > 0)) {
                    throw new Exception("this question already have score");
                } else {
                    if (score > scoreEachQuestion.get(question)) {
                        throw new Exception("this score must less or equals intended score");
                    }
                }
                studentAnswer.setScore(score);
                studentAnswerRepository.save(studentAnswer);
                return true;
            }
        }
        return true;
    }

    @Transactional
    public Map<Student, Double> getStudentResultForTeacher(String courseTitle) {
        Course course = courseService.findCourseByTitle(courseTitle);
        List<User> userList = course.getUserList();
        List<Exam> examList = course.getExamList();
        List<Student> studentList = mapper.getStudentList(userList);
        Map<Student, Double> studentScore = new HashMap<>();

        for (Student student :
                studentList) {
            List<StudentAnswer> studentAnswerList = getStudentAnswerList(student);
            Double result = calculateEachStudentAnswer(studentAnswerList);
            studentScore.put(student, result);
        }
        return studentScore;
    }

    public StudentAnswer isAnsweredQuestion(Question question) {
        if (studentAnswerRepository.findByQuestion(question) != null) {
            return studentAnswerRepository.findByQuestion(question);
        }
        return null;
    }

    @Transactional
    public void changeAnswer(Long id, String inputAnswer) {
        StudentAnswer studentAnswer = getStudentAnswer(id);
        String answer = "";
        if (inputAnswer.equals(null) || inputAnswer.equals("")) {
            answer = "no answered";
        } else if (studentAnswer.getAnswer().equals(inputAnswer)) {
            return;
        } else {
            answer = inputAnswer;
        }
        studentAnswer.setAnswer(answer);
        Question question = studentAnswer.getQuestion();
        Exam exam = studentAnswer.getExam();
        if (studentAnswer.getQuestion().getType().equals("multipleChoice")) {
            Double point = examService.getStudentPoint(answer, question.getId(), exam.getId());
            //  newStudentAnswer =examService. setStudentAnswer(student.getId(), question, exam.getId(), answer);
            studentAnswer.setScore(point);
        }else if(studentAnswer.getQuestion().getType().equals("descriptive")){
            studentAnswer.setScore(0.0);
        }

        studentAnswerRepository.updateStudentAnswer(studentAnswer.getQuestion(), inputAnswer);
    }


    public boolean participateStudentExam(Exam exam, Student student) {
        if (studentAnswerRepository.getStudentAnswerByExamAndStudent(exam, student).size() > 0) {
            return true;
        }
        return false;
    }

    public Double calculateEachStudentAnswer(List<StudentAnswer> studentAnswers) {
        Double totalScore = 0.0;
        for (StudentAnswer studentAnswer :
                studentAnswers) {
            totalScore += studentAnswer.getScore();
        }
        return totalScore;
    }

    public List<StudentAnswer> getStudentAnswerList(Student student) {
        return studentAnswerRepository.findByStudent(student);
    }

    public Map<Student, Double> getResultTeacher(Long examId) {
        Exam exam = examService.getExamById(examId);
        Map<Student, Date> students = exam.getStudentsStartTimes();
        Map<Student, Double> studentScore = new HashMap<>();

        for (Student student :
                students.keySet()) {
            List<StudentAnswer> studentAnswerList = studentAnswerRepository.getStudentAnswerByExamAndStudent(exam, student);
            Double result = calculateEachStudentAnswer(studentAnswerList);
            studentScore.put(student, result);
        }
        return studentScore;
    }

    @Transactional
    public void setStudentAnswerTemplate(Exam exam, Student student) {
        StudentAnswer studentAnswer = new StudentAnswer();
        List<Question> examQuestions = examService.getExamQuestions(exam.getId());
        for (Question question :
                examQuestions) {
            studentAnswer.setExam(exam);
            studentAnswer.setStudent(student);
            studentAnswer.setQuestion(question);
            studentAnswer.setAnswer("no answered");
        }
        studentAnswerRepository.save(studentAnswer);
    }
}


