package ir.maktab.aspect;

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
public class UserAspect {
    private static Logger logger = LogManager.getLogger(User.class);

    @Around("userAdd()")
    public void logAround(JoinPoint joinPoint) throws Throwable {
        Object arg = joinPoint.getArgs()[0];
        logger = LogManager.getLogger(arg.toString());
        logger.info(joinPoint.getSignature().getName() + "called");
    }

    @After("userUpdateByAdmin()")
    public void afterUpdate(JoinPoint joinPoint) throws Throwable {
        Object arg = joinPoint.getArgs()[0];
        logger = LogManager.getLogger(arg.toString());
        logger.info(joinPoint.getSignature().getName() + "called");

    }

    @Pointcut("execution(* ir.maktab.service.UserService.save(..))")
    public void userAdd() {
    }

    @Pointcut("execution(* ir.maktab.service.UserService.updateUser(..))")
    public void userUpdateByAdmin() {
    }
}
