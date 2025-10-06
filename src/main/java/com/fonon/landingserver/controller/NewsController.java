package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.News;
import com.fonon.landingserver.service.NewsCrudService;
import com.fonon.landingserver.web.dto.NewsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsController extends BaseCrudController<News> {
    private final NewsCrudService service;

    public NewsController(NewsCrudService service) {
        super(service);
        this.service = service;
    }

    @Override
    @GetMapping("/{id}")
    @Operation(
            summary = "Get news",
            description = "Retrieves a news entry along with the last five other news items",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "News entry with last items",
                            content = @Content(schema = @Schema(implementation = NewsDTO.class)))
            }
    )
    public NewsDTO get(@PathVariable Long id) {
        return service.get(id);
    }
}
