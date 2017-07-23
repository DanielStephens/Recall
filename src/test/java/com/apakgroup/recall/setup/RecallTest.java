package com.apakgroup.recall.setup;

import org.junit.Test;

public class RecallTest {

    @Test
    public void test() throws Throwable {
        Obj o = new Obj();

        Recall recall = new Recall();

        recall.enhance(o).print();

        recall.playback();
    }

    @Test
    public void test2() throws Throwable {
        Obj2 o = new Obj2("ABC");

        Recall recall = new Recall();

        recall.enhance(o).print();
        recall.enhance(o).getObj().print();

        recall.playback();
    }

}

