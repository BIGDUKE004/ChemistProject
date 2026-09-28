//package ng.chemist.repositoryTest;
//
//import ng.Chemist.Data.repositories.UserRepository;
//import ng.Chemist.Data.model.User;
//import ng.Chemist.service.AuthService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepo;
//
////    @Autowired
////    private AuthService authService;
//
//    private User user;
////    @BeforeEach
////    this.user = new User();
//
//    @Test
//    public void saveUser(){
//        user.setFullName("Elijah Miracle");
//        user.setUserName("BigDuke");
//        user.setPassWord("BIGDUKE004");
//        userRepo.save(user);
//        assertEquals(1, userRepo.count());
//    }
//
//    @Test
//    public void deleteUser(){
//        user.setFullName("Elijah Miracle");
//        user.setUserName("BigDuke");
//        user.setPassWord("BIGDUKE004");
//        userRepo.save(user);
//        assertEquals(1, userRepo.count());
//        userRepo.delete(user);
//        assertEquals(0, userRepo.count());
//    }
//
//    public void countUser(){
//        user.setFullName("Elijah Miracle");
//        user.setUserName("BigDuke");
//        user.setPassWord("BIGDUKE004");
//        userRepo.save(user);
//        assertEquals(1, userRepo.count());
//    }
//
//    @Test
//    public void findByName(){
//        user.setFullName("Elijah Miracle");
//        user.setUserName("BigDuke");
//        user.setPassWord("BIGDUKE004");
//        userRepo.save(user);
//        assertEquals(1, userRepo.count());
//
//        User checkingForUser = userRepo.findByUserName("BigDuke");
//        assertEquals("Elijah Miracle", checkingForUser.getFullName());
//        assertEquals("BigDuke", checkingForUser.getUserName());
//    }
//
//    @Test
//    public void deleteAllUser(){
//        user.setFullName("Elijah Miracle");
//        user.setUserName("BigDuke");
//        user.setPassWord("BIGDUKE004");
//
//        User userTwo = new User();
//        userTwo.setFullName("Elijah Miracle");
//        userTwo.setUserName("Big");
//        userTwo.setPassWord("BIGDUKE");
//
//        userRepo.save(user);
//        userRepo.save(userTwo);
//
//        assertEquals(2, userRepo.count());
//        userRepo.deleteAll();
//        assertEquals(0, userRepo.count());
//    }
//
//}
//
