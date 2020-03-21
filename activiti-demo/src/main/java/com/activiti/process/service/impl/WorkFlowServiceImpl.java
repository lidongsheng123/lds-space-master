package com.activiti.process.service.impl;

import com.activiti.process.entity.*;
import com.activiti.process.service.WorkFlowService;
import com.activiti.process.utils.DataGridView;
import com.activiti.process.utils.SessionUtils;
import com.activiti.process.vo.WorkFlowVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipInputStream;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/12/4 16:52
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WorkFlowServiceImpl implements WorkFlowService {

    private final RepositoryService repositoryService;
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final HistoryService historyService;
    private final ProcessEngineConfiguration processEngineConfiguration;

    @Override
    public DataGridView queryProcessDeploy(WorkFlowVo workFlowVo) {
        if (workFlowVo.getDeploymentName() == null) {
            workFlowVo.setDeploymentName("");
        }
        String name = workFlowVo.getDeploymentName();
        //查询总条数
        long count = repositoryService.createDeploymentQuery().deploymentNameLike("%" + name + "%").count();
        //查询
        int firstResult = (workFlowVo.getPage() - 1) * workFlowVo.getPageSize();
        int maxResults = workFlowVo.getPageSize();

        List<Deployment> list = repositoryService.createDeploymentQuery().deploymentNameLike("%" + name + "%").listPage(firstResult, maxResults);
        List<ActDeploymentEntity> data = new ArrayList<>();
        for (Deployment deployment : list) {
            ActDeploymentEntity entity = new ActDeploymentEntity();
            //copy
            BeanUtils.copyProperties(deployment, entity);
            data.add(entity);
        }
        return new DataGridView(count, data);
    }

    @Override
    public DataGridView queryAllProcessDefinition(WorkFlowVo workFlowVo) {
        if (workFlowVo.getDeploymentName() == null) {
            workFlowVo.setDeploymentName("");
        }
        String name = workFlowVo.getDeploymentName();
        //先根据部署的的名称模糊查询出所有的部署的ID
        List<Deployment> dlist = repositoryService.createDeploymentQuery().deploymentNameLike("%" + name + "%").list();
        Set<String> deploymentIds = new HashSet<>();
        for (Deployment deployment : dlist) {
            deploymentIds.add(deployment.getId());
        }
        long count = 0;
        List<ActProcessDefinitionEntity> data = new ArrayList<>();
        if (deploymentIds.size() > 0) {
            count = this.repositoryService.createProcessDefinitionQuery().deploymentIds(deploymentIds).count();
            //查询流程部署信息
            int firstResult = (workFlowVo.getPage() - 1) * workFlowVo.getPageSize();
            int maxResults = workFlowVo.getPageSize();
            List<ProcessDefinition> list = this.repositoryService.createProcessDefinitionQuery().deploymentIds(deploymentIds).listPage(firstResult, maxResults);
            for (ProcessDefinition pd : list) {
                ActProcessDefinitionEntity entity = new ActProcessDefinitionEntity();
                BeanUtils.copyProperties(pd, entity);
                data.add(entity);
            }
        }
        return new DataGridView(count, data);
    }

    @Override
    public void addWorkFlow(InputStream inputStream, String deploymentName) {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        try {
            this.repositoryService.createDeployment()
                    .name(deploymentName)
                    .addZipInputStream(zipInputStream)
                    .deploy();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                zipInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteWorkFlow(String deploymentId) {
        this.repositoryService.deleteDeployment(deploymentId, true);
    }

    @Override
    public InputStream queryProcessDeploymentImage(String deploymentId) {
        // 1,根据部署ID查询流程定义对象
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId).singleResult();
        // 2从流程定义对象里面得到图片的名称
        String resourceName = processDefinition.getDiagramResourceName();
        // 3使用部署ID和图片名称去查询图片流
        return this.repositoryService.getResourceAsStream(deploymentId, resourceName);
    }

    @Override
    public void  startProcess(Integer leaveBillId) {

        // 找到流程的key
        String processDefinitionKey = LeaveBillEntity.class.getSimpleName();
        String businessKey = processDefinitionKey + ":" + leaveBillId;// LeaveBillEntity:1
        Map<String, Object> variables = new HashMap<>();
        // 设置流程变量去设置下个任务的办理人
        variables.put("username", SessionUtils.getCurrentUserName());
        this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
    }

    @Override
    public DataGridView queryCurrentUserTask(WorkFlowVo workFlowVo) {
        // 1,得到办理人信息
        String assignee = SessionUtils.getCurrentUserName();
        // 2,查询总数
        long count = this.taskService.createTaskQuery().taskAssignee(assignee).count();
        // 3,查询集合
        int firstResult = (workFlowVo.getPage() - 1) * workFlowVo.getPageSize();
        int maxResults = workFlowVo.getPageSize();
        List<Task> list = this.taskService.createTaskQuery().taskAssignee(assignee).listPage(firstResult, maxResults);
        List<ActTaskEntity> taskEntities = new ArrayList<>();
        for (Task task : list) {
            ActTaskEntity entity = new ActTaskEntity();
            BeanUtils.copyProperties(task, entity);
            taskEntities.add(entity);
        }
        return new DataGridView(count, taskEntities);
    }

    @Override
    public List<String> queryOutComeByTaskId(String taskId) {
        return null;
    }

    @Override
    public DataGridView queryCommentByTaskId(String taskId) {
        // 1,根据任务ID查询任务实例
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        // 2,从任务里面取出流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        List<Comment> comments = taskService.getProcessInstanceComments(processInstanceId);
        List<ActCommentEntity> data = new ArrayList<>();
        if (null != comments && comments.size() > 0) {
            for (Comment comment : comments) {
                ActCommentEntity entity = new ActCommentEntity();
                BeanUtils.copyProperties(comment, entity);
                data.add(entity);
            }
        }
        return new DataGridView((long) data.size(), data);
    }

    @Override
    public void completeTask(WorkFlowVo workFlowVo) {
        String taskId = workFlowVo.getTaskId();// 任务ID
        String outcome = workFlowVo.getOutcome();// 连接名称
        String comment = workFlowVo.getComment();// 批注信息

        // 1,根据任务ID查询任务实例
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        // 2,从任务里面取出流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        // 设置批注人名
        String userName = SessionUtils.getCurrentUserName();
        Authentication.setAuthenticatedUserId(userName);
        // 添加批注信息
        this.taskService.addComment(taskId, processInstanceId, "[" + outcome + "]" + comment);
        // 完成任务
        Map<String, Object> variables = new HashMap<>();
        variables.put("outcome", outcome);
        this.taskService.complete(taskId, variables);
        // 判断任务是否结束
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        if (processInstance == null) {
            System.out.println("任务执行结束");
        }

    }

    @Override
    public ProcessDefinition queryProcessDefinitionByTaskId(String taskId) {
        // 1,根据任务ID查询任务实例
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        // 2,取出流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        // 3,根据流程实例ID查询流程实例对象
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        // 4，取出流程部署ID
        String processDefinitionId = processInstance.getProcessDefinitionId();
        // 5,查询流程定义对象
        return this.repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
    }

    @Override
    public InputStream queryTaskCoordinateByTaskId(String taskId) {
        // 1,根据任务ID查询任务实例
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        // 2,取出流程定义ID
        String processDefinitionId = task.getProcessDefinitionId();
        // 3,取出流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        //4,获取历史流程实例
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        if (historicProcessInstance == null) {
            throw new NullPointerException("获取流程实例ID[" + processDefinitionId + "]对应的历史流程实例失败！");
        } else {
            // 获取流程定义
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(historicProcessInstance.getProcessDefinitionId());
            // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
            List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            // 已执行的节点ID集合
            List<String> executedActivityIdList = new ArrayList<>();
            int index = 1;
            log.info("获取已经执行的节点ID");
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                executedActivityIdList.add(activityInstance.getActivityId());
                log.info("第[" + index + "]个已执行节点=" + activityInstance.getActivityId() + " : " + activityInstance.getActivityName());
                index++;
            }
            BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
            // 获取流程走过的线
            List<String> flowIds = getHighLightedFlows(bpmnModel, processDefinition, historicActivityInstanceList);
            // 获取流程图图像字符流
            ProcessDiagramGenerator pec = processEngineConfiguration.getProcessDiagramGenerator();
            //配置字体
            return pec.generateDiagram(bpmnModel, "png", executedActivityIdList, flowIds,
                    "宋体", "微软雅黑", "黑体", null, 2.0);
        }
    }

    /**
     * 查询我的审批记录
     */
    public DataGridView queryCurrentUserHistoryTask(WorkFlowVo workFlowVo) {
        String assignee = SessionUtils.getCurrentUserName();
        int firstResult = (workFlowVo.getPage() - 1) * workFlowVo.getPageSize();
        int maxResults = workFlowVo.getPageSize();
        long count = this.historyService.createHistoricTaskInstanceQuery().taskAssignee(assignee).count();
        List<HistoricTaskInstance> list = this.historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(assignee).listPage(firstResult, maxResults);
        return new DataGridView(count, list);
    }


    private List<String> getHighLightedFlows(BpmnModel bpmnModel, ProcessDefinitionEntity processDefinitionEntity, List<HistoricActivityInstance> historicActivityInstances) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //24小时制
        List<String> highFlows = new ArrayList<>();// 用以保存高亮的线flowId

        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
            // 对历史流程节点进行遍历
            // 得到节点定义的详细信息
            FlowNode activityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(i).getActivityId());


            List<FlowNode> sameStartTimeNodes = new ArrayList<>();// 用以保存后续开始时间相同的节点
            FlowNode sameActivityImpl1 = null;

            HistoricActivityInstance activityImpl_ = historicActivityInstances.get(i);// 第一个节点
            HistoricActivityInstance activityImp2_;

            for (int k = i + 1; k <= historicActivityInstances.size() - 1; k++) {
                activityImp2_ = historicActivityInstances.get(k);// 后续第1个节点

                if (activityImpl_.getActivityType().equals("userTask") && activityImp2_.getActivityType().equals("userTask") &&
                        df.format(activityImpl_.getStartTime()).equals(df.format(activityImp2_.getStartTime()))) //都是usertask，且主节点与后续节点的开始时间相同，说明不是真实的后继节点
                {

                } else {
                    sameActivityImpl1 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(k).getActivityId());//找到紧跟在后面的一个节点
                    break;
                }

            }
            sameStartTimeNodes.add(sameActivityImpl1); // 将后面第一个节点放在时间相同节点的集合里
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点

                if (df.format(activityImpl1.getStartTime()).equals(df.format(activityImpl2.getStartTime()))) {// 如果第一个节点和第二个节点开始时间相同保存
                    FlowNode sameActivityImpl2 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {// 有不相同跳出循环
                    break;
                }
            }
            List<SequenceFlow> pvmTransitions = activityImpl.getOutgoingFlows(); // 取出节点的所有出去的线

            for (SequenceFlow pvmTransition : pvmTransitions) {// 对所有的线进行遍历
                FlowNode pvmActivityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(pvmTransition.getTargetRef());// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }

        }
        return highFlows;
    }
}
