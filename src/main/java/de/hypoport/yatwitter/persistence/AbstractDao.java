package de.hypoport.yatwitter.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDao<K extends Serializable, T extends DoInterface<K>> implements DaoInterface<K, T> {
	private static final Logger _logger = LoggerFactory.getLogger(AbstractDao.class);

	private Class<T> _domainClass;

	private SessionFactory _sessionFactory;

	public AbstractDao() {

	}

	protected AbstractDao(Class<T> domainClass) {
		_domainClass = domainClass;
	}

	@Override
	public int countAll() {
		Criteria criteria = getSession().createCriteria(_domainClass);
		criteria.setProjection(Projections.rowCount());
		return (Integer) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public void delete(T object) {
		getSession().delete(object);
	}

	@Override
	@Transactional
	public List<T> findAll(int offset, int max) {
		Criteria criteria = getSession().createCriteria(_domainClass);
		if (offset != 0)
			criteria.setFirstResult(offset);
		if (max != 0)
			criteria.setMaxResults(max);

		return criteria.list();
	}

	@Override
	public T get(K id) {
		return (T) getSession().get(_domainClass, id);
	}

	@Override
	public T getNew() {
		try {
			return _domainClass.newInstance();
		} catch (InstantiationException e) {
			_logger.error("newInstance failed", e);
		} catch (IllegalAccessException e) {
			_logger.error("newInstance failed", e);
		}
		return null;
	}

	public Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return _sessionFactory;
	}

	@Override
	@Transactional
	public void save(T object) {
		getSession().save(object);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		_sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void update(T object) {
		getSession().update(object);
	}

	protected Criteria getCriteria() {
		return getSession().createCriteria(_domainClass);
	}

	protected Class<T> getDomainClass() {
		return _domainClass;
	}
}
