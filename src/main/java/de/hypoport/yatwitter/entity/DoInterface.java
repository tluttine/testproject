package de.hypoport.yatwitter.entity;

import java.io.Serializable;

public interface DoInterface<K extends Serializable> extends Serializable {
		public K getId(); 
}
