package com.decisionwanted.domain.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.decisionwanted.domain.dao.decision.DecisionDao;
import com.decisionwanted.domain.dao.decision.characteristic.value.ValueDao;
import com.decisionwanted.domain.dao.user.UserDao;
import com.decisionwanted.domain.model.BaseEntity;
import com.decisionwanted.domain.model.user.User;
import com.decisionwanted.domain.repository.BaseRepository;

@Transactional
public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

	private static final String GENERATE_UNIQUE_ID_CYPHER_QUERY = "MERGE (id:UniqueId{name:{entityName}}) ON CREATE SET id.count = 1 ON MATCH SET id.count = id.count + 1 RETURN id.count AS uid";
	private static final String ENTITY_NAME = "entityName";
	private static final String UID_PARAMETER = "uid";

	@Autowired
	private Session session;

	@Lazy
	@Autowired
	private UserDao userDao;

	@Lazy
	@Autowired
	private DecisionDao decisionDao;

	@Lazy
	@Autowired
	private ValueDao valueDao;

	@Override
	public T getById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T createOrUpdate(T t, User user) {
		return createOrUpdate(t, true, user);
	}

	@Override
	public T createOrUpdate(T t, boolean auditing, User user) {
		return createOrUpdate(t, auditing, 1, user);
	}

	@Override
	public T createOrUpdate(T t, boolean auditing, int depth, User user) {

		if (t.getId() == null) {
			t.setId(generateUniqueId(t));
		}

		if (auditing) {
			auditing(t, user);
		}

		return getRepository().save(t, depth);
	}

	protected void auditing(T t, User user) {

		Date date = new Date();

		if (t.getCreateDate() == null) {

			t.setCreateDate(date);
			t.setUpdateDate(date);

			if (user != null) {
				t.setCreateUser(user);
				t.addOwnerUser(user);
			}

		} else {
			t.setUpdateDate(date);
			t.setUpdateUser(user);
		}

	}

	protected Long generateUniqueId(T t) {

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(ENTITY_NAME, t.getClass().getSimpleName());

		Result queryResult = session.query(GENERATE_UNIQUE_ID_CYPHER_QUERY, parameters);

		return ((Number) queryResult.iterator().next().get(UID_PARAMETER)).longValue();
	}

	public abstract BaseRepository<T> getRepository();

	@Override
	public long getTotalCount() {
		return getRepository().count();
	}

}