package ir.maktab.service;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.repository.ClassificationRepository;
import ir.maktab.model.entity.Classification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ClassificationService {

    private ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationService(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }
@Transactional
    public boolean addClassification(Classification classification) {

        try {
            Classification title = classificationRepository.findByClassificationTitle(classification.getClassificationTitle());
            if (StringUtils.isEmpty(title) || title == null) {
                classificationRepository.save(classification);
                return true;
            }

        } catch (NullPointerException e) {
            throw new CourseAlreadyExist(" already exist a classification with this specification");
        }
        return false;
    }

    public List<Classification> getAllClassification() {
        return classificationRepository.findAll();
    }

    public Classification getClassificationByTitle(String title){
        return classificationRepository.findByClassificationTitle(title);
    }

}
