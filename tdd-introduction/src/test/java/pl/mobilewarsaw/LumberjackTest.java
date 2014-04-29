package pl.mobilewarsaw;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class LumberjackTest {

    Lumberjack lumberjack;

    @Before
    public void setUp() throws Exception {
        lumberjack = new Lumberjack();
    }

    @Test
    public void shouldBeOk() throws Exception {
        // then
        assertThat(lumberjack.isOk()).isTrue();
    }

    @Test
    public void shouldGoToLavatory() throws Exception {
        // then
        assertThat(lumberjack.isInLavatory()).isTrue();
    }

    @Test
    public void shouldWearHighHeelsSuspendersAndABra() throws Exception {
        // then
        assertThat(lumberjack.isWearingHighHeelsSuspendersAndABra()).isTrue();
    }
}
