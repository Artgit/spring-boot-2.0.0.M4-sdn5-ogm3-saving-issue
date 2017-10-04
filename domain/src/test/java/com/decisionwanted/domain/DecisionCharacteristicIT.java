package com.decisionwanted.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.decisionwanted.domain.configuration.TestApplication;
import com.decisionwanted.domain.dao.decision.DecisionDao;
import com.decisionwanted.domain.dao.decision.characteristic.CharacteristicDao;
import com.decisionwanted.domain.dao.decision.characteristic.value.ValueDao;
import com.decisionwanted.domain.dao.decision.characteristic.value.history.HistoryValueDao;
import com.decisionwanted.domain.dao.user.UserDao;
import com.decisionwanted.domain.model.decision.Decision;
import com.decisionwanted.domain.model.decision.characteristic.Characteristic;
import com.decisionwanted.domain.model.decision.characteristic.value.Value;
import com.decisionwanted.domain.model.decision.characteristic.value.history.HistoryValue;
import com.decisionwanted.domain.model.user.User;
import com.decisionwanted.domain.service.data.DataGenerator;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class DecisionCharacteristicIT {

	final static Logger logger = LoggerFactory.getLogger(DecisionCharacteristicIT.class);

	@Autowired
	private DecisionDao decisionDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CharacteristicDao characteristicDao;

	@Autowired
	private ValueDao valueDao;

	@Autowired
	private HistoryValueDao historyValueDao;
	
	@Autowired
	private DataGenerator dataGenerator;

	@Before
	public void setUp() {
		dataGenerator.cleanDb();
	}
	
	@Test
	public void testUpdateValue() {
		
        final Set<Class<?>> allClasses = new HashSet<>();
        new FastClasspathScanner("com.decisionwanted.domain.configuration").matchAllClasses(allClasses::add).strictWhitelist().scan();

        System.out.println("!!!!!: " + allClasses);

		User user1 = userDao.create("test1", "test1", "test1@test.com", null, null, null);
		User user2 = userDao.create("test2", "test2", "test2@test.com", null, null, null);
		User user3 = userDao.create("test3", "test3", "test3@test.com", null, null, null);
		User user4 = userDao.create("test4", "test4", "test4@test.com", null, null, null);

		final Decision databaseDecision = decisionDao.create("Database", "How to choose proper database type", null,
				false, null, user1);

		final Characteristic horScalingCharacteristic = characteristicDao.create("Horizontal scaling",
				"Horizontal scaling characteristic description", false, false, databaseDecision, user1, null, null);

		final Decision rdbmsDecision = decisionDao.create("RDBMS", "RDBMS description", null, false, databaseDecision,
				user1);

		assertEquals(0, historyValueDao.getTotalCount());

		String originalValue = "Value";

		Value rdbmsHorScalingValue = valueDao.create(rdbmsDecision, horScalingCharacteristic, user1,
				new Value(originalValue, null), null);

		rdbmsHorScalingValue = valueDao.getById(rdbmsHorScalingValue.getId());

		assertEquals(user1, rdbmsHorScalingValue.getCreateUser());
		assertNull(rdbmsHorScalingValue.getUpdateUser());

		assertNotNull(rdbmsHorScalingValue);
		assertNotNull(rdbmsHorScalingValue.getId());
		Date createDate = rdbmsHorScalingValue.getCreateDate();
		assertNotNull(createDate);
		assertNotNull(rdbmsHorScalingValue.getUpdateDate());
		assertEquals(createDate, rdbmsHorScalingValue.getUpdateDate());
		assertEquals(originalValue, rdbmsHorScalingValue.getValue());
		assertNull(rdbmsHorScalingValue.getDescription());
		assertEquals(user1, rdbmsHorScalingValue.getCreateUser());
		assertNull(rdbmsHorScalingValue.getUpdateUser());
		assertEquals(0, rdbmsHorScalingValue.getTotalHistoryValues(), 0);

		assertEquals(0, historyValueDao.getTotalCount());

		String newStringValue1 = "Value 1";
		String newStringDescription1 = "Hello, World!";
		rdbmsHorScalingValue = valueDao.update(rdbmsHorScalingValue, newStringValue1, newStringDescription1, user2,
				false);

		assertEquals(user2, rdbmsHorScalingValue.getUpdateUser());

		rdbmsHorScalingValue = valueDao.getById(rdbmsHorScalingValue.getId());

		assertEquals(user1, rdbmsHorScalingValue.getCreateUser());
		assertEquals(user2, rdbmsHorScalingValue.getUpdateUser());

		assertNotNull(rdbmsHorScalingValue);
		assertNotNull(rdbmsHorScalingValue.getId());
		assertNotNull(rdbmsHorScalingValue.getCreateDate());
		Date value1UpdateDate = rdbmsHorScalingValue.getUpdateDate();
		assertNotNull(value1UpdateDate);
		assertThat(rdbmsHorScalingValue.getCreateDate(), not(equalTo(value1UpdateDate)));
		assertEquals(newStringValue1, rdbmsHorScalingValue.getValue());
		assertEquals(newStringDescription1, rdbmsHorScalingValue.getDescription());
		assertEquals(user1, rdbmsHorScalingValue.getCreateUser());
		assertEquals(user2, rdbmsHorScalingValue.getUpdateUser());

		assertEquals(1, historyValueDao.getTotalCount());

		assertEquals(1, rdbmsHorScalingValue.getTotalHistoryValues(), 0);

		List<HistoryValue> historyValues = historyValueDao.findAllByValueId(rdbmsHorScalingValue.getId());

		assertEquals(1, historyValues.size());

		assertEquals(originalValue, historyValues.get(0).getOriginalValue());
		assertNull(historyValues.get(0).getDescription());
		assertEquals(createDate, historyValues.get(0).getCreateDate());
		assertEquals(user1, historyValues.get(0).getCreateUser());
		assertNull(historyValues.get(0).getUpdateUser());

		String newStringValue2 = "Value 2";
		String newStringDescription2 = "Value description2";
		rdbmsHorScalingValue = valueDao.update(rdbmsHorScalingValue, newStringValue2, newStringDescription2, user3,
				null);

		assertEquals(user3, rdbmsHorScalingValue.getUpdateUser());

		rdbmsHorScalingValue = valueDao.getById(rdbmsHorScalingValue.getId());

		assertEquals(user3, rdbmsHorScalingValue.getUpdateUser());

	}

}