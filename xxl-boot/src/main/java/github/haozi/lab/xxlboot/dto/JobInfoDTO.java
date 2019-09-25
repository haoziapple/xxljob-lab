package github.haozi.lab.xxlboot.dto;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanghao
 * @Description
 * @date 2019-09-25 11:25
 */
@ToString
public class JobInfoDTO implements Serializable {
    private Integer id;				// 主键ID
    private Integer jobGroup;		// 执行器主键ID
    private String jobCron;		// 任务执行CRON表达式
    private String jobDesc;
    private Date addTime;
    private Date updateTime;
    private String author;		// 负责人
    private String alarmEmail;	// 报警邮件
    /**
     *  执行器路由策略
     *  FIRST-第一个
     *  LAST-最后一个
     *  ROUND-轮询
     *  RANDOM-随机
     *  CONSISTENT_HASH-一致性哈希
     *  LEAST_FREQUENTLY_USED-最不经常使用
     *  LEAST_RECENTLY_USED-最近最久未使用
     *  FAILOVER-故障转移
     *  BUSYOVER-忙碌转移
     *  SHARDING_BROADCAST-分片广播
     */
    private String executorRouteStrategy;
    private String executorHandler;		    // 执行器，任务Handler名称
    private String executorParam;		    // 执行器，任务参数
    /**
     * 	阻塞处理策略
     * 	SERIAL_EXECUTION-单机串行
     * 	DISCARD_LATER-丢弃后续调度
     * 	COVER_EARLY-覆盖之前调度
     */
    private String executorBlockStrategy;
    private Integer executorTimeout;     		// 任务执行超时时间，单位秒
    private Integer executorFailRetryCount;		// 失败重试次数
    private String glueType;		// GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
    private String glueSource;		// GLUE源代码
    private String glueRemark;		// GLUE备注
    private Date glueUpdatetime;	// GLUE更新时间
    private String childJobId;		// 子任务ID，多个逗号分隔
    private Integer triggerStatus;		// 调度状态：0-停止，1-运行
    private Long triggerLastTime;	// 上次调度时间
    private Long triggerNextTime;	// 下次调度时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(Integer jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlarmEmail() {
        return alarmEmail;
    }

    public void setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
    }

    public String getExecutorRouteStrategy() {
        return executorRouteStrategy;
    }

    public void setExecutorRouteStrategy(String executorRouteStrategy) {
        this.executorRouteStrategy = executorRouteStrategy;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    public String getExecutorParam() {
        return executorParam;
    }

    public void setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
    }

    public String getExecutorBlockStrategy() {
        return executorBlockStrategy;
    }

    public void setExecutorBlockStrategy(String executorBlockStrategy) {
        this.executorBlockStrategy = executorBlockStrategy;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public Integer getExecutorFailRetryCount() {
        return executorFailRetryCount;
    }

    public void setExecutorFailRetryCount(Integer executorFailRetryCount) {
        this.executorFailRetryCount = executorFailRetryCount;
    }

    public String getGlueType() {
        return glueType;
    }

    public void setGlueType(String glueType) {
        this.glueType = glueType;
    }

    public String getGlueSource() {
        return glueSource;
    }

    public void setGlueSource(String glueSource) {
        this.glueSource = glueSource;
    }

    public String getGlueRemark() {
        return glueRemark;
    }

    public void setGlueRemark(String glueRemark) {
        this.glueRemark = glueRemark;
    }

    public Date getGlueUpdatetime() {
        return glueUpdatetime;
    }

    public void setGlueUpdatetime(Date glueUpdatetime) {
        this.glueUpdatetime = glueUpdatetime;
    }

    public String getChildJobId() {
        return childJobId;
    }

    public void setChildJobId(String childJobId) {
        this.childJobId = childJobId;
    }

    public Integer getTriggerStatus() {
        return triggerStatus;
    }

    public void setTriggerStatus(Integer triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    public Long getTriggerLastTime() {
        return triggerLastTime;
    }

    public void setTriggerLastTime(Long triggerLastTime) {
        this.triggerLastTime = triggerLastTime;
    }

    public Long getTriggerNextTime() {
        return triggerNextTime;
    }

    public void setTriggerNextTime(Long triggerNextTime) {
        this.triggerNextTime = triggerNextTime;
    }
}
