package com.simonalorenc;

import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block.getColor().equals(color)) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.getMaterial().equals(material))
                .toList();
    }

    @Override
    public int count() {
        int count = 0;
        for (Block block : blocks) {
            count += countBlocks(block);
        }
        return count;
    }

    private int countBlocks(Block block) {
        if (block instanceof CompositeBlock) {
            int count = 0;
            List<Block> compositeBlocks = ((CompositeBlock) block).getBlocks();
            for (Block compositeBlock : compositeBlocks) {
                count += countBlocks(compositeBlock);
            }
            return count;
        } else {
            return 1;
        }
    }
}
