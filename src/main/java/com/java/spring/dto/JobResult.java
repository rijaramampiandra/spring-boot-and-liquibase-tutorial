package com.java.spring.dto;

import java.util.List;

/**
 * Job response.
 * 
 * @author Rija RAMAMPIANDRA.
 *
 */
public class JobResult {

	public enum ResultType {
		RESPONSE_INIT, RESPONSE_NOT_STARTED, RESPONSE_RUNNING, RESPONSE_OK, RESPONSE_UNKNOWN, RESPONSE_ERROR, RESPONSE_ERROR_RETRY, BAD_AUTHENTICATION, RESPONSE_STOPPED
	};

	private Long jid;
	private ResultType result;
	private String description;
	private String extra;
	private List<InternalResult> internal;

	public JobResult() {
	}

	public JobResult(ResultType result, String description, String extra) {
		super();
		this.result = result;
		this.description = description;
		this.extra = extra;
	}

	public JobResult(Long jid, ResultType result, String description, String extra) {
		super();
		this.jid = jid;
		this.result = result;
		this.description = description;
		this.extra = extra;
	}

	public JobResult(Long jid, ResultType result, String description, String extra, List<InternalResult> internal) {
		super();
		this.jid = jid;
		this.result = result;
		this.description = description;
		this.extra = extra;
		this.internal = internal;
	}

	public JobResult(ResultType result, String description, String extra, List<InternalResult> internal) {
		super();
		this.result = result;
		this.description = description;
		this.extra = extra;
		this.internal = internal;
	}

	public Long getJid() {
		return jid;
	}

	public void setJid(Long jid) {
		this.jid = jid;
	}

	public ResultType getResult() {
		return result;
	}

	public void setResult(ResultType result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public List<InternalResult> getInternal() {
		return internal;
	}

	public void setInternal(List<InternalResult> internal) {
		this.internal = internal;
	}

	@Override
	public String toString() {
		return String.format("Result [jid=%s, result=%s, description=%s, extra=%s, internal=%s]", jid, result,
				description, extra, internal);
	}

	public static class InternalResult {
		private String element;
		private ResultType result;
		private String description;

		public InternalResult() {
			super();
		}

		public InternalResult(String element, ResultType result, String description) {
			this.element = element;
			this.result = result;
			this.description = description;
		}

		public String getElement() {
			return element;
		}

		public void setElement(String element) {
			this.element = element;
		}

		public ResultType getResult() {
			return result;
		}

		public void setResult(ResultType result) {
			this.result = result;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return String.format("InternalResult [element=%s, result=%s, description=%s]", element, result,
					description);
		}
	}
}