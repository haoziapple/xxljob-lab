package github.haozi.lab.xxlboot.config;

import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.xxl.rpc.remoting.invoker.call.CallType;
import com.xxl.rpc.remoting.invoker.reference.XxlRpcReferenceBean;
import com.xxl.rpc.remoting.invoker.route.LoadBalance;
import com.xxl.rpc.remoting.net.NetEnum;
import com.xxl.rpc.serialize.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

    /**
     * 执行器API服务, 参考代码：com.xxl.job.executor.ExecutorBizTest
     * 1、心跳检测(beat)：调度中心使用
     * 2、忙碌检测(idleBeat)：调度中心使用
     * 3、触发任务执行(run)：调度中心使用；本地进行任务开发时，可使用该API服务模拟触发任务；
     * 4、获取Rolling Log(log)：调度中心使用
     * 5、终止任务(kill)：调度中心使用
     * @return
     */
    @Bean
    public ExecutorBiz executorBiz() {
        ExecutorBiz executorBiz = (ExecutorBiz) new XxlRpcReferenceBean(
                NetEnum.NETTY_HTTP,
                Serializer.SerializeEnum.HESSIAN.getSerializer(),
                CallType.SYNC,
                LoadBalance.ROUND,
                ExecutorBiz.class,
                null,
                10000,
                ip+":"+port,
                null,
                null,
                null).getObject();

        return executorBiz;
    }

    /**
     * 1、任务结果回调服务；
     * 2、执行器注册服务；
     * 3、执行器注册摘除服务；
     * @return
     */
    @Bean
    public AdminBiz adminBiz() {
        AdminBiz adminBiz = (AdminBiz) new XxlRpcReferenceBean(
                NetEnum.NETTY_HTTP,
                Serializer.SerializeEnum.HESSIAN.getSerializer(),
                CallType.SYNC,
                LoadBalance.ROUND,
                AdminBiz.class,
                null,
                3000,
                adminAddresses,
                accessToken,
                null,
                null).getObject();

        return adminBiz;
    }



    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     *
     *      1、引入依赖：
     *          <dependency>
     *             <groupId>org.springframework.cloud</groupId>
     *             <artifactId>spring-cloud-commons</artifactId>
     *             <version>${version}</version>
     *         </dependency>
     *
     *      2、配置文件，或者容器启动变量
     *          spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     *
     *      3、获取IP
     *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */


}