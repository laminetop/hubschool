package sn.projet.hubschool.facade;

import java.util.List;

public interface BaseFacade<Base, KeyType> {

	Base load(Long id);

	Base saveOrUpdate(Base dto);

	void delete(Base dto);

	List < Base > getList();

	void deleteById(Long id);

}
