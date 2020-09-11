package ir.maktab.controller;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.User;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.CourseService;
import ir.maktab.service.TeacherService;
import ir.maktab.service.UserService;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class CourseController {


    private UserService userService;
    private CourseService courseService;
    private TeacherService teacherService;
    private ClassificationService classificationService;
    private Mapper mapper;


    @Autowired
    public CourseController(UserService userService, CourseService courseService,
                            TeacherService teacherService, ClassificationService classificationService
            , Mapper mapper) {
        this.userService = userService;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.classificationService = classificationService;
        this.mapper = mapper;
    }


    @RequestMapping(value = "addCourse", method = RequestMethod.GET)
    public ModelAndView addCourse(Model model) {
        model.addAttribute("course", new CourseDto());
        ModelAndView modelAndView = new ModelAndView("addCourse");
        List<Classification> allClassification = classificationService.getAllClassification();
        modelAndView.addObject("allClassification", allClassification);
        return modelAndView;
    }

    @RequestMapping(value = "addCourseProcess", method = RequestMethod.GET)
    public ModelAndView addCourseProcess(@ModelAttribute("course") CourseDto courseDto,
                                         @RequestParam("classification") String title) {
        String courseTitle = courseDto.getCourseTitle();
        Classification classification = classificationService.getClassificationByTitle(title);
        try {
            courseService.createNewCourse(courseDto,classification);

        } catch (CourseAlreadyExist e) {
            new ModelAndView("error", "errorMsg", e.getMessage());
        }
        String message = " Course " + courseDto.getCourseTitle() + " successfully add";
        ModelAndView modelAndView = new ModelAndView("simpleMessage", "message", message);
        return modelAndView;
    }


    @RequestMapping(value = "getCoursePage", method = RequestMethod.GET)
    public ModelAndView getCoursePage(@ModelAttribute("user") String email) {
        User user = userService.findUserByEmail(email);
        ModelAndView modelAndView = new ModelAndView("teacher_Courses");
        try {
            Set<Course> courses = courseService.getUserCourses(user);
            modelAndView.addObject("user", user);
            modelAndView.addObject("courseList", courses);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return modelAndView;
        }
    }

    @RequestMapping(value = "/getExamsOfCourse", method = RequestMethod.GET)
    public ModelAndView getExamsOfCourse(@RequestParam("courseTitle") String courseTitle
            , @RequestParam("user") String email, Model model) {
        List<Exam> examsOfCourse = courseService.getExamsOfCourse(courseTitle);
        User user = userService.findUserByEmail(email);
        ModelAndView modelAndView = new ModelAndView("teacher_showExam", "examsOfCourse", examsOfCourse);
        modelAndView.addObject("user", user);
        modelAndView.addObject("question", new QuestionDto());
        modelAndView.addObject("exam", new ExamDto());
        return modelAndView;
    }

    @RequestMapping(value = "getCourseList", method = RequestMethod.GET)
    public ModelAndView getCourseList() {
        ModelAndView modelAndView = new ModelAndView("showAllCourse");
        List<Course> allCourse = courseService.getAllCourse();
        List<CourseDto> courseDtoList = mapper.convertCourseToDtoList(allCourse);
        modelAndView.addObject("allCourse", courseDtoList);
        return modelAndView;
    }
}
