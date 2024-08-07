package sys.controller;


import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sys.dto.TodoDto;
import sys.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    //Build add todo api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
        TodoDto savedTodo = todoService.getTodo(todoId);
        return new ResponseEntity<>(savedTodo, HttpStatus.OK);
    }


    //Build Get All Todo REST API

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/getAll")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoList = todoService.getAllTodos();

        //there is two ways for response
        //and basically second way is
        //a shortcut to apply for that

//        return new ResponseEntity<>(todoList, HttpStatus.OK);
        return ResponseEntity.ok(todoList);

    }


    // Build Update Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
        TodoDto updateTodo = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updateTodo);
    }

    //Build Delete Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("todo deleted successfully");
    }

    //Build Update-confirmed Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/update/complete/{id}")
    public ResponseEntity<TodoDto> updateTodoConfirmed(@PathVariable("id") Long todoId){
        TodoDto updateTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updateTodo);
    }

    //Build Update-not-confirmed Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/update/in-complete/{id}")
    public ResponseEntity<TodoDto> updateTodoNotConfirmed(@PathVariable("id") Long todoId){
        TodoDto updateTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(updateTodo);
    }
}
