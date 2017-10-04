package com.decisionwanted.domain.dao;

import javax.validation.constraints.NotNull;

import com.decisionwanted.domain.model.BaseEntity;
import com.decisionwanted.domain.model.user.User;

public interface BaseDao<T extends BaseEntity> {

	T getById(@NotNull Long id);

	T createOrUpdate(T t, User user);

	T createOrUpdate(T t, boolean auditing, User user);

	T createOrUpdate(T t, boolean auditing, int depth, User user);

	long getTotalCount();

}