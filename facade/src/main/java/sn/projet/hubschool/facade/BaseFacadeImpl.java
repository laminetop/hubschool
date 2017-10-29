package sn.projet.hubschool.facade;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import sn.projet.hubschool.facade.dto.BaseVO;
import sn.projet.exception.IExceptionFactory;

public abstract class BaseFacadeImpl<T extends BaseVO, K extends Serializable> implements BaseFacade < T, K > {
	@Autowired
	public DozerBeanMapper dozerBeanMapper;

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
