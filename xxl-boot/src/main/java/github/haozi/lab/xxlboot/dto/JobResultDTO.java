package github.haozi.lab.xxlboot.dto;

import com.xxl.job.core.biz.model.ReturnT;

import java.io.Serializable;

/**
 * @author wanghao
 * @Description 远程调用jobAdmin的返回体
 * @date 2019-09-25 13:52
 */
public class JobResultDTO<T> implements Serializable {
    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    public static final JobResultDTO<String> SUCCESS = new JobResultDTO<String>(null);
    public static final JobResultDTO<String> FAIL = new JobResultDTO<String>(FAIL_CODE, null);

    private int code;
    private String msg;
    private T content;

    public JobResultDTO(){}
    public JobResultDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public JobResultDTO(T content) {
        this.code = SUCCESS_CODE;
        this.content = content;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getContent() {
        return content;
    }
    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JobResultDTO [code=" + code + ", msg=" + msg + ", content=" + content + "]";
    }
}
