package pl.projectorc.services;


import org.junit.jupiter.api.Test;
import pl.projectorc.entities.User;
import pl.projectorc.repositories.UserRepository;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


//    @Test
//    void test(){
//        UserRepository userRepo = mock(UserRepository.class);
//
//        UserService service = new UserService(userRepo);
//        String username = "georg";
//        User user = new User();
//        user.setUsername("georg");
//        when(userRepo.findByUsername(username)).thenReturn(Optional.ofNullable(user));
//        Optional<User> byUsername = userRepo.findByUsername(username);
//        String userString = byUsername.map(User::getUsername).orElse("nie ma");
//        System.out.println(userString);
//
//        boolean result = service.checkIfUsernameExist("georg");
//        assertTrue(result);
//    }
}