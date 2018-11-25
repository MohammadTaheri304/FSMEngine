package io.zino.fsmengine.dto;

public class StateFunctionOutput<S extends State, D> {
    private S newState;
    private D output;
    private boolean triggerNextStateFunction;

    public StateFunctionOutput(S newState, D output, boolean triggerNextStateFunction) {
        this.newState = newState;
        this.output = output;
        this.triggerNextStateFunction = triggerNextStateFunction;
    }

    public S getNewState() {
        return newState;
    }

    public void setNewState(S newState) {
        this.newState = newState;
    }

    public D getOutput() {
        return output;
    }

    public void setOutput(D output) {
        this.output = output;
    }

    public boolean isTriggerNextStateFunction() {
        return triggerNextStateFunction;
    }

    public void setTriggerNextStateFunction(boolean triggerNextStateFunction) {
        this.triggerNextStateFunction = triggerNextStateFunction;
    }
}
