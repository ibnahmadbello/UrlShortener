package com.spring.arab.urlshortener.service;

import java.time.LocalDateTime;

import com.spring.arab.urlshortener.entities.Url;
import com.spring.arab.urlshortener.entities.UrlDto;

public class UrlServiceImpl implements UrlService{

	@Override
	public Url generateShortLink(UrlDto urlDto) {
		if(urlDto.getUrl() != null) {
			String encodedUrl = encodeUrl(urlDto.getUrl());
		} //if(StringUtils.isNotEmpty(uriDto.getUrl())
		return null;
	}

	private String encodeUrl(String url) {
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = "";
		return encodedUrl;
	}

	@Override
	public Url persistShortLink(Url uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Url getEncodedUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShortLink(Url uri) {
		// TODO Auto-generated method stub
		
	}

}
