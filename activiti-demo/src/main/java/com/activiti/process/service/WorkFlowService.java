package com.activiti.process.service;

import com.activiti.process.utils.DataGridView;
import com.activiti.process.vo.WorkFlowVo;
import org.activiti.engine.repository.ProcessDefinition;

import java.io.InputStream;
import java.util.List;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/12/4 16:37
 */
public interface WorkFlowService {

    //查询流程部署信息
    public DataGridView queryProcessDeploy(WorkFlowVo workFlowVo);

    //查询所有的流程定义
    public DataGridView queryAllProcessDefinition(WorkFlowVo workFlowVo);

    //添加流程部署
    public void addWorkFlow(InputStream inputStream, String deploymentName);

    //根据流程部署ID删除流程部署信息
    public void deleteWorkFlow(String deploymentId);

    //根据流程部署ID查询流程图
    public InputStream queryProcessDeploymentImage(String deploymentId);

    //启动流程
    public void startProcess(Integer leaveBillId);

    //查询当前登陆用户的待办任务
    public  DataGridView queryCurrentUserTask(WorkFlowVo workFlowVo);


    //根据任务ID查询连线信息
    public List<String> queryOutComeByTaskId(String taskId);

    //根据任务ID查询批注信息
    public DataGridView queryCommentByTaskId(String taskId);

    //完成任务
    public void completeTask(WorkFlowVo workFlowVo);

    //根据任务ID查询流程定义对象
    public ProcessDefinition queryProcessDefinitionByTaskId(String taskId);

    InputStream  queryTaskCoordinateByTaskId(String taskId);
}

