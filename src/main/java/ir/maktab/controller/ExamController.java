package ir.maktab.controller;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.dto.UserDto;
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
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.*;

@Controller
public class ExamController {
    private ExamService examService;
    private CourseService courseService;
    private UserService userService;
    private StudentService studentService;
    private DescriptiveQuestionService dQuestionService;
    private MultipleChoiceQuestionService mQuestionService;
    @Autowired
    private StudentAnswerService studentAnswerService;
    @Autowired
    private QuestionService questionService;

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
                Exam exam = examService.updateExam(examDto, Long.valueOf(teacher));
                model.addAttribute("message", "exam " + exam.getTitle() + " successfully updated");
            } else if (requestedValue.equals("/deleteExam")) {
                examService.deleteExam(Long.valueOf((examDto.getId())), Long.valueOf(teacher));
                model.addAttribute("message", "exam successfully deleted");
            } else if (requestedValue.equals("/stopExam")) {
                examService.stopExam(examDto, Long.valueOf(teacher));
                model.addAttribute("message", "exam successfully stop");
            }

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("message", e.getMessage());
        }
        return "teacher_addExam";
    }


    @RequestMapping(value = "/examsOfCourse", method = RequestMethod.GET)
    public ModelAndView getExamsOfCourse(@RequestParam("courseTitle") String courseTitle, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        UserDto userDto = (UserDto) session.getAttribute("user");
        Student student = studentService.getStudentById(userDto.getId());
        List<Exam> examsOfCourse = courseService.getExamsOfCourse(courseTitle);
        Map<Exam, Double> studentScoreEachExam = null;
        ModelAndView modelAndView = new ModelAndView("student_exams", "examsOfCourse", examsOfCourse);

        try {
            studentScoreEachExam = studentService.getStudentScoreEachExam(student.getId());
        } catch (Exception e) {
            modelAndView.addObject("message", e.getMessage());
        }

        modelAndView.addObject("studentScoreEachExam", studentScoreEachExam);
        modelAndView.addObject("user", student);
        return modelAndView;
    }

    @RequestMapping(value = "/takeExam", method = RequestMethod.GET)
    public ModelAndView takeExam(@RequestParam("examId") String examId, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("startExam");
        HttpSession session = request.getSession(false);
        UserDto userDto = (UserDto) session.getAttribute("user");
        Student student = studentService.getStudentById(userDto.getId());
        Exam exam = examService.getExamById(Long.valueOf(examId));
        Date date = new Date(System.currentTimeMillis());
        modelAndView.addObject("exam", exam);
        String message;
        if (studentAnswerService.participateStudentExam(exam, student)) {
            modelAndView = new ModelAndView("simpleMessage");
             message="You have already taken this exam";
            modelAndView.addObject("message",message );
            return modelAndView;
        } else if (exam.getEndDate().before(date)) {
            modelAndView = new ModelAndView("simpleMessage");
            message="Exam time is over";
            modelAndView.addObject("message", message);
            return modelAndView;
        } else {
            examService.addStudentToExam(Long.valueOf(examId), student.getId());
            modelAndView.addObject("examStarted", exam.getStudentsStartTimes().get(student));
            modelAndView.addObject("question", new QuestionDto());
        }
        return getNextQuestion(request);
    }

    @RequestMapping(value = {"/nextQuestion", "/previousQuestion", "/finishExam"}, method = RequestMethod.GET)
    public ModelAndView getNextQuestion(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("startExam");
        String requestedValue = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        int index = Integer.parseInt(request.getParameter("index"));
        Long examId = Long.valueOf(request.getParameter("examId"));
        List<Question> questions = examService.getExamQuestions(examId);
        String requestAnswer = request.getParameter("userAnswer");
        HttpSession session = request.getSession(false);
        UserDto userDto = (UserDto) session.getAttribute("user");
        Student student = studentService.getStudentById(userDto.getId());
        Map<Integer, Question> questionList = questionService.getQuestionMap(questions);

        if (index < questionList.size() && index > 0) {
            Question question = questionList.get(index);


            if (studentAnswerService.isAnsweredQuestion(question) != null) {
                StudentAnswer studentAnswer = studentAnswerService.isAnsweredQuestion(question);
                studentAnswerService.changeAnswer(studentAnswer.getId(), requestAnswer);
            } else {
                StudentAnswer studentAnswer1 = examService.addStudentAnswers(requestAnswer, examId, question.getId(), student.getId());
            }

            switch (requestedValue) {
                case "/nextQuestion":
                    index += 1;
                    question = questionList.get(index);
                    break;
                case "/previousQuestion":
                    if (index > 1) {
                        index -= 1;
                        question = questionList.get(index);
                    }
                    break;
            }

            if (question instanceof DescriptiveQuestion) {
                DescriptiveQuestion dQuestion = dQuestionService.getDQuestionByID(question.getId());
                modelAndView.addObject("question", dQuestion);
                request.setAttribute("question", dQuestion);

            } else if (question instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion multiQuestion = mQuestionService.getMultiQuestionById(question.getId());
                modelAndView.addObject("question", multiQuestion);
                request.setAttribute("question", multiQuestion);
            }
            modelAndView.addObject("questionType", questionList.get(index).getType());
        }

        if (requestedValue.equals("/finishExam")) {
            StudentAnswer studentAnswer1 = examService.addStudentAnswers(requestAnswer, examId,  questionList.get(index).getId(), student.getId());
            modelAndView = new ModelAndView("simpleMessage", "message", "exam finished");
            return modelAndView;
        }
        java.util.Date startTime = getStartTime(examService.getExamById(examId), student);
        modelAndView.addObject("index", index);
        modelAndView.addObject("startTime",startTime);
        modelAndView.addObject("examTime", examService.getExamById(examId).getTime());
        modelAndView.addObject("exam", examService.getExamById(examId));
        modelAndView.addObject("listSize", questionList.size());
        return modelAndView;
    }


    @RequestMapping(value = "/studentsResult", method = RequestMethod.GET)
    public ModelAndView getExamResult(@RequestParam("id") String examId) {
        Exam exam = examService.getExamById(Long.valueOf(examId));
        Map<Student, Double> studentScores = studentAnswerService.getResultTeacher(Long.valueOf(examId));
        ModelAndView modelAndView = new ModelAndView("teacher_studentResult");
        modelAndView.addObject("size", studentScores.size());
        modelAndView.addObject("studentScores", studentScores);
        modelAndView.addObject("exam", exam);
        return modelAndView;
    }

    private java.util.Date getStartTime(Exam exam, Student student) {
        java.util.Date start = new java.util.Date();
        for (Map.Entry<Student, java.util.Date> entry : exam.getStudentsStartTimes().entrySet()) {
            Student key = entry.getKey();
            if (key.getId().equals(student.getId())) {
                start = entry.getValue();
            }
        }
        return start;
    }
}

