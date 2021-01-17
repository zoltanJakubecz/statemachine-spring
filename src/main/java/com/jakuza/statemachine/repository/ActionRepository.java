package com.jakuza.statemachine.repository;

import com.jakuza.statemachine.action.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {
}
