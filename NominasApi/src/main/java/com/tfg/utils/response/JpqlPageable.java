package com.tfg.utils.response;

import java.io.Serializable;
import java.util.regex.Pattern;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


public class JpqlPageable implements Serializable{
	
	private final Integer DEFAULT_PAGE=1;
	private final Integer DEFAULT_PAGE_SIZE=50;
	private final String DEFAULT_SIDX= "id";
	private final String DEFAULT_SORD= "ASC";
	
	private Integer page;
	private Integer rows;
	private String sidx;
	private String sord;

	public JpqlPageable() {
		super();
		this.page=DEFAULT_PAGE;
		this.rows=DEFAULT_PAGE_SIZE;
		this.sord = DEFAULT_SORD;
		this.sidx = DEFAULT_SIDX;
	}
	
	public JpqlPageable(Integer page, Integer rows, String sidx, String sord) {
		super();
		this.page = page;
		this.rows = rows;
		this.sidx = sidx;
		this.sord = sord;
	}

	@Override
	public String toString() {
		return String.format("Pagina %d con %d registros. Ordenado por %s %s", page,rows,sidx,sord);
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * nunca devuelve null. Si el sord es null, entonces devuelve 'asc'
	 * @return el sentido de la ordenación, o 'asc' si no se se ha definido uno.
	 */
	public String getSord() {
		return sord != null ? sord : "asc";
	}

	public void setSord(String sord) {
		this.sord = sord;
	}
	
	/**
	 * Builds a {@link org.springframework.data.domain.Pageable} spring object, as needed in repositories 
	 * @return a pure Spring Pageable object
	 */
	public Pageable getPageable() {
		Sort sort = null;
		String orderBy = getSidx();
		if (orderBy != null ) {
			String[] orderbys = orderBy.split(",");
			Direction dir = getSord().toLowerCase().equals("desc") ? Direction.DESC : Direction.ASC;
			for (int i=0; i < orderbys.length; i++) {
				if (i==0)
					sort = new Sort(dir, orderbys[i]);
				else
					sort = sort.and(new Sort(dir, orderbys[i]));
			}

		}
		// Constructs page request for current page
		// Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
		Pageable pageable = null;
		if (sort != null) {
				pageable = new PageRequest(getPage() - 1, getRows(), sort);
			} else {
				pageable = new PageRequest(getPage() - 1, getRows());
			}
		return pageable;
	}

	public JpqlPageable traduceCampoOrdenacion(String buscaEsteCampo, String traduceAEste) {
		if (this.sidx != null) {
			Pattern p = Pattern.compile("^" + buscaEsteCampo + "$" +     // matches only value
	                "|^" + buscaEsteCampo + "," +     // matches first value + ','
	                "|," + buscaEsteCampo + "$" +     // matches ',' + last value
	                "|," + buscaEsteCampo + "(?=,)"); // matches ',' + middle value (+ ',')
			this.sidx = p.matcher(this.sidx).replaceAll(traduceAEste);
		}
		return this;
	}
	

	private static final long serialVersionUID = 9167583390484075674L;
}
