//package com.github.k9nz00.server.repository;
//
//import java.util.List;
//
//import com.github.k9nz00.server.dao.entity.UserEntity;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//@RepositoryRestResource(collectionResourceRel = "people", path = "people")
//public interface PersonRepository extends PagingAndSortingRepository<UserEntity, Integer> {
//
//  List<UserEntity> findByLastName(@Param("name") String name);
//
//}