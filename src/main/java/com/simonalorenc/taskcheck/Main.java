package com.simonalorenc.taskcheck;

import com.simonalorenc.Block;
import com.simonalorenc.CompositeBlock;
import com.simonalorenc.Structure;
import com.simonalorenc.Wall;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Block block1 = new BlockImpl("gray", "concrete");
        Block block2 = new BlockImpl("red", "brick");
        Block block3 = new BlockImpl("white", "silicate");
        Block block4 = new BlockImpl("green", "gypsum");

        Block block5 = new BlockImpl("gray", "concrete");
        Block block6 = new BlockImpl("gray", "concrete");
        CompositeBlock compositeBlock1 = new CompositeBlockImpl(List.of(block5, block6));

        Block block7 = new BlockImpl("green", "gypsum");
        Block block8 = new BlockImpl("green", "gypsum");
        Block block9 = new BlockImpl("green", "gypsum");
        CompositeBlock compositeBlock2 = new CompositeBlockImpl(List.of(block8, block9));
        CompositeBlock compositeBlock3 = new CompositeBlockImpl(List.of(block7, compositeBlock2));

        Block block10 = new BlockImpl("red", "brick");
        Block block11 = new BlockImpl("red", "brick");
        Block block12 = new BlockImpl("red", "brick");
        Block block13 = new BlockImpl("red", "brick");
        CompositeBlock compositeBlock4 = new CompositeBlockImpl(List.of(block10, block11));
        CompositeBlock compositeBlock5 = new CompositeBlockImpl(List.of(block12, compositeBlock4));
        CompositeBlock compositeBlock6 = new CompositeBlockImpl(List.of(block13, compositeBlock5));

        Structure wall = new Wall(
                List.of(
                        block1,
                        block2,
                        block3,
                        block4,
                        compositeBlock1,
                        compositeBlock3,
                        compositeBlock6
                )
        );

        System.out.println("Wall count: " + wall.count());

        Block greenBlock = wall.findBlockByColor("green").get();
        System.out.println("Any block with green color: " + greenBlock + " block color: " + greenBlock.getColor());

        List<Block> concreteBlocks = wall.findBlocksByMaterial("concrete");
        System.out.println("Concrete blocks: " + concreteBlocks.stream().map(block -> block.getMaterial()).toList());
    }
}
