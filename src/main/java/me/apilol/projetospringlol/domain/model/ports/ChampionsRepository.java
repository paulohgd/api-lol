package me.apilol.projetospringlol.domain.model.ports;

import me.apilol.projetospringlol.domain.model.Champions;

import java.util.List;
import java.util.Optional;

public interface ChampionsRepository {
    List<Champions> findAll();

    Optional<Champions> findById(Long id);
}
