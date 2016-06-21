package hberumen.me.facebookrecipes.libs;


import hberumen.me.facebookrecipes.libs.base.EventBus;

/**
 * Created by hberumen on 14/06/16.
 */
public class GreenRoborEventBus implements EventBus {
    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRoborEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object unsuscriber) {
        eventBus.unregister(unsuscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
