package sys.service.Impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sys.dto.TodoDTO;
import sys.entity.Todo;
import sys.exception.ResourceNotFoundException;
import sys.repository.TodoRepository;
import sys.service.TodoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDTO addTodo(TodoDTO todoDto) {

        //convert todoDto to todoJpa entity obj
//        Todo todo = Todo.builder()
//                .title(todoDto.getTitle())
//                .completed(todoDto.isCompleted())
//                .description(todoDto.getDescription())
//                .build();
        Todo todo = modelMapper.map(todoDto, Todo.class);

        //todo jpa entity
        Todo todoSaved = todoRepository.save(todo);

        //convert saved todo to todo dto entity obj
//        TodoDto todoDtoTwo = new TodoDto();
//        todoDtoTwo.setId(todoSaved.getId());
//        todoDtoTwo.setTitle(todoSaved.getTitle());
//        todoDtoTwo.setDescription(todoSaved.getDescription());
//        todoDtoTwo.setCompleted(todoSaved.isCompleted());
        TodoDTO todoDtoTwo = modelMapper.map(todoSaved, TodoDTO.class);

        return todoDtoTwo;
    }

    @Override
    public TodoDTO getTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("todo not found with id " + id));
        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public List<TodoDTO> getAllTodos() {

        List<Todo> todoList = todoRepository.findAll();

        return todoList.stream()
                .map((todo) -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDTO updateTodo(TodoDTO todoDto, Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("todo not found with id"+ id));

        todo.setDescription(todoDto.getDescription());
        todo.setTitle(todoDto.getTitle());
        todo.setCompleted(todoDto.isCompleted());

        Todo todoSaved =  todoRepository.save(todo);
        return modelMapper.map(todoSaved, TodoDTO.class);
    }


    @Override
    public void deleteTodo(Long todoId) {

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("todo not found"));

        todoRepository.deleteById(todoId);
    }

    @Override
    public TodoDTO completeTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("todo not found"));

        todo.setCompleted(true);
        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(savedTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("todo not found"));

        todo.setCompleted(false);
        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(savedTodo, TodoDTO.class);
    }


}
