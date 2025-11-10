package com.bookmark.user.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.bookmark.common.domain.exception.DuplicateEntryException;
import com.bookmark.common.domain.exception.NotFoundException;
import com.bookmark.user.domain.Email;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import com.bookmark.user.domain.UserRepository;
import com.bookmark.user.domain.Username;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock private PasswordEncoder encoder;

  @Mock private UserRepository repository;

  @InjectMocks private UserServiceImpl userService;

  private static final UserId USER_ID = new UserId();
  private static final String USERNAME = "testuser";
  private static final String EMAIL = "test@example.com";
  private static final String RAW_PASSWORD = "rawPass123";
  private static final String ENCODED_PASSWORD = "$2a$10$encodedHash";
  private static final String ROLE = "USER";
  private User mockUser;

  private User createMockUser() {
    return new User(USER_ID, EMAIL, USERNAME, RAW_PASSWORD, ROLE);
  }

  @BeforeEach
  void setUp() {
    mockUser = createMockUser();
  }

  @Test
  void queryByIdShouldReturnUserWhenFound() {
    when(repository.findById(USER_ID)).thenReturn(Optional.of(mockUser));

    User result = userService.query(USER_ID);

    assertNotNull(result);

    assertEquals(USERNAME, result.getUsername().toString());

    verify(repository).findById(USER_ID);
  }

  @Test
  void queryByIdShouldThrowNotFoundExceptionWhenNotFound() {
    when(repository.findById(USER_ID)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> userService.query(USER_ID));

    verify(repository).findById(USER_ID);
  }

  @Test
  void queryByUsernameShouldReturnUserWhenFound() {
    when(repository.findByUsername(new Username(USERNAME))).thenReturn(Optional.of(mockUser));

    User result = userService.query(new Username(USERNAME));

    assertNotNull(result);

    assertEquals(EMAIL, result.getEmail().toString());

    verify(repository).findByUsername(new Username(USERNAME));
  }

  @Test
  void queryByUsernameShouldThrowNotFoundExceptionWhenNotFound() {
    when(repository.findByUsername(new Username(USERNAME))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> userService.query(new Username(USERNAME)));

    verify(repository).findByUsername(new Username(USERNAME));
  }

  @Test
  void saveShouldEncodePasswordAndSaveUserWhenUserIsNew() {
    User newUser = createMockUser();
    when(repository.findByUsername(new Username(USERNAME))).thenReturn(Optional.empty());
    when(repository.findByEmail(new Email(EMAIL))).thenReturn(Optional.empty());
    when(encoder.encode(RAW_PASSWORD)).thenReturn(ENCODED_PASSWORD);
    when(repository.save(any(User.class))).thenReturn(newUser);
    User savedUser = userService.save(newUser);
    assertNotNull(savedUser);
    assertEquals(
        ENCODED_PASSWORD, savedUser.getPassword().toString(), "Password should be encoded");
    verify(encoder).encode(RAW_PASSWORD.toString());
    verify(repository).save(newUser);
  }

  @Test
  void saveShouldThrowDuplicateEntryExceptionWhenUsernameExists() {
    User newUser = createMockUser();
    when(repository.findByUsername(new Username(USERNAME))).thenReturn(Optional.of(mockUser));
    assertThrows(DuplicateEntryException.class, () -> userService.save(newUser));
    verify(repository, never()).findByEmail(any());
    verify(encoder, never()).encode(any());
    verify(repository, never()).save(any());
  }

  @Test
  void saveShouldThrowDuplicateEntryExceptionWhenEmailExists() {
    User newUser = createMockUser();
    when(repository.findByUsername(new Username(USERNAME))).thenReturn(Optional.empty());
    when(repository.findByEmail(new Email(EMAIL))).thenReturn(Optional.of(mockUser));
    assertThrows(DuplicateEntryException.class, () -> userService.save(newUser));
    verify(encoder, never()).encode(any());
    verify(repository, never()).save(any());
  }
}
