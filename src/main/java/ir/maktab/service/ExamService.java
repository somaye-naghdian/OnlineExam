package ir.maktab.service;

import ir.maktab.model.dto.ExamDto;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
                       CourseService courseService
            , TeacherService teacherService) {
        this.examRepository = examRepository;
        this.courseService = courseService;
        this.teacherService = teacherService;

        this.mapper = mapper;
    }

    public Exam getExamById(Integer id) {
        return examRepository.findById(id);
    }

    @Modifying
    @Transactional
    public Exam saveExam(ExamDto examDto, Integer userId, String courseTitle) throws Exception {
        Exam exam = mapper.convertExamDtoToEntity(examDto);
        exam.setTeacher(teacherService.getTeacher(userId));
        exam.setCourse(courseService.findCourseByTitle(courseTitle));

        exam.setExamState(ExamStatus.NotStart);
        return examRepository.save(exam);
    }

    public Exam getExamByTitle(String title) {
        return examRepository.findByTitle(title);
    }

    public Exam stopExam(String title, String id) throws Exception {
        Exam exam = getExamByTitle(title);
        if (exam.getTeacher().getId().equals(Integer.parseInt(id))) {
            exam.setExamState(ExamStatus.STOP);
            return examRepository.save(exam);
        } else {
            throw new Exception("You are not allowed to change this exam");
        }

    }

    @Modifying
    @Transactional
    public Exam updateExam(String examId, String title, String description, String startDate,
                           String endDate, String time, String teacherEmail) throws Exception {
        Exam exam = examRepository.findById(Integer.parseInt(examId));
        try {
            if (teacherEmail.equals(exam.getTeacher().getEmail())) {
                exam.setTitle(title);
                exam.setDescription(description);
                exam.setStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDate));
                exam.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDate));
                exam.setTime(Integer.parseInt(time));
            } else {
                throw new AccessDeniedException("you cant edit this exam");
            }
            return examRepository.save(exam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examRepository.save(exam);
    }

    @Transactional
    public void deleteExam(Integer id, String teacher_email) throws AccessDeniedException {
        Exam exam = examRepository.findById(id);
        if (exam.getTeacher().getEmail().equals(teacher_email)) {
            examRepository.deleteById(id);
        } else {
            throw new AccessDeniedException("you can't delete this exam");
        }
    }

    @Transactional
    public void addExamScore(Integer id, Double score, Question question) {
        Exam exam = getExamById(id);
   //     questionService.
     Map<Question, Double> scoreEachQuestion =exam.getScoreEachQuestion();
       scoreEachQuestion.put(question, score);
        examRepository.save(exam);
    }
}