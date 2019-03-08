package com.bmsoft.dao;

import com.bmsoft.entity.User;
import com.bmsoft.framework.orm.BaseDaoSupport;
import com.bmsoft.framework.orm.QueryRule;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.core.common.jdbc.datasource.DynamicDataSource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName UserDao.java
 * @Description TODO
 * @createTime 2018年10月15日 16:01:00
 */
@Repository
public class UserDao extends BaseDaoSupport<User, Long> {

    private DynamicDataSource dataSource;

    @Resource(name = "dynamicDataSource")
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = (DynamicDataSource) dataSource;
        this.setDataSourceReadOnly(dataSource);
        this.setDataSourceWrite(dataSource);
    }

    @Override
    protected String getPKColumn() {
        return "id";
    }

    /**
     * @param name
     * @return
     */
    public List<User> selectByName(String name) throws Exception {
        //构建一个QureyRule 查询规则
        QueryRule queryRule = QueryRule.getInstance();
        //查询一个name= 赋值 结果，List
        queryRule.andEqual("name", name);
        //相当于自己再拼SQL语句
        return super.select(queryRule);
    }


    public List<User> selectGTAge(Integer age) throws Exception {
        //构建一个QureyRule 查询规则
        QueryRule queryRule = QueryRule.getInstance();
        //查询一个name= 赋值 结果，List
        queryRule.andGreaterEqual("age", age);
        //相当于自己再拼SQL语句
        return super.select(queryRule);
    }

}
