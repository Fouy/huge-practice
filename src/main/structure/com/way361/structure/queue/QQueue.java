package com.way361.structure.queue;

//4.2.1   队列抽象数据类型

public interface QQueue<T>                       //队列接口，描述队列抽象数据类型
{
    boolean isEmpty();                           //判断队列是否空
    void enqueue(T x);                           //元素x入队
    T dequeue();                                 //出队，返回队头元素
}
//？？    T get();                           //取队头元素
