package ir.maktab.controller;

import ir.maktab.model.dto.MultipleChoiceQuestionDto;
import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.*;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.ExamService;
import ir.maktab.service.MultipleChoiceQuestionService;
import ir.maktab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MultipleChoiceQuestionController {

    private MultipleChoiceQuestionService mcQuestionService;
    private ExamService examService;
    private ClassificationService classificationService;
    private UserService userService;

    @Autowired
    public MultipleChoiceQuestionController(MultipleChoiceQuestionService mcQuestionService
            , ExamService examService,ClassificationService classificationService ,UserService userService) {
        this.mcQuestionService = mcQuestionService;
        this.examService = examService;
        this.classificationService=classificationService;
        this.userService = userService;
    }

    @RequestMapping(value = "/multipleChoice", method = RequestMethod.GET)
    public ModelAndView getDescriptivePage(@RequestParam("examId") String examId,
                                           @RequestParam("teacher") String teacherId,
                                           HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UserDto userDto = (UserDto) session.getAttribute("user");
        Exam exam = examService.getExamById(Long.valueOf(examId));
        User teacher = userService.findById(userDto.getId());
        ModelAndView modelAndView = new ModelAndView("teacher_multipleChoice");
        modelAndView.addObject("exam", exam);
        modelAndView.addObject("teacher", teacher);
        modelAndView.addObject("question",new Question());
        return modelAndView;
    }

    @RequestMapping(value = "/newMultipleChoiceQuestion", method = RequestMethod.GET)
    public ModelAndView addMultipleChoiceQuestion(@ModelAttribute("question") MultipleChoiceQuestionDto question,
                                                  @RequestParam("examId") String examId, @RequestParam("status") String status,
                                                  @RequestParam("score") String score,@RequestParam("correctAnswer") String correctAnswer,
                                                  @RequestParam("answer") String answers) {
        MultipleChoiceQuestion choiceQuestion = mcQuestionService.saveMultiQuestion(question, Long.valueOf(examId), answers,correctAnswer);
        System.out.println(choiceQuestion);
        Double totalScore = examService.addExamScore(Long.valueOf((examId)), Double.valueOf(score), choiceQuestion);
      examService.addQuestionToExam(Long.valueOf(examId),choiceQuestion,Double.valueOf(score));
        if (status.equals("YES")) {
            classificationService.addMultiQuestionToClassification(choiceQuestion);
        }
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        return modelAndView.addObject("message", "multiple Choice Question added successfully and total Score " +
                "of exam is : " +totalScore );
    }
}



