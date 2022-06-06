package com.medolia.arch.game.buff;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;

import java.util.List;
import java.util.Set;

/**
 * @author lbli
 * @date 2022/5/3
 */
public class BuffFactory {

    public static final int TYPE_AGGRE = 1;
    public static final int TYPE_DEFEN = 2;
    private static List<Buff> aggressiveBuffPool, defensiveBuffPool;

    static {
        init();
    }

    public static List<Buff> randomList(int num, int type) {

        Set<Buff> result = Sets.newHashSet();
        List<Buff> tgtPool =
                type == TYPE_AGGRE ? aggressiveBuffPool :
                        (type == TYPE_DEFEN ? defensiveBuffPool : Lists.newArrayList());

        try {
            while (num-- > 0) {
                Buff buff = tgtPool.get(RandomUtils.nextInt(0, tgtPool.size()));
                result.add(buff);
            }
        } catch (Exception e) {
            //
        }

        return Lists.newArrayList(result);
    }

    private static void init() {
        aggressiveBuffPool = new ImmutableList.Builder<Buff>()
                .add(new BaseBuff("crack", 1.3))
                .add(new BaseBuff("eternal bless", 1.2))
                .add(new BaseBuff("absolute attack", 1.5))
                .add(new BaseBuff("focus", 1.4))
                .add(new BaseBuff("power of oath", 1.5))
                .add(new BaseBuff("ultimate blust", 2.0))
                .build();

        defensiveBuffPool = new ImmutableList.Builder<Buff>()
                .add(new BaseBuff("base armor", 0.65))
                .add(new BaseBuff("holy shield", 0.35))
                .add(new BaseBuff("imperfect dodge", 0.5))
                .add(new BaseBuff("smash resist", 0.8))
                .add(new BaseBuff("experience in danger", 0.2))
                .build();
    }

}
