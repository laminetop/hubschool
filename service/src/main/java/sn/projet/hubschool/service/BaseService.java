package sn.projet.hubschool.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BaseService<BaseEntity, KeyType> {

	BaseEntity load(Long id);

	@Transactional
	BaseEntity saveOrUpdate(BaseEntity entity);

	@Transactional
	void delete(BaseEntity entity);

	List < BaseEntity > getList();

	void deleteById(Long id);
}
