package com.base.orm.service.impl;


import com.base.orm.repository.BaseDao;
import com.base.orm.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Transactional(readOnly = true)
public class BaseServiceImpl<D extends BaseDao<E,ID>, E, ID extends Serializable>
		implements BaseService<D, E, ID> {

	private final D d;

	public BaseServiceImpl(D d) {
		this.d = d;
	}


	@Override
	@Transactional
	public E doSave(E entity) throws Exception {

		E en = null;

		try {
			en = d.save( entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("保存出错");
		}
		return en;
	}

	@Override
	@Transactional
	public List<E> doSave(Iterable<E> entities) throws Exception {

		List<E> list=null;

		try {
			  list = d.save(entities);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("批量保存出错");
		}
		return list;
	}

	@Override
	public E doSelect(ID id) throws Exception {
		E en = null;
		try {
			en = d.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}
		return en;
	}

	@Override
	public E doSelect(Specification<E> spec) throws Exception {
		E en = null;
		try {
			en = d.findOne(spec);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}
		return en;
	}

	@Override
	public List<E> doSearch() throws Exception {
		List<E> list=null;
		try {
			list = d.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}
		return list;
	}

	@Override
	public List<E> doSearch(Iterable<ID> ids) throws Exception {
		List<E> list=null;
		try {
			list = d.findAll(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}
		return list;
	}

	@Override
	public Page<E> doSearch(Pageable pageable) throws Exception {
		Page<E> page=null;
		try {
			page = d.findAll(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}
		return page;
	}

	@Override
	public List<E> doSearch(Sort sort) throws Exception {

		List<E> list=null;
		try {
			list = d.findAll(sort);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}

		return list;
	}

	@Override
	public List<E> doSearch(Specification<E> spec) throws Exception {
		List<E> list=null;
		try {
			list = d.findAll(spec);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}
		return list;
	}

	@Override
	public Page<E> doSearch(Specification<E> spec, Pageable pageable) throws Exception {

		Page<E> page=null;
		try {
			page = d.findAll(spec,pageable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}

		return page;
	}

	@Override
	public List<E> doSearch(Specification<E> spec, Sort sort) throws Exception {

		List<E> list=null;
		try {
			list =d.findAll(spec,sort);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}
		return list;
	}

	@Override
	@Transactional
	public void doDelete(ID id) throws Exception {
		try {
			d.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("删除出错");
		}
	}

	@Override
	@Transactional
	public void doDelete(E entity) throws Exception {
		try {
			d.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("删除出错");
		}
	}

	@Override
	@Transactional
	public void doDelete(Iterable<E> entities) throws Exception {
		try {
			d.delete(entities);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("删除出错");
		}
	}

	@Override
	@Transactional
	public void doDeleteAll() throws Exception {
		try {
			d.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("删除出错");
		}
	}

	@Override
	public List<E> findAttr(Map<String,Object> map) throws Exception {

		List<E> list=null;
		try {
			list =d.findByAttr(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}

		return list;
	}

	@Override
	public List<E> findAttr(Map<String,Object> map,String orderBy) throws Exception {

		List<E> list=null;
		try {
			list =d.findByAttr(map,orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错");
		}

		return list;
	}


	@Override
	@Transactional
	public int updateFieldById(Map<String,Object> map,ID id) throws Exception {
		int code = 0;
		try {
			code =d.updateFieldById(map, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("更新出错");
		}
		return code;
	}

}
