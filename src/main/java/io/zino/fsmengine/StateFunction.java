package io.zino.fsmengine;

import io.zino.fsmengine.dto.State;
import io.zino.fsmengine.dto.StateFunctionOutput;

@FunctionalInterface
public interface StateFunction<S extends State, D> {
    StateFunctionOutput<S, D> method(S state, D data);
}
