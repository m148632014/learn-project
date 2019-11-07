package org.mfm.learn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 筛选和切片
 * filter/distinct/skip/limit
 *
 * @author MengFanmao
 * @since 2019年10月30日
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
//        5.1.1 用谓词筛选
        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian)
            .collect(Collectors.toList());
        System.out.println(vegetarianMenu);

//        5.1.2 筛选各异的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct()
            .forEach(System.out::println);

//        5.1.3 截短流
        List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300)
            .limit(3).collect(Collectors.toList());
        System.out.println(dishes);

//        5.1.4 跳过元素
        dishes = menu.stream().filter(d -> d.getCalories() > 300).skip(2)
            .collect(Collectors.toList());
        System.out.println(dishes);

//        测验5.1：筛选
//        你将如何利用流来筛选前两个荤菜呢？
        dishes = menu.stream().filter(d -> d.getType() == Dish.Type.MEAT)
            .limit(2).collect(Collectors.toList());
        System.out.println(dishes);

    }
}
