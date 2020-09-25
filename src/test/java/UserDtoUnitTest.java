import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.User;
import ir.maktab.service.ExamService;
import ir.maktab.util.UserRole;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDtoUnitTest {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ExamService examService;
//    @Test
//    public void testExamService() {
//        for (int i = 0; i < 10; i++) {
//            final Exam ex = examService.getRandomExam();
//            assertNotNull(ex);
//            assertNotNull(ex.getName());
//            assertNotNull(ex.getDescription());
//            assertTrue(ex.getName().contains("Java") || ex.getName().contains("Scala"));
//            logger.info(ex.getDescription());
//        }
//    }
//
//    private static final Logger logger = LoggerFactory.getLogger(ServiceTest.class);
//}


//    @Test
//    public void whenConvertPostEntityToPostDto_thenCorrect() {
//        User user = new User();
//        user.setId(5);
//        user.setName(randomAlphabetic(6));
//        user.setFamily(randomAlphabetic(6));
//        user.setPassword();
//        user.setEnabled();
//        user.setRole(UserRole.STUDENT);
//        user.setCourseList();
//
//        PostDto postDto = modelMapper.map(post, PostDto.class);
//        assertEquals(post.getId(), postDto.getId());
//        assertEquals(post.getTitle(), postDto.getTitle());
//        assertEquals(post.getUrl(), postDto.getUrl());
//    }
//
//    @Test
//    public void whenConvertPostDtoToPostEntity_thenCorrect() {
//        PostDto postDto = new PostDto();
//        postDto.setId(1L);
//        postDto.setTitle(randomAlphabetic(6));
//        postDto.setUrl("www.test.com");
//
//        Post post = modelMapper.map(postDto, Post.class);
//        assertEquals(postDto.getId(), post.getId());
//        assertEquals(postDto.getTitle(), post.getTitle());
//        assertEquals(postDto.getUrl(), post.getUrl());
//    }
}