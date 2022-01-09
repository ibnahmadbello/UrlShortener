package com.spring.arab.urlshortener.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Url {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Lob
	private String originalUrl;
	private String shortLink;
	private LocalDateTime creationTime;
	private LocalDateTime expirationTime;
	
	public Url(long id, String originalUrl, String shortLink, LocalDateTime creationTime,
			LocalDateTime expirationTime) {
		super();
		this.id = id;
		this.originalUrl = originalUrl;
		this.shortLink = shortLink;
		this.creationTime = creationTime;
		this.expirationTime = expirationTime;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortLink() {
		return shortLink;
	}
	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}
	@Override
	public String toString() {
		return "Url [id=" + id + ", originalUrl=" + originalUrl + ", shortLink=" + shortLink + ", creationTime="
				+ creationTime + ", expirationTime=" + expirationTime + "]";
	}
		
}
