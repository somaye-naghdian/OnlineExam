package ir.maktab.util.comperator;

import ir.maktab.model.entity.Question;

import java.util.Comparator;

public class SortQuestionById implements Comparator<Question> {
    @Override
    public int compare(Question q1, Question q2) {
        return q1.getId().compareTo(q2.getId());
    }
}
