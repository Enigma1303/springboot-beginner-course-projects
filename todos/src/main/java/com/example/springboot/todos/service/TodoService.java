package com.example.springboot.todos.service;

import java.util.List;

import com.example.springboot.todos.request.TodoRequest;
import com.example.springboot.todos.response.TodoResponse;

public interface TodoService {
    List<TodoResponse> getAllTodos();
    TodoResponse createTodo(TodoRequest todoRequest);
    TodoResponse toggleTodoCompletion(long id);
    void deleteTodo(long id);
}
