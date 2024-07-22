import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import task.Singleton;
import task.SingletonBillPugh;
import task.Solution;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class SolutionTest {

    @InjectMocks
    private Solution solution;

    @Test
    void shouldMakeTwoPartitionOfPalindromes() {
        //given
        String word = "aab";

        //when
        List<List<String>> partition = solution.partition(word);

        //then
        assertThat(partition).hasSize(2)
                .contains(List.of("a", "a", "b"), List.of("aa", "b"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"exceedingStringLengthWord"})
    void shouldThrowExceptionWhenStringLengthIsOutOfScope(String value) {
        //when then
        assertThatThrownBy(() -> solution.partition(value))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Given string is out of scope");
    }

    @Test
    void shouldUseSingletonEnum() {
        //given
        Singleton singleton = Singleton.INSTANCE;
        singleton.setValue(10);

        for (int i = 0; i < 100; i++) {
            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                Singleton.INSTANCE.setValue(20);
                System.out.println(Thread.currentThread().getName() + ": Value set to 20");
            });
            CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> {
                Singleton.INSTANCE.setValue(30);
                System.out.println(Thread.currentThread().getName() + ": Value set to 30");
            });
            CompletableFuture<Void> voidCompletableFuture3 = CompletableFuture.runAsync(() -> {
                Singleton.INSTANCE.setValue(40);
                System.out.println(Thread.currentThread().getName() + ": Value set to 40");
            });

            CompletableFuture<Void> voidCompletableFuture4 = CompletableFuture.runAsync(() -> {
                Singleton.INSTANCE.setValue(20);
                System.out.println(Thread.currentThread().getName() + ": Value set to 20");
            });
            CompletableFuture<Void> voidCompletableFuture5 = CompletableFuture.runAsync(() -> {
                Singleton.INSTANCE.setValue(30);
                System.out.println(Thread.currentThread().getName() + ": Value set to 30");
            });
            CompletableFuture<Void> voidCompletableFuture6 = CompletableFuture.runAsync(() -> {
                Singleton.INSTANCE.setValue(40);
                System.out.println(Thread.currentThread().getName() + ": Value set to 40");
            });


        }
        Awaitility.await().atLeast(6, TimeUnit.SECONDS).pollDelay(6, TimeUnit.SECONDS).until(() -> singleton.getValue() >= 10);
        System.out.println("Final value: " + singleton.getValue());
        //then
        assertThat(singleton.getValue()).isGreaterThan(10);
    }

    @Test
    void shouldUseSingletonBillPugh() {
        //given
        SingletonBillPugh singleton = SingletonBillPugh.getSingleton();
        singleton.setValue(10);

        for (int i = 0; i < 100; i++) {
            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                SingletonBillPugh.getSingleton().setValue(20);
                System.out.println(Thread.currentThread().getName() + ": Value set to 20");
            });
            CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> {
                SingletonBillPugh.getSingleton().setValue(30);
                System.out.println(Thread.currentThread().getName() + ": Value set to 30");
            });
            CompletableFuture<Void> voidCompletableFuture3 = CompletableFuture.runAsync(() -> {
                SingletonBillPugh.getSingleton().setValue(40);
                System.out.println(Thread.currentThread().getName() + ": Value set to 40");
            });

            CompletableFuture<Void> voidCompletableFuture4 = CompletableFuture.runAsync(() -> {
                SingletonBillPugh.getSingleton().setValue(20);
                System.out.println(Thread.currentThread().getName() + ": Value set to 20");
            });
            CompletableFuture<Void> voidCompletableFuture5 = CompletableFuture.runAsync(() -> {
                SingletonBillPugh.getSingleton().setValue(30);
                System.out.println(Thread.currentThread().getName() + ": Value set to 30");
            });
            CompletableFuture<Void> voidCompletableFuture6 = CompletableFuture.runAsync(() -> {
                SingletonBillPugh.getSingleton().setValue(40);
                System.out.println(Thread.currentThread().getName() + ": Value set to 40");
            });


        }
        Awaitility.await().atLeast(6, TimeUnit.SECONDS).pollDelay(6, TimeUnit.SECONDS).until(() -> SingletonBillPugh.getSingleton().getValue() >= 10);
        System.out.println("Final value: " + SingletonBillPugh.getSingleton().getValue());
        //then
        assertThat(singleton.getValue()).isGreaterThan(10);
    }
}
