package com.ghm.giftcardfleamarket.user.dao;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SmsVerificationDao {

	private final StringRedisTemplate redisTemplate;

	@Value("${spring.data.redis.timeout}")
	private long TIME_OUT;

	public void saveVerificationCode(String phone, String verificationCode) {
		redisTemplate.opsForValue().set(phone, verificationCode, Duration.ofMillis(TIME_OUT));
	}

	public String getVerificationCode(String phone) {
		return redisTemplate.opsForValue().get(phone);
	}

	public void deleteVerificationCode(String phone) {
		redisTemplate.delete(phone);
	}
}
