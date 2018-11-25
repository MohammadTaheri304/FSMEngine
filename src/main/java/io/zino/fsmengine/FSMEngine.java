package io.zino.fsmengine;

import io.zino.fsmengine.dto.State;
import io.zino.fsmengine.dto.StateFunctionOutput;

abstract public class FSMEngine<S extends State, D> {

    public D run(S state, D inputData) {
        S innerState = state;
        D innerDate = inputData;
        while (true) {
            StateFunctionOutput<S, D> stateFunctionOutput = routerToStateFunction(innerState, innerDate);
            if (!stateFunctionOutput.isTriggerNextStateFunction()) {
                return (D) stateFunctionOutput.getOutput();
            } else {
                innerState = (S) stateFunctionOutput.getNewState();
                innerDate = (D) stateFunctionOutput.getOutput();
            }
        }
    }

    private StateFunctionOutput<S, D> routerToStateFunction(S state, D inputData){
        StateFunction stateFunctionFunction = getStateFunctionFunction(state.getState());
        return stateFunctionFunction.method(state, inputData);
    }

    abstract protected StateFunction getStateFunctionFunction(String state);
}
