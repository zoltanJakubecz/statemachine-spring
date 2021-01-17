package com.jakuza.statemachine.config;

import com.jakuza.statemachine.applicationreview.ApplicationEvents;
import com.jakuza.statemachine.applicationreview.ApplicationStates;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;


@Configuration
@EnableStateMachine
public class StateMachineConfiguration extends StateMachineConfigurerAdapter<ApplicationStates, ApplicationEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<ApplicationStates, ApplicationEvents> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new StateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<ApplicationStates, ApplicationEvents> states) throws Exception {
        states
                .withStates()
                .initial(ApplicationStates.OPEN)
                .state(ApplicationStates.PROGRAMMING)
                .state(ApplicationStates.TESTING)
                .end(ApplicationStates.CLOSE);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ApplicationStates, ApplicationEvents> transitions) throws Exception {
        transitions.withExternal()
                .source(ApplicationStates.OPEN).target(ApplicationStates.PROGRAMMING).event(ApplicationEvents.APPROVE)
                .and().withExternal()
                .source(ApplicationStates.PROGRAMMING).target(ApplicationStates.TESTING).event(ApplicationEvents.APPROVE)
                .and().withExternal()
                .source(ApplicationStates.PROGRAMMING).target(ApplicationStates.PROGRAMMING).event(ApplicationEvents.REJECT)
                .and().withExternal()
                .source(ApplicationStates.TESTING).target(ApplicationStates.CLOSE).event(ApplicationEvents.APPROVE);
    }

}
