package validator;

import answer.AnswerStudent;
import domain.StudentOrder;

public class StudentValidator implements Validator<AnswerStudent, StudentOrder> {
    @Override
    public AnswerStudent check(StudentOrder so) {
        System.out.println("Проверка студетна");
        return new AnswerStudent();
    }
}
