package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.controllers.CtrlTask;
import fr.lauparr.pplanner.server.dao.DaoProjectMenuItem;
import fr.lauparr.pplanner.server.dao.DaoTask;
import fr.lauparr.pplanner.server.entities.Task;
import fr.lauparr.pplanner.server.projections.ProjTask;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SrvTask {

	@Autowired
	private DaoTask daoTask;
	@Autowired
	private DaoProjectMenuItem daoProjectMenuItem;
	@Autowired
	private SrvJpaUtils srvJpaUtils;

	public void createTask(final CtrlTask.ParamsCreateTask params, final String itemId) {
		final Task task = new Task();
		task.setName(params.getName());
		task.setDescription(params.getDescription());
		task.setItem(this.daoProjectMenuItem.findById(itemId).orElseThrow(() -> new EntityNotFoundException("Le menu n'existe pas")));
		this.daoTask.save(task);
	}

	public List<Task> findAllTasksByMenuItemId(final String itemId) {
		return this.daoTask.findAllTasksByMenuItemId(itemId);
	}

	public Map<String, List<ProjTask>> findAllTasksByMenuItemIdGroupedByStatus(final String itemId) {
		final List<ProjTask> tasks = this.srvJpaUtils.convertListDto(this.daoTask.findAllTasksByMenuItemId(itemId), ProjTask.class);
		return tasks.stream().collect(Collectors.groupingBy(projTask -> projTask.getStatus().getId()));
	}
}
