package com.cd.autoTest.dao;

import java.util.List;

import com.cd.autoTest.model.Task;

/**
 * @author xiangjx
 *
 */
public interface TaskDAO {
	List<Task> findTaskList(Task task);
	List<Task> findTaskListByProjectId(int projectId);
	int insertTask(Task task);
	int updateTask(Task task);
	Task findTaskById(int id);
	int deleteTask(int id);
}
