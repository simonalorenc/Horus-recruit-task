package com.simonalorenc;

import com.simonalorenc.taskcheck.BlockImpl;
import com.simonalorenc.taskcheck.CompositeBlockImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class WallTest {

    private Wall wall;

    @Before
    public void setUp() {
        Block block1 = new BlockImpl("gray", "concrete");
        Block block2 = new BlockImpl("red", "brick");

        Block block3 = new BlockImpl("gray", "concrete");
        Block block4 = new BlockImpl("gray", "concrete");
        CompositeBlock compositeBlock1 = new CompositeBlockImpl(List.of(block3, block4));

        Block block5 = new BlockImpl("white", "silicate");
        Block block6 = new BlockImpl("white", "silicate");
        Block block7 = new BlockImpl("white", "silicate");
        CompositeBlock compositeBlock2 = new CompositeBlockImpl(List.of(block5, block6));
        CompositeBlock compositeBlock3 = new CompositeBlockImpl(List.of(block7, compositeBlock2));

        wall = new Wall(List.of(block1, block2, compositeBlock1, compositeBlock3));
    }

    @Test
    public void testFoundBlockByColor() {
        String color = "red";
        Optional<Block> block = wall.findBlockByColor(color);
        assertTrue(block.isPresent());
        assertEquals(color, block.get().getColor());
    }

    @Test
    public void testNotFoundBlockByColor() {
        String color = "green";
        Optional<Block> block = wall.findBlockByColor(color);
        assertFalse(block.isPresent());
    }

    @Test
    public void testFoundBlocksByMaterial() {
        String material = "concrete";
        List<Block> blocks = wall.findBlocksByMaterial(material);
        assertEquals(2, blocks.size());
    }

    @Test
    public void testNotFoundBlocksByMaterial() {
        String material = "gypsum";
        List<Block> blocks = wall.findBlocksByMaterial(material);
        assertTrue(blocks.isEmpty());
    }

    @Test
    public void testCount() {
        int count = wall.count();
        assertEquals(7, count);
    }
}