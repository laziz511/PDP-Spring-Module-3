package uz.pdp.online.springbootapplication.hateoas.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import uz.pdp.online.springbootapplication.hateoas.controller.AuthUserController;
import uz.pdp.online.springbootapplication.hateoas.entity.AuthUserEntity;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthUserModelAssembler implements RepresentationModelAssembler<AuthUserEntity, EntityModel<AuthUserEntity>> {

    @Override
    public EntityModel<AuthUserEntity> toModel(AuthUserEntity user) {
        Link selfRelation = linkTo(methodOn(AuthUserController.class).getUserById(user.getId())).withSelfRel();
        Link pagedRelation = linkTo(methodOn(AuthUserController.class).getPage(10, 0)).withRel("paged");

        return EntityModel.of(user, selfRelation, pagedRelation);
    }

    @Override
    public CollectionModel<EntityModel<AuthUserEntity>> toCollectionModel(Iterable<? extends AuthUserEntity> entities) {
        List<EntityModel<AuthUserEntity>> entityModels = new ArrayList<>();
        entities.forEach(user -> entityModels.add(toModel(user)));

        Link usersRelation = linkTo(methodOn(AuthUserController.class).getAllUsers()).withRel("authUsers");
        Link pagedRelation = linkTo(methodOn(AuthUserController.class).getPage(10, 0)).withRel("paged");

        return CollectionModel.of(entityModels, usersRelation, pagedRelation);
    }
}

