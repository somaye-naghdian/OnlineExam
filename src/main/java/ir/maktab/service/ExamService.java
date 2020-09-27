package ir.maktab.service;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.*;
import ir.maktab.model.repository.ExamRepository;
import ir.maktab.util.Mapper;
import ir.maktab.util.comperator.SortQuestionById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private MultipleChoiceQuestionService mCQuestionService;
    @Autowired
    private StudentAnswerService studentAnswerService;
    @Autowired
    private Mapper mapper;


    public Exam getExamById(Long id) {
        return examRepository.findById(id);
    }

    @Modifying
    @Transactional
    public Exam saveExam(ExamDto examDto, Long userId, String courseTitle) throws Exception {
        Date startDate = mapper.convertStringToSqlDate(examDto.getStartDate());
        Date endDate = mapper.convertStringToSqlDate(examDto.getEndDate());

        if (endDate.before(startDate)) {
            throw new RuntimeException("invalid date. end date must not before start date");
        } else {
            Exam exam = mapper.convertExamDtoToEntity(examDto);
            exam.setTeacher(teacherService.getTeacher(userId));
            exam.setCourse(courseService.findCourseByTitle(courseTitle));
            exam.setExamState(false);
            return examRepository.save(exam);
        }
    }

    @Transactional
    public void update(Exam exam) {
        examRepository.save(exam);
    }

    public void addQuestionToExam(Long examId, Question question, Double score) {
        Exam exam = getExamById(Long.valueOf(examId));
        exam.getScoreEachQuestion().put(question, score);
        examRepository.save(exam);
    }

    public Exam stopExam(ExamDto examDto, Long id) throws Exception {
        Exam exam = getExamById(examDto.getId());
        Date startDate = exam.getStartDate();
        Date endDate = exam.getEndDate();
        Date now = new Date(System.currentTimeMillis());

        if (exam.getTeacher().getId().equals(id)) {
            if (startDate.after(now)) {
                throw new Exception(" exam not yet started");
            } else if (endDate.before(now)) {
                throw new Exception(" Exam time is over");
            }
            exam.setExamState(false);
            return examRepository.save(exam);
        } else {
            throw new Exception("You are not allowed to change this exam");
        }

    }

    @Modifying
    @Transactional
    public Exam updateExam(ExamDto examDto, Long teacherId) throws Exception {
        Exam exam = examRepository.findById(examDto.getId());
        try {
            if (teacherId == exam.getTeacher().getId()) {
                exam.setTitle(examDto.getTitle());
                exam.setDescription(examDto.getDescription());
                exam.setStartDate(java.sql.Date.valueOf(examDto.getStartDate()));
                exam.setEndDate(java.sql.Date.valueOf(examDto.getEndDate()));
                exam.setTime(examDto.getTime());
            } else {
                throw new AccessDeniedException("you cant edit this exam");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examRepository.save(exam);
    }

    @Transactional
    public void deleteExam(Long id, Long teacher_Id) throws AccessDeniedException {
        Exam exam = examRepository.findById(id);
        if (exam.getTeacher().getId() == teacher_Id) {
            examRepository.delete(exam);
        } else {
            throw new AccessDeniedException("you can't delete this exam");
        }
    }

    @Transactional
    public Double addExamScore(Long id, Double score, Question question) {
        Exam exam = getExamById(id);
        Map<Question, Double> scoreEachQuestion = exam.getScoreEachQuestion();
        scoreEachQuestion.put(question, score);
        exam.setScoreEachQuestion(scoreEachQuestion);
        Double totalPoint = calculateExamTotalPoint(scoreEachQuestion);
        examRepository.save(exam);
        return totalPoint;

    }

    public List<Question> getExamQuestions(Long examId) {
        Exam exam = getExamById(examId);
        Map<Question, Double> examQuestion = exam.getScoreEachQuestion();
        List<Question> questions = new ArrayList<>(examQuestion.keySet());
       questions.sort(Comparator.comparing(Question::getId));

        /*for (int i = 0; i < questions.size(); i++) {
            questions.sort(new SortQuestionById());
        }*/
        System.out.println(questions);
        return questions;
    }


    public void addQuestionToExamFromBank(Long examId, String score, QuestionDto questionDto) throws Exception {
        Question question = questionService.getQuestionById(questionDto.getId());
        Exam exam = getExamById(examId);
        if (exam.getScoreEachQuestion().get(question).equals(question)) {
            throw new Exception("duplicate question");
        } else {
            Double examScore = addExamScore((examId), Double.valueOf(score), question);
            addQuestionToExam(examId,question,examScore);
        }
    }

    public Double calculateExamTotalPoint(Map<Question, Double> scoreOfQuestion) {
        Double totalScore ;
        totalScore = scoreOfQuestion.values().stream().reduce(0.0, Double::sum);
        return totalScore;
    }


    @Modifying
    @Transactional
    public void addStudentToExam(Long examId, Long studentId) {
        Exam exam = getExamById(examId);
        Student student = studentService.getStudentById(studentId);
        if (!exam.getStudentsStartTimes().containsKey(student)) {
            exam.getStudentsStartTimes().put(student, new java.util.Date());
            examRepository.save(exam);
        }
    }

    @Transactional
    public StudentAnswer addStudentAnswers(String answer, Long examId, Long questionId, Long studentId) {
        Question question = questionService.getQuestionById(questionId);
        Student student = studentService.getStudentById(studentId);
        Exam exam = examRepository.findById(examId);
        StudentAnswer newStudentAnswer = null;

        if (question.getType().equals("multipleChoice")) {
            Double point = getStudentPoint(answer, question.getId(), exam.getId());
            newStudentAnswer = setStudentAnswer(student.getId(), question, exam.getId(), answer);
            newStudentAnswer.setScore(point);
        } else if (question.getType().equals("descriptive")) {
                newStudentAnswer = setStudentAnswer(student.getId(), question, exam.getId(), answer);
            newStudentAnswer.setScore(0.0);
            }

        studentAnswerService.save(newStudentAnswer);
        return newStudentAnswer;
    }

    @Transactional
    public Double getStudentPoint(String answer, Long questionId, Long examId) {
        Question question = questionService.getQuestionById(questionId);
        MultipleChoiceQuestion multiQuestion = mCQuestionService.getMultiQuestionById(question.getId());
        String content = multiQuestion.getCorrectAnswer().getContent();
        if (content.equals(answer)) {
            Double score = getQuestionPoint(examId, questionId);
            return score;
        }
        return 0.0;
    }

    @Transactional
    public Double getQuestionPoint(Long examId, Long questionId) {
        Double score = null;
        Exam exam = getExamById(examId);
        Map<Question, Double> scoreEachQuestion = exam.getScoreEachQuestion();

        for (Map.Entry<Question, Double> entry : scoreEachQuestion.entrySet()) {
            Question key = entry.getKey();
            if (key.getId().equals(questionId)) {
                score = entry.getValue();
            }
        }
        return score;
    }

    public long getRemainingTime(Long examId, Long studentId) {
        Exam exam = getExamById(examId);
        Student student = studentService.getStudentById(studentId);

        Map<Student, java.util.Date> students = exam.getStudentsStartTimes();
        boolean b = students.containsKey(student);
        java.util.Date startTime = exam.getStudentsStartTimes().get(student);
        System.out.println(startTime);

        long remainingTime = (exam.getTime() * 60) - ((new java.util.Date()).getTime() - startTime.getTime()) / 1000;
        return remainingTime;
    }

    public StudentAnswer setStudentAnswer(Long studentId, Question question, Long examId, String inputAnswer) {
        StudentAnswer studentAnswer = new StudentAnswer();
        if (inputAnswer==null) {
            inputAnswer="no answer";
            studentAnswer.setScore(0.0);
        }
        studentAnswer.setQuestion(question);
        studentAnswer.setExam(examRepository.findById(examId));
        studentAnswer.setStudent(studentService.getStudentById(studentId));
        studentAnswer.setAnswer(inputAnswer);
        return studentAnswerService.save(studentAnswer);
    }
}
