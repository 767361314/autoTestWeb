package com.cd.autoTest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cd.autoTest.dao.TaskDAO;
import com.cd.autoTest.model.Task;

/**
 * @author xiangjx
 *
 */
public class TaskService extends IService {
	private TaskDAO taskDao;

	public List<Task> findTaskList(Task task) {
		return taskDao.findTaskList(task);
	}

	public List<Task> findTaskListByProjectId(int projectId) {
		return taskDao.findTaskListByProjectId(projectId);
	}

	public int insertTask(Task Task) {
		try {
			return taskDao.insertTask(Task);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateTask(Task Task) {
		try {
			return taskDao.updateTask(Task);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public Task findTaskById(int id) {
		return taskDao.findTaskById(id);
	}

	public int deleteTask(int id) {
		try {
			int i = taskDao.deleteTask(id);
			return i;
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}

	}

	public TaskDAO getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDAO taskDao) {
		this.taskDao = taskDao;
	}	
}
