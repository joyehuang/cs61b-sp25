import deque.ArrayDeque61B;

import jh61b.utils.Reflection;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;
import java.util.Deque;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    /** Basic test for addFirst method */
    public void addFirstTestBasic(){
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        // check if initial deque is null
        assertThat(deque.toList()).isEmpty();

        // adding element using addFirst()
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        // check deque element and sequence after addFirst()
        assertThat(deque.toList()).containsExactly(3, 2, 1);

        // check size after adding element
        assertThat(deque.size()).isEqualTo(3);
    }

    @Test
    /** Basic test for addLast method */
    public void addLastTestBasic(){
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        // check if initial deque is null
        assertThat(deque.toList()).isEmpty();

        // adding element using addLast()
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        // check deque element and sequence after addLast()
        assertThat(deque.toList()).containsExactly(1, 2, 3);

        // check size after adding element
        assertThat(deque.size()).isEqualTo(3);
    }
}
