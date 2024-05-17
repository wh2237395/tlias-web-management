package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除员工数据
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工数据
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据id查询员工数据
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 修改员工数据
     * @param emp
     */
    void update(Emp emp);

    /**
     * 员工登录
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
