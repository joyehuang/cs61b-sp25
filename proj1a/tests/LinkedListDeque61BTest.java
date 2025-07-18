import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** This test will test an empty deque */
    public void testIsEmpty() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         assertThat(lld1.isEmpty()).isTrue(); // True
    }

    @Test
    /** This test will first test an empty deque and then add an element */
    public void testIsNotEmpty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue(); // True
        lld1.addFirst(0);
        assertThat(lld1.isEmpty()).isFalse(); // False
    }

    @Test
    /** This test will test the size method on three different values */
    public void testSize() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.size()).isEqualTo(0); // 0
        lld1.addFirst(0);
        assertThat(lld1.size()).isEqualTo(1); // 1
        lld1.addLast(1);
        assertThat(lld1.size()).isEqualTo(2); // 2
    }

    @Test
    /** This test will test large assertations of deque */
    public void testLargeSize() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        for (int i = 0; i < 1000; i++){
            lld1.addLast(i);
        }
        assertThat(lld1.size()).isEqualTo(1000);
    }

    @Test
    /** This test will test size with remove methods */
    public void testSizeWithRemove() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.size()).isEqualTo(0); // 0
        lld1.addFirst(0);
        assertThat(lld1.size()).isEqualTo(1); // 1
        lld1.addLast(1);
        assertThat(lld1.size()).isEqualTo(2); // 2
        lld1.removeFirst();
        assertThat(lld1.size()).isEqualTo(1); // 1
        lld1.removeLast();
        assertThat(lld1.size()).isEqualTo(0); // 0
    }

    @Test
    /** This test will test size and isEmpty methods with first add then remove */
    public void testAddThenRemove() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addFirst(0);
        lld.removeFirst();
        assertThat(lld.size()).isEqualTo(0);
        assertThat(lld.isEmpty()).isTrue();
    }

    @Test
    /** This test will test basic get method */
    public void testGet() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.get(0)).isEqualTo(null);
        lld1.addFirst(0);
        assertThat(lld1.get(0)).isEqualTo(0);
        lld1.addLast(1);
        assertThat(lld1.get(0)).isEqualTo(0);
        assertThat(lld1.get(1)).isEqualTo(1);
        lld1.addLast(2);
        lld1.addFirst(-1);
        assertThat(lld1.get(-1)).isEqualTo(null);
        assertThat(lld1.get(0)).isEqualTo(-1);
        assertThat(lld1.get(1)).isEqualTo(0);
        assertThat(lld1.get(2)).isEqualTo(1);
        assertThat(lld1.get(3)).isEqualTo(2);
    }

    @Test
    /** This test will test get method of large assertations of deque */
    public void testGetLarge() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        for (int i = 0; i < 1000; i++){
            lld1.addLast(i);
        }
        assertThat(lld1.get(-1)).isEqualTo(null);
        assertThat(lld1.get(0)).isEqualTo(0);
        assertThat(lld1.get(200)).isEqualTo(200);
        assertThat(lld1.get(567)).isEqualTo(567);
        assertThat(lld1.get(798)).isEqualTo(798);
        assertThat(lld1.get(999)).isEqualTo(999);
        assertThat(lld1.get(1000)).isEqualTo(null);
    }

    @Test
    /** This test will test basic getRecusive method */
    public void testGetRecursive() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.getRecursive(0)).isEqualTo(null);
        lld1.addFirst(0);
        assertThat(lld1.getRecursive(0)).isEqualTo(0);
        lld1.addLast(1);
        assertThat(lld1.getRecursive(0)).isEqualTo(0);
        assertThat(lld1.getRecursive(1)).isEqualTo(1);
        lld1.addLast(2);
        lld1.addFirst(-1);
        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
        assertThat(lld1.getRecursive(0)).isEqualTo(-1);
        assertThat(lld1.getRecursive(1)).isEqualTo(0);
        assertThat(lld1.getRecursive(2)).isEqualTo(1);
        assertThat(lld1.getRecursive(3)).isEqualTo(2);
    }

    @Test
    /** This test will test getRecursive method of large assertations of deque */
    public void testGetRecursiveLarge() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        for (int i = 0; i < 1000; i++){
            lld1.addLast(i);
        }
        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
        assertThat(lld1.getRecursive(0)).isEqualTo(0);
        assertThat(lld1.getRecursive(200)).isEqualTo(200);
        assertThat(lld1.getRecursive(567)).isEqualTo(567);
        assertThat(lld1.getRecursive(798)).isEqualTo(798);
        assertThat(lld1.getRecursive(999)).isEqualTo(999);
        assertThat(lld1.getRecursive(1000)).isEqualTo(null);
    }

    @Test
    /** This test is for removeFirst method basic conditions */
    public void testRemoveFirst() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.removeFirst()).isEqualTo(null);

        lld1.addFirst(0);
        lld1.addLast(1);
        lld1.addFirst(-1);

        assertThat(lld1.removeFirst()).isEqualTo(-1);
        assertThat(lld1.removeFirst()).isEqualTo(0);
        assertThat(lld1.removeFirst()).isEqualTo(1);
    }

    @Test
    /** This test is for removeLast method basic conditions */
    public void testRemoveLast() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.removeLast()).isEqualTo(null);

        lld1.addFirst(0);
        lld1.addFirst(-1);
        lld1.addLast(1);

        assertThat(lld1.removeLast()).isEqualTo(1);
        assertThat(lld1.removeLast()).isEqualTo(0);
        assertThat(lld1.removeLast()).isEqualTo(-1);
    }

    @Test
    /** This test if for random calls on removeLast and removeFirst methods */
    public void testRemove() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.removeFirst()).isEqualTo(null);
        assertThat(lld1.removeLast()).isEqualTo(null);

        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.removeLast()).isEqualTo(2);
        assertThat(lld1.removeFirst()).isEqualTo(-2);
        assertThat(lld1.removeFirst()).isEqualTo(-1);
        assertThat(lld1.removeLast()).isEqualTo(1);
        assertThat(lld1.removeLast()).isEqualTo(0);
        assertThat(lld1.removeFirst()).isEqualTo(null);
    }
}