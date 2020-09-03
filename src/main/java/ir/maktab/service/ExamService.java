package ir.maktab.service;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.entity.Exam;
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
import java.util.Date;

@Service
public class ExamService {

    private ExamRepository examRepository;
    private CourseService courseService;
    private UserService userService;
    private TeacherService teacherService;
    private Mapper mapper;

    @Autowired
    public ExamService(ExamRepository examRepository, Mapper mapper,
                       UserService userService, CourseService courseService
            , TeacherService teacherService) {
        this.examRepository = examRepository;
        this.userService = userService;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.mapper = mapper;
    }


    @Modifying
    @Transactional
    public Exam saveExam(ExamDto examDto, Integer userId, String courseTitle) throws Exception {
        Exam exam = mapper.convertExamDtoToEntity(examDto);
        exam.setTeacher(teacherService.getTeacher(userId));
        exam.setCourse(courseService.findCourseByTitle(courseTitle));
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
    public Exam updateExam(ExamDto examDto, Integer userId) throws Exception {
        Exam exam = mapper.convertExamDtoToEntity(examDto);
        if (exam.getTeacher().getId().equals(userId)) {
            return examRepository.save(exam);

        } else {
            throw new Exception("You are not allowed to change this exam");
        }
    }

    void updateExamByTeacher() {

    }
}
