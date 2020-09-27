package ir.maktab.aspect;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Teacher;
import ir.maktab.model.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Aspect
@Component
public class CourseAspect {

    private static Logger logger = LogManager.getLogger(Course.class);

    @Around("addCourse()")
    public void logAround(JoinPoint joinPoint) throws Throwable {
        User user = (User) joinPoint.getArgs()[0];
        logger = LogManager.getLogger(user.getEmail());
        logger.info(joinPoint.getSignature().getName() + "called");
    }

    @After("addUserToCourse()")
    public void afterUpdate(JoinPoint joinPoint) throws Throwable {
        User user = (User) joinPoint.getArgs()[0];
        logger = LogManager.getLogger(user.getEmail());
        logger.info(joinPoint.getSignature().getName() + "called");

    }
    @After("deleteStudentFromCourse()")
    public void afterDelete(JoinPoint joinPoint) throws Throwable {
        Object arg = joinPoint.getArgs()[0];
        logger = LogManager.getLogger(arg.toString());
        logger.info(joinPoint.getSignature().getName() + "called");

    }

    @Pointcut("execution(* ir.maktab.service.CourseService.createNewCourse(..))")
    public void addCourse() {
    }

    @Pointcut("execution(* ir.maktab.service.CourseService.addUserToCourse(..))")
    public void addUserToCourse() {
    }

    @Pointcut("execution(* ir.maktab.service.CourseService.deleteStudentFromCourse(..))")
    public void deleteStudentFromCourse() {
    }
}


