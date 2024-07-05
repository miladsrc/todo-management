package sys.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sys.dto.TodoDto;
import sys.entity.Todo;
import sys.repository.TodoRepository;
import sys.service.TodoService;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //convert todoDto to todoJpa entity obj
        Todo todo = Todo.builder()
                .title(todoDto.getTitle())
                .completed(todoDto.isCompleted())
                .description(todoDto.getDescription())
                .build();

        //todo jpa entity
        Todo todoSaved = todoRepository.save(todo);

        //convert saved todo to todo dto entity obj
        TodoDto todoDtoTwo = new TodoDto();
        todoDtoTwo.setId(todoSaved.getId());
        todoDtoTwo.setTitle(todoSaved.getTitle());
        todoDtoTwo.setDescription(todoSaved.getDescription());
        todoDtoTwo.setCompleted(todoSaved.isCompleted());


        return todoDtoTwo;
    }
}
