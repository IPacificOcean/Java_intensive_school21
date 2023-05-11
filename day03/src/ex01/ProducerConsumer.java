package ex01;

//import java.util.LinkedList;
//import java.util.List;
//
//public class ProducerConsumer <T> {
//    private static final int BUFFER_MAX_SIZE = 1;
//    private List<T> buffer = new LinkedList<>();
//
//    synchronized void producer(T val) throws InterruptedException {
//        while (buffer.size() == BUFFER_MAX_SIZE) {
//            wait();
//        }
//        buffer.add(val);
//        notify();
//    }
//
//    synchronized T consume() throws InterruptedException {
//        while (buffer.size() == 0) {
//            wait();
//        }
//        T result = buffer.remove(0);
//        notify();
//        return result;
//    }
//}
