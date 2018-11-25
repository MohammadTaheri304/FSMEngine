import io.zino.fsmengine.FSMEngine;
import io.zino.fsmengine.StateFunction;
import io.zino.fsmengine.dto.StateFunctionOutput;

public class OrderFSM extends FSMEngine<Order, Object> {

    public static void main(String[] args) {
        Order order = new Order();
        order.setOrderState(Order.State.init);

        OrderFSM orderFSM = new OrderFSM();
        Object res = orderFSM.run(order, "Start");
        System.out.println("res === "+res);

        Object res2 = orderFSM.run(order, "RunItAgain");
        System.out.println("res2 === "+res2);
    }

    @Override
    protected StateFunction<Order, Object> getStateFunctionFunction(String state) {
         switch (Order.State.valueOf(state)){
             case init:
                 return init();
             case retry:
                 return retry();
             case unknown:
                 return unknown();
             case finished:
                 return finish();
         }
        return null;
    }

    private StateFunction<Order, Object> finish() {
        return (orderState, data) -> {
            System.out.println(orderState.getOrderState());
            return new StateFunctionOutput<Order, Object>(orderState, "Finished", false);
        };
    }

    private StateFunction<Order, Object> unknown() {
        return (orderState, data) -> {
            System.out.println(orderState.getOrderState());
            orderState.setOrderState(Order.State.finished);
            return new StateFunctionOutput<Order, Object>(orderState, null, true);
        };
    }

    private StateFunction<Order, Object> retry() {
        return (orderState, data) -> {
            System.out.println(orderState.getOrderState());
            if ( ((Integer)data).intValue()>=3 ){
                orderState.setOrderState(Order.State.unknown);
                return new StateFunctionOutput<Order, Object>(orderState, "it`s go to unknown", false);
            }
            return new StateFunctionOutput<Order, Object>(
                    orderState, new Integer((Integer)data).intValue()+1, true);
        };
    }

    private StateFunction<Order, Object> init() {
        return (orderState, data) -> {
            System.out.println(orderState.getOrderState());
            orderState.setOrderState(Order.State.retry);
            return new StateFunctionOutput<Order, Object>(orderState, new Integer(0), true);
        };
    }
}
