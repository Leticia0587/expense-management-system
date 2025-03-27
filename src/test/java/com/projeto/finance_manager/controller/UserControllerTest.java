package com.projeto.finance_manager.controller;

import com.projeto.finance_manager.model.User;
import com.projeto.finance_manager.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setUp() {
        // Criando usuário sem ID, apenas nome, email e senha
        user = new User("João Silva", "joao@email.com", "senha123");
    }

    @Test
    public void deveCriarUsuarioComSucesso() throws Exception {
        // ARRANGE
        when(userService.createUser(any(User.class))).thenReturn(user);

        // ACT & ASSERT
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("João Silva"));
    }

    @Test
    public void deveRetornarUsuarioPorId() throws Exception {
        // ARRANGE
        when(userService.getUserById(1L)).thenReturn(user);

        // ACT & ASSERT
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    public void deveRetornar404SeUsuarioNaoExistir() throws Exception {
        // ARRANGE
        when(userService.getUserById(2L)).thenThrow(new RuntimeException("Usuário não encontrado"));

        // ACT & ASSERT
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarTodosOsUsuarios() throws Exception {
        // ARRANGE
        List<User> users = Arrays.asList(
                new User("Ana Lima", "ana@email.com", "senha123"),
                new User("Carlos Souza", "carlos@email.com", "senha123")
        );
        when(userService.getAllUsers()).thenReturn(users);

        // ACT & ASSERT
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void deveAtualizarUsuarioComSucesso() throws Exception {
        // ARRANGE
        User updatedUser = new User("João Oliveira", "joao@email.com", "senha456");
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);

        // ACT & ASSERT
        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("João Oliveira"));
    }

    @Test
    public void deveDeletarUsuarioComSucesso() throws Exception {
        // ARRANGE
        doNothing().when(userService).deleteUser(1L);

        // ACT & ASSERT
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }
}
