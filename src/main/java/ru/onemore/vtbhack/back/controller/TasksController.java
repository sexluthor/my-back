package ru.onemore.vtbhack.back.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.onemore.vtbhack.back.enumeration.RolesEnum;
import ru.onemore.vtbhack.back.jooq.tables.pojos.Task;
import ru.onemore.vtbhack.back.service.TasksService;
import ru.onemore.vtbhack.back.util.SecurityUtil;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TasksController {

	private final TasksService tasksService;

	@PostMapping
	public Task create(@RequestBody String request) {
		return tasksService.create(request, SecurityUtil.getUserLogin());
	}

	@GetMapping("/{id}")
	public Task getOne(@PathVariable Long id) {
		if (SecurityUtil.containsRole(RolesEnum.ROLE_MANAGER))
			return tasksService.getOne(id);
		return tasksService.getOneByUser(id, SecurityUtil.getUserLogin());
	}

	@GetMapping
	public List<Task> getAll() {
		if (SecurityUtil.containsRole(RolesEnum.ROLE_MANAGER))
			return tasksService.getAll();
		return tasksService.getAllByUser(SecurityUtil.getUserLogin());
	}

}
