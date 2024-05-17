package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门数据
     *
     * @return
     */
// @RequestMapping(value = "/depts",method = RequestMethod.GET) // 指定请求方式为GET
    @GetMapping
    public Result list() {
        log.info("查询全部数据");

        // 调用service查询部门数据
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    /**
     * 删除部门数据
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门数据" + id);

        // 调用service删除部门数据
        deptService.delete(id);

        return Result.success();
    }

    /**
     * 新增部门
     *
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门数据" + dept);

        // 调用service新增部门数据
        deptService.add(dept);

        return Result.success();
    }

    /**
     * 根据id查询部门
     * @return
     */
    @GetMapping("/{id}")
    public Result listById(@PathVariable Integer id){
        log.info("根据id查询部门数据" + id);

        // 调用service删除部门数据
        Dept dept =  deptService.listById(id);

        return Result.success(dept);
    }

    /**
     * 修改部门数据
     * @param dept
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门数据" + dept);

        // 调用service新增部门数据
        deptService.update(dept);

        return Result.success();
    }
}
