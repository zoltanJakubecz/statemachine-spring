package com.jakuza.statemachine.service;

import com.jakuza.statemachine.action.Action;
import com.jakuza.statemachine.action.ApplicationEvent;
import com.jakuza.statemachine.action.ApplicationState;
import com.jakuza.statemachine.repository.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository repository;
    private final StateMachineFactory<ApplicationState, ApplicationEvent> stateMachineFactory;

    @Override
    public Action newAction(Action action) {
        action.setState(ApplicationState.OPEN);
        return repository.save(action);
    }

    @Override
    public StateMachine<ApplicationState, ApplicationEvent> approve(Long actionId) {
        return null;
    }

    @Override
    public StateMachine<ApplicationState, ApplicationEvent> reject(Long actionId) {
        return null;
    }

}
