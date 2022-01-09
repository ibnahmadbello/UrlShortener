package com.spring.arab.urlshortener.service;

import org.springframework.stereotype.Service;

import com.spring.arab.urlshortener.entities.Url;
import com.spring.arab.urlshortener.entities.UrlDto;

@Service
public interface UrlService {
	public Url generateShortLink(UrlDto uriDto);
	public Url persistShortLink(Url uri);
	public Url getEncodedUrl(String url);
	public void deleteShortLink(Url uri);
}
