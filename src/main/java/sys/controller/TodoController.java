package sys.controller;


import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/add")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
        TodoDto savedTodo = todoService.getTodo(todoId);
        return new ResponseEntity<>(savedTodo, HttpStatus.OK);
    }


    //Build Get All Todo REST API

    @GetMapping("/getAll")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoList = todoService.getAllTodos();

        //there is two ways for response
        //and basically secound way is
        //a short cut to apply for that

//        return new ResponseEntity<>(todoList, HttpStatus.OK);
        return ResponseEntity.ok(todoList);

    }


    // Build Update Todo REST API
    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
        TodoDto updateTodo = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updateTodo);
    }

}
