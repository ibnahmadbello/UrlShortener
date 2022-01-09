package com.spring.arab.urlshortener.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.arab.urlshortener.entities.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{
	public Url findByShortLink(String shortLink);
}
