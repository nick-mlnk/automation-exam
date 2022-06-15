package web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


import static java.lang.Math.abs;

public class Prediktive {


    public static void main(String[] args) {
        int[] arr = {3, 1, -2, -4, -1, 16, 2, -45, -3};
        int[] arr2 = {3, -1, -2, -4, 1, 16, 2, -45, -3};

        System.out.println(compute_closest_number_to_zero(arr));
        System.out.println(compute_closest_number_to_zero(arr2));
    }

    public static int compute_closest_number_to_zero(int[] arr) {
        AtomicInteger result = new AtomicInteger(arr[0]);
        IntStream.of(arr).forEach(el -> {
            if (Math.abs(el) < Math.abs(result.get())) {
                result.set(el);
            }
            if (el == Math.abs(result.get())) {
                result.set(el);
            }
        });
        return result.get();
    }

    public static int compute_closest_temp_to_zero(int[] arr) {
        int closest = abs(Arrays.stream(arr).max().getAsInt());
        for (int number : arr) {
            if (abs(number) < abs(closest)) {
                closest = number;
            }
            if (number == abs(closest)) {
                closest = number;
            }
        }
        return closest;
    }
}
