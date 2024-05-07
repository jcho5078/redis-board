package com.study.board;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.stream.IntStream;

@SpringBootTest
class BoardApplicationTests {

	@Test
	void testJedisSetStream(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();

		// test jedis set memory 100000 count
		IntStream.rangeClosed(0, 100000).forEach(i -> {
			jedis.sadd("request-test:jedis:set", String.valueOf(i), "test");
		});

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
	}

	@Test
	void testJedisPiplineSetStream(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();
		Pipeline pipeline = jedis.pipelined();

		IntStream.rangeClosed(0, 100000).forEach(i -> {
			pipeline.sadd("request-test:pipline:set", String.valueOf(i), "test");
		});
		pipeline.sync();
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
	}

	@Test
	void testJedisPiplineBitStream(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();
		Pipeline pipeline = jedis.pipelined();

		IntStream.rangeClosed(0, 100000).forEach(i -> {
			pipeline.setbit("request-test:pipline:bit", i, true);

			if(i==1000){
				pipeline.sync();
			}
		});
		pipeline.sync();
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
	}

}
