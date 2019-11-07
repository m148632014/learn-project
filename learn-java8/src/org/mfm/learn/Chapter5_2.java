package org.mfm.learn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 映射
 * map、flatMap
 *
 * @author MengFanmao
 * @since 2019年10月30日
 */
public class Chapter5_2 {

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
//       5.2.1 对流中每一个元素应用函数
        List<String> dishNames = menu.stream().map(Dish::getName)
            .collect(Collectors.toList());
        System.out.println(dishNames);

//        5.2.1 对流中每一个元素应用函数
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(words);
//        如果你要找出每道菜的名称有多长，怎么做？
        List<Integer> dishNameLengths = menu.stream().map(Dish::getName)
            .map(String::length).collect(Collectors.toList());
        System.out.println(dishNameLengths);

//       0. 如何返回一张列表， 列出里面各不相同的字符呢？
        words.stream().map(word -> word.split("")).distinct()
            .collect(Collectors.toList()); // List<Stream<Sring[]>>

//        有一个叫作Arrays.stream()的方法可以接受一个数组并产生一个流
        String[] arrayOfWords = { "Goodbye", "World" };
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        System.out.println(streamOfwords);

//        1. 尝试使用map和Arrays.stream()
        words.stream().map(word -> word.split("")).map(Arrays::stream)
            .distinct().collect(Collectors.toList()); // List<Stream<Sring>>

//        2. 使用flatMap
        List<String> uniqueCharacters = words.stream().map(w -> w.split(""))
            .flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(uniqueCharacters); // List<Sring>

//        测验5.2：映射
//        (1) 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4, 5]，应该返回[1, 4, 9, 16, 25]。
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println(squares);

//        (2) 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代表数对。
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
            .flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
            .collect(Collectors.toList());
        System.out.println(pairs);
//        (3) 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        List<Integer> numbers4 = Arrays.asList(3, 4);
        List<int[]> pairs2 = numbers3
            .stream().flatMap(i -> numbers4.stream()
                .filter(j -> (i + j) % 3 == 0).map(j -> new int[] { i, j }))
            .collect(Collectors.toList());
        System.out.println(pairs2);
    }
}
