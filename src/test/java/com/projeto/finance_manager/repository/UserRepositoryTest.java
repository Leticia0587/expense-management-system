package com.projeto.finance_manager.repository;

import com.projeto.finance_manager.model.Role;
import com.projeto.finance_manager.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Ativa o Mockito no JUnit 5
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository; // Simula o repositório

    private User user;

    @BeforeEach
    void setup() {
        // Usuário base para os testes
        user = new User(null, "João Silva", "joao@email.com", "123456", null, Role.USER);
    }

    @Test
    public void deveSalvarUsuarioComSucesso() {
        // ARRANGE: Simulando o comportamento do método save()
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L); // Simula a atribuição de ID
            return u;
        });

        // ACT: Chamando o método
        User savedUser = userRepository.save(user);

        // ASSERT: Verificando o retorno esperado
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getId()).isEqualTo(1L);
        assertThat(savedUser.getUsername()).isEqualTo("João Silva");
        assertThat(savedUser.getEmail()).isEqualTo("joao@email.com");
        assertThat(savedUser.getPassword()).isEqualTo("123456");

        // Verifica se o método foi chamado corretamente
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void deveEncontrarUsuarioPorId() {
        // ARRANGE
        User userComId = new User(1L, "João Silva", "joao@email.com", "12345678",null, Role.USER);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userComId));

        // ACT
        Optional<User> foundUser = userRepository.findById(1L);

        // ASSERT
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("João Silva");

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void deveRetornarTodosOsUsuarios() {
        // ARRANGE
        List<User> users = Arrays.asList(
                new User(1L, "Carlos Santos", "carlos@email.com", "12345678",null, Role.USER),
                new User(2L, "Ana Lima", "ana@email.com", "12345678", null, Role.USER)
        );
        when(userRepository.findAll()).thenReturn(users);

        // ACT
        List<User> result = userRepository.findAll();

        // ASSERT
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getUsername()).isEqualTo("Carlos Santos");
        assertThat(result.get(1).getUsername()).isEqualTo("Ana Lima");

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void deveDeletarUsuarioComSucesso() {
        // ACT
        userRepository.deleteById(1L);

        // ASSERT
        verify(userRepository, times(1)).deleteById(1L);
    }
}