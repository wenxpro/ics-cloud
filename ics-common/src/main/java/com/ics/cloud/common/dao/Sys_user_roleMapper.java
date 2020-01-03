package com.ics.cloud.common.dao;

import com.ics.cloud.common.model.Sys_user_role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Sys_user_roleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sys_user_role record);

    int insertSelective(Sys_user_role record);

    Sys_user_role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sys_user_role record);

    int updateByPrimaryKey(Sys_user_role record);

    @Insert("<script>" +
            " insert into sys_user_role (id, user_id, role_id,\n" +
            "        create_date, create_userid, del_date,\n" +
            "        del_uerid, status)\n" +
            "        values " +
            "<foreach item='item' index='index' collection='list' separator=','>"+
            "       (#{item.id,jdbcType=VARCHAR}, #{item.user_id,jdbcType=VARCHAR}, #{item.role_id,jdbcType=VARCHAR},\n" +
            "        #{item.create_date,jdbcType=TIMESTAMP}, #{item.create_userid,jdbcType=VARCHAR}, #{item.del_date,jdbcType=TIMESTAMP},\n" +
            "        #{item.del_uerid,jdbcType=VARCHAR}, #{item.status,jdbcType=INTEGER})" +
            "</foreach>"+
            "</script>")
    int insertBatch(@Param("list") List<Sys_user_role> list);

    @Select("select id,user_id,role_id from sys_user_role where status = 1 and user_id = #{userid}")
    List<Sys_user_role> queryListByUserId(@Param("userid")String userid);

    @Update("<script>" +
            "update sys_user_role set status = 0 where id in " +
            "<foreach  collection = 'array' item = 'item' index = 'index' open = '(' separator= ',' close = ')' >"+
            "	#{item} "+
            "</foreach>"+
            "</script>")
    int deleteBatch(@Param("array") List<String> array);
}