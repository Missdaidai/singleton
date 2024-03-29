package clone;

import java.util.*;

/**
 * 已知一个 HashMap<Integer，User>集合， User 有 name（String）和 age（int）属性。请写一个方法实现对
 HashMap 的排序功能，该方法接收 HashMap<Integer，User>为形参，返回类型为 HashMap<Integer，User>，
 要求对 HashMap 中的 User 的 age 倒序进行排序。排序时 key=value 键值对不得拆散。
 */
public class HashMap_Test {
    /**
     * 注意：要做出这道题必须对集合的体系结构非常的熟悉。HashMap 本身就是不可排序的，但是该道题偏偏让给
     HashMap 排序，那我们就得想在 API 中有没有这样的 Map 结构是有序的，LinkedHashMap，对的，就是他，他是
     Map 结构，也是链表结构，有序的，更可喜的是他是 HashMap 的子类，我们返回 LinkedHashMap<Integer,User>
     */
    public static void main(String[] args) {
        HashMap<Integer,User> user = new HashMap<>();
        user.put(1,new User("zs",12));
        user.put(3,new User("ss",23));
        user.put(2,new User("fs",3));
        user.put(4,new User("zfg",34));
        System.out.println(user);
        HashMap<Integer,User> sortHashMap = sortHashMap(user);
        System.out.println(sortHashMap);
    }

    public static HashMap<Integer,User> sortHashMap(HashMap<Integer,User> map){
//        首先拿到map的键值对集合
        Set<Map.Entry<Integer,User>> entryMap = map.entrySet();

//        将集合转化为list集合,为了使用工具类的排序方法
        List<Map.Entry<Integer,User>> list = new ArrayList<Map.Entry<Integer,User>>(entryMap);
//        使用collections集合工具类对list进行排序操作,排序规则要使用匿名内部类来实现
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
//                按照要求根据User的age来倒叙排序
                return o2.getValue().getAge()-o1.getValue().getAge();
            }
        });

//       创建一个新的有序的HashMap子类的集合
        LinkedHashMap<Integer,User> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer,User> entry:list){
            linkedHashMap.put(entry.getKey(),entry.getValue());
        }
//         返回结果
        return linkedHashMap;
    }
}
