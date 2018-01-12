package com.dy.base.domain;

import com.base.orm.core.DBUtil;
import com.base.springmvc.core.JsonDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.catalina.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 创建人
	 */
	@CreatedBy
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "CREATE_BY", nullable = false)
	private UserModel createBy;

	/**
	 * 创建时间
	 */
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/**
	 * 最后修改人
	 */
	@LastModifiedBy
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "LAST_UPDATE_BY", nullable = false)
	private UserModel lastUpdateBy;

	/**
	 * 最后修改时间
	 */
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "LAST_UPDATE_TIME", nullable = false)
	private Date lastUpdateTime;

	@Transient
	public String getCreaterId() {
		return getCreateBy() == null ? null : getCreateBy().getId();
	}

	@Transient
	public String getCreater() {
		return getCreateBy() == null ? null : getCreateBy().getRealname();
	}

	@Transient
	public String getLastUpdaterId() {
		return getLastUpdateBy() == null ? null : getLastUpdateBy().getId();
	}
	
	@Transient
	public String getLastUpdater() {
		return getLastUpdateBy() == null ? null : getLastUpdateBy().getRealname();
	}

	public UserModel getCreateBy() {
		return createBy;
	}

	public void setCreateBy(UserModel createBy) {
		this.createBy = createBy;
	}

	public void setCreaterId(Integer createrId) {
		if (createrId != null) {
			this.createBy =	DBUtil.find(UserModel.class, createrId);
		}
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserModel getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(UserModel lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setLastUpdaterId(Integer lastUpdaterId) {
		if (lastUpdaterId != null) {
			this.lastUpdateBy =	DBUtil.find(UserModel.class, lastUpdaterId);
		}
	}
	
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
