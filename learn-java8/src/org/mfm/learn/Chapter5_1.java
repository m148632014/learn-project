package org.mfm.learn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ɸѡ����Ƭ
 * filter/distinct/skip/limit
 *
 * @author MengFanmao
 * @since 2019��10��30��
 */
public class Chapter5_1 {

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
//        5.1.1 ��ν��ɸѡ
        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian)
            .collect(Collectors.toList());
        System.out.println(vegetarianMenu);

//        5.1.2 ɸѡ�����Ԫ��
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct()
            .forEach(System.out::println);

//        5.1.3 �ض���
        List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300)
            .limit(3).collect(Collectors.toList());
        System.out.println(dishes);

//        5.1.4 ����Ԫ��
        dishes = menu.stream().filter(d -> d.getCalories() > 300).skip(2)
            .collect(Collectors.toList());
        System.out.println(dishes);

//        ����5.1��ɸѡ
//        �㽫�����������ɸѡǰ��������أ�
        dishes = menu.stream().filter(d -> d.getType() == Dish.Type.MEAT)
            .limit(2).collect(Collectors.toList());
        System.out.println(dishes);

    }
}
