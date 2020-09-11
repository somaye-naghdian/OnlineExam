package ir.maktab.controller;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExamRestController {

    private ExamService examService;

    @Autowired
    public ExamRestController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping(value = "/createNewExam/{course}/{user}")
    public ResponseEntity createNewExam(@RequestBody ExamDto examDto,
                                        @PathVariable("course") String courseTitle
            , @PathVariable("user") String userId) {

        try {

            examService.saveExam(examDto, Long.valueOf((userId)), courseTitle);
            return ResponseEntity.ok()
                    .body("Exam saved with title:" + examDto.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("error " + e.getMessage());
        }
    }

    @PutMapping(value = "/stopExam/{title}/{id}")
    public ResponseEntity stopExam(@PathVariable("title") String title
            , @PathVariable("id") String id) {
        try {
           // examService.stopExam(title, id);
            return ResponseEntity.ok()
                    .body("exam stopped");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("error " + e.getMessage());
        }
    }
}
