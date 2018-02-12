package com.mfm.learn.spring.data.jpa.entity.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * 投影接口：只有get方法，用来接收投影出来的数据
 * <p>
 * target表示投影出来的所有字段. eg:select a,b,c from Clazz; #{target.a}
 *
 * @author MengFanmao
 * @since 2017年7月26日
 */
public interface EmpDept {
    @Value("#{target.empNo}")
    public Integer getEmpNo();

    @Value("#{target.ename}")
    String getEname();

    @Value("#{target.dname}")
    String getDname();

    //虚拟一个属性  另外：如过使用了关联对象也可以使用.访问关联对象。 比如Emp类有一个属性Dept dept #{target.dept.dname}
    @Value("#{target.empNo} #{target.ename} #{target.dname}")
    String getFullname();
}
