package ir.maktab.controller;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.entity.*;
import ir.maktab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {

    private ExamService examService;
    private CourseService courseService;
    private UserService userService;
    private StudentService studentService;
    private DescriptiveQuestionService dQuestionService;
    private MultipleChoiceQuestionService mQuestionService;

    @Autowired
    public ExamController(ExamService examService, CourseService courseService
            , UserService userService, StudentService studentService
            , DescriptiveQuestionService dQuestionService, MultipleChoiceQuestionService mQuestionService) {
        this.examService = examService;
        this.courseService = courseService;
        this.userService = userService;
        this.studentService = studentService;
        this.dQuestionService = dQuestionService;
        this.mQuestionService = mQuestionService;
    }

    @RequestMapping(value = "newExam", method = RequestMethod.GET)
    public ModelAndView getNewExam(@RequestParam("courseTitle") String courseTitle,
                                   @RequestParam("userEmail") String email,
                                   Model model) {
        System.out.println(email);
        User user = userService.findUserByEmail(email);
        Course course = courseService.findCourseByTitle(courseTitle);
        ModelAndView modelAndView = new ModelAndView("teacher_addExam");
        modelAndView.addObject("user", user);
        modelAndView.addObject("exam", new ExamDto());
        modelAndView.addObject("course", course);
        modelAndView.addObject("message", model.getAttribute("message"));
        return modelAndView;
    }

    @RequestMapping(value = "/addNewExam", method = RequestMethod.POST)
    public String createNewExam(@ModelAttribute("exam") ExamDto examDto,
                                @RequestParam("courseTitle") String courseTitle
            , @RequestParam("user") String userId, Model model) {
        try {
            System.out.println(examDto);
            examService.saveExam(examDto, Long.valueOf((userId)), courseTitle);
            model.addAttribute("message", "Exam added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
        }
        return "teacher_addExam";
    }

    @RequestMapping(value = {"/editExam", "/deleteExam", "/stopExam"}, method = RequestMethod.GET)
    public String updateExam(@ModelAttribute("exam") ExamDto examDto
            , HttpServletRequest request, Model model, @RequestParam("user") String teacher) {
        String requestedValue = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        try {
            if (requestedValue.equals("/editExam")) {
                Exam exam = examService.updateExam(examDto, teacher);
                model.addAttribute("message", "exam " + exam.getTitle() + " successfully updated");
            } else if (requestedValue.equals("/deleteExam")) {
                examService.deleteExam(Long.valueOf((examDto.getId())), teacher);
                model.addAttribute("message", "exam successfully deleted");
            } else if (requestedValue.equals("/stopExam")) {
                examService.stopExam(examDto, teacher);
                model.addAttribute("message", "exam successfully stop");
            }

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("message", e.getMessage());
        }
        return "teacher_addExam";
    }


    @RequestMapping(value = "/examsOfCourse", method = RequestMethod.GET)
    public ModelAndView getExamsOfCourse(@RequestParam("courseTitle") String courseTitle
            , @RequestParam("user") String email) {
        List<Exam> examsOfCourse = courseService.getExamsOfCourse(courseTitle);
        User user = userService.findUserByEmail(email);
        ModelAndView modelAndView = new ModelAndView("student_exams", "examsOfCourse", examsOfCourse);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/takeExam", method = RequestMethod.POST)
    public ModelAndView takeExam(@RequestParam("examId") String examId,
                                 @RequestParam("user") String studentId) {
        ModelAndView modelAndView = new ModelAndView("startExam");
        Exam exam = examService.getExamById(Long.valueOf(examId));
        Student student = studentService.getStudentById(Long.valueOf(studentId));
        Date date = new Date(System.currentTimeMillis());
        if (student.getExams().contains(exam)) {
            modelAndView = new ModelAndView("student_exams");
            modelAndView.addObject("message", "You have already taken this exam");
            return modelAndView;
        } else if (exam.getEndDate().before(date)) {
            modelAndView = new ModelAndView("student_exams");
            modelAndView.addObject("student", student);
            modelAndView.addObject("message", "Exam time is over");
            return modelAndView;
        } else {
            List<Question> questions = exam.getQuestions();
            Question question = questions.get(0);
            if (question instanceof DescriptiveQuestion) {
                DescriptiveQuestion dQuestion = dQuestionService.getDQuestionByID(question.getId());
                modelAndView.addObject("question", dQuestion);

            } else {
                MultipleChoiceQuestion multiQuestion = mQuestionService.getMultiQuestionById(question.getId());
                modelAndView.addObject("question", multiQuestion);
                modelAndView.addObject("answers",multiQuestion.getAnswers());
            }
        }
        modelAndView.addObject("exam",exam);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @RequestMapping(value = {"/nextQuestion", "/previousQuestion"}, method = RequestMethod.POST)
    public ModelAndView getNextQuestion(@RequestParam("examId") String examId,
                                        @RequestParam("user") String studentId,
                                        @RequestParam("questionId") String questionId,
                                        HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("startExam");
        String requestedValue = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        Student student = studentService.getStudentById(Long.valueOf(studentId));
        //todo
        Exam exam = examService.getExamById(Long.valueOf(examId));
        List<Question> questions = exam.getQuestions();

        Map<Question, String> answers = new HashMap<>();
        String answer = request.getParameter("answer");

        Integer counter = Integer.valueOf(questionId) -1;

        answers.put(questions.get(Integer.parseInt(questionId)), answer);
        System.out.println(answers);
        Integer nextQuestionId = 0;

        if (requestedValue.equals("/nextQuestion") &&
                counter <= questions.size()) {
            nextQuestionId = ++counter;
        } else if (requestedValue.equals("/previousQuestion") &&
                counter >= 1) {
            nextQuestionId = --counter;
        }
        System.out.println("nextQuestionId "+nextQuestionId);
        Question question = questions.get(nextQuestionId);
        System.out.println(question);
        System.out.println(question.getId());
        System.out.println("*******************");
        if (question instanceof DescriptiveQuestion) {
            DescriptiveQuestion dQuestion = dQuestionService.getDQuestionByID(question.getId());
            modelAndView.addObject("question", dQuestion);
            modelAndView.addObject("answer",new Answer());

        } else {
            MultipleChoiceQuestion multiQuestion = mQuestionService.getMultiQuestionById(question.getId());
            modelAndView.addObject("question", multiQuestion);
            modelAndView.addObject("answers",multiQuestion.getAnswers());
        }
        modelAndView.addObject("student",student);
        modelAndView.addObject("exam",exam);

    return modelAndView;
    }
}

