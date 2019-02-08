package com.java.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity for WS REGION.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
@Entity
@Table(name = "REGION")
@Data
public class Region implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100)
	private String description;

	@Column(name = "create_date")
	private Date createDate;

	public static class Builder {
		private Integer id;
		private String description;
		private Date createDate;

		public Builder initId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder onCreateDate(Date createDate) {
			this.createDate = createDate;
			return this;
		}

		public Region build() {
			final Region regionBuild = new Region();
			regionBuild.id = this.id;
			regionBuild.description = this.description;
			regionBuild.createDate = this.createDate;

			return regionBuild;
		}
	}

	private Region() {
	}
}
