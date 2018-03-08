package com.mfm.learn.spring.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfm.learn.spring.data.jpa.entity.Emp;
import com.mfm.learn.spring.data.jpa.entity.dto.DeptDTO;
import com.mfm.learn.spring.data.jpa.entity.dto.EmpDeptDto;
import com.mfm.learn.spring.data.jpa.entity.projection.EmpDept;

/**
 * 测试spring-data-jpa
 * join查询的不同方式和不同场景
 * 方式1： 原生SQL
 * 方式2：J PQL/HQL
 *
 * @author MengFanmao
 * @since 2017年7月26日
 */
public interface EmpJpaRepository extends JpaRepository<Emp, Integer> {

    @Query(value = "select e.empNo,e.ename from Emp e  where e.deptNo=deptNo")
    List<Emp> findEmpDeptNo(@Param("deptNo") String deptNo);

    //默认 方式  返回所有字段
    @Query(
            value = "select e from Emp e,Dept d where e.deptNo=d.deptNo and d.dname=:dname")
    List<Emp> findEmpByDname(@Param("dname") String dname);

    @Query(
            value = "select e.* from emp e,dept d where e.dept_no=d.dept_no and d.dname=:dname",
            nativeQuery = true)
    List<Emp> findEmpByDnameSQL(@Param("dname") String dname);

    //默认 方式 返回个别字段
    @Query(
            value = "select e.empNo,e.ename,d.dname from Emp e,Dept d where e.deptNo=d.deptNo")
    List<Object[]> findAllEmpDname();

    @Query(
            value = "select e.emp_no as empNo,e.ename,d.dname from emp e,dept d where e.dept_no=d.dept_no",
            nativeQuery = true)
    List<Object[]> findAllEmpDnameSQL();

    //projection 方式
    @Query(
            value = "select e.empNo as empNo,e.ename as ename,d.dname as dname from Emp e,Dept d where e.deptNo=d.deptNo and e.empNo=:empNo")
    EmpDept getEmpDept(@Param("empNo") Integer empNo);

    @Query(
            value = "select e.empNo as empNo,e.ename as ename,d.dname as dname from Emp e,Dept d where e.deptNo=d.deptNo")
    List<EmpDept> getEmpDeptList();

    //不行 ConverterNotFoundException: No converter found capable of converting from type [java.lang.Integer] to type [com.mfm.learn.spring.data.entity.jpa.projection.EmpDept]
    @Query(
            value = "select e.emp_no as empNo,e.ename as ename,d.dname as dname from emp e,dept d where e.dept_no=d.dept_no and e.emp_no=:empNo",
            nativeQuery = true)
    EmpDept getEmpDeptSQL(@Param("empNo") Integer empNo);

    //不行 java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
    @Query(
            value = "select e.emp_no as empNo,e.ename as ename,d.dname as dname from emp e,dept d where e.dept_no=d.dept_no",
            nativeQuery = true)
    List<EmpDept> getEmpDeptListSQL();

    //dto 方式
    //别名法
    //JPQL:No converter found capable of converting from type [java.util.HashMap<?, ?>] to type [com.mfm.learn.spring.data.jpa.entity.dto.EmpDeptDto]
    //SQL :No converter found capable of converting from type [java.lang.Integer] to type [com.mfm.learn.spring.data.jpa.entity.dto.EmpDeptDto]
    @Query(
            value = "select e.empNo as empNo,e.ename as ename,d.dname as departmentName from Emp e,Dept d where e.deptNo=d.deptNo and e.empNo=:empNo")
    EmpDeptDto getEmpDeptDto(@Param("empNo") Integer empNo);

    @Query(
            value = "select e.emp_no as empNo,e.ename as ename,d.dname as departmentName from emp e,dept d where e.dept_no=d.dept_no  and e.emp_no=:empNo",
            nativeQuery = true)
    EmpDeptDto getEmpDeptDtoSQL(@Param("empNo") Integer empNo);

    //构造函数法
    //JPQL:new EmpDeptDto() 必须使用类全名，否则报错Caused by: org.hibernate.hql.internal.ast.QuerySyntaxException: Unable to locate class [EmpDeptDto]
    //SQL :new EmpDeptDto() 放到原生SQL中肯定不行

    //必须使用类全名 new com.mfm.learn.spring.data.jpa.entity.dto.EmpDeptDto()
    @Query(
            value = "select  new com.mfm.learn.spring.data.jpa.entity.dto.EmpDeptDto(e.empNo,e.ename,d.dname,d.deptNo) from Emp e,Dept d where e.deptNo=d.deptNo")
    List<EmpDeptDto> getEmpDeptDtoList();

    //new EmpDeptDto() 放到原生SQL中肯定不行
    @Query(
            value = "select new com.mfm.learn.spring.data.jpa.entity.dto.EmpDeptDto(e.emp_no,e.ename,d.dname,d.dept_no) from emp e,dept d where e.dept_no=d.dept_no",
            nativeQuery = true)
    List<EmpDeptDto> getEmpDeptDtoListSQL();

    //namedQuery 方式 Emp.empDtoQuery
    List<EmpDeptDto> empDtoQuery();

    //dto-使用hibernate特性查询
    @Query(value = "select d.loc as loc,d.dname as dname from Dept d")
    List<DeptDTO> getDept();
}
