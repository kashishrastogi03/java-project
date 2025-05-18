import com.nagarro.model.User;
import com.nagarro.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.nagarro.service.UserService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("john");
        user.setPassword("password");
    }

    @Test
    void testRegisterUser() {
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        userService.registerUser(user);

        assertEquals("encodedPassword", user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testValidateUser_Success() {
        user.setPassword("encodedPassword");
        when(userRepository.findByUsername("john")).thenReturn(user);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        boolean isValid = userService.validateUser("john", "password");
        assertTrue(isValid);
    }

    @Test
    void testValidateUser_Failure() {
        when(userRepository.findByUsername("john")).thenReturn(null);

        boolean isValid = userService.validateUser("john", "password");
        assertFalse(isValid);
    }
}
