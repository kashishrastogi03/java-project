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
    public void setup() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("testuser");
        user.setPassword("plaintext123");
    }

    @Test
    public void testRegisterUser_EncryptsPasswordAndSaves() {
        // Arrange
        String encryptedPassword = "encrypted123";
        when(passwordEncoder.encode(user.getPassword())).thenReturn(encryptedPassword);

        // Act
        userService.registerUser(user);

        // Assert
        assertEquals(encryptedPassword, user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testValidateUser_ValidCredentials_ReturnsTrue() {
        // Arrange
        User storedUser = new User();
        storedUser.setUsername("testuser");
        storedUser.setPassword("hashedPassword");

        when(userRepository.findByUsername("testuser")).thenReturn(storedUser);
        when(passwordEncoder.matches("plaintext123", "hashedPassword")).thenReturn(true);

        // Act
        boolean isValid = userService.validateUser("testuser", "plaintext123");

        // Assert
        assertTrue(isValid);
    }

    @Test
    public void testValidateUser_InvalidPassword_ReturnsFalse() {
        User storedUser = new User();
        storedUser.setUsername("testuser");
        storedUser.setPassword("hashedPassword");

        when(userRepository.findByUsername("testuser")).thenReturn(storedUser);
        when(passwordEncoder.matches("wrongpassword", "hashedPassword")).thenReturn(false);

        boolean isValid = userService.validateUser("testuser", "wrongpassword");

        assertFalse(isValid);
    }

    @Test
    public void testValidateUser_UserNotFound_ReturnsFalse() {
        when(userRepository.findByUsername("nonexistent")).thenReturn(null);

        boolean isValid = userService.validateUser("nonexistent", "any");

        assertFalse(isValid);
    }
}
