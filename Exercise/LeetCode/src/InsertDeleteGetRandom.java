import java.util.*;

public class InsertDeleteGetRandom {

    public static void main(String[] args) {


    }


static class RandomizedSet {
     Map<Integer, Integer> map = new HashMap<>();
     List<Integer> list = new ArrayList<>();
     Random rand = new Random();

     public  boolean insert(int val) {
         if (map.containsKey(val)) {
             return false;
         }

         map.put(val, list.size());
         list.add(val);

         return true;
     }

     public  boolean remove(int val) {
         if (!map.containsKey(val)) {
             return false;
         }

         int indexToRemove = map.get(val);
         int lastElement = list.get(list.size() - 1);
         list.set(indexToRemove, lastElement);
         map.put(lastElement, indexToRemove);

         list.remove(list.size() - 1);
         map.remove(val);


         return true;
     }

     public  int getRandom() {
         int randIndex = rand.nextInt(list.size());
         return list.get(randIndex);
     }

 }
}
