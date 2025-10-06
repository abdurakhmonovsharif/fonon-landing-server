package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Famous;
import com.fonon.landingserver.repository.FamousRepository;
import com.fonon.landingserver.web.dto.FamousDTO;
import com.fonon.landingserver.web.dto.LastItemsDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FamousCrudService extends CrudService<Famous> {
    private final FamousRepository repository;
    private final FileStorageService storageService;

    public FamousCrudService(FamousRepository repository, FileStorageService storageService) {
        super(repository, "Famous");
        this.repository = repository;
        this.storageService = storageService;
    }
    public FamousDTO get(Long id) {
        Famous famous = this.findOne(id);
        List<LastItemsDTO> lastItems = this.findLast5Famous(id);
        if (famous != null) {
            return new FamousDTO(
                    famous.getTitleUz(),
                    famous.getTitleRu(),
                    famous.getTitleEn(),
                    famous.getBodyUz(),
                    famous.getBodyRu(),
                    famous.getBodyEn(),
                    famous.getImages() == null ? List.of() : List.copyOf(famous.getImages()),
                    famous.getCreatedAt(),
                    lastItems);
        } else {
            throw new RuntimeException("Famous not found with id: " + id);
        }
    }

    public List<LastItemsDTO> findLast5Famous(Long id) {
        return repository.findTop6ByOrderByCreatedAtDesc().stream()
                .filter(f -> !f.getId().equals(id))
                .limit(5)
                .map(f -> {
                    List<String> images = f.getImages();
                    String firstImage = (images == null || images.isEmpty()) ? null : images.get(0);
                    return new LastItemsDTO(
                            f.getId(),
                            f.getTitleUz(),
                            f.getTitleRu(),
                            f.getTitleEn(),
                            firstImage,
                            f.getCreatedAt());
                })
                .toList();
    }

    @Override
    public void remove(Long id) {
        Famous famous = findOne(id);
        List<String> images = famous.getImages();
        if (images != null && !images.isEmpty()) {
            storageService.deleteAll(images);
        }
        super.remove(id);
    }
}
