package uz.pdp.online.springbootapplication.hateoas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.springbootapplication.hateoas.assembler.AuthUserModelAssembler;
import uz.pdp.online.springbootapplication.hateoas.entity.AuthUserEntity;
import uz.pdp.online.springbootapplication.hateoas.service.AuthService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth-users")
public class AuthUserController {

    private final AuthService authService;
    private final AuthUserModelAssembler userAssembler;

    @Qualifier("postPagedResourcesAssembler")
    private final PagedResourcesAssembler<AuthUserEntity> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<AuthUserEntity>> getAllUsers() {
        List<AuthUserEntity> users = authService.getAllUsers();
        return userAssembler.toCollectionModel(users);
    }

    @GetMapping("/{id}")
    public EntityModel<AuthUserEntity> getUserById(@PathVariable Long id) {
        AuthUserEntity user = authService.getUserById(id);
        return userAssembler.toModel(user);
    }

    @PostMapping
    public ResponseEntity<EntityModel<AuthUserEntity>> createUser(@RequestBody AuthUserEntity user) {
        AuthUserEntity createdUser = authService.createUser(user);
        EntityModel<AuthUserEntity> userEntityModel = userAssembler.toModel(createdUser);
        return ResponseEntity.ok(userEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<AuthUserEntity>> updateUser(@PathVariable Long id, @RequestBody AuthUserEntity updatedUser) {
        return authService.updateUser(id, updatedUser)
                .map(savedUser -> ResponseEntity.ok(userAssembler.toModel(savedUser)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        authService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public PagedModel<EntityModel<AuthUserEntity>> getPage(
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "0") int page) {

        Page<AuthUserEntity> usersPage = authService.getAllUsers(page, size);

        return pagedResourcesAssembler.toModel(usersPage, userAssembler);
    }
}

