package com.base.orm.repository.impl;

import com.base.orm.core.search.SearchFilter;
import com.base.orm.core.search.SearchUtil;
import com.base.orm.repository.CustomRepository;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements CustomRepository<T, ID> {

    String SORT_PAIR_REGEX = SearchUtil.SORT_PAIR_REGEX;
    String SORT_REGEX = SearchUtil.SORT_REGEX;

    private final EntityManager em;

    private Class<T> entityClass;

    private String idName;

    public EntityManager getEntityManager() {
        return em;
    }

    public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.entityClass = domainClass;
        Field[] field = domainClass.getDeclaredFields();
        for (Field f : field) {
           if(f.isAnnotationPresent(Id.class)){
               this.idName=f.getName();
               break;
           }
        }
    }

    /**
     * 根据HQL 更新 或者 指定属性
     * @param sqlInfo
     * @param array
     * @return
     */
    @Transactional
    public int updateOrDeleteByJPQL(String sqlInfo,Object[] array){
        Query query = em.createQuery(sqlInfo);
        if(array!=null && array.length>0){
            for(int i=0;i<array.length;i++){
                query.setParameter(i,array[i]);
            }
        }
        return  query.executeUpdate();
    }

    /**
     * 本地sql 更新数据
     * @param sqlInfo
     * @param array
     * @return
     */
    @Transactional
    public int updateOrDeleteBySQL(String sqlInfo,Object[] array){
        Query query = em.createNativeQuery(sqlInfo);
        if(array!=null && array.length>0){
            for(int i=0;i<array.length;i++){
                query.setParameter(i,array[i]);
            }
        }
        return  query.executeUpdate();
    }




    /**
     * 根据ID 更新指定属性
     * @param map
     * @param id
     * @return
     */
    @Transactional
    public int updateFieldById(Map<String,Object> map,ID id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<T> update = cb.createCriteriaUpdate(entityClass);
        Root root = update.from(entityClass);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(),entry.getValue());
        }
        update.where(cb.equal(root.get(idName),id));
       return em.createQuery(update).executeUpdate();
    }


    @Transactional
    public int update(T t) throws Exception {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<T> update = cb.createCriteriaUpdate(entityClass);
        Root root = update.from(entityClass);

        Field[] fs = entityClass.getDeclaredFields ();
        for ( int i = 0 ; i < fs. length ; i++){
           Field field = fs[i];
           String fieldName = field.getName();
           if(!fieldName.equals(idName) && isPrivate(field.getModifiers())){
               Object value =  PropertyUtils.getProperty(t,fieldName);
               if(value!=null){
                   update.set(fieldName,value);
               }
           }
        }
        ID idvalue = (ID) PropertyUtils.getProperty(t,idName);
        if(idvalue==null){
            throw new Exception("主键ID 不能为空 ");
        }
        update.where(cb.equal(root.get(idName),idvalue));

        return em.createQuery(update).executeUpdate();
    }


    /**
     * Map 格式
     * map.put("name_LIKE","XIAOMING")
     *
     * 根据查询条件查询对象列表
     * @param queryMap
     * @return
     */
    public List<T> findByAttr(Map<String,Object> queryMap){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);

        if(queryMap!=null && queryMap.size()>0){
            Predicate Predicate = SearchFilter.spec(SearchFilter.parse(queryMap).values(),entityClass,"and").toPredicate(root,query,builder);
            query.where(Predicate);
        }

        TypedQuery<T> typedQuery = em.createQuery(query);

        return   typedQuery.getResultList();
    }


    /**
     *
     * @param queryMap 参数
     * @param orderBy 排序
     * @return
     */
    public List<T> findByAttr(Map<String,Object> queryMap,String orderBy){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);

        if(queryMap!=null && queryMap.size()>0){
            Predicate Predicate = SearchFilter.spec(SearchFilter.parse(queryMap).values(),entityClass,"and").toPredicate(root,query,builder);
            query.where(Predicate);
        }

        if(StringUtils.isNotBlank(orderBy)){

            List<Order> listOrder = new LinkedList<>();
            String[] sorts = orderBy.split(SORT_REGEX);

            for (int i = 0; i < sorts.length; i++) {
                String[] sortPair = sorts[i].split(SORT_PAIR_REGEX);
                String property = "";
                for (int j = 0; j < sortPair.length-1; j++) {
                    property += "_" + sortPair[j];
                    String pair = sortPair[sortPair.length-1];
                    String fieldName = property.substring(1);
                    pair = StringUtils.upperCase(pair);
                    switch (pair) {
                        case "ASC" :
                            listOrder.add(builder.asc(root.get(fieldName)));
                            break;
                        case "DESC":
                            listOrder.add(builder.desc(root.get(fieldName)));
                            break;
                        default:
                            listOrder.add(builder.desc(root.get(fieldName)));
                            break;
                    }
                }
            }
            query.orderBy(listOrder);
        }
        TypedQuery<T> typedQuery = em.createQuery(query);

        return  typedQuery.getResultList();
    }

    private boolean isPrivate(int modifiers){
        return ((modifiers & 0x2)!=0);
    }

}

































