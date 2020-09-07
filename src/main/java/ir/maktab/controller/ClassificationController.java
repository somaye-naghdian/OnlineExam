package ir.maktab.controller;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.dto.ClassificationDto;
import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.service.ClassificationService;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassificationController {

    private ClassificationService classificationService;
    private Mapper mapper;

    @Autowired
    public ClassificationController(ClassificationService classificationService
            , Mapper mapper) {
        this.classificationService = classificationService;
        this.mapper = mapper;
    }

    @RequestMapping(value = "addClassification", method = RequestMethod.GET)
    public String addClassification(Model model) {
        model.addAttribute("classification", new ClassificationDto());
        return "addClassification";
    }

    @RequestMapping(value = "addClassificationProcess", method = RequestMethod.GET)
    public ModelAndView addClassificationProcess(@ModelAttribute("classification") ClassificationDto classificationDto) {
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        try {
            classificationService.addClassification(mapper.convertClassifyDtoToEntity(classificationDto));

        } catch (CourseAlreadyExist e) {
            new ModelAndView("error", "errorMsg", e.getMessage());
        }
        String message = " classification " + classificationDto.getClassificationTitle() + " successfully add";
        new ModelAndView("simpleMessage", "message", message);

        return modelAndView;
    }

//    @RequestMapping(value = "addQuestionToBank",method = RequestMethod.GET)
//    public ModelAndView addQuestionToBank(@ModelAttribute("question")QuestionDto questionDto
//         , @RequestParam("examId") String examId){
//        System.out.println(questionDto);
//        Classification classification = classificationService.addQuestionToClassification(questionDto);
//        ModelAndView modelAndView = new ModelAndView("simpleMessage");
//        modelAndView.addObject("message","question successfully added to "+classification.getClassificationTitle());
//        return modelAndView;
//    }
}
