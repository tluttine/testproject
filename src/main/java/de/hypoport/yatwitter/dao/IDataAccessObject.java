package de.hypoport.yatwitter.dao;

import java.io.Serializable;
import java.util.List;

import de.hypoport.yatwitter.dto.IDataTransferObject;

public interface IDataAccessObject<K extends Serializable,T extends IDataTransferObject<K>> {
	void delete(T o);	
	
	void save(T o);
	
	void update(T o);
	
	T get(K id);
	
	T getNew();
	
	public List<T> findAll(int offset,int max);
	
	public int countAll();
}
