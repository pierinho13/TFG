package com.tfg.utils.services;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;


/**
 * Clase con métodos de utilidad para trabajar con JPQL
 *
 */
@Service
public class QueryUtils {

	/**
	 * Añade al string jpql las condiciones de ordenado y paginación, y luego crea un objeto de tipo Query
	 * a partir de ese string.
	 * @param jpql String con una sentencia JPQL a la que hay que añadir aun algunas cosas
	 * @param page Objeto Page con los parametros de paginacion y ordenacion
	 * @param entityManager el EntityManager
	 * @return La Query ya lista para ser lanzada.
	 */	
	public Query configuraPaginacionEnJpql(String jpql, Pageable page, EntityManager entityManager) {
		return configuraPaginacionEnJpqlConPrefijos(jpql,page,entityManager);
	}
	
	/**
	 * Añade al string jpql las condiciones de ordenado y paginación, y luego crea un objeto de tipo Query
	 * a partir de ese string. Y luego fusiona el resto de parametros que nos llegan en el mapa parametrosQuery
	 * @param jpql String con una sentencia JPQL a la que hay que añadir aun algunas cosas
	 * @param page Objeto Page con los parametros de paginacion y ordenacion
	 * @param entityManager el EntityManager
	 * @param parametrosQuery Mapa con los parametros a inyectar y sus valores.
	 * @return La Query ya lista para ser lanzada.
	 */	
	public Query configuraPaginacionEnJpql(String jpql, Pageable page, EntityManager entityManager,Map<String, Object> parametrosQuery) {
		return configuraPaginacionEnJpqlConPrefijos(jpql,page,entityManager,parametrosQuery);
	}

	/**
	 * Añade al string jpql las condiciones de ordenado y paginación, y luego crea un objeto de tipo Query
	 * a partir de ese string.
	 * @param jpql String con una sentencia JPQL a la que hay que añadir aun algunas cosas
	 * @param page Objeto Page con los parametros de paginacion y ordenacion
	 * @param entityManager el EntityManager
	 * @param prefijos para añadir a los campos de ordenación. A veces hay conflicto con dos atributos que se llaman igual 
	 * en diferentes tablas, y al hacer el order by, dice que hay conflicto. Suele ser una letra como "p" o "c" que es el
	 * alias de la tabla donde está el campo por el que queremos ordenar 
	 * @return La Query ya lista para ser lanzada.
	 */	
	public Query configuraPaginacionEnJpqlConPrefijos(String jpql, Pageable page, EntityManager entityManager, String... prefijos) {
		if (page != null) {
			if (page.getSort()!=null){
				jpql = jpql.concat("order by ");
				Iterator<Order> sortIterator = page.getSort().iterator();
				int index=0;
				while(sortIterator.hasNext()) {
					String prefijo = getPrefijoOrden(index,prefijos);
					Order order =  sortIterator.next();
					String property = (!order.getProperty().contains(prefijo)) ? prefijo+order.getProperty() : order.getProperty();
					jpql = jpql.concat(property+ " "+order.getDirection().toString()+",");
				}
				jpql=StringUtils.chop(jpql);
			}
		}
		Query query = entityManager.createQuery(jpql);
		if (page != null) {
			int offset=((page.getPageNumber())*page.getPageSize());
			query.setFirstResult(offset);
			query.setMaxResults(page.getPageSize());
		}
		return query;
	}

	/**
	 * Añade al string jpql las condiciones de ordenado y paginación, y luego crea un objeto de tipo Query
	 * a partir de ese string. Y luego fusiona el resto de parametros que nos llegan en el mapa parametrosQuery
	 * @param jpql String con una sentencia JPQL a la que hay que añadir aun algunas cosas
	 * @param page Objeto Page con los parametros de paginacion y ordenacion
	 * @param entityManager el EntityManager
	 * @param parametrosQuery Mapa con los parametros a inyectar y sus valores.
	 * @return La Query ya lista para ser lanzada.
	 */
	public Query configuraPaginacionEnJpqlConPrefijos(String jpql, Pageable page, EntityManager entityManager, Map<String, Object> parametrosQuery, String... prefijos ) {
		Query query = configuraPaginacionEnJpqlConPrefijos(jpql,page,entityManager,prefijos);
		//inyectamos el resto de parametros a la query.
		fusionaParametros(query,parametrosQuery);
		return query;
	}
	
	private String getPrefijoOrden(int index, String[] prefijos) {
		if (prefijos!=null) {
    		if ((prefijos.length) > index) {
    			return prefijos[index]+".";
    		}
		}
		return "";
	}


	public void fusionaParametros(Query query, Map<String, Object> parametrosQuery) {
		for (String key : parametrosQuery.keySet()) {
			query.setParameter(key, parametrosQuery.get(key));
		}
	}
	
	/**
	 * A partir de la Query que se le pasa (que es un SELECT COUNT) se fusiona los parametros
	 * que llegan en el mapa y se llama al getSingleResult que nos da el total de registros.
	 * @param queryCount un objeto de tipo Query con el JPQL a ejecutar
	 * @param parametrosQuery el mapa con los parametros que hay que fusionar
	 * @return El total de registros encontrados para la Query recogida.
	 */
	public Long cuentaRegistros(Query queryCount, Map<String, Object> parametrosQuery) {
		if (parametrosQuery!=null)
			fusionaParametros(queryCount,parametrosQuery);
		Long totalRegistros = (Long)queryCount.getSingleResult();
		return totalRegistros;
	}

	public BigDecimal sumaRegistros(Query queryCount, Map<String, Object> parametrosQuery) {
		if (parametrosQuery!=null)
			fusionaParametros(queryCount,parametrosQuery);
		BigDecimal totalRegistros = (BigDecimal)queryCount.getSingleResult();
		return totalRegistros;
	}

	
	/**
	 * Concatena a un string JPQL la condición pasada, si es que el valor de la condición es 
	 * distinto de null. En este caso, se concatena la condición, y añade el parametro al 
	 * mapa de paramétros para luego fundirlo con la Query que se genere a apartir del string JPQL 
	 * @param valorParametro El valor que hay que meter en el mapa de parametros
	 * @param condicionAnadir El texto con la condición para concatenar
	 * @param predicadoJpql La sentencia JPQL tal cual esta hasta este momento
	 * @param parametrosQuery El mapa donde se van almacenando los parametros (nombre, valor)
	 * @return la sentencia JPQL modificada (si es que ha sufrido cambios).
	 */
	public String concatenaSiEsNecesario(Object valorParametro, String condicionAnadir, String predicadoJpql, Map<String, Object> parametrosQuery) {
		String nuevoPredicado=predicadoJpql;
		
		if (valorParametro!=null) {
			String nombreParametro=null;
			Pattern pattern = Pattern.compile(":(\\w*)");
			Matcher m = pattern.matcher(condicionAnadir);
			if (m.find()) {
				nombreParametro = m.group(1);
			}
			nuevoPredicado = predicadoJpql.concat(condicionAnadir+" ");
			parametrosQuery.put(nombreParametro, valorParametro);
		}
		return nuevoPredicado;
		
	}

	/**
	 * Concatena a un string JPQL la condición pasada, si es que el ultimo de los parametros,
	 * el llamado esNecesario es true. En este caso, se concatena la condición, y añade el parametro 
	 * al mapa de paramétros para luego fundirlo con la Query que se genere a apartir del string JPQL 
	 * @param valorParametro El valor que hay que meter en el mapa de parametros
	 * @param condicionAnadir El texto con la condición para concatenar
	 * @param predicadoJpql La sentencia JPQL tal cual esta hasta este momento
	 * @param parametrosQuery El mapa donde se van almacenando los parametros (nombre, valor)
	 * @param esNecesario condicion booleana que indica si es necesario concatenar. 
	 * @return la sentencia JPQL modificada (si es que ha sufrido cambios).
	 */
	public String concatenaSiEsNecesario(Object valorParametro, String condicionAnadir, String predicadoJpql, Map<String, Object> parametrosQuery, boolean esNecesario) {
		String nuevoPredicado=predicadoJpql;
		if (esNecesario) {
			String nombreParametro=null;
			Pattern pattern = Pattern.compile(":(\\w*)");
			Matcher m = pattern.matcher(condicionAnadir);
			if (m.find()) {
				nombreParametro = m.group(1);
			}
			nuevoPredicado = predicadoJpql.concat(condicionAnadir+" ");
			if (nombreParametro!=null) { //si encuentra un parámetro en la condicionAnadir
				parametrosQuery.put(nombreParametro, valorParametro);
			}
		}
		return nuevoPredicado;
		
	}


	/**
	 * Funcion que devuelve la cadena a añadir, si es que hay que añadirla. Para ver si hay que añadirla,
	 * se mira simplemente que no esté a null. Si se añade, se mete en el mapa de parámetros su valor para
	 * luego fundirlo con la Query que se generará.
	 * @param valorParametro valor del parámetro, que generalmente viene en un Command de un formulario
	 * @param condicionAnadir la condición a devolver (para meter en medio de otro SQL)
	 * @param parametrosQuery el mapa que contiene los parámetros (con su valor) a fusionar con la Query
	 * @return la condición a añadir si es que hay que añadirla, o una cadena vacía si no.
	 */
	public String insertaSiEsNecesario(Object valorParametro, String condicionAnadir, Map<String, Object> parametrosQuery) {
		String predicadoSql="";
		if (valorParametro!=null) {
			String nombreParametro=null;
			Pattern pattern = Pattern.compile(":(\\w*)");
			Matcher m = pattern.matcher(condicionAnadir);
			if (m.find()) {
				nombreParametro = m.group(1);
			}
			parametrosQuery.put(nombreParametro, valorParametro);
			predicadoSql = predicadoSql.concat(condicionAnadir+" ");
		}
		return predicadoSql;
	}

	public String likeSiEsNecesario(Object valorParametro, String condicionAnadir, String predicadoJpql, Map<String, Object> parametrosQuery, boolean esNecesario) {
		String nuevoPredicado=predicadoJpql;
		if (esNecesario) {
    		if (valorParametro!=null) {
    			String nombreParametro=null;
    			Pattern pattern = Pattern.compile(":(\\w*)");
    			Matcher m = pattern.matcher(condicionAnadir);
    			if (m.find()) {
    				nombreParametro = m.group(1);
    			}
    			nuevoPredicado = predicadoJpql.concat(condicionAnadir+" ");
    			parametrosQuery.put(nombreParametro, "%"+valorParametro+"%");
    		}
		}
		return nuevoPredicado;
	}	
}