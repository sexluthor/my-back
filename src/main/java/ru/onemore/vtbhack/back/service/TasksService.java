package ru.onemore.vtbhack.back.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.onemore.vtbhack.back.enumeration.TaskStatusEnum;
import ru.onemore.vtbhack.back.jooq.tables.pojos.Task;
import ru.onemore.vtbhack.back.repository.TasksRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TasksService {

	private final TasksRepository tasksRepository;

	public Task create(String request, String username) {
		return tasksRepository.save(new Task(
				null,
				LocalDateTime.now(),
				request,
				TaskStatusEnum.PENDING,
				username
		));
	}

	public List<Task> getAllByUser(String username) {
		return tasksRepository.findAllByUsername(username);
	}

	public List<Task> getAll() {
		return tasksRepository.findAll();
	}

	public Task getOne(Long id) {
		return tasksRepository.findById(id).orElseThrow();
	}

	public Task getOneByUser(Long id, String username) {
		return tasksRepository.findByIdAndUsername(id, username);
	}

}
