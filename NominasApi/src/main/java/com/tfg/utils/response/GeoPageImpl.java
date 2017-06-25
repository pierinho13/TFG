package com.tfg.utils.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Basic {@code Page} implementation.
 * 
 * @param <T>
 *            the type of which the page consists.
 * @author Oliver Gierke
 */
public class GeoPageImpl<T> extends PageImpl<T> implements 
		Serializable {

	public interface ForJqGrid {};
	
	private static final long serialVersionUID = 867755909294344406L;
	private final JpqlPageable pageable;
	private final List<T> rows = new ArrayList<T>();
	private Map<String,Object> userdata;

	/**
	 * Constructor of {@code QonPageImpl}.
	 * 
	 * @param content
	 *            the content of this page
	 * @param pageable
	 *            the paging information
	 * @param total
	 *            the total amount of items available
	 */
	public GeoPageImpl(List<T> content, JpqlPageable pageable, long total) {
		//I pass an empty ArrayList, cause if not, I would have twice the collection,
		//once in content and another in rows. I only want it in rows, as is the attribute
		//that jqGrid will look for.
		super(new ArrayList<T>(0), pageable != null ? pageable.getPageable() : null, total);
		if (null == content) {
			throw new IllegalArgumentException("Content must not be null!");
		}
		this.rows.addAll(content);

		this.pageable = pageable;
		
	}

	/**
	 * Constructor of {@code QonPageImpl}.
	 * 
	 * @param springPage
	 *            to be converted into a QonPageImpl
	 * @param pageable
	 *            used for the paging, passed to the repository
	 */
	public GeoPageImpl(Page<T> springPage, JpqlPageable pageable) {
		this(springPage.getContent(), pageable, springPage.getTotalElements());
	}

	/**
	 * Creates a new {@link GeoPageImpl} with the given content. This will
	 * result in the created {@link GeoPageImpl} being identical to the entire
	 * {@link List}.
	 * 
	 * @param content
	 */
	public GeoPageImpl(List<T> content) {

		this(content, null, (null == content) ? 0 : content.size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Page#getNumber()
	 */
	@JsonView(ForJqGrid.class)
	public int getPage() {

		return pageable == null ? 0 : pageable.getPage();
	}

	@JsonView(ForJqGrid.class)
	public int getTotal() {

		return getSize() == 0 ? 0 : (int) Math.ceil((double) super
				.getTotalElements() / (double) getSize());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Page#getTotalElements()
	 */
	@JsonView(ForJqGrid.class)
	public long getRecords() {
		return super.getTotalElements();
	}

	@JsonView(ForJqGrid.class)
	public List<T> getRows() {
		//return Collections.unmodifiableList(super.getContent());
		return rows;
	}
	
	@JsonView(ForJqGrid.class)
	public Map<String, Object> getUserdata() {
		return userdata;
	}

	public void setUserdata(Map<String, Object> userData) {
		this.userdata = userData;
	}
	
	public void addUserdataField(String fieldName,Object fieldValue) {
		if (userdata==null) {
			userdata = new HashMap<String,Object>(1);
		}
		userdata.put(fieldName, fieldValue);
	}

	public String toString() {

		String contentType = "UNKNOWN";

		if (rows.size() > 0) {
			contentType = rows.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
	}

}
