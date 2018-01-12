package com.base.orm.repository.impl;

import com.base.orm.core.search.SearchFilter;
import com.base.orm.repository.BaseDaoPlus;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class BaseDaoImpl implements BaseDaoPlus {

    @PersistenceContext
    protected EntityManager entityManager;

    private static int DEFAULT_PAGE = 1;
    private static int DEFAULT_ROWS = 10;


    public List<Map<String, Object>> selectBySql(String sqlString) {
        return selectBySql(sqlString,null,null,null);
    }

    public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions) {
        return selectBySql(sqlString,conditions,null,null);
    }

    public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy) {
        return selectBySql(sqlString,conditions,groupBy,null);
    }

    public List<Map<String, Object>> selectBySql(String sqlString, Map<String, SearchFilter> conditions, Sort sort) {
        return selectBySql(sqlString,conditions,null,sort);
    }

    public List<Map<String, Object>> selectBySql(String sqlString, String groupBy) {
        return selectBySql(sqlString,null,groupBy,null);
    }

    public List<Map<String, Object>> selectBySql(String sqlString, String groupBy, Sort sort) {
        return selectBySql(sqlString,null,groupBy,sort);
    }

    public List<Map<String, Object>> selectBySql(String sqlString, Sort sort) {
        return selectBySql(sqlString,null,null,sort);
    }


    @Override
    public <T> List<T> selectBySql(Class<T> clazz, String sqlString) {
        return  selectBySql(clazz, sqlString, null, null ,null);
    }

    @Override
    public <T> List<T> selectBySql(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions) {
        return  selectBySql(clazz, sqlString, conditions, null ,null);
    }

    @Override
    public <T> List<T> selectBySql(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions, String groupBy) {
        return selectBySql(clazz, sqlString, conditions, groupBy ,null);
    }

    @Override
    public <T> List<T> selectBySql(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions, Sort sort) {
        return selectBySql(clazz, sqlString, conditions, null ,sort);
    }

    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Pageable pageable) {
        return selectBySqlPageable(sqlString, null, null, pageable);
    }

    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, String groupBy, Pageable pageable) {
        return selectBySqlPageable(sqlString, null, groupBy, pageable);
    }

    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, Pageable pageable) {
        return selectBySqlPageable(sqlString, conditions, null, pageable);
    }



    @Override
    public <T> Page<T> selectBySqlPageable(Class<T> clazz, String sqlString, Pageable pageable) {
        return selectBySqlPageable(clazz ,  sqlString, null,  null,  pageable);
    }

    @Override
    public <T> Page<T> selectBySqlPageable(Class<T> clazz, String sqlString, String groupBy, Pageable pageable) {
        return selectBySqlPageable(clazz ,  sqlString, null,  groupBy,  pageable);
    }

    @Override
    public <T> Page<T> selectBySqlPageable(Class<T> clazz, String sqlString, Map<String, SearchFilter> conditions, Pageable pageable) {
        return selectBySqlPageable(clazz ,  sqlString, conditions,  null,  pageable);
    }

    @Override
    public <T> Page<T> selectByHQLPageable(Class<T> clazz, String hqlString, Pageable pageable) {
        return selectByHQLPageable(clazz ,  hqlString,  null ,  null,  pageable);
    }

    @Override
    public <T> Page<T> selectByHQLPageable(Class<T> clazz, String hqlString, String groupBy, Pageable pageable) {
        return selectByHQLPageable(clazz ,  hqlString, null,  groupBy,  pageable);
    }

    @Override
    public <T> Page<T> selectByHQLPageable(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions, Pageable pageable) {
        return selectByHQLPageable(clazz ,  hqlString, conditions,  null ,  pageable);
    }

    @Override
    public <T> List<T> selectByHQL(Class<T> clazz, String hqlString) {
        return selectByHQL(clazz,hqlString,null, null, null);
    }

    @Override
    public <T> List<T> selectByHQL(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions) {
        return selectByHQL(clazz,hqlString,conditions, null, null);
    }

    @Override
    public <T> List<T> selectByHQL(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions, Sort sort) {
        return selectByHQL(clazz,hqlString,conditions, null, sort);
    }


    @Override
    public <T> List<T> selectByHQL(Class<T> clazz,String hqlString, Map<String, SearchFilter> conditions, String groupBy){
        return selectByHQL(clazz,hqlString,conditions, groupBy, null);
    }


    /**
     * 根据本地SQL 查询 不带分页的List<Map>结果集
     * @param sqlStr
     * @param conditions
     * @param groupBy
     * @param sort
     * @return
     */
    @Override
    public List<Map<String, Object>> selectBySql(String sqlStr, Map<String, SearchFilter> conditions, String groupBy, Sort sort) {
        sqlStr = addSortSql(sort,addConditionSql(sqlStr, conditions, "and", groupBy));
       /* Query query = entityManager.createNativeQuery(addSortSql(sort,sqlStr));
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);*/
        return getListMap(addSortSql(sort,sqlStr),null);
    }


    /**
     * 本地SQL分页查询 返回 一个Page<Map>
     *
     * @param sqlString
     * @param conditions
     * @param groupBy
     * @param pageable
     * @return
     */
    @Override
    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable){

        //总条数
        String sqlStr = addConditionSql(sqlString, conditions, "and", groupBy);
        long total =getSQLResultCount(countSql(sqlStr));

        //分页
        List<Map<String, Object>> resultMap =  getListMap(sqlSortBuilder(sqlStr,pageable),pageable);

        return new PageImpl<Map<String, Object>>(resultMap, pageable, total);
    }

    @Override
    public Page<Map<String, Object>> selectBySqlPageable(String sqlString, String countSqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable) {

        String sqlStr = addConditionSql(sqlString, conditions, "and", groupBy);

        long total =getSQLResultCount(addConditionSql(countSqlString, conditions, "and", groupBy));

        List<Map<String, Object>> resultMap =  getListMap(sqlSortBuilder(sqlStr,pageable),pageable);

        return new PageImpl<Map<String, Object>>(resultMap, pageable, total);
    }


    private List<Map<String, Object>> getListMap(String sql, Pageable pageable){
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (pageable!=null) {
            setPageNumber(query,pageable);
        }
        return  query.getResultList();
    }


    /**
     * 自定义 本地SQL 返回一个 javaBean 类型的 list结果集
     * @param clazz
     * @param sqlString
     * @param conditions
     * @param groupBy
     * @param sort
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> selectBySql(Class<T> clazz,String sqlString, Map<String, SearchFilter> conditions, String groupBy,Sort sort){
        String sqlStr = addConditionSql(sqlString, conditions, "and", groupBy);
       /* Query query = entityManager.createNativeQuery(addSortSql(sort,sqlStr));
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clazz));*/
        return getListBean(clazz,addSortSql(sort,sqlStr),null);
    }


    /**
     * 自定义 本地SQL 返回一个 javaBean 类型的 分页结果集
     * @param clazz 数据转换对象
     * @param sqlString  自定义SQL
     * @param conditions 条件
     * @param groupBy 分组
     * @param pageable 排序 页数
     * @param <T>
     * @return
     */
    @Override
    public  <T> Page<T> selectBySqlPageable(Class<T> clazz , String sqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable){

        String sqlStr = addConditionSql(sqlString, conditions, "and", groupBy);
        long total = getSQLResultCount(countSql(sqlStr));//总数
        List list = getListBean(clazz,sqlSortBuilder(sqlStr,pageable),pageable);

        return  new PageImpl<T>(list,pageable,total);
    }

    @Override
    public <T> Page<T> selectBySqlPageable(Class<T> clazz, String sqlString, String countSqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable) {

        String sqlStr = addConditionSql(sqlString, conditions, "and", groupBy);
        long total = getSQLResultCount(addConditionSql(countSqlString, conditions, "and", groupBy));//总数

        List list = getListBean(clazz,sqlSortBuilder(sqlStr,pageable),pageable);

        return  new PageImpl<T>(list,pageable,total);
    }


    private List getListBean(Class clazz , String sql , Pageable pageable){
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clazz));
        if (pageable!=null) {
            setPageNumber(query,pageable);
        }
        return query.getResultList();
    }


    /**
     * 自定义HQL 返回一个 javaBean 类型的 分页结果集
     * @param clazz
     * @param hqlString
     * @param conditions
     * @param groupBy
     * @param pageable
     * @param <T>
     * @return
     */
    @Override
    public <T> Page<T> selectByHQLPageable(Class<T> clazz , String hqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable){

        String sqlStr = addConditionSql(hqlString, conditions, "and", groupBy);

        long total = getHQLResultCount(countSql(sqlStr));//总数

        List list  = getHQLResultList(clazz,sqlSortBuilder(sqlStr,pageable),pageable);

        return new PageImpl<T>(list,pageable,total);
    }

    @Override
    public <T> Page<T> selectByHQLPageable(Class<T> clazz, String hqlString, String countSqlString, Map<String, SearchFilter> conditions, String groupBy, Pageable pageable) {
        String sqlStr = addConditionSql(hqlString, conditions, "and", groupBy);
        long total = getHQLResultCount(countSql(sqlStr));//总数
        List list = getHQLResultList(clazz,sqlSortBuilder(sqlStr,pageable),pageable);
        return new PageImpl<T>(list,pageable,total);
    }


    /**
     * 自定义HQL 返回一个 List结果集
     * @param clazz
     * @param hqlString
     * @param conditions
     * @param groupBy
     * @param sort
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> selectByHQL(Class<T> clazz, String hqlString, Map<String, SearchFilter> conditions, String groupBy, Sort sort) {
        hqlString = addConditionSql(hqlString, conditions, "and", groupBy);
        //Query query = entityManager.createQuery(addSortSql(sort,hqlString),clazz);
        return getHQLResultList(clazz,addSortSql(sort,hqlString),null);
    }

    @Override
    public Map<String, Object> selectOneBySql(String sqlString) {
        return selectOneBySql( sqlString ,null,null);
    }

    @Override
    public Map<String, Object> selectOneBySql(String sqlString, String groupBy) {
        return selectOneBySql( sqlString ,null,groupBy);
    }

    @Override
    public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions) {
        return selectOneBySql( sqlString ,conditions,null);
    }

    @Override
    public Map<String, Object> selectOneBySql(String sqlString, Map<String, SearchFilter> conditions, String groupBy) {
        sqlString = addSortSql(null,addConditionSql(sqlString, conditions, "and", groupBy));
        Query query = entityManager.createNativeQuery(sqlString);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map<String, Object>) query.getSingleResult();
    }


    private List getHQLResultList(Class clazz , String sql , Pageable pageable){
        TypedQuery query = entityManager.createQuery(sql,clazz);
        if(pageable!=null){
            setPageNumber(query,pageable);
        }
        return query.getResultList();
    }



    //设置分页信息
    private void setPageNumber(Query query, Pageable pageable){
        if(pageable!=null){
            query.setFirstResult(pageable.getPageNumber()*pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }else{
            query.setFirstResult(DEFAULT_PAGE*DEFAULT_ROWS);
            query.setMaxResults(DEFAULT_ROWS);
        }
    }

    /**
     * 根据HQL 获取结果 总数
     * @param sqlString
     * @return
     */
    private long getHQLResultCount(String sqlString) {

        return Long.parseLong(entityManager.createQuery(sqlString).getSingleResult().toString()) ;
    }


    /**
     * 根据SQL 获取结果 总数
     * @param sqlString
     * @return
     */
    private long getSQLResultCount(String sqlString) {
        return Long.parseLong(entityManager.createNativeQuery(sqlString).getSingleResult().toString()) ;
    }



    private String addConditionSql(String sqlString, Map<String, SearchFilter> conditions, String conditionsConnector, String groupBy) {
        sqlString = StringUtils.defaultIfBlank(sqlString, StringUtils.EMPTY);
        if (conditions != null) {
            Collection<SearchFilter> c = conditions.values();
            Iterator<SearchFilter> i = c.iterator();
            StringBuilder conditionStr = new StringBuilder();
            while (i.hasNext()) {
                SearchFilter searchFilter = i.next();
                String value = "";
                switch(searchFilter.operator) {
                    case EQ:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " = " + searchFilter.value);
                        break;
                    case NEQ:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " <> " + searchFilter.value);
                        break;
                    case LIKE:
                        value = searchFilter.value.toString().replaceAll("_", "|_").replaceAll("%", "|%");
                        value = value.substring(1, value.length()-1);
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " like '%" + value + "%' escape '|'");
                        break;
                    case STARTWITH:
                        value = searchFilter.value.toString().replaceAll("_", "|_").replaceAll("%", "|%");
                        value = value.substring(1, value.length()-1);
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " like " + value + "%' escape '|'");
                        break;
                    case ENDWITH:
                        value = searchFilter.value.toString().replaceAll("_", "|_").replaceAll("%", "|%");
                        value = value.substring(1, value.length()-1);
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " like '%" + value + "' escape '|'");
                        break;
                    case GT:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " > " + searchFilter.value);
                        break;
                    case LT:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " < " + searchFilter.value);
                        break;
                    case GTE:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " >= " + searchFilter.value);
                        break;
                    case LTE:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " <= " + searchFilter.value);
                        break;
                    case IN:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " in (" + searchFilter.value + ")");
                        break;
                    case NIN:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " not in (" + searchFilter.value + ")");
                        break;
                    case ISN:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " is null");
                        break;
                    case ISNN:
                        conditionStr.append(" " + conditionsConnector + " " + searchFilter.fieldName + " is not null");
                        break;
                }
            }
            sqlString = sqlString.concat(conditionStr.toString());
        }
        if (!StringUtils.isEmpty(groupBy)) {
            sqlString = sqlString.concat(" group by ").concat(groupBy);
        }
        return sqlString;
    }

    //构建排序
    private String sqlSortBuilder(String sqlString,Pageable pageable){
        if (pageable!=null) {
            sqlString  = addSortSql(pageable.getSort(),sqlString);
        }
        return sqlString;
    }

    private String addSortSql(Sort sort, String sqlString) {
        if (sort != null) {
            Iterator<Sort.Order> orders = sort.iterator();
            StringBuilder orderByStr = new StringBuilder();
            while (orders.hasNext()) {
                Sort.Order order = orders.next();
                String property = order.getProperty();
                String direction = order.getDirection().name();
                orderByStr.append(", " + property + " " + direction);
            }
            sqlString = sqlString + " Invoicing by" + orderByStr.toString().substring(1);
        }
        return sqlString;
    }


    /**
     * 生成 Count 语句
     * @param sql
     * @return
     */
    private String countSql(String sql){

        return StringUtils.isNotBlank(sql) ? sql.replaceAll("(?<=SELECT|select).*?(?=FROM|form)"," COUNT(1) ") : "";
    }



}
