package com.ics.cloud.common.base;

/**
 * 基本 dao crud
 *
 * @param <T>
 */
public interface BaseCrudMapper<T> {

    int deleteByPrimaryKey(String id);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

}