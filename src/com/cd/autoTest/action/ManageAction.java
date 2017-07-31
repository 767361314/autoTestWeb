package com.cd.autoTest.action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.cd.autoTest.model.Category;
import com.cd.autoTest.model.Environment;
import com.cd.autoTest.model.Group;
import com.cd.autoTest.model.Project;
import com.cd.autoTest.model.Task;
import com.cd.autoTest.model.User;
import com.cd.autoTest.service.CategoryService;
import com.cd.autoTest.service.EnvironmentService;
import com.cd.autoTest.service.GroupService;
import com.cd.autoTest.service.ProjectService;
import com.cd.autoTest.service.TaskService;
import com.opensymphony.xwork2.Action;

public class ManageAction extends BaseAction {
	private GroupService groupService;
	private CategoryService categoryService;
	private EnvironmentService environmentService;
	private List<Group> groupList;
	private Group group;
	private Category category;
	private Environment env;
	private int groupId;
	private int environmentId;
	private int categoryId;
	private int projectId;
	private TaskService taskService;
	private List<Task> taskList;
	private Task task;
	private int taskId;
	private String code;


	

	public String initTask() {
		Task t =new Task();
		t.setProjectId(projectId);
		taskList = taskService.findTaskList(t);
		return Action.SUCCESS;
	}
	public void findTaskListByProjectId(){
		try {		
		List<Task> taskList = taskService.findTaskListByProjectId(projectId);
		JSONArray json = new JSONArray();
		for(Task t : taskList){
			JSONObject js = new JSONObject();
			js.put("id", t.getId());
			js.put("name", t.getName());
			js.put("username", t.getUserName());
			js.put("time", t.getTime());
			json.put(js);
			}
			this.WriteJson(taskList.size(), json);		
		} catch (JSONException e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}	
	public void insertTask(){
		try {
			String userName = (String) request.getSession().getAttribute("userName");
			task.setUserName(userName);
			int i = taskService.insertTask(task);
			this.WriteInteger(i);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}

	}
	public void updateTask(){
		try {
			int i = taskService.updateTask(task);
			this.WriteInteger(i);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}
	public void findTaskById(){
		try {		
			Task t = taskService.findTaskById(taskId);
			JSONObject js = new JSONObject();
			js.put("id", t.getId());
			js.put("name", t.getName());
			js.put("username", t.getUserName());
			js.put("time", t.getTime());
			this.WriteJson(js);		
		} catch (JSONException e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}
	public void deleteTask(){
		int i = taskService.deleteTask(taskId);
		this.WriteInteger(i);		
	}
	

	public String initGroup() {
		Group g = new Group();
		g.setParentGroupId(-1);
		g.setProjectId(projectId);
		groupList = groupService.findGroupList(g);
		return Action.SUCCESS;
	}
	
	public void findGroupListByProjectId() {
		try {
			List<Group> groupList = groupService.findGroupListByProjectId(projectId);
			JSONArray json = new JSONArray();
			for (Group g : groupList) {
				JSONObject jo = new JSONObject();
				jo.put("id", g.getId());
				jo.put("name", g.getName());
				jo.put("code", g.getCode());
				jo.put("parentGroupName", g.getParentGroupName());
				json.put(jo);
			}
			this.WriteJson(groupList.size(), json);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void insertGroup() {
		try {
			int i = groupService.insertGroup(group);
			this.WriteInteger(i);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void updateGroup() {
		try {
			int i = groupService.updateGroup(group);
			this.WriteInteger(i);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void findGroupById() {
		try {
			Group g = groupService.findGroupById(groupId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", g.getId());
			jsonObject.put("name", g.getName());
			jsonObject.put("code", g.getCode());
			jsonObject.put("parentGroupId", g.getParentGroupId());
			this.WriteJson(jsonObject);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void findGroupByCode(){
		try {
			Group g = groupService.findGroupByCode(code);
			if(g!=null){
				this.WriteInteger(1);
			}
			else{
				this.WriteInteger(0);
			}
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}
	public void deleteGroup()
	{
		int i=groupService.deleteGroup(groupId);
		this.WriteInteger(i);
	}

	public String initCategory() {
		return Action.SUCCESS;
	}

	public void findCategoryListByUserId() {
		try {
			List<Category> categoryList = categoryService.findCategoryList(category);
			JSONArray json = new JSONArray();
			for (Category c : categoryList) {
				JSONObject jo = new JSONObject();
				jo.put("id", c.getId());
				jo.put("name", c.getName());
				json.put(jo);
			}
			this.WriteJson(categoryList.size(), json);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void insertCategory() {
		try {
			User user = (User) request.getSession().getAttribute("user");
			category.setUserId(user.getId());
			int i = categoryService.insertCategory(category);
			this.WriteInteger(i);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void findCategoryById() {
		try {
			Category c = categoryService.findCategoryById(categoryId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", c.getId());
			jsonObject.put("name", c.getName());
			this.WriteJson(jsonObject);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void updateCategory(){
		try {
			int i=categoryService.updateCategory(category);
			this.WriteInteger(i);
		} catch (Exception e){
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}

	}

	public void deleteCategory() {
		try {
			int i = categoryService.deleteCategory(categoryId);
			this.WriteInteger(i);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public String initEnvironment() {
		return Action.SUCCESS;
	}

	public void findEnvironmentListByProjectId() {
		try {
			int projectId=Integer.parseInt(request.getParameter("projectId"));
			List<Environment> environmentList = environmentService.findEnvironmentListByProjectId(projectId);
			JSONArray json = new JSONArray();
			for (Environment e : environmentList) {
				JSONObject jo = new JSONObject();
				jo.put("id", e.getId());
				jo.put("name", e.getName());
				jo.put("frontUrl", e.getFrontUrl());
				jo.put("backUrl", e.getBackUrl());
				jo.put("projectId", e.getProjectId());
				json.put(jo);
			}
			this.WriteJson(environmentList.size(), json);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}
	
	public void insertEnvironment() {
		try {
			int i = environmentService.insertEnvironment(env);
			this.WriteInteger(i);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void findEnvironmentById() {
		try {
			Environment e = environmentService.findEnvironmentById(environmentId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", e.getId());
			jsonObject.put("name", e.getName());
			jsonObject.put("frontUrl", e.getFrontUrl());
			jsonObject.put("backUrl", e.getBackUrl());
			jsonObject.put("projectId", e.getProjectId());
			this.WriteJson(jsonObject);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void updateEnvironment() {
		try {
			int i = environmentService.updateEnvironment(env);
			this.WriteInteger(i);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public void deleteEnvironment() {
		try {
			int i = environmentService.deleteEnvironment(environmentId);
			this.WriteInteger(i);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	
	
	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public EnvironmentService getEnvironmentService() {
		return environmentService;
	}

	public void setEnvironmentService(EnvironmentService environmentService) {
		this.environmentService = environmentService;
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	public int getEnvironmentId() {
		return environmentId;
	}

	public void setEnvironmentId(int environmentId) {
		this.environmentId = environmentId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public List<Task> getTaskList() {
		return taskList;
	}

	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
}
