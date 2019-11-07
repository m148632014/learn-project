package org.mfm.learn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 查找和匹配
 * anymatch/nonematch/allmatch
 * findAny,findFirst
 * ifpresent,ispresent,get,orelse
 *
 * @author MengFanmao
 * @since 2019年10月30日
 */
public class Chapter5_3 {

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
//        5.3.1 检查谓词是否至少匹配一个元素
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
//        5.3.2 检查谓词是否匹配所有元素
        boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println("isHealthy=" + isHealthy);
        isHealthy = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        System.out.println("isHealthy=" + isHealthy);
//        5.3.3 查找元素
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian)
            .findAny();
        System.out.println("dish=" + dish);
//是否存在一道菜可以访问其名称
        menu.stream().filter(Dish::isVegetarian).findAny()
            .ifPresent(d -> System.out.println(d.getName()));
//        5.3.4 查找第一个元素
//        例如，给定一个数字列表，下面的代码能找出第一个平方能被3整除的数
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
            .map(x -> x * x).filter(x -> x % 3 == 0).findFirst(); // 9
        System.out.println(firstSquareDivisibleByThree);
    }
}
