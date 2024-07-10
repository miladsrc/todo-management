package sys.service.Impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sys.dto.TodoDto;
import sys.entity.Todo;
import sys.exception.ResourceNotFoundException;
import sys.repository.TodoRepository;
import sys.service.TodoService;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

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
        TodoDto todoDtoTwo = modelMapper.map(todoSaved, TodoDto.class);

        return todoDtoTwo;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("todo not found with id " + id));
        return modelMapper.map(todo, TodoDto.class);
    }

}
