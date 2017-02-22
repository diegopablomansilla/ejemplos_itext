package org.cendra.eadministration.model.document;

import java.util.HashMap;
import java.util.Map;

import org.cendra.eadministration.model.Identifier;

public class Document extends Identifier {

	private Map<String, Object> properties = new HashMap<String, Object>();

	public void add(String k, Object v) {
		if (k == null) {
			throw new IllegalArgumentException("El nombre de la propiedad debe ser distinto de NULL.");
		}

		k = k.trim();

		if (k.length() == 0) {
			throw new IllegalArgumentException("El nombre de la propiedad debe ser distinto de vacio.");
		}

		if (properties.containsKey(k)) {
			throw new IllegalArgumentException("Este documento ya contiene una propiedad llamada " + k + ".");
		}

		properties.put(k, v);
	}

	public void set(String k, Object v) {
		if (k == null) {
			throw new IllegalArgumentException("El nombre de la propiedad debe ser distinto de NULL.");
		}

		k = k.trim();

		if (k.length() == 0) {
			throw new IllegalArgumentException("El nombre de la propiedad debe ser distinto de vacio.");
		}

		if (properties.containsKey(k) == false) {
			throw new IllegalArgumentException("Este documento no contiene una propiedad llamada " + k + ".");
		}

		properties.put(k, v);
	}

	public Object get(String k) {
		if (k == null) {
			throw new IllegalArgumentException("El nombre de la propiedad debe ser distinto de NULL.");
		}

		k = k.trim();

		if (k.length() == 0) {
			throw new IllegalArgumentException("El nombre de la propiedad debe ser distinto de vacio.");
		}

		if (properties.containsKey(k) == false) {
			throw new IllegalArgumentException("Este documento no contiene una propiedad llamada " + k + ".");
		}

		return properties.get(k);
	}

}
