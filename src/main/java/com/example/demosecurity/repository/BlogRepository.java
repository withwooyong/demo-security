package com.example.demosecurity.repository;

import com.example.demosecurity.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}

