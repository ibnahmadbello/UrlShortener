package com.spring.arab.urlshortener.controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.arab.urlshortener.entities.Url;
import com.spring.arab.urlshortener.entities.UrlDto;
import com.spring.arab.urlshortener.entities.UrlErrorResponseDto;
import com.spring.arab.urlshortener.entities.UrlResponseDto;
import com.spring.arab.urlshortener.service.UrlService;

@RestController
public class UrlShortenerController {
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto){
		Url urlToReturn = urlService.generateShortLink(urlDto);
		if(urlToReturn != null) {
			UrlResponseDto urlResponseDto = new UrlResponseDto();
			urlResponseDto.setOriginalUrl(urlToReturn.getOriginalUrl());
			urlResponseDto.setExpirationDate(urlToReturn.getExpirationDate());
			urlResponseDto.setShortLink(urlToReturn.getShortLink());
			
			return new ResponseEntity<UrlResponseDto>(urlResponseDto, HttpStatus.OK);
		}
		UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
		urlErrorResponseDto.setStatus("404");
		urlErrorResponseDto.setError("there was an error processing your request. please try again");
		return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
	}
	
	@GetMapping("/{shortLink}")
	public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException{
		if(StringUtils.isEmpty(shortLink)) {
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Invalid Url");
			urlErrorResponseDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}
		Url urlToReturn = urlService.getEncodedUrl(shortLink);
		if(urlToReturn == null) {
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Url does not exist or it might have expired!");
			urlErrorResponseDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}
		if(urlToReturn.getExpirationDate().isBefore(LocalDateTime.now())) {
			urlService.deleteShortLink(urlToReturn);
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Url Expired. Please try generating a fresh one.");
			urlErrorResponseDto.setStatus("200");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}
		response.sendRedirect(urlToReturn.getOriginalUrl());
		return null;
	}
	
}
