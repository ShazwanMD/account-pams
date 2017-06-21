package my.edu.umk.pams.account.web.module.util.vo;

import java.util.List;

public class CovalentDtQuery {

    private String searchTerm;
    private Integer currentPage;
    private Integer pageSize;
    private CovalentDtColumnOrder sort;
    private List<CovalentDtColumn> columns;
    
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public CovalentDtColumnOrder getSort() {
		return sort;
	}
	public void setSort(CovalentDtColumnOrder sort) {
		this.sort = sort;
	}
	public List<CovalentDtColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<CovalentDtColumn> columns) {
		this.columns = columns;
	}

}
