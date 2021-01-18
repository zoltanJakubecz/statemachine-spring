package com.jakuza.statemachine.service;

import com.jakuza.statemachine.action.Action;
import com.jakuza.statemachine.action.ApplicationEvent;
import com.jakuza.statemachine.action.ApplicationState;
import org.springframework.statemachine.StateMachine;

public interface ActionService {

    Action newAction(Action action);

    StateMachine<ApplicationState, ApplicationEvent> approve(Long actionId);

    StateMachine<ApplicationState, ApplicationEvent> reject(Long actionId);


}
