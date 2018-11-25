import io.zino.fsmengine.dto.State;

public class Order implements State {
    private State orderState;

    public State getOrderState() {
        return orderState;
    }

    public void setOrderState(State orderState) {
        this.orderState = orderState;
    }

    public String getState() {
        return getOrderState().name();
    }

    public enum State{
        init,
        retry,
        unknown,
        finished
    }


}
