package com.jakuza.statemachine.config;

import com.jakuza.statemachine.action.ApplicationEvent;
import com.jakuza.statemachine.action.ApplicationState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StateMachineConfigurationTest {

    @Autowired
    StateMachineFactory<ApplicationState, ApplicationEvent> factory;

    @Test
    void testNewStateMachine() {

        StateMachine<ApplicationState, ApplicationEvent> sm = factory.getStateMachine(UUID.randomUUID());

        sm.start();

        System.out.println(sm.getState().toString());

        sm.sendEvent(ApplicationEvent.APPROVE);

        System.out.println(sm.getState().toString());

        sm.sendEvent(ApplicationEvent.APPROVE);

        System.out.println(sm.getState().toString());

        sm.sendEvent(ApplicationEvent.REJECT);

        System.out.println(sm.getState().toString());


    }
}