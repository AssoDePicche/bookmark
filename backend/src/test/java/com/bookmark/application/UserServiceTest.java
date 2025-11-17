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
import java.util.Optional;
import java.util.Set;
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
  private static final String EMAIL = "test@example.com";
  private static final Set<String> ROLES = Set.of("user");
  private User mockUser;

  private User createMockUser() {
    return new User(USER_ID.toString(), EMAIL, ROLES);
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

    verify(repository).findById(USER_ID);
  }

  @Test
  void queryByIdShouldThrowNotFoundExceptionWhenNotFound() {
    when(repository.findById(USER_ID)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> userService.query(USER_ID));

    verify(repository).findById(USER_ID);
  }

  @Test
  void saveShouldThrowDuplicateEntryExceptionWhenEmailExists() {
    User newUser = createMockUser();
    when(repository.findByEmail(new Email(EMAIL))).thenReturn(Optional.of(mockUser));
    assertThrows(DuplicateEntryException.class, () -> userService.save(newUser));
    verify(encoder, never()).encode(any());
    verify(repository, never()).save(any());
  }
}
