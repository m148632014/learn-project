package org.mfm.learn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ӳ��
 * map��flatMap
 *
 * @author MengFanmao
 * @since 2019��10��30��
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
//       5.2.1 ������ÿһ��Ԫ��Ӧ�ú���
        List<String> dishNames = menu.stream().map(Dish::getName)
            .collect(Collectors.toList());
        System.out.println(dishNames);

//        5.2.1 ������ÿһ��Ԫ��Ӧ�ú���
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(words);
//        �����Ҫ�ҳ�ÿ���˵������ж೤����ô����
        List<Integer> dishNameLengths = menu.stream().map(Dish::getName)
            .map(String::length).collect(Collectors.toList());
        System.out.println(dishNameLengths);

//       0. ��η���һ���б� �г����������ͬ���ַ��أ�
        words.stream().map(word -> word.split("")).distinct()
            .collect(Collectors.toList()); // List<Stream<Sring[]>>

//        ��һ������Arrays.stream()�ķ������Խ���һ�����鲢����һ����
        String[] arrayOfWords = { "Goodbye", "World" };
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        System.out.println(streamOfwords);

//        1. ����ʹ��map��Arrays.stream()
        words.stream().map(word -> word.split("")).map(Arrays::stream)
            .distinct().collect(Collectors.toList()); // List<Stream<Sring>>

//        2. ʹ��flatMap
        List<String> uniqueCharacters = words.stream().map(w -> w.split(""))
            .flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(uniqueCharacters); // List<Sring>

//        ����5.2��ӳ��
//        (1) ����һ�������б���η���һ����ÿ������ƽ�����ɵ��б��أ����磬����[1, 2, 3, 4, 5]��Ӧ�÷���[1, 4, 9, 16, 25]��
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println(squares);

//        (2) �������������б���η������е������أ����磬�����б�[1, 2, 3]���б�[3, 4]��Ӧ�÷���[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]��Ϊ��������������������Ԫ�ص��������������ԡ�
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
            .flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
            .collect(Collectors.toList());
        System.out.println(pairs);
//        (3) �����չǰһ�����ӣ�ֻ�����ܺ��ܱ�3�����������أ�����(2, 4)��(3, 3)�ǿ��Եġ�
        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        List<Integer> numbers4 = Arrays.asList(3, 4);
        List<int[]> pairs2 = numbers3
            .stream().flatMap(i -> numbers4.stream()
                .filter(j -> (i + j) % 3 == 0).map(j -> new int[] { i, j }))
            .collect(Collectors.toList());
        System.out.println(pairs2);
    }
}
