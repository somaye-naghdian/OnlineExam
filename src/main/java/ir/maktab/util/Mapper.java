package ir.maktab.util;

import ir.maktab.model.dto.ClassificationDto;
import ir.maktab.model.dto.CourseDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Student;
import ir.maktab.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class Mapper {

    private ModelMapper modelMapper;

    @Autowired
    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Mapper() {
    }

    public CourseDto convertCourseToCourseDto(Course course) {
        return modelMapper.map(course, CourseDto.class);
    }

    public Classification ConvertToClassificationEntity(ClassificationDto classificationDto) {
        return modelMapper.map(classificationDto, Classification.class);
    }

    public UserDto convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> convertToUserDtoList(List<User> users) {
        List<UserDto> usersDto = users.stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());
        return usersDto;
    }

    public User convertToUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }


    public Page<UserDto> convertToPageDto(Page<User> objects) {
        Page<UserDto> userDtoPage = objects.map(this::convertUserToUserDto);
        return userDtoPage;
    }

    public List<CourseDto> convertToCourseDtoList(List<Course> courses) {
        List<CourseDto> usersDto = courses.stream()
                .map(this::convertCourseToCourseDto)
                .collect(Collectors.toList());
        return usersDto;
    }

//    public Student convertToStudentEntity(S userDto) {
//        return modelMapper.map(userDto, User.class);
//    }

}
