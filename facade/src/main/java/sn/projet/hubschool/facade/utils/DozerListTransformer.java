package sn.projet.hubschool.facade.utils;

import org.apache.commons.collections.Transformer;
import org.dozer.DozerBeanMapper;

public class DozerListTransformer implements Transformer {

	final Class < ? > typeParameterClass;
	final DozerBeanMapper dozerBeanMapper;

	public DozerListTransformer(final Class < ? > typeParameterClass, final DozerBeanMapper dozerBeanMapper) {
		this.typeParameterClass = typeParameterClass;
		this.dozerBeanMapper = dozerBeanMapper;
	}

	@Override
	public Object transform(final Object input) {
		return dozerBeanMapper.map(input, typeParameterClass);
	}
}
