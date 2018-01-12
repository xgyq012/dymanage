package com.base.orm.core.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public class SearchUtil {

	public static final String SORT_PARAM_NAME = "orderBy";
	public static final String SORT_REGEX= " ";
	public static final String SORT_PAIR_REGEX= "_";
	
	/**
	 * @param params
	 * @return
	 */
	public static Map<String, SearchFilter> getSearchFilters(Map<String, Object> params) {
		return SearchFilter.parseBySql(params);
	}
	
	/**
	 * @param req
	 * @return
	 */
	public static Map<String, SearchFilter> getSearchFilters(ServletRequest req) {
		Map<String, Object> searchParams = WebUtils.getParametersStartingWith(req, SearchFilter.SEARCH_PREFIX);
		return getSearchFilters(searchParams);
	}
	
	/**
	 * @param req
	 * @param params
	 * @return
	 */
	public static Map<String, SearchFilter> getSearchFilters(ServletRequest req, Map<String, Object> params) {
		Map<String, Object> searchParams = WebUtils.getParametersStartingWith(req, SearchFilter.SEARCH_PREFIX);
		searchParams.putAll(params);
		return getSearchFilters(searchParams);
	}

	/**
	 * @param clazz
	 * @param params
	 * @param conditionsConnector
	 * @return
	 */
	public static <T> Specification<T> getSpecification(Class<T> clazz, Map<String, Object> params, String conditionsConnector) {
		Map<String, SearchFilter> filters = SearchFilter.parse(params);
		return SearchFilter.spec(filters.values(), clazz, conditionsConnector);
	}	

	/**
	 * @param clazz
	 * @param req
	 * @return
	 */
	public static <T> Specification<T> getSpecification(Class<T> clazz, ServletRequest req, String conditionsConnector) {
		Map<String, Object> searchParams = WebUtils.getParametersStartingWith(req, SearchFilter.SEARCH_PREFIX);
		return getSpecification(clazz, searchParams, conditionsConnector);
	}

	/**
	 * @param clazz
	 * @param req
	 * @param params
	 * @return
	 */
	public static <T> Specification<T> getSpecification(Class<T> clazz, ServletRequest req, Map<String, Object> params, String conditionsConnector) {
		Map<String, Object> searchParams = WebUtils.getParametersStartingWith(req, SearchFilter.SEARCH_PREFIX);
		searchParams.putAll(params);
		return getSpecification(clazz, searchParams, conditionsConnector);
	}	
	
	/**
	 * @param clazz
	 * @param params
	 * @return
	 */
	public static <T> Specification<T> getSpecification(Class<T> clazz, Map<String, Object> params) {
		Map<String, SearchFilter> filters = SearchFilter.parse(params);
		return SearchFilter.spec(filters.values(), clazz, "and");
	}

	/**
	 * @param clazz
	 * @param req
	 * @return
	 */
	public static <T> Specification<T> getSpecification(Class<T> clazz, ServletRequest req) {
		Map<String, Object> searchParams = WebUtils.getParametersStartingWith(req, SearchFilter.SEARCH_PREFIX);
		return getSpecification(clazz, searchParams);
	}

	/**
	 * @param clazz
	 * @param req
	 * @param params
	 * @return
	 */
	public static <T> Specification<T> getSpecification(Class<T> clazz, ServletRequest req, Map<String, Object> params) {
		Map<String, Object> searchParams = WebUtils.getParametersStartingWith(req, SearchFilter.SEARCH_PREFIX);
		searchParams.putAll(params);
		return getSpecification(clazz, searchParams);
	}
	
	/**
	 * @param req
	 * @return
	 */
	public static Sort getSort(ServletRequest req) {
		if (req == null) {
			return null;
		}
		String orderBy = req.getParameter(SORT_PARAM_NAME);
		return getSort(orderBy);
	}
	
	/**
	 * @param orderBy
	 * @return
	 */
	public static Sort getSort(String orderBy) {
		if (StringUtils.isEmpty(orderBy)) {
			return null;
		}
		String[] sorts = orderBy.split(SORT_REGEX);
		List<Order> orders = new ArrayList<Order>();
		for (int i = 0; i < sorts.length; i++) {
			String[] sortPair = sorts[i].split(SORT_PAIR_REGEX);
			String property = "";
			for (int j = 0; j < sortPair.length-1; j++) {
				property += "_" + sortPair[j];
			}
			orders.add(new Order(Direction.fromString(sortPair[sortPair.length-1]), property.substring(1)));
		}
		return new Sort(orders);
	}
	
	/**
	 * @param req
	 * @param orderBy
	 * @return
	 */
	public static Sort getSort(ServletRequest req, String orderBy) {
		Sort sort1 = getSort(req);
		Sort sort2 = getSort(orderBy);
		if (sort1 == null) {
			if (sort2 == null) {
				return null;
			} else {
				return sort2;
			}
		} else {
			if (sort2 == null) {
				return sort1;				
			} else {
				return sort1.and(sort2);
			}
		}
	}

	/**
	 * 默认page=1，rows=10
	 * 
	 * @return
	 */
	public static Pageable getPageable() {
		return getPageableWithSort(null, null);
	}
	
	/**
	 * @param req
	 * @return
	 */
	public static Pageable getPageable(ServletRequest req) {
		return getPageableWithSort(req, null);
	}
	
	/**
	 * @param req
	 * @return
	 */
	public static Pageable getPageableWithSort(ServletRequest req) {
		return getPageableWithSort(req, getSort(req));
	}
	
	/**
	 * @param req
	 * @param sort
	 * @return
	 */
	public static Pageable getPageableWithSort(ServletRequest req, Sort sort) {
		if (req == null) {
			return new PageRequest(0, 10, sort);
		}
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		if (StringUtils.isEmpty(page) || StringUtils.isEmpty(rows)) {
			return new PageRequest(0, 10, sort);
		}
		return new PageRequest(Integer.valueOf(page) - 1, Integer.valueOf(rows), sort);
	}
	
	/**
	 * 默认page=1，rows=10
	 * 
	 * @param orderBy
	 * @return
	 */
	public static Pageable getPageableWithOrderBy(String orderBy) {
		return getPageableWithOrderBy(null, orderBy);
	}
	
	/**
	 * @param req
	 * @param orderBy
	 * @return
	 */
	public static Pageable getPageableWithOrderBy(ServletRequest req, String orderBy) {
		return getPageableWithSort(req, getSort(req, orderBy));
	}



	
}
