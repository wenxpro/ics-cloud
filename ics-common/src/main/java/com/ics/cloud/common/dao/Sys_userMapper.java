package com.ics.cloud.common.dao;

import com.ics.cloud.common.model.Sys_user;
import com.ics.cloud.common.system.bean.SysUserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface Sys_userMapper {

    int deleteByPrimaryKey(String id);

    int insert(Sys_user record);

    int insertSelective(Sys_user record);

    Sys_user selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sys_user record);

    int updateByPrimaryKey(Sys_user record);

    @Select("<script>" +
            "select " +
            "id, username, password,nickname, mobile, email, " +
            "access_token, refresh_token " +
            "from  sys_user " +
            "where status = 1 " +
            "      <if test=\"id != null\">\n" +
            "        and id = #{record.id}\n" +
            "      </if>\n" +
            "      <if test=\"username != null\">\n" +
            "        and username = #{record.username}\n" +
            "      </if>\n" +
            "      <if test=\"password != null\">\n" +
            "        and password = #{record.password}\n" +
            "      </if>\n" +
            "      <if test=\"nickname != null\">\n" +
            "        and nickname = #{record.nickname}\n" +
            "      </if>\n" +
            "      <if test=\"mobile != null\">\n" +
            "        and mobile = #{record.mobile} \n" +
            "      </if>\n" +
            "      <if test=\"email != null\">\n" +
            "        and email = #{record.email} \n" +
            "      </if>\n" +
            "      <if test=\"access_token != null\">\n" +
            "        and access_token = #{record.access_token} \n" +
            "      </if>\n" +
            "      <if test=\"refresh_token != null\">\n" +
            "        and refresh_token = #{record.refresh_token} \n" +
            "      </if>" +
            "limit 1" +
            "</script>")
    Sys_user queryUserBySelective(@Param("record") Sys_user record);

    @Cacheable(cacheNames = "sys_user", keyGenerator = "cloudKeyGenerator")
    @Select("select id,username,password from sys_user where username = #{name} limit 1")
    Sys_user queryUserByName(@Param("name") String name);

    @Select("select id,username,password,nickname,create_date,mobile,email " +
            "from sys_user where status = 1 ")
    List<SysUserBean> queryAll();
}