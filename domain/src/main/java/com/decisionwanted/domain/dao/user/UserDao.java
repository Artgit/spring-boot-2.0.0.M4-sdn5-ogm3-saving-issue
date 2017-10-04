package com.decisionwanted.domain.dao.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import com.decisionwanted.domain.dao.BaseDao;
import com.decisionwanted.domain.model.user.User;

@Validated
public interface UserDao extends BaseDao<User> {

	int MIN_PASSWORD_LENGHT = 4;
	int MAX_PASSWORD_LENGHT = 64;

	User create(@NotBlank String username, @NotNull @Size(min = MIN_PASSWORD_LENGHT, max = MAX_PASSWORD_LENGHT) String password, @NotNull @Email String email, String firstName, String lastName, User author);


}