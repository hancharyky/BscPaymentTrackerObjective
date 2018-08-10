package bsc.example.objective.main;

import java.io.IOException;

/**
 * Application entry point.
 *
 * @author Yahor
 */
public class Main {
    public static void main(String[] args) {
        PaymentTracker paymentTracker = new PaymentTracker();
        paymentTracker.shutdownExecutor();
    }
}
