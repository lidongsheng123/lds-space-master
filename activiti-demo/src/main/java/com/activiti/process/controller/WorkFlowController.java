package com.activiti.process.controller;

import com.activiti.process.service.WorkFlowService;
import com.activiti.process.utils.DataGridView;
import com.activiti.process.vo.WorkFlowVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Mr.Li
 * @date ：Created in 2019/12/5 10:24
 */
@RestController
@RequiredArgsConstructor
public class WorkFlowController {
    private final WorkFlowService workFlowService;
    private final HttpServletResponse response;

    /**
     * 获取部署信息数据
     */
    @PostMapping("/getAllDeployment")
    public DataGridView loadAllDeployment(@RequestBody WorkFlowVo workFlowVo) {
        return this.workFlowService.queryProcessDeploy(workFlowVo);
    }

    /**
     * 获取流程定义信息数据
     */
    @PostMapping("/getAllProcessDefinition")
    public DataGridView loadAllProcessDefinition(@RequestBody WorkFlowVo workFlowVo) {
        return this.workFlowService.queryAllProcessDefinition(workFlowVo);
    }


    /**
     * 添加流程部署
     */
    @PostMapping("/addWorkFlow")
    public Map<String, Object> addWorkFlow(@Validated WorkFlowVo workFlowVo) {
        Map<String, Object> map = new HashMap<>();
        try {
            this.workFlowService.addWorkFlow(workFlowVo.getMf().getInputStream(), workFlowVo.getDeploymentName());
            map.put("msg", "部署成功");
        } catch (Exception e) {
            map.put("msg", "部署失败");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 批量删除流程部署
     */
    @RequestMapping("/batchDeleteWorkFlow")
    public Map<String, Object> batchDeleteWorkFlow(@RequestBody WorkFlowVo workFlowVo) {
        Map<String, Object> map = new HashMap<>();
        try {
            String[] deploymentIds = workFlowVo.getIds();
            for (String deploymentId : deploymentIds) {
                this.workFlowService.deleteWorkFlow(deploymentId);
            }
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("msg", "删除失败");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查看流程图
     */
    @GetMapping("/viewProcessImage/{deploymentId}")
    public void viewProcessImage(@PathVariable("deploymentId") String deploymentId) {
        InputStream stream = this.workFlowService.queryProcessDeploymentImage(deploymentId);
        try {
            BufferedImage image = ImageIO.read(stream);
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "JPEG", outputStream);
            stream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动流程
     */
    @RequestMapping("/startProcess")
    @ResponseBody
    public Map<String, Object> startProcess(WorkFlowVo workFlowVo) {
        Map<String, Object> map = new HashMap<>();
        try {
            Integer leaveBillId = workFlowVo.getId();
            this.workFlowService.startProcess(leaveBillId);
            map.put("msg", "启动成功");
        } catch (Exception e) {
            map.put("msg", "启动失败");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询当前登陆人的代办任务
     */
    @PostMapping("/getCurrentUserTask")
    public DataGridView loadCurrentUserTask(@RequestBody WorkFlowVo workFlowVo) {
        return this.workFlowService.queryCurrentUserTask(workFlowVo);
    }

    /**
     * 根据任务ID查看流程进度图
     * 跟踪流程执行情况用红色框在流程图上标识路线跟节点
     */
    @GetMapping("/getViewProcessByTaskId/{taskId}")
    public void toViewProcessByTaskId(@PathVariable(name = "taskId") String taskId) {
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        try (InputStream resourceAsStream = workFlowService.queryTaskCoordinateByTaskId(taskId)) {
            while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成任务
     */
    @PostMapping("doTask")
    public Map<String, Object> doTask(WorkFlowVo workFlowVo) {
        Map<String, Object> map = new HashMap<>(10);
        try {
            this.workFlowService.completeTask(workFlowVo);
            map.put("msg", "任务完成成功");
        } catch (Exception e) {
            map.put("msg", "任务完成失败");
            e.printStackTrace();
        }
        return map;
    }


}
