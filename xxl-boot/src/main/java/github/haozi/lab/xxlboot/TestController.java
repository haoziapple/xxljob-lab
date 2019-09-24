package github.haozi.lab.xxlboot;

import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.glue.GlueTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wanghao
 * @Description
 * @date 2019-09-24 15:59
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private XxlJobExecutor xxlJobExecutor;
    @Autowired
    private ExecutorBiz executorBiz;

    @GetMapping("test1")
    public List getAdminBizList() {
        return XxlJobExecutor.getAdminBizList();
    }

    /**
     * 立即调度执行一个任务，奇怪的是没有任务日志
     * http://localhost:8801/test/test2?j=demoJobHandler&p=myparam
     * @param j
     * @param p
     * @return
     */
    @GetMapping("test2")
    public Object submitJob(String j, String p) {
        TriggerParam triggerParam = new TriggerParam();
        triggerParam.setExecutorParams(p);
        triggerParam.setJobId(2);
        triggerParam.setExecutorHandler(j);
        triggerParam.setExecutorBlockStrategy(ExecutorBlockStrategyEnum.COVER_EARLY.name());
        triggerParam.setGlueType(GlueTypeEnum.BEAN.name());
        triggerParam.setGlueSource(null);
        triggerParam.setGlueUpdatetime(System.currentTimeMillis());
        triggerParam.setLogId(2);
        triggerParam.setLogDateTim(System.currentTimeMillis());

        ReturnT<String> runResult = executorBiz.run(triggerParam);
        if(runResult.getCode() == ReturnT.SUCCESS_CODE){
            return true;
        }
        return false;
    }

    /**
     * 把正在执行的任务停止掉
     * @param id
     * @return
     */
    @GetMapping("test3")
    public Object killJob(Integer id) {
        ReturnT<String> runResult = executorBiz.kill(id);
        if(runResult.getCode() == ReturnT.SUCCESS_CODE){
            return true;
        }
        return false;
    }
}
