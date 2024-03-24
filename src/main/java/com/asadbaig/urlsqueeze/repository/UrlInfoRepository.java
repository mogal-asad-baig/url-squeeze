package com.asadbaig.urlsqueeze.repository;

import com.asadbaig.urlsqueeze.entity.UrlInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlInfoRepository extends JpaRepository<UrlInfoEntity, Long> {

    Optional<UrlInfoEntity> findByUrlId(String urlId);

    int deleteByUrlId(String urlId);

}
