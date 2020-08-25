package ir.maktab.model.repository;

import ir.maktab.model.entity.Classification;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ClassificationRepository extends Repository<Classification, Integer> {

    void save(Classification classification);

    List<Classification> findAll();

   Classification findByClassificationTitle(String title);


}
