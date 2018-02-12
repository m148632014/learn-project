package com.mfm.learn.spring.data.jpa.entity.dto;

/**
 * @author MengFanmao
 * @since 2017年7月26日
 */

public class EmpDeptDto {
    private Integer empNo;
    private String ename;
    private String departmentName;
    private Integer deptNo;

    public EmpDeptDto() {
        super();
    }

    /**
     * @param empNo
     * @param ename
     * @param departmentName
     */
    public EmpDeptDto(Integer empNo, String ename, String departmentName) {
        super();
        this.empNo = empNo;
        this.ename = ename;
        this.departmentName = departmentName;
    }

    public EmpDeptDto(Integer empNo, String ename, String departmentName,
            Integer deptNo) {
        super();
        this.empNo = empNo;
        this.ename = ename;
        this.departmentName = departmentName;
        this.deptNo = deptNo;
    }

    public EmpDeptDto(Integer empNo, String ename) {
        this.empNo = empNo;
        this.ename = ename;
    }

    public Integer getDeptNo() {
        return this.deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

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
     * @return the departmentName
     */
    public String getDepartmentName() {
        return this.departmentName;
    }

    /**
     * @param departmentName
     *        the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "EmpDeptDto [empNo=" + this.empNo + ", ename=" + this.ename
            + ", departmentName=" + this.departmentName + ", deptNo="
            + this.deptNo + "]";
    }

}
