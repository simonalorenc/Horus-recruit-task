package com.simonalorenc.taskcheck;

import com.simonalorenc.Block;
import com.simonalorenc.CompositeBlock;

import java.util.List;

public class CompositeBlockImpl implements CompositeBlock {
    private final List<Block> compositeBlocks;

    public CompositeBlockImpl(List<Block> compositeBlocks) {
        this.compositeBlocks = compositeBlocks;
        if (compositeBlocks.isEmpty()) {
            throw new IllegalArgumentException("Empty argument list.");
        }
        String firstColor = getColor();
        String firstMaterial = getMaterial();
        for (Block compositeBlock : compositeBlocks) {
            if (!compositeBlock.getColor().equals(firstColor)) {
                throw new IllegalArgumentException("Different block colors.");
            } else if (!compositeBlock.getMaterial().equals(firstMaterial)) {
                throw new IllegalArgumentException("Different block materials.");
            }
        }
    }

    @Override
    public String getColor() {
        return getFirstBlock().getColor();
    }

    @Override
    public String getMaterial() {
        return getFirstBlock().getMaterial();
    }

    @Override
    public List<Block> getBlocks() {
        return compositeBlocks;
    }

    private Block getFirstBlock() {
        return compositeBlocks.get(0);
    }
}
