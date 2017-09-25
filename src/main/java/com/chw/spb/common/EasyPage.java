package com.chw.spb.common;

import com.chw.spb.system.service.ResultMapService;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
public class EasyPage{

	private Class type;

	private String resultMapId;

	private int pageSize;

	private int pageNumber;
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * 排序 asc desc
	 */
	private String order;

	private List<QueryField> queryFields;

	public EasyPage() {
	}

	public EasyPage(Class type) {
		this.type = type;
	}

	public EasyPage build(HttpServletRequest request) {
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		this.order = order;
		this.sort = ResultMapService.get(this.type.getName() + "." +sort );
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.queryFields = createQueryFields(request.getParameterMap());
		return this;
	}

	private List<QueryField> createQueryFields(Map<String,String[]> params){
		ArrayList<QueryField> fields = Lists.newArrayList();
		for (String key: params.keySet()){
			if (key.startsWith("filter_")) {
				String val = params.get(key)[0];
				QueryField queryField = QueryField.create(key, val);
//				queryField.setName(CommonSqlSession.transferProperty2Column(resultMapId, queryField.getName()));
				String name = ResultMapService.get(this.type.getName() + "." +queryField.getName() );
				queryField.setName(name);
				fields.add(queryField);
			}

		}
		return fields;
	}

	public String getResultMapId() {
		return resultMapId;
	}

	public void setResultMapId(String resultMapId) {
		this.resultMapId = resultMapId;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<QueryField> getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(List<QueryField> queryFields) {
		this.queryFields = queryFields;
	}



	public static class QueryField{

		private String name;

		private String type;

		private String value;

		@Override
		public String toString() {
			return "QueryField{" +
					"name='" + name + '\'' +
					", type='" + type + '\'' +
					", value='" + value + '\'' +
					'}';
		}

		public static QueryField create(String filterStr, String value) {
			//先取括号( )
			int i = filterStr.indexOf("(");
			String[] filterStrs;
			String name;

			if (i < 0) {
				filterStrs = filterStr.split("_");
				name = filterStrs[2];
			} else {
				filterStrs = filterStr.substring(0,i).split("_");
				name = filterStr.substring(i+1,filterStr.lastIndexOf(")"));
			}
			String typeStr = filterStrs[1];
			//转换
			Type type = Type.valueOf(typeStr.toUpperCase());
			if (type.name().equals("LIKE") && !StringUtils.isEmpty(value)) {
				value = "%" + value +"%";
			}

			QueryField queryField = new QueryField(name,type.getType(),value);
			return queryField;
		}
		public enum Type{

			GT(">"),
			GE(">="),
			LT("<"),
			LE("<"),
			EQ("="),
			LIKE("like");

			private String type;
			Type(String type ){
				this.type = type;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}
		}


		public QueryField(String name, String type, String value) {
			this.name = name;
			this.type = type;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	@Override
	public String toString() {
		return "EasyPage{" +
				" pageSize=" + pageSize +
				", pageNumber=" + pageNumber +
				", sort='" + sort + '\'' +
				", order='" + order + '\'' +
				", queryFields=" + queryFields +
				'}';
	}


}
