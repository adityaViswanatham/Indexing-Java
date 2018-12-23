// Name: Aditya Viswanatham.

package arv160730;

import java.util.*;

// MDS Class. (MultiDimensional Search).
public class MDS {

    // Databases to hold the information of items.
    HashMap<Integer, Product> map1;
    HashMap<Integer, HashSet<Product>> map2;

    // MDS Class Constructor that initializes the hashmaps.
    public MDS() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }

    /** Insert method to insert a new item into the two databases.
     *
     * @param id
     * @param price
     * @param dlist
     * @return int.
     */
    public int insert(int id, int price, java.util.List<Integer> dlist) {
        HashSet<Integer> list = new HashSet<>(dlist);
        // Checking for ID in first database.
        if(!map1.containsKey(id)) {
            Product item = new Product(id, price, list);
            // Adding element in the first database.
            map1.put(id, item);
            // Checking to see if an element in the desc exists in the second database.
            for(Integer element: list) {
                // Creating a new hashset if no duplicate is found.
                if(!map2.containsKey(element)) {
                    map2.put(element, new HashSet<Product>());
                    map2.get(element).add(item);
                }
                // Adding the product object into the hashset.
                else {
                    map2.get(element).add(item);
                }
            }
            return 1;
        }
        // If a duplicate exists updating the information of the item.
        else {
            // Updating list and price if the desc list is not null.
            if(!list.isEmpty() && list!= null) {
                Product item = map1.get(id);
                for(Integer element: item.desc) {
                    map2.get(element).remove(item);
                }
                item.price = price;
                item.desc = list;
                for(Integer element: list) {
                  if(!map2.containsKey(element)) {
                      map2.put(element, new HashSet<Product>());
                      map2.get(element).add(item);
                  }
                  else {
                      map2.get(element).add(item);
                  }
                }
            }
            // Updating the price only.
            else {
                Product item = map1.get(id);
                item.price = price;
            }
            return 0;
        }
    }

    /** Find method to check if an ID exists in the database and return the price.
     *
     * @param id
     * @return int
     */
    public int find(int id) {
        // Checking to see if the ID exists in the first database.
        if(map1.containsKey(id)) {
            return map1.get(id).price;
        }
        return 0;
    }

    /** Delete method to delete the item with a specific ID from the two databases and returns the sum of the desc list.
     *
     * @param id
     * @return int
     */
    public int delete(int id) {
        int sum = 0;
        // Checking to see if the item exists in the first database.
        if(map1.containsKey(id)) {
            HashSet<Integer> list = map1.get(id).desc;
            for(Integer element: list) {
                sum += element;
            }
            Product item = map1.get(id);
            map1.remove(id);
            for(Integer element: item.desc) {
                map2.get(element).remove(item);
            }
            return sum;
        }
        return 0;
    }

    /** findMinPrice method returns the minimum price for a particular element in the desc list.
     *
     * @param n
     * @return int
     */
    public int findMinPrice(int n) {
        // Adding product prices to a priority queue to find min price.
        PriorityQueue<Integer> q = new PriorityQueue<>();
        if(map2.containsKey(n)) {
            for(Product product: map2.get(n)) {
                q.offer(product.price);
            }
            if(!q.isEmpty())
                return q.poll();
            else
                return 0;
        }
        return 0;
    }

    /** findMaxPrice method returns the maximum price for a particular element in the desc list.
     *
     * @param n
     * @return int
     */
    public int findMaxPrice(int n) {
        // Adding product prices to a priority queue to find the max price.
        PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 < o2) {
                    return 1;
                }
                else if(o1 == o2) {
                    return 0;
                }
                else {
                    return -1;
                }
            }
        });
        // Checking to see if the number exists in the second database.
        if(map2.containsKey(n)) {
            for(Product product: map2.get(n)) {
                q.offer(product.price);
            }
            if(!q.isEmpty())
                return q.poll();
            else
                return 0;
        }
        return 0;
    }

    /** findPriceRange method returns the count of items in a specific price range.
     *
     * @param n
     * @param low
     * @param high
     * @return int
     */
    public int findPriceRange(int n, int low, int high) {
        int count = 0;
        // Checking to see if the number exists in the second database.
        if(map2.containsKey(n)) {
            for(Product product: map2.get(n)) {
                // Counting all the objects that fall in the range.
                if(product.price >= low && product.price <= high) {
                    count++;
                }
            }
            return count;
        }
        return 0;
    }

    /** removeNames method removes the numbers from the desc list and return their sum.
     *
     * @param id
     * @param list
     * @return int
     */
    public int removeNames(int id, java.util.List<Integer> list) {
       // Creating a new list for reference.
       List<Integer> dlist = new ArrayList<>(list);
       int sum_count = 0;
       // Getting the product object from first database.
       Product product = map1.get(id);
       // Removing every occurance of the object from the second database.
       for(Integer element: product.desc) {
           map2.get(element).remove(product);
       }
       // Removing all the integers from the desc list that match the numbers in the list.
       for(Integer element: dlist) {
           if(product.desc.contains(element)) {
               sum_count += element;
               product.desc.remove(element);
           }
       }
       // Adding new objects back into the second database.
       for(Integer element: product.desc) {
           if(!product.desc.isEmpty()) {
               if(product.desc.contains(element)) {
                   map2.get(element).add(product);
               }
               else {
                   map2.put(element, new HashSet<Product>());
                   map2.get(element).add(product);
               }
           }
       }
        return sum_count;
    }
}
