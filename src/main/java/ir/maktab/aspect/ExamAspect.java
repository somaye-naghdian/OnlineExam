package ir.maktab.aspect;

import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.Student;
import ir.maktab.model.entity.Teacher;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExamAspect {

    private static Logger logger = LogManager.getLogger(Exam.class);

    @After("addExam()")
    public void addExamAfter(JoinPoint joinPoint) throws Throwable {
        Object arg = joinPoint.getArgs()[0];
        logger = LogManager.getLogger(arg.toString());
        logger.info( "exam added ");
    }
    @After("stopExam()")
    public void stopExamAfter(JoinPoint joinPoint) throws Throwable {
        Object arg = joinPoint.getArgs()[0];
        logger = LogManager.getLogger(arg.toString());
        logger.info( "exam stop ");
    }
    @After("updateExam()")
    public void updateExamAfter(JoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature().getName() + "called");
    }
    @After("deleteExam()")
    public void deleteExamAfter(JoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature().getName() + "called");
    }
    @After("addQuestionToExam()")
    public void addQuestionToExamAfter(JoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature().getName() + "called");
    }
    @After("addStudentToExam()")
    public void addStudentToExamAfter(JoinPoint joinPoint) throws Throwable {
        Object joinPointArg = joinPoint.getArgs()[0];
        logger = LogManager.getLogger(joinPointArg.toString());
        logger.info( "student  added to exam");
    }

    @Pointcut("execution(* ir.maktab.service.ExamService.saveExam(..))")
    public void addExam() {
    }
    @Pointcut("execution(* ir.maktab.service.ExamService.stopExam(..))")
    public void stopExam() {
    }
    @Pointcut("execution(* ir.maktab.service.ExamService.updateExam(..))")
    public void updateExam() {
    }
    @Pointcut("execution(* ir.maktab.service.ExamService.deleteExam(..))")
    public void deleteExam() {
    }
    @Pointcut("execution(* ir.maktab.service.ExamService.addQuestionToExamFromBank(..))")
    public void addQuestionToExam() {
    }
   @Pointcut("execution(* ir.maktab.service.ExamService.addStudentToExam(..))")
    public void addStudentToExam() {
    }

}


