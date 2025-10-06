package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.Famous;
import com.fonon.landingserver.service.FamousCrudService;
import com.fonon.landingserver.web.dto.FamousDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/famous")
public class FamousController extends BaseCrudController<Famous> {
    private final FamousCrudService service;

    public FamousController(FamousCrudService service) {
        super(service);
        this.service = service;
    }

    @Override
    @GetMapping("/{id}")
    @Operation(
            summary = "Get famous",
            description = "Retrieves a famous entry with the last five other famous items",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Famous entry with last items",
                            content = @Content(schema = @Schema(implementation = FamousDTO.class)))
            }
    )
    public FamousDTO get(@PathVariable Long id) {
        return service.get(id);
    }
}
