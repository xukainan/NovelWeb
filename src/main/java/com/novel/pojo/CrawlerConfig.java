package com.novel.pojo;

public class CrawlerConfig {
	private Integer id;
	private String crawlerId;
	private String configName;
	private String selector;
	private String num;
	private String attrName;
	private String reg;
	private Integer regGroupNum;
	private String headAppendResult;
	private String tailAppendResult;
	private String replaceResult;
	private Integer sort;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(String crawlerId) {
		this.crawlerId = crawlerId;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public Integer getRegGroupNum() {
		return regGroupNum;
	}

	public void setRegGroupNum(Integer regGroupNum) {
		this.regGroupNum = regGroupNum;
	}

	public String getHeadAppendResult() {
		return headAppendResult;
	}

	public void setHeadAppendResult(String headAppendResult) {
		this.headAppendResult = headAppendResult;
	}

	public String getTailAppendResult() {
		return tailAppendResult;
	}

	public void setTailAppendResult(String tailAppendResult) {
		this.tailAppendResult = tailAppendResult;
	}

	public String getReplaceResult() {
		return replaceResult;
	}

	public void setReplaceResult(String replaceResult) {
		this.replaceResult = replaceResult;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public CrawlerConfig(Integer id, String crawlerId, String configName, String selector, String num, String attrName,
			String reg, Integer regGroupNum, String headAppendResult, String tailAppendResult, String replaceResult,
			Integer sort) {
		this.id = id;
		this.crawlerId = crawlerId;
		this.configName = configName;
		this.selector = selector;
		this.num = num;
		this.attrName = attrName;
		this.reg = reg;
		this.regGroupNum = regGroupNum;
		this.headAppendResult = headAppendResult;
		this.tailAppendResult = tailAppendResult;
		this.replaceResult = replaceResult;
		this.sort = sort;
	}

	public CrawlerConfig() {
	}

	@Override
	public String toString() {
		return "CrawlerConfig [id=" + id + ", crawlerId=" + crawlerId + ", configName=" + configName + ", selector="
				+ selector + ", num=" + num + ", attrName=" + attrName + ", reg=" + reg + ", regGroupNum=" + regGroupNum
				+ ", headAppendResult=" + headAppendResult + ", tailAppendResult=" + tailAppendResult
				+ ", replaceResult=" + replaceResult + ", sort=" + sort + "]";
	}

}
