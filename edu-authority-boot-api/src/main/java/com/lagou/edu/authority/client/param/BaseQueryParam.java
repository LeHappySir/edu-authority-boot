package com.lagou.edu.authority.client.param;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询条件基类
 *
 * @author : chenrg
 * @create 2020/7/13 18:19
 **/
@Data
@ToString
public class BaseQueryParam implements Serializable {

    private Integer id;

    /**
     * 创建时间起始
     */
    private Date startCreateTime;

    /**
     * 创建时间结束
     */
    private Date endCreateTime;

    /**
     * 当前页
     */
    private Integer current = 1;

    /**
     * 每页大小
     */
    private Integer size = 10;

}
