package ru.onemore.vtbhack.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onemore.vtbhack.back.jooq.tables.pojos.Task;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {
	Task findByIdAndUsername(Long id, String username);
	List<Task> findAllByUsername(String username);
}
