package com.mfm.learn.spring.data.jpa.entity;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mfm.learn.spring.data.jpa.entity.dto.EmpDeptDto;

/**
 * @author MengFanmao
 * @since 2017年7月26日
 */

@SqlResultSetMappings({
    @SqlResultSetMapping(name = "EmpMapping", entities = @EntityResult(
            entityClass = Emp.class, fields = {
                @FieldResult(name = "empNo", column = "emp_no"),
                @FieldResult(name = "ename", column = "ename"),
                @FieldResult(name = "job", column = "job"),
                @FieldResult(name = "mgr", column = "mgr"),
                @FieldResult(name = "hireDate", column = "hire_date"),
                @FieldResult(name = "sal", column = "sal"),
                @FieldResult(name = "comm", column = "comm"),
                @FieldResult(name = "deptNo", column = "dept_no") })),
    @SqlResultSetMapping(name = "EmpDeptDtoMapping",
            classes = @ConstructorResult(targetClass = EmpDeptDto.class,
                    columns = { @ColumnResult(name = "emp_no"),
                        @ColumnResult(name = "ename"),
                        @ColumnResult(name = "dname"),
                        @ColumnResult(name = "dept_no") })) })
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "empquery",
            query = "select e.emp_no, e.ename, e.job,e.mgr,e.hire_date,e.sal,e.comm,e.dept_no from emp e",
            resultSetMapping = "EmpMapping"),
    @NamedNativeQuery(
            name = "Emp.empDtoQuery",
            query = "select e.emp_no, e.ename,d.dname,d.dept_no from emp e,dept d where d.dept_no=e.dept_no",
            resultSetMapping = "EmpDeptDtoMapping") })
//@NamedQueries({
//    @NamedQuery(name = "empqueryJPQL", query = "from Emp"),
//    @NamedQuery(
//            name = "empDtoqueryJPQL",
//            query = "select e.empNo, e.ename,d.dname,d.deptNo from Emp e,dept d where d.deptNo=e.deptNo") })
@Entity
public class Emp {
    @Id
    private Integer empNo;
    private String ename;
    private String job;
    private Integer mgr;
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    private Double sal;
    private Double comm;
    private Integer deptNo;

    /**
     * @return the empNo
     */
    public Integer getEmpNo() {
        return this.empNo;
    }

    /**
     * @param empNo
     *        the empNo to set
     */
    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    /**
     * @return the ename
     */
    public String getEname() {
        return this.ename;
    }

    /**
     * @param ename
     *        the ename to set
     */
    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * @return the job
     */
    public String getJob() {
        return this.job;
    }

    /**
     * @param job
     *        the job to set
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @return the mgr
     */
    public Integer getMgr() {
        return this.mgr;
    }

    /**
     * @param mgr
     *        the mgr to set
     */
    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    /**
     * @return the hireDate
     */
    public Date getHireDate() {
        return this.hireDate;
    }

    /**
     * @param hireDate
     *        the hireDate to set
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * @return the sal
     */
    public Double getSal() {
        return this.sal;
    }

    /**
     * @param sal
     *        the sal to set
     */
    public void setSal(Double sal) {
        this.sal = sal;
    }

    /**
     * @return the comm
     */
    public Double getComm() {
        return this.comm;
    }

    /**
     * @param comm
     *        the comm to set
     */
    public void setComm(Double comm) {
        this.comm = comm;
    }

    /**
     * @return the deptNo
     */
    public Integer getDeptNo() {
        return this.deptNo;
    }

    /**
     * @param deptNo
     *        the deptNo to set
     */
    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public String toString() {
        return "Emp [empNo=" + this.empNo + ", ename=" + this.ename + ", job="
            + this.job + ", mgr=" + this.mgr + ", hireDate=" + this.hireDate
            + ", sal=" + this.sal + ", comm=" + this.comm + ", deptNo="
            + this.deptNo + "]";
    }

}
