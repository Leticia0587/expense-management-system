package com.projeto.finance_manager.repository;

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
        // Não definimos ID, pois ele será gerado automaticamente
        user = new User(null, "João Silva", "joao@email.com");
    }

    @Test
    public void deveSalvarUsuarioComSucesso() {
        // ARRANGE: Simulando o comportamento do método save()
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User userToSave = invocation.getArgument(0);

            // Simulando um ID gerado automaticamente pelo banco de dados (1L)
            // Estamos criando um novo User com o ID e os valores necessários
            return new User(userToSave.getName(), userToSave.getEmail(), userToSave.getPassword()) {
                @Override
                public Long getId() {
                    return 1L; // Simulando o ID gerado automaticamente
                }
            };
        });

        // Criando um usuário para salvar
        User userToSave = new User("João Silva", "joao@email.com", "senha123");

        // ACT: Chamando o método
        User savedUser = userRepository.save(userToSave);

        // ASSERT: Verificando o retorno esperado
        assertThat(savedUser).isNotNull(); // Verifica que o objeto não é nulo
        assertThat(savedUser.getId()).isNotNull(); // Verifica que o ID foi atribuído
        assertThat(savedUser.getId()).isEqualTo(1L); // Verifica que o ID é o que simulamos
        assertThat(savedUser.getName()).isEqualTo("João Silva"); // Verifica o nome
        assertThat(savedUser.getEmail()).isEqualTo("joao@email.com"); // Verifica o email
        assertThat(savedUser.getPassword()).isEqualTo("senha123"); // Verifica a senha

        // Verifica se o método foi chamado corretamente
        verify(userRepository, times(1)).save(any(User.class));
    }



    @Test
    public void deveEncontrarUsuarioPorId() {
        // ARRANGE: Simulando o comportamento do método findById()
        User userComId = new User("João Silva", "joao@email.com", "12345678");
        when(userRepository.findById(1L)).thenReturn(Optional.of(userComId));

        // ACT: Chamando o método
        Optional<User> foundUser = userRepository.findById(1L);

        // ASSERT: Verificando se o usuário foi encontrado
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("João Silva");

        // Verifica se o método foi chamado
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void deveRetornarTodosOsUsuarios() {
        // ARRANGE: Simulando o comportamento do método findAll()
        List<User> users = Arrays.asList(
                new User("Carlos Santos", "carlos@email.com","12345678"),
                new User("Ana Lima", "ana@email.com","12345678")
        );
        when(userRepository.findAll()).thenReturn(users);

        // ACT: Chamando o método
        List<User> result = userRepository.findAll();

        // ASSERT: Verificando se a lista contém 2 usuários
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Carlos Santos");

        // Verifica se o método foi chamado
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void deveDeletarUsuarioComSucesso() {
        // ACT: Chamando o método
        userRepository.deleteById(1L);

        // ASSERT: Verifica se o método foi chamado corretamente
        verify(userRepository, times(1)).deleteById(1L);
    }
}
