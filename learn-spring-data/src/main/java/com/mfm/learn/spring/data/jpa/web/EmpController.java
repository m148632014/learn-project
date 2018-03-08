package com.mfm.learn.spring.data.jpa.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfm.learn.spring.data.jpa.entity.Emp;
import com.mfm.learn.spring.data.jpa.entity.dto.EmpDeptDto;
import com.mfm.learn.spring.data.jpa.entity.projection.EmpDept;
import com.mfm.learn.spring.data.jpa.repository.EmpJpaRepository;
import com.mfm.learn.spring.data.jpa.service.impl.EmpServiceImpl;

/**
 * @author MengFanmao
 * @since 2017年7月26日
 */
@RestController
@RequestMapping("/emp/join")
public class EmpController {
    @Autowired
    EmpJpaRepository empJpaRepository;
    @Autowired
    EmpServiceImpl empService;

    //测试nativeSQL
    @RequestMapping("/q3")
    public void test01() {
        this.empService.q3();
    }

    //测试JPQL/HQL
    @RequestMapping("/test2")
    public void test02() {
        this.empService.q2();
    }

    //spring-data-jpa默认提供的查询方法
    @GetMapping("getOne")
    public Emp getOne(Integer empNo) {
        return this.empJpaRepository.getOne(empNo);
    }

    @GetMapping("findOneByExample")
    public Emp findOneByExample(Emp emp) {
        return this.empJpaRepository.findOne(Example.of(emp));
    }

    @GetMapping("findOneByExampleCount")
    public Long findOneByExampleCount(Emp emp) {
        return this.empJpaRepository.count(Example.of(emp));
    }

    @GetMapping("findOneByExampleExists")
    public boolean findOneByExampleExists(Emp emp) {
        return this.empJpaRepository.exists(Example.of(emp));
    }

    @GetMapping("findAll")
    public List<Emp> findAll() {
        return this.empJpaRepository.findAll();
    }

    @GetMapping("findAllBySort")
    public List<Emp> findAllBySort() {
        return this.empJpaRepository.findAll(new Sort(Direction.ASC, "ename"));
    }

    @GetMapping("findAllByPage")
    public Page<Emp> findAllByPage() {
        return this.empJpaRepository.findAll(new PageRequest(1, 2));
    }

    @GetMapping("findAllByIds")
    public List<Emp> findAllByIds(Integer[] ids) {
        return this.empJpaRepository.findAll(Arrays.asList(ids));
    }

    @GetMapping("findAllByExample")
    public List<Emp> findAllByExample(Emp emp) {
        return this.empJpaRepository.findAll(Example.of(emp));
    }

    @PostMapping("deleteInBatch")
    public void deleteInBatch(List<Emp> emps) {
        this.empJpaRepository.deleteInBatch(emps);
    }

    @GetMapping("deleteAllInBatch")
    public void deleteAllInBatch() {
        this.empJpaRepository.deleteAllInBatch();
    }

    //=======================join查询分割线==============================

    /**
     * http://127.0.0.1:8080/emp/join/findEmpByDname?dname=RESEARCH
     * JPQL:join查询多个实体，只返回一个实体
     *
     * @param dname
     * @return
     */
    @RequestMapping("/findEmpByDname")
    public List<Emp> findEmpByDname(String dname) {
        return this.empJpaRepository.findEmpByDname(dname);
    }

    /**
     * http://127.0.0.1:8080/emp/join/findEmpByDnameSQL?dname=RESEARCH
     * SQL:join查询多个实体，只返回一个实体
     *
     * @param dname
     * @return
     */
    @RequestMapping("/findEmpByDnameSQL")
    public List<Emp> findEmpByDnameSQL(String dname) {
        return this.empJpaRepository.findEmpByDnameSQL(dname);
    }

    /**
     * http://127.0.0.1:8080/emp/join/findAllEmpDname
     * JPQL:join查询多个实体，返回不同实体的部分字段
     *
     * @return 默认返回了List<Object[]>
     */
    @RequestMapping("/findAllEmpDname")
    public List<EmpDeptDto> findAllEmpDname() {
        List<Object[]> dtos = this.empJpaRepository.findAllEmpDname();
        return dtos
            .stream()
            .map(
                dto -> new EmpDeptDto((Integer) dto[0], (String) dto[1],
                    (String) dto[2])).collect(Collectors.toList());
    }

    /**
     * http://127.0.0.1:8080/emp/join/findAllEmpDnameSQL
     * SQL:join查询多个实体，返回不同实体的部分字段
     *
     * @return 默认返回了List<Object[]>
     */
    @RequestMapping("/findAllEmpDnameSQL")
    public List<EmpDeptDto> findAllEmpDnameSQL() {
        List<Object[]> dtos = this.empJpaRepository.findAllEmpDnameSQL();
        return dtos
            .stream()
            .map(
                dto -> new EmpDeptDto((Integer) dto[0], (String) dto[1],
                    (String) dto[2])).collect(Collectors.toList());
    }

    /**
     * http://127.0.0.1:8080/emp/join/getEmpDept?empNo=7369
     * JPQL:join查询多个实体，返回不同实体的部分字段，自动封装成投影接口
     *
     * @param empNo
     * @return
     */
    @RequestMapping("/getEmpDept")
    public EmpDept getEmpDept(Integer empNo) {
        return this.empJpaRepository.getEmpDept(empNo);
    }

    /**
     * http://127.0.0.1:8080/emp/join/getEmpDeptList
     * JPQL:join查询多个实体，返回不同实体的部分字段，自动封装成投影接口
     *
     * @return
     */
    @RequestMapping("/getEmpDeptList")
    public List<EmpDept> getEmpDeptList() {
        return this.empJpaRepository.getEmpDeptList();
    }

    /**
     * http://127.0.0.1:8080/emp/join/getEmpDeptSQL?empNo=7369
     *
     * @param empNo
     * @return
     */
    //使用new，不行 http://127.0.0.1:8080/emp/join/getEmpDeptSQL?empNo=7369
    @RequestMapping("/getEmpDeptSQL")
    public EmpDept getEmpDeptSQL(Integer empNo) {
        return this.empJpaRepository.getEmpDeptSQL(empNo);
    }

    //SQL使用别名，不行  http://127.0.0.1:8080/emp/join/getEmpDeptListSQL
    @RequestMapping("/getEmpDeptListSQL")
    public List<EmpDept> getEmpDeptListSQL() {
        return this.empJpaRepository.getEmpDeptListSQL();
    }

    //JPQL使用别名，不行  http://127.0.0.1:8080/emp/join/getEmpDeptDto?empNo=7369
    @RequestMapping("/getEmpDeptDto")
    public EmpDeptDto getEmpDeptDto(Integer empNo) {
        return this.empJpaRepository.getEmpDeptDto(empNo);
    }

    //JPQL使用new 可行，但必须使用类全名 new
    //http://127.0.0.1:8080/emp/join/getEmpDeptDtoList
    @RequestMapping("/getEmpDeptDtoList")
    public List<EmpDeptDto> getEmpDeptDtoList() {
        return this.empJpaRepository.getEmpDeptDtoList();
    }

    //SQL使用new 不行 http://127.0.0.1:8080/emp/join/getEmpDeptDtoSQL
    @RequestMapping("/getEmpDeptDtoSQL")
    public EmpDeptDto getEmpDeptDtoSQL(Integer empNo) {
        return this.empJpaRepository.getEmpDeptDtoSQL(empNo);
    }

    //不行  http://127.0.0.1:8080/emp/join/getEmpDeptDtoListSQL
    @RequestMapping("/getEmpDeptDtoListSQL")
    public List<EmpDeptDto> getEmpDeptDtoListSQL() {
        return this.empJpaRepository.getEmpDeptDtoListSQL();
    }

    @RequestMapping("/empDtoQuery ")
    public List<EmpDeptDto> empDtoQuery() {
        return this.empJpaRepository.empDtoQuery();
    }

    @RequestMapping("/getEmpDtoByHibernateHQL")
    public List<EmpDeptDto> getEmpDtoByHibernateHQL() {
        return this.empService.getEmpDtoByHibernateHQL();
    }

    @RequestMapping("/getEmpDtoByHibernateSQL")
    public List<EmpDeptDto> getEmpDtoByHibernateSQL() {
        return this.empService.getEmpDtoByHibernateSQL();
    }

    //测试客户端接收JSON字符串
    public final static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        String jsonMsg = "[ { \"departmentName\": \"RESEARCH\", \"empNo\": 7369, \"ename\": \"SMITH\" }, { \"departmentName\": \"SALES\", \"empNo\": 7499, \"ename\": \"ALLEN\" }, { \"departmentName\": \"SALES\", \"empNo\": 7521, \"ename\": \"WARD\" }, { \"departmentName\": \"RESEARCH\", \"empNo\": 7566, \"ename\": \"JONES\" }, { \"departmentName\": \"SALES\", \"empNo\": 7654, \"ename\": \"MARTIN\" }, { \"departmentName\": \"SALES\", \"empNo\": 7698, \"ename\": \"BLAKE\" }, { \"departmentName\": \"ACCOUNTING\", \"empNo\": 7782, \"ename\": \"CLARK\" }, { \"departmentName\": \"RESEARCH\", \"empNo\": 7788, \"ename\": \"SCOTT\" }, { \"departmentName\": \"ACCOUNTING\", \"empNo\": 7839, \"ename\": \"KING\" }, { \"departmentName\": \"SALES\", \"empNo\": 7844, \"ename\": \"TURNER\" }, { \"departmentName\": \"RESEARCH\", \"empNo\": 7876, \"ename\": \"ADAMS\" }, { \"departmentName\": \"SALES\", \"empNo\": 7900, \"ename\": \"JAMES\" }, { \"departmentName\": \"RESEARCH\", \"empNo\": 7902, \"ename\": \"FORD\" }, { \"departmentName\": \"ACCOUNTING\", \"empNo\": 7934, \"ename\": \"MILLER\" } ]";
        JavaType javaType = EmpController.getCollectionType(ArrayList.class,
            EmpDeptDto.class);
        List<EmpDeptDto> dtos = (List<EmpDeptDto>) EmpController.mapper
            .readValue(jsonMsg, javaType);
        dtos.stream().forEach(System.out::println);
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass
     *        泛型的Collection
     * @param elementClasses
     *        元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass,
            Class<?>... elementClasses) {
        return EmpController.mapper.getTypeFactory().constructParametricType(
            collectionClass, elementClasses);
    }
}
