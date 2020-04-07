package com.caisl.dt.internal.sharding;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * RandomSelector
 *
 * @author caisl
 * @since 2019-05-09
 */
@Component
public class RandomSelector extends AbstractShardingIdSelector {
    /**
     * 自己实现分区ID选择算法（随机）
     * @param shardingIds
     * @return
     */
    @Override
    protected Integer doSelect(List<Integer> shardingIds) {
        return ThreadLocalRandom.current().nextInt(shardingIds.size());
    }
}
