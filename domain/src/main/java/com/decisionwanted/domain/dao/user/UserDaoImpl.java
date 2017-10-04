package com.decisionwanted.domain.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decisionwanted.domain.dao.BaseDaoImpl;
import com.decisionwanted.domain.model.user.User;
import com.decisionwanted.domain.repository.BaseRepository;
import com.decisionwanted.domain.repository.user.UserRepository;

@Service
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(String username, String password, String email, String firstName, String lastName, User author) {

		User user = new User(username, password, email, firstName, lastName);

		user.setUsernameConfirmed(true);

		return createOrUpdate(user, author);
	}

	@Override
	@Transactional(readOnly = true)
	public BaseRepository<User> getRepository() {
		return userRepository;
	}

}