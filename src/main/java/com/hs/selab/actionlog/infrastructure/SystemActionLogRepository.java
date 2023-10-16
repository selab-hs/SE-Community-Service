package com.hs.selab.actionlog.infrastructure;

import com.hs.selab.actionlog.domain.SystemActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemActionLogRepository extends JpaRepository<SystemActionLog, Long> {
}
