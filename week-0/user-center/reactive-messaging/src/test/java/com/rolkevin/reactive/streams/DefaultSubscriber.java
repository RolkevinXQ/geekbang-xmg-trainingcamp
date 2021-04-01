package com.rolkevin.reactive.streams;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultSubscriber<T> implements Subscriber<T> {

    private Subscription subscription;

    private AtomicInteger count = new AtomicInteger(0);

    private final int MAX = 3;

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
    }

    @Override
    public void onNext(Object o) {
        //MAX值从0开始，按照原来顺序，怎样都会漏一个，尴尬。。。理论上，刚好达到阈值时，应该要给放行吧？
        System.out.println("收到数据：" + o);
        //count.getAndAdd(1);
        if (count.addAndGet(1)> MAX) { // 当到达数据阈值时，取消 Publisher 给当前 Subscriber 发送数据
            subscription.cancel();
            return;
        }
        //System.out.println("收到数据：" + o);

    }

    @Override
    public void onError(Throwable t) {
        System.out.println("遇到异常：" + t);
    }

    @Override
    public void onComplete() {
        System.out.println("收到数据完成");
    }
}
