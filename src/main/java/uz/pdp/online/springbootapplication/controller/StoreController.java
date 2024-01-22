package uz.pdp.online.springbootapplication.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.springbootapplication.entity.Store;
import uz.pdp.online.springbootapplication.exception.ResourceNotFoundException;
import uz.pdp.online.springbootapplication.service.StoreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@PreAuthorize("hasRole('ADMIN')")
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "Get all stores", description = "Get a list of all stores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of stores retrieved successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Store.class))}),
            @ApiResponse(responseCode = "204", description = "No stores found")
    })
    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        return stores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(stores);
    }

    @Operation(summary = "Get a store by ID", description = "Get a store by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Store found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Store.class))}),
            @ApiResponse(responseCode = "404", description = "Store not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Store store = storeService.getStoreById(id);
        return ResponseEntity.ok(store);
    }

    @Operation(summary = "Create a new store", description = "Create a new store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Store created successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Store.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class))})
    })
    @PostMapping
    public ResponseEntity<Store> create(@RequestBody Store entity) {
        Store createdStore = storeService.createStore(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStore);
    }

    @Operation(summary = "Update a store by ID", description = "Update a store by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Store updated successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Store.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResourceNotFoundException.class))}),
            @ApiResponse(responseCode = "404", description = "Store not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<Store> update(@PathVariable Long id, @RequestBody Store store) {
        Store updatedStore = storeService.updateStore(id, store);
        return ResponseEntity.ok(updatedStore);
    }

    @Operation(summary = "Delete a store by ID", description = "Delete a store by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Store deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Store not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
