package org.mfm.learn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * ��Լ
 * reduce
 *
 * @author MengFanmao
 * @since 2019��10��30��
 */
public class Chapter5_4 {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));
//        5.4.1 Ԫ�����
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);
//        �޳�ʼֵ ��������û���κ�Ԫ�ص������reduce�����޷�������ͣ���Ϊ��û�г�ʼֵ
//        reduce����һ�����صı��壬�������ܳ�ʼֵ�����ǻ᷵��һ��Optional����
        Optional<Integer> sumOp = numbers.stream().reduce((a, b) -> (a + b));
        System.out.println(sumOp);
//        5.4.2 ���ֵ����Сֵ
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println(max);
        System.out.println(min);
//        ����5.3����Լ
//        ������map��reduce������һ�������ж��ٸ����أ�
        int count = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
        System.out.println(count);
        long count2 = menu.stream().count();
        System.out.println(count2);
    }
}
