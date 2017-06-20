package my.edu.umk.pams.account.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import my.edu.umk.pams.account.web.module.util.vo.CovalentDtColumn;

public class DaoQuery {

	public static void setFilteringTerm(Query query, List<String> columns, String term) {
    	for(int i=0; i < columns.size(); i++){
    		query.setString(columns.get(i), term);
    	}
	}

	public static String buildFilteringSql(List<String> columns, String comparator) {
    	String filter = "";
    	
    	for(int i=0; i < columns.size(); i++){
    		if(i == 0)
    			filter += "where ";
    		else
    			filter += "or ";
    		
    		filter += "i." + columns.get(i) + " " + comparator + " :" + columns.get(i) + " ";
    	}
    	
    	return filter;
	}
	
	public static List<String> format(List<CovalentDtColumn> covalentDtColumns){
		List<String> stringColumns = new ArrayList<String>();
		
		for(CovalentDtColumn covalentDtColumn : covalentDtColumns){
			stringColumns.add(covalentDtColumn.getName());
		}
		
		return stringColumns;
	}
}
