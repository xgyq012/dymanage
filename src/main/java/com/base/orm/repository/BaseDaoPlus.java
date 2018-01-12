package com.base.orm.repository;

import com.base.orm.core.search.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaguangjun on 2016/12/23.
 */
public interface BaseDaoPlus {


    public List<Map<String, Object>> selectBySql(String sqlString);

    public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions);

    public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy);

    public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, Sort sort);

    public List<Map<String, Object>> selectBySql(String sqlString, String groupBy);

    public List<Map<String, Object>> selectBySql(String sqlString, String groupBy, Sort sort);

    public List<Map<String, Object>> selectBySql(String sqlString, Sort sort);

    public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Sort sort);


    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Pageable pageable) ;

    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, String groupBy, Pageable pageable) ;

    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, Pageable pageable) ;

    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable) ;

    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, String countSqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable) ;

    /** ################## **/


    public <T> List<T> selectBySql(Class<T> clazz, String sqlString);

    public <T> List<T> selectBySql(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions);

    public <T> List<T> selectBySql(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions, Sort sort);

    public <T> List<T> selectBySql(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions, String groupBy);

    public <T> List<T> selectBySql(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions, String groupBy, Sort sort);



    /** ################## **/


    public  <T> Page<T> selectBySqlPageable(Class<T> clazz, String sqlString, Pageable pageable);

    public  <T> Page<T> selectBySqlPageable(Class<T> clazz, String sqlString, String groupBy, Pageable pageable);

    public  <T> Page<T> selectBySqlPageable(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions, Pageable pageable);

    public  <T> Page<T> selectBySqlPageable(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable);

    public  <T> Page<T> selectBySqlPageable(Class<T> clazz, String sqlString, String countSqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable);


    /** ################## **/


    public  <T> Page<T> selectByHQLPageable(Class<T> clazz, String hqlString, Pageable pageable);

    public  <T> Page<T> selectByHQLPageable(Class<T> clazz, String hqlString, String groupBy, Pageable pageable);

    public  <T> Page<T> selectByHQLPageable(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions, Pageable pageable);

    public  <T> Page<T> selectByHQLPageable(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable);

    public  <T> Page<T> selectByHQLPageable(Class<T> clazz, String hqlString, String countSqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable);


    /** ################## **/


    public <T> List<T> selectByHQL(Class<T> clazz, String hqlString);

    public <T> List<T> selectByHQL(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions);

    public <T> List<T> selectByHQL(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions, Sort sort);

    public <T> List<T> selectByHQL(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions, String groupBy);

    public <T> List<T> selectByHQL(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions, String groupBy, Sort sort);

    /** ################## **/

    public Map<String, Object> selectOneBySql(String sqlString);

    public Map<String, Object> selectOneBySql(String sqlString, String groupBy);

    public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions);

    public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy);


}
