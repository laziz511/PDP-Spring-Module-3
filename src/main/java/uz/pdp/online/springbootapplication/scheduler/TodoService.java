package uz.pdp.online.springbootapplication.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public ResponseEntity<Todo> updateTodo(Long id, Todo updatedTodo) {
        return getTodoById(id)
                .map(existingTodo -> {
                    existingTodo.setTitle(updatedTodo.getTitle());
                    existingTodo.setCompleted(updatedTodo.isCompleted());
                    Todo savedTodo = saveTodo(existingTodo);
                    return ResponseEntity.ok(savedTodo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteTodoById(Long id) {
        return getTodoById(id)
                .map(existingTodo -> {
                    todoRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
