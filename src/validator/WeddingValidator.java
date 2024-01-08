package validator;

import answer.AnswerWedding;
import domain.StudentOrder;

public class WeddingValidator implements Validator<AnswerWedding, StudentOrder> {
    @Override
    public AnswerWedding check(StudentOrder so) {
        System.out.println("Проверка брака");
        return new AnswerWedding();
    }
}
