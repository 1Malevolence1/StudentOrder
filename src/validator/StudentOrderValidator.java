package validator;

import answer.AnswerChildren;
import domain.reigster.AnswerCityRegister;
import answer.AnswerStudent;
import answer.AnswerWedding;
import domain.StudentOrder;
import validator.register.CityRegisterValidator;

import java.util.LinkedList;
import java.util.List;

public class StudentOrderValidator {
    private ChildrenValidator childrenValidator;
    private CityRegisterValidator cityRegisterValidator;
    private StudentValidator studentValidator;

    private WeddingValidator weddingValidator;

    public void read(){
        List<StudentOrder>  listStudentOrder = readStudentOrder();
        for (StudentOrder so: listStudentOrder
             ) {
            checkOneOrder(so);
        }
    }

    public List<StudentOrder> readStudentOrder(){
        List<StudentOrder> listStudentOrder = new LinkedList<>();
        for (int index = 0; index < 5; index++) {
            StudentOrder so = TestBuildStudentOrder.buildStudentOrder(10);
            listStudentOrder.add(so);
        }
        return listStudentOrder;
    }

    public void checkOneOrder(StudentOrder so) {
        AnswerCityRegister cityRegister = checkCityRegister(so);
    }

    public static void checkAll(){
        StudentOrder studentOrder = new StudentOrder();
    }
    public StudentOrderValidator() {
        this.childrenValidator = new ChildrenValidator();
        this.studentValidator = new StudentValidator();
        this.cityRegisterValidator = new CityRegisterValidator();
        this.weddingValidator = new WeddingValidator();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so){
        return cityRegisterValidator.check(so);
    }
    public AnswerWedding checkWedding(StudentOrder so){
        return weddingValidator.check(so);
    }

    public AnswerChildren checkChildren(StudentOrder so) {
        return childrenValidator.check(so);
    }
    public AnswerStudent checkStudent(StudentOrder so){
        return studentValidator.check(so);
    }
}
