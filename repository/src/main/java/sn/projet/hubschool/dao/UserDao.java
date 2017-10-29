package sn.projet.hubschool.dao;

import sn.projet.hubschool.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository < User, Long > {

	User findByUsername(String username);

}
