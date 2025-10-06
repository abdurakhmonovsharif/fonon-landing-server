package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.News;
import com.fonon.landingserver.repository.NewsRepository;
import com.fonon.landingserver.web.dto.LastItemsDTO;
import com.fonon.landingserver.web.dto.NewsDTO;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NewsCrudService extends CrudService<News> {

    private final NewsRepository repository;
    private final FileStorageService storageService;

    public NewsCrudService(NewsRepository repository, FileStorageService storageService) {
        super(repository, "News");
        this.repository = repository;
        this.storageService = storageService;
    }

    public NewsDTO get(Long id) {
        News news = this.findOne(id);
        List<LastItemsDTO> lastItems = this.findLast5News(id);
        return new NewsDTO(
                news.getTitleUz(),
                news.getTitleRu(),
                news.getTitleEn(),
                news.getBodyUz(),
                news.getBodyRu(),
                news.getBodyEn(),
                news.getImages() == null ? List.of() : List.copyOf(news.getImages()),
                news.getPublishedAt() != null ? news.getPublishedAt() : news.getCreatedAt(),
                lastItems);
    }

    public List<LastItemsDTO> findLast5News(Long id) {
        Sort sort = Sort.by(Sort.Order.desc("publishedAt"), Sort.Order.desc("createdAt"));
        return repository.findAll(PageRequest.of(0, 6, sort)).getContent().stream()
                .filter(n -> !n.getId().equals(id))
                .limit(5)
                .map(n -> {
                    List<String> images = n.getImages();
                    String firstImage = (images == null || images.isEmpty()) ? null : images.get(0);
                    OffsetDateTime publishedAt = n.getPublishedAt() != null ? n.getPublishedAt() : n.getCreatedAt();
                    return new LastItemsDTO(
                            n.getId(),
                            n.getTitleUz(),
                            n.getTitleRu(),
                            n.getTitleEn(),
                            firstImage,
                            publishedAt);
                })
                .toList();
    }

    @Override
    public void remove(Long id) {
        News news = findOne(id);
        List<String> images = news.getImages();
        if (images != null && !images.isEmpty()) {
            storageService.deleteAll(images);
        }
        super.remove(id);
    }
}
