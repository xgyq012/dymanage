package com.base.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    public int updateOrDeleteByJPQL(String sqlInfo, Object[] array);

    public int updateFieldById(Map<String, Object> map, ID id);

    public List<T> findByAttr(Map<String, Object> queryMap);

    public List<T> findByAttr(Map<String, Object> queryMap, String orderBy);

    public int update(T t) throws Exception;

}
