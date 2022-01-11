package com.example.user_demo.config;


import com.example.user_demo.domain.User;
import com.example.user_demo.controller.UserController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
    HATEOAS 설정
 */


@Component
public class UserResourceAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {


    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).retriveUserById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).removeUser(user.getId())).withRel("remove-user"),
                linkTo(methodOn(UserController.class).createUser(user)).withRel("create-user"),
                linkTo(methodOn(UserController.class).retriveAllUsers()).withRel("all-users"));
    }


    @Override
    public CollectionModel<EntityModel<User>> toCollectionModel(Iterable<? extends User> users) {
        return RepresentationModelAssembler.super.toCollectionModel(users)
                .add(linkTo(methodOn(UserController.class).retriveAllUsers()).withRel("all-users"));
    }


}
