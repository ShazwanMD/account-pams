package my.edu.umk.pams.account.web.module.util.vo;

import java.util.List;

public class CovalentDatatableQuery {

    private String searchTerm;
    private Integer currentPage;
    private Integer pageSize;
    private CovalentDatatableColumnOrder sort;
    private List<CovalentDatatableColumn> columns;
    
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
	public CovalentDatatableColumnOrder getSort() {
		return sort;
	}
	public void setSort(CovalentDatatableColumnOrder sort) {
		this.sort = sort;
	}
	public List<CovalentDatatableColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<CovalentDatatableColumn> columns) {
		this.columns = columns;
	}

}
