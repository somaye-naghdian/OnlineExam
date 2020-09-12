package ir.maktab.service;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.DescriptiveQuestion;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.Question;
import ir.maktab.model.entity.User;
import ir.maktab.model.repository.ExamRepository;
import ir.maktab.util.ExamStatus;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.nio.file.AccessDeniedException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamService {

    private ExamRepository examRepository;
    private CourseService courseService;
    private TeacherService teacherService;
    private QuestionService questionService;
    private Mapper mapper;

    @Autowired
    public ExamService(ExamRepository examRepository, Mapper mapper,
                       CourseService courseService, QuestionService questionService
            , TeacherService teacherService) {
        this.examRepository = examRepository;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.questionService = questionService;
        this.mapper = mapper;
    }

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

    public Exam getExamByTitle(String title) {
        return examRepository.findByTitle(title);
    }

    public Exam stopExam(ExamDto examDto, String id) throws Exception {
        Exam exam = getExamById(examDto.getId());
        Date startDate = exam.getStartDate();
        Date endDate = exam.getEndDate();
        Date now = new Date(System.currentTimeMillis());

        if (exam.getTeacher().getId().equals(Long.valueOf(id))) {
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
    public Exam updateExam(ExamDto examDto, String teacherId) throws Exception {
        Exam exam = examRepository.findById(examDto.getId());
        try {
            if (Long.valueOf(teacherId) == exam.getTeacher().getId()) {
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
    public void deleteExam(Long id, String teacher_Id) throws AccessDeniedException {
        Exam exam = examRepository.findById(id);
        if (exam.getTeacher().getId() == Long.valueOf(teacher_Id)) {
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
        examRepository.save(exam);
        return calculateExamTotalPoint(scoreEachQuestion);
    }

    public Double addQuestionToExamFromBank(String examId, String score, QuestionDto questionDto) throws Exception {
        Question question = questionService.getQuestionById(questionDto.getId());
        Exam exam = getExamById(Long.valueOf(examId));
        if (exam.getQuestions().contains(question)) {
            throw new Exception("duplicate question");
        } else {
            return addExamScore(Long.valueOf((examId)), Double.valueOf(score), question);
        }
    }

    public Double calculateExamTotalPoint(Map<Question, Double> scoreOfQuestion) {
        Double totalScore = 0.0;
        for (Double score :
                scoreOfQuestion.values()) {
            totalScore += score;
        }
        return totalScore;
    }

    public void addUserScore(Map<Question, String> answers,Exam exam){
        System.out.println(answers);

    }
}
