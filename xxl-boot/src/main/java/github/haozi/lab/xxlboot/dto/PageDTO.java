package github.haozi.lab.xxlboot.dto;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanghao
 * @Description
 * @date 2019-09-25 11:26
 */
@ToString
public class PageDTO<T> implements Serializable {
    /**
     * 总记录数
     */
    private Integer recordsTotal;

    /**
     * 过滤后的总记录数
     */
    private Integer recordsFiltered;

    private List<T> data;

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
