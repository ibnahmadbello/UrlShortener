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
	private Long id;
	@Lob
	private String originalUrl;
	private String shortLink;
	private LocalDateTime creationDate;
	private LocalDateTime expirationDate;
	public Url(Long id, String originalurl, String shorturl, LocalDateTime creationDate, LocalDateTime expirationDate) {
		this.id = id;
		this.originalUrl = originalurl;
		this.shortLink = shorturl;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
	}
	public Url() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalurl) {
		this.originalUrl = originalurl;
	}
	public String getShortLink() {
		return shortLink;
	}
	public void setShortLink(String shorturl) {
		this.shortLink = shorturl;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
	@Override
	public String toString() {
		return "Url [id=" + id + ", originalurl=" + originalUrl + ", shorturl=" + shortLink + ", creationDate="
				+ creationDate + ", expirationDate=" + expirationDate + "]";
	}
	
	
}
