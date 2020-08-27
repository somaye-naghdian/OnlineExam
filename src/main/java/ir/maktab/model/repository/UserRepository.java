package ir.maktab.model.repository;


import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Integer>, JpaSpecificationExecutor<User> {

    User save(User user);

    List<User> findAll();

    User findByEmail(String email);

    User findById(Integer id);

    List<User> findByEnabled(StatusType status);

    List<User> findByRole(UserRole role);


    @Modifying
    @Query("update User set status=:newEnable where email=:email")
    void updateUserEnabled(@Param("newEnable") StatusType enabled, @Param("email") String email);


    @Modifying
    @Query("update User set name=:newName,family=:newFamily,status=:newEnable,role=:newRole,email=:email where email=:email")
    void updateUser(@Param("newName") String name, @Param("newFamily") String family,
                    @Param("newEnable") StatusType statusType, @Param("newRole") UserRole newRole,
                    @Param("email") String email);

//    @Modifying
//    @Query("update User set courseList=:courseList where id=:id")
//    void updateUserCourse(@Param("courseList") Set<Course> courseList, @Param("id") Integer id);


}
