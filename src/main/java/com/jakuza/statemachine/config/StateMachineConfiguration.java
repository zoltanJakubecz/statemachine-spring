package com.jakuza.statemachine.config;

import com.jakuza.statemachine.action.ApplicationEvent;
import com.jakuza.statemachine.action.ApplicationState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;


@Configuration
@EnableStateMachineFactory
public class StateMachineConfiguration extends StateMachineConfigurerAdapter<ApplicationState, ApplicationEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<ApplicationState, ApplicationEvent> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new StateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<ApplicationState, ApplicationEvent> states) throws Exception {
        states
                .withStates()
                .initial(ApplicationState.OPEN)
                .state(ApplicationState.PROGRAMMING)
                .state(ApplicationState.TESTING)
                .end(ApplicationState.CLOSE);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ApplicationState, ApplicationEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(ApplicationState.OPEN).target(ApplicationState.PROGRAMMING).event(ApplicationEvent.APPROVE)
                .and().withExternal()
                .source(ApplicationState.PROGRAMMING).target(ApplicationState.TESTING).event(ApplicationEvent.APPROVE)
                .and().withExternal()
                .source(ApplicationState.TESTING).target(ApplicationState.PROGRAMMING).event(ApplicationEvent.REJECT)
                .and().withExternal()
                .source(ApplicationState.TESTING).target(ApplicationState.CLOSE).event(ApplicationEvent.APPROVE);
    }

}
