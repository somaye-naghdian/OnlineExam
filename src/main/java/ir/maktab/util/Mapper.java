package ir.maktab.util;

import ir.maktab.model.dto.ClassificationDto;
import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
        userDto.setCourseList(user.getCourseList());
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
        user.setCourseList(userDto.getCourseList());
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
        courseDto.setUserList(course.getUserList());
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

    public Classification convertClassifyDtoToEntity(ClassificationDto classificationDto){
        Classification classification=new Classification();
        classification.setId(classificationDto.getId());
        classification.setClassificationTitle(classificationDto.getClassificationTitle());
        return classification;
    }
}
