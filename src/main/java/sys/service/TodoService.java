package sys.service;

import sys.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    TodoDTO addTodo(TodoDTO todoDto);

    TodoDTO getTodo(Long id);

    List<TodoDTO> getAllTodos();

    TodoDTO updateTodo(TodoDTO todoDto, Long id);

    void deleteTodo(Long id);

    TodoDTO completeTodo(Long id);

    TodoDTO inCompleteTodo(Long id);
}
