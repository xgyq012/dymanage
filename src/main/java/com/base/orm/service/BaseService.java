package com.base.orm.service;


import com.base.orm.repository.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface BaseService<D extends BaseDao<E, ID>, E, ID extends Serializable> {


    /**
     * 保存一个实体对象
     *
     * @param entity
     * @return
     */
    public E doSave(E entity) throws Exception;

    /**
     * 保存实体对象集合里所有的实体对象
     *
     * @param entities
     * @return
     */
    public List<E> doSave(Iterable<E> entities) throws Exception;

    /**
     * 根据主键查找一个实体对象
     *
     * @param id
     * @return
     */
    public E doSelect(ID id) throws Exception;

    /**
     * @param spec
     * @return
     */
    public E doSelect(Specification<E> spec) throws Exception;

    /**
     * 查找所有实体
     *
     * @return 返回所有实体
     */
    public List<E> doSearch() throws Exception;

    /**
     * 根据主键集合里所有的主键查找出对应的实体对象
     *
     * @param ids
     * @return 返回主键集合里对应的实体对象的集合
     */
    public List<E> doSearch(Iterable<ID> ids) throws Exception;

    /**
     * @param pageable
     * @return
     */
    public Page<E> doSearch(Pageable pageable) throws Exception;

    /**
     * @param sort
     * @return
     */
    public List<E> doSearch(Sort sort) throws Exception;

    /**
     * @param spec
     * @return
     */
    public List<E> doSearch(Specification<E> spec) throws Exception;

    /**
     * @param spec
     * @param pageable
     * @return
     */
    public Page<E> doSearch(Specification<E> spec, Pageable pageable) throws Exception;

    /**
     * @param spec
     * @param sort
     * @return
     */
    public List<E> doSearch(Specification<E> spec, Sort sort) throws Exception;


    /**
     * 根据主键删除一个实体对象
     *
     * @param id
     */
    public void doDelete(ID id) throws Exception;

    /**
     * 根据实体对象删除一个实体对象
     *
     * @param entity
     */
    public void doDelete(E entity) throws Exception;

    /**
     * 根据实体对象集合删除一个实体对象集合里所有的实体对象
     *
     * @param entities
     */
    public void doDelete(Iterable<E> entities) throws Exception;

    /**
     * 删除所有实体对象
     */
    public void doDeleteAll() throws Exception;


    /**
     * 根据 查询 条件 查询实体
     * @param map
     * @return
     */
    public List<E> findAttr(Map<String, Object> map) throws Exception;


    /**
     * 根据 查询 条件 查询实体
     * @param map
     * @return
     */
    public List<E> findAttr(Map<String, Object> map, String orderBy) throws Exception;



    public int updateFieldById(Map<String, Object> map, ID id) throws Exception;

}
