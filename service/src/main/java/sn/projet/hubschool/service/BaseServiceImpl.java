package sn.projet.hubschool.service;

import sn.projet.hubschool.model.BaseEntity;
import sn.projet.exception.IExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity, K extends Serializable> implements BaseService < T, K > {

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected IExceptionFactory factory;

	protected Logger log = null;

	/** Post construction du bean. */
	@PostConstruct
	public void after() {
		log = LoggerFactory.getLogger(this.getClass());
		Assert.notNull(factory);
		Assert.notNull(log);
	}

	public abstract T load(K id);

	@Override
	public abstract T saveOrUpdate(T t);

	@Override
	public abstract void delete(T t);

	@Override
	public abstract List < T > getList();

}
