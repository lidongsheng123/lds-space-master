package com.activiti.process.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class WorkFlowVo{
	
	//批量删除使用
	private String[] ids;
	private Integer page;
	private Integer pageSize;
	//流程部署名称
	@NotNull(message = "primary mf is not null")
	private String deploymentName;
	//流程部署ID
	private String deploymentId;
	//请假单ID
	private Integer id;
	//任务ID
	private String taskId;
	//连接名称
	private String outcome;
	//批注信息
	private String comment;

	@NotNull(message = "primary mf is not null")
	MultipartFile mf;
	
}
