package com.darkwiki.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;


@NoRepositoryBean
public interface AbstractRepository<T, ID extends Serializable> extends Repository<T, ID> {

    void delete(T entity);

    List<T> findAll();


    Optional<T> findById(ID id);


    <S extends T> S save(S entity);
}