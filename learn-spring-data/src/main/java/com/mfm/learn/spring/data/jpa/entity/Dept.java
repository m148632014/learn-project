package com.mfm.learn.spring.data.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author MengFanmao
 * @since 2017年7月26日
 */
@Entity
public class Dept {
    @Id
    private int deptNo;
    private String dname;
    private String loc;

    /**
     * @return the deptNo
     */
    public int getDeptNo() {
        return this.deptNo;
    }

    /**
     * @param deptNo
     *        the deptNo to set
     */
    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * @return the dname
     */
    public String getDname() {
        return this.dname;
    }

    /**
     * @param dname
     *        the dname to set
     */
    public void setDname(String dname) {
        this.dname = dname;
    }

    /**
     * @return the loc
     */
    public String getLoc() {
        return this.loc;
    }

    /**
     * @param loc
     *        the loc to set
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Dept [deptNo=" + this.deptNo + ", dname=" + this.dname
            + ", loc=" + this.loc + "]";
    }

}
