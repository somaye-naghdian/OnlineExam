package ir.maktab.util;

import ir.maktab.model.dto.*;
import ir.maktab.model.entity.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class Mapper {

    public UserDto convertUserEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setFamily(user.getFamily());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setPassword(user.getPassword());
        userDto.setStatus(user.getStatus());
//        userDto.setCourseList(user.getCourseList());
        return userDto;
    }

    public User convertUserDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setFamily(userDto.getFamily());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setPassword(userDto.getPassword());
        user.setStatus(userDto.getStatus());
//        user.setCourseList(userDto.getCourseList());
        return user;
    }

    public List<User> convertUserDtoListToEntity(List<UserDto> userDtoList) {
        List<User> users = new ArrayList<>();
        for (UserDto userDto :
                userDtoList) {
            User user = convertUserDtoToEntity(userDto);
            users.add(user);
        }
        return users;
    }

    public List<UserDto> convertEntityListToDto(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user :
                userList) {
            UserDto userDto = convertUserEntityToDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public CourseDto convertCourseToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCourseTitle(course.getCourseTitle());
        courseDto.setClassification(String.valueOf(course.getClassification()));
//        courseDto.setUserList(course.getUserList());
        return courseDto;
    }


    public List<CourseDto> convertCourseToDtoList(List<Course> courses) {
        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course course :
                courses) {
            CourseDto courseDto = convertCourseToDto(course);
            courseDtoList.add(courseDto);
        }
        return courseDtoList;
    }

    public Classification convertClassifyDtoToEntity(ClassificationDto classificationDto) {
        Classification classification = new Classification();
        classification.setId(classificationDto.getId());
        classification.setClassificationTitle(classificationDto.getClassificationTitle());
        return classification;
    }

    public Exam convertExamDtoToEntity(ExamDto examDto) throws ParseException {
        Exam exam = new Exam();


        exam.setId(examDto.getId());
        exam.setCourse(examDto.getCourse());
        exam.setDescription(examDto.getDescription());
        exam.setEndDate(convertStringToSqlDate(examDto.getEndDate()));
        exam.setStartDate(convertStringToSqlDate(examDto.getStartDate()));
        exam.setQuestions(examDto.getQuestions());
        exam.setTeacher(examDto.getTeacher());
        exam.setTime(examDto.getTime());
        exam.setTitle(examDto.getTitle());
        return exam;
    }

    public ExamDto  convertEntityToExamDto(Exam exam) {
        ExamDto examDto = new ExamDto();
        examDto.setId(exam.getId());
        examDto.setCourse(exam.getCourse());
        examDto.setDescription(exam.getDescription());
        examDto.setEndDate(String.valueOf(exam.getEndDate()));
        examDto.setStartDate(String.valueOf(exam.getStartDate()));
        examDto.setQuestions(exam.getQuestions());
        examDto.setTeacher(exam.getTeacher());
        examDto.setTime(exam.getTime());
        examDto.setTitle(exam.getTitle());
        return examDto;
    }


    public Question convertDtoToQuestionEntity(QuestionDto questionDto){
        Question question =new Question();
        question.setId(questionDto.getId());
        question.setText(questionDto.getText());
        question.setTitle(questionDto.getTitle());
        question.setExam(questionDto.getExam());
        question.setClassification(questionDto.getClassification());
       return  question;
    }



    public Date convertStringToSqlDate(String inputDate){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = df.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
}
