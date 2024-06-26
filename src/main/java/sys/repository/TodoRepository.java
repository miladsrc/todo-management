package sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
