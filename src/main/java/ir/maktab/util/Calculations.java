package ir.maktab.util;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class Calculations {

    public Double sumOfDoubleSet(Set<Double> scoreList) {
        Double sum = 0.0;
        for (Double score :
                scoreList) {
            sum += score;
        }
        return sum;
    } public Double sumOfDoubleList(List<Double> scoreList) {
        Double sum = 0.0;
        for (Double score :
                scoreList) {
            sum += score;
        }
        return sum;
    }

}
