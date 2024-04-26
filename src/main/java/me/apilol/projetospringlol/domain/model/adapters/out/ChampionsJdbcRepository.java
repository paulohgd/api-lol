package me.apilol.projetospringlol.domain.model.adapters.out;

import me.apilol.projetospringlol.domain.model.Champions;
import me.apilol.projetospringlol.domain.model.ports.ChampionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Champions> rowMapper;

    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = (rs, rowNum) -> new Champions(
                rs.getLong("id"),
                rs.getNString("name"),
                rs.getNString("role"),
                rs.getNString("lore"),
                rs.getNString("imagem_url")
                );
    }
    
    @Override
    public List<Champions> findAll() {
        return jdbcTemplate.query("select * from champions", rowMapper);
    }

    @Override
    public Optional<Champions> findById(Long id) {
        String sql = "select * from champions where id = ?";
        Champions champion = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return Optional.ofNullable(champion);
    }
}
