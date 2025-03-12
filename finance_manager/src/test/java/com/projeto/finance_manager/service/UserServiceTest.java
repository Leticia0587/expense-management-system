package com.projeto.finance_manager.service;

import com.projeto.finance_manager.exception.UserNotFoundException;
import com.projeto.finance_manager.model.User;
import com.projeto.finance_manager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService; // Classe a ser testada

    @Mock
    private UserRepository userRepository; // Mock do repositório

    @Test
    public void deveSalvarUsuarioComSucesso() {
        // ARRANGE (Preparação)
        User user = new User("Letícia", "leticia@email.com", "root");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // ACT (Ação)
        User savedUser = userService.createUser(user);

        // ASSERT (Verificação)
        assertNotNull(savedUser);
        assertEquals("Letícia", savedUser.getName());
        assertEquals("leticia@email.com", savedUser.getEmail());
    }

    @Test
    public void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        // ARRANGE
        Long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

}