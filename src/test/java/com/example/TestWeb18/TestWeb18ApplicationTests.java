package com.example.TestWeb18;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.util.concurrent.ConcurrentHashMap;

//@RunAs(SpringRunner.class)
@SpringBootTest()
class TestWeb18ApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(TestWeb18ApplicationTests.class);
	@Test
	void contextLoads() {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		logger.info("didi");
	}

}
