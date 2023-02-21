package com.capitole.service.backend.inventory.manager.adapter.repository;

import com.capitole.service.backend.inventory.manager.entities.Prices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PriceRepository implements CrudRepository<Prices, Prices> {
    @Override
    public <S extends Prices> S save(S s) {
        return null;
    }

    @Override
    public <S extends Prices> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Prices> findById(Prices prices) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Prices prices) {
        return false;
    }

    @Override
    public Iterable<Prices> findAll() {
        return null;
    }

    @Override
    public Iterable<Prices> findAllById(Iterable<Prices> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Prices prices) {

    }

    @Override
    public void delete(Prices prices) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Prices> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Prices> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}