package com.example.demosecurity.service;

import com.example.demosecurity.domain.Article;
import com.example.demosecurity.dto.AddArticleRequest;
import com.example.demosecurity.dto.UpdateArticleRequest;
import com.example.demosecurity.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        log.info("request: {}", request);
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        log.info("findAll");
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        log.info("findById: {}", id);
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        log.info("delete: {}", id);
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        log.info("id: {}, request: {}", id, request);
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
