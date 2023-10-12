package com.hs.selab.image.infrastructure;

import com.hs.selab.image.domain.ImageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageLogRepository extends JpaRepository<ImageLog, Long> {
}
