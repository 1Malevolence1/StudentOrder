package validator;

import answer.AnswerChildren;
import domain.StudentOrder;

public class ChildrenValidator implements Validator<AnswerChildren, StudentOrder> {

    @Override
    public AnswerChildren check(StudentOrder so) {
        return null;
    }
}
