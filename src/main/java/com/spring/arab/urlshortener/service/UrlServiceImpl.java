package com.spring.arab.urlshortener.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;
import com.spring.arab.urlshortener.entities.Url;
import com.spring.arab.urlshortener.entities.UrlDto;
import com.spring.arab.urlshortener.repositories.UrlRepository;

@Component
public class UrlServiceImpl implements UrlService{

	@Autowired
	private UrlRepository urlRepository;
	
	@Override
	public Url generateShortLink(UrlDto urlDto) {
		if(StringUtils.isNotEmpty(urlDto.getUrl()) {
			String encodedUrl = encodeUrl(urlDto.getUrl());
			Url urlToPersist = new Url();
			urlToPersist.setCreationTime(LocalDateTime.now());
			urlToPersist.setOriginalUrl(urlDto.getUrl());
			urlToPersist.setShortLink(encodedUrl);
			urlToPersist.setExpirationTime(getExpirationDate(urlDto.getExpirationDate(), urlToPersist.getCreationTime()));
			Url urlToRet = persistShortLink(urlToPersist);
		}
		return null;
	}

	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationTime) {
		if(StringUtils.isBlank(expirationDate)) {
			return creationTime.plusSeconds(120);
		}
		LocalDateTime expirationDateToUrl = LocalDateTime.parse(expirationDate);
		return expirationDateToUrl;
	}

	private String encodeUrl(String url) {
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32()
				.hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
				.toString();
		return encodedUrl;
	}

	@Override
	public Url persistShortLink(Url url) {
		Url urlToRet = urlRepository.save(url);
		return urlToRet;
	}

	@Override
	public Url getEncodedUrl(String url) {
		Url urlToRet = urlRepository.findByShortLink(url);
		return urlToRet;
	}

	@Override
	public void deleteShortLink(Url url) {
		urlRepository.delete(url);
	}

}
