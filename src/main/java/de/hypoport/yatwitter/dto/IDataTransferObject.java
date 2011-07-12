package de.hypoport.yatwitter.dto;

import java.io.Serializable;

public interface IDataTransferObject<K extends Serializable> extends Serializable {
		public K getId(); 
}
