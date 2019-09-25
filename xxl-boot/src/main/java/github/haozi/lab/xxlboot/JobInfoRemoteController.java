package github.haozi.lab.xxlboot;

import github.haozi.lab.xxlboot.dto.JobInfoDTO;
import github.haozi.lab.xxlboot.dto.JobResultDTO;
import github.haozi.lab.xxlboot.dto.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static github.haozi.lab.xxlboot.dto.JobConstants.*;

/**
 * @author wanghao
 * @Description 远程操作jobinfo的Controller
 * @date 2019-09-25 10:12
 */
@RestController
@RequestMapping("job")
@Slf4j
public class JobInfoRemoteController {
    private static final Integer SUCCESS_CODE = 200;

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.admin.username}")
    private String userName;

    @Value("${xxl.job.admin.password}")
    private String password;

    private String cookie = null;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("testLogin")
    public void testLogin() {
        login();
    }

    @PostMapping("add")
    public JobResultDTO add(JobInfoDTO jobInfoDTO) {
        MultiValueMap<String, Object> params = getParamsMap(jobInfoDTO);
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<JobResultDTO> response = restTemplate.exchange(adminAddresses + "/jobinfo/add", HttpMethod.POST, request, JobResultDTO.class);
        checkResponse(response);
        return response.getBody();
    }

    @PostMapping("update")
    public JobResultDTO update(JobInfoDTO jobInfoDTO) {
        MultiValueMap<String, Object> params = getParamsMap(jobInfoDTO);
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<JobResultDTO> response = restTemplate.exchange(adminAddresses + "/jobinfo/update", HttpMethod.POST, request, JobResultDTO.class);
        checkResponse(response);
        return response.getBody();
    }

    @GetMapping("remove")
    public JobResultDTO remove(Integer id) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("id", id);
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<JobResultDTO> response = restTemplate.exchange(adminAddresses + "/jobinfo/remove", HttpMethod.POST, request, JobResultDTO.class);
        checkResponse(response);
        return response.getBody();
    }

    @GetMapping("stop")
    public JobResultDTO stop(Integer id) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("id", id);
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<JobResultDTO> response = restTemplate.exchange(adminAddresses + "/jobinfo/stop", HttpMethod.POST, request, JobResultDTO.class);
        checkResponse(response);
        return response.getBody();
    }

    @GetMapping("start")
    public JobResultDTO start(Integer id) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("id", id);
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<JobResultDTO> response = restTemplate.exchange(adminAddresses + "/jobinfo/start", HttpMethod.POST, request, JobResultDTO.class);
        checkResponse(response);
        return response.getBody();
    }

    @GetMapping("trigger")
    public JobResultDTO trigger(Integer id, String executorParam) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("id", id);
        params.add("executorParam", executorParam);
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<JobResultDTO> response = restTemplate.exchange(adminAddresses + "/jobinfo/trigger", HttpMethod.POST, request, JobResultDTO.class);
        checkResponse(response);
        return response.getBody();
    }

    private MultiValueMap<String, Object> getParamsMap(JobInfoDTO jobInfoDTO) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        if (jobInfoDTO.getId() != null) {
            params.add("id", jobInfoDTO.getId());
        }

        if (jobInfoDTO.getJobGroup() != null) {
            params.add("jobGroup", jobInfoDTO.getJobGroup());
        } else {
            params.add("jobGroup", 1);
        }

        if (jobInfoDTO.getJobCron() != null) {
            params.add("jobCron", jobInfoDTO.getJobCron());
        }

        if (jobInfoDTO.getJobDesc() != null) {
            params.add("jobDesc", jobInfoDTO.getJobDesc());
        } else {
            params.add("jobDesc", "remote client set this job!");
        }

        if (jobInfoDTO.getAuthor() != null) {
            params.add("author", jobInfoDTO.getAuthor());
        } else {
            params.add("author", "remote client");
        }

        if (jobInfoDTO.getAlarmEmail() != null) {
            params.add("alarmEmail", jobInfoDTO.getAlarmEmail());
        } else {
            params.add("alarmEmail", "remote-client@163.com");
        }
        if (jobInfoDTO.getExecutorRouteStrategy() != null) {
            params.add("executorRouteStrategy", jobInfoDTO.getExecutorRouteStrategy());
        } else {
            params.add("executorRouteStrategy", RouteStrategy.ROUND);
        }

        if (jobInfoDTO.getExecutorHandler() != null) {
            params.add("executorHandler", jobInfoDTO.getExecutorHandler());
        }

        if (jobInfoDTO.getExecutorParam() != null) {
            params.add("executorParam", jobInfoDTO.getExecutorParam());
        }

        if (jobInfoDTO.getExecutorBlockStrategy() != null) {
            params.add("executorBlockStrategy", jobInfoDTO.getExecutorBlockStrategy());
        } else {
            params.add("executorBlockStrategy", BlockStrategy.SERIAL_EXECUTION);
        }

        if (jobInfoDTO.getExecutorTimeout() != null) {
            params.add("executorTimeout", jobInfoDTO.getExecutorTimeout());
        } else {
            params.add("executorTimeout", 0);
        }

        if (jobInfoDTO.getExecutorFailRetryCount() != null) {
            params.add("executorFailRetryCount", jobInfoDTO.getExecutorFailRetryCount());
        } else {
            params.add("executorFailRetryCount", 0);
        }

        if (jobInfoDTO.getGlueType() != null) {
            params.add("glueType", jobInfoDTO.getGlueType());
        } else {
            params.add("glueType", GlueType.BEAN);
        }
        if (jobInfoDTO.getGlueSource() != null) {
            params.add("glueSource", jobInfoDTO.getGlueSource());
        }
        if (jobInfoDTO.getGlueRemark() != null) {
            params.add("glueRemark", jobInfoDTO.getGlueRemark());
        }
        if (jobInfoDTO.getChildJobId() != null) {
            params.add("childJobId", jobInfoDTO.getChildJobId());
        }
        return params;
    }


    /**
     * 查询任务
     * @param start
     * @param length
     * @param jobGroup 执行器id(如何获取？目前只能到管理端通过F12查看)
     * @param triggerStatus   1-启动 0-停止 -1全部
     * @param jobDesc
     * @param executorHandler
     * @param author
     * @return
     */
    @GetMapping("pageList")
    public PageDTO<JobInfoDTO> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                        @RequestParam(required = false, defaultValue = "10") int length,
                                        @RequestParam(required = false, defaultValue = "1") int jobGroup,
                                        @RequestParam(required = false, defaultValue = "-1") int triggerStatus,
                                        @RequestParam(required = false, defaultValue = "") String jobDesc,
                                        @RequestParam(required = false, defaultValue = "") String executorHandler,
                                        @RequestParam(required = false, defaultValue = "") String author) throws Exception {
        HttpHeaders httpHeaders = getHttpHeaders();

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("jobGroup", jobGroup);
        params.add("triggerStatus", triggerStatus);
        params.add("start", start);
        params.add("length", length);
        params.add("jobDesc", jobDesc);
        params.add("executorHandler", executorHandler);
        params.add("author", author);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<PageDTO> response = restTemplate.exchange(adminAddresses + "/jobinfo/pageList", HttpMethod.POST, request, PageDTO.class);
        checkResponse(response);

        PageDTO<JobInfoDTO> result = new PageDTO();
        List<JobInfoDTO> jobInfoDTOList = new ArrayList<>();
        result.setRecordsFiltered(response.getBody().getRecordsFiltered());
        result.setRecordsTotal(response.getBody().getRecordsTotal());
        result.setData(jobInfoDTOList);

        if (!CollectionUtils.isEmpty(response.getBody().getData())) {
            for (Object o : response.getBody().getData()) {
                JobInfoDTO jobInfoDTO = new JobInfoDTO();
                BeanUtils.populate(jobInfoDTO, (Map) o);
                jobInfoDTOList.add(jobInfoDTO);
            }
        }
        return result;
    }

    /**
     * 定时执行查询，保证会话不过期
     */
    @Scheduled(fixedDelay = 5 * 60 * 1000L)
    public void keepAlive() throws Exception {
        PageDTO pageDTO = this.pageList(0, 1, 1, -1, "", "", "");
        log.info("定时执行查询，保证会话不过期: " + pageDTO);
    }

    @PostConstruct
    private void login() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("userName", userName);
        params.add("password", password);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(adminAddresses + "/login", HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("尝试登陆xxl-job-admin失败, " +
                    "responseHttpCode:" + response.getStatusCode().toString() +
                    " ,response" + response.getBody() == null ? "null" : response.getBody().toString());
        }

        if (response.getBody() == null || !SUCCESS_CODE.equals(response.getBody().get("code"))) {
            throw new RuntimeException("尝试登陆xxl-job-admin失败, " +
                    " ,response" + response.getBody() == null ? "null" : response.getBody().toString());
        }

        if (!CollectionUtils.isEmpty(response.getHeaders().get("Set-Cookie"))) {
            this.cookie = response.getHeaders().get("Set-Cookie").get(0);
        }
        log.info("login succeed, set cookie:" + this.cookie);

    }

    private void checkResponse(ResponseEntity response) {
        if (response.getStatusCode() == HttpStatus.FOUND) {
            // 需要登陆，返回302
            this.login();
            throw new RuntimeException("需要登陆，返回302, 请稍后再试");
        }

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("response" + response.getBody() == null ? "null" : response.getBody().toString());
        }
    }

    /**
     * 构造请求头，如果cookie为空，就尝试登陆并初始化cookie
     *
     * @return
     */
    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (StringUtils.isEmpty(this.cookie)) {
            this.login();
        }
        httpHeaders.set("Cookie", this.cookie);
        return httpHeaders;
    }
}
