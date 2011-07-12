package de.hypoport.yatwitter.dao;

import java.io.Serializable;
import java.util.List;

import de.hypoport.yatwitter.entity.DoInterface;

public interface DaoInterface<K extends Serializable,T extends DoInterface<K>> {
	void delete(T o);	
	
	void save(T o);
	
	void update(T o);
	
	T get(K id);
	
	T getNew();
	
	public List<T> findAll(int offset,int max);
	
	public int countAll();
}
