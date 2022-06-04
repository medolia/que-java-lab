package com.medolia.arch.game.damage;

import com.google.common.collect.ImmutableSet;

/**
 * @author lbli
 * @date 2022/6/4
 */
public enum Condition {
    /**
     * 内脏暴击
     */
    VISCERAL_ATK {
        @Override
        public boolean match(ConditionContext context) {
            return context.isVisceralATK();
        }
    },
    /**
     * 满血
     */
    FULL_HP {
        @Override
        public boolean match(ConditionContext context) {
            return context.getHpPercent() >= 1.0;
        }
    },
    /**
     * 所有物理
     */
    PHYSICAL_ALL {
        @Override
        public boolean match(ConditionContext context) {
            return ImmutableSet.of(DamageType.PHYSICAL_NORMAL, DamageType.PHYSICAL_BLUNT, DamageType.PHYSICAL_THRUST)
                    .contains(context.getDamageType());
        }
    },
    /**
     * 濒死低血量
     */
    LOW_HP {
        @Override
        public boolean match(ConditionContext context) {
            return context.getHpPercent() < 0.3;
        }
    },
    /**
     * 野兽
     */
    BEAST {
        @Override
        public boolean match(ConditionContext context) {
            return context.isFoeIsBeast();
        }
    },
    /**
     * 眷族
     */
    KIN {
        @Override
        public boolean match(ConditionContext context) {
            return context.isFoeIsKin();
        }
    },
    /**
     * 锯齿
     */
    SERRATED {
        @Override
        public boolean match(ConditionContext context) {
            return context.isFoeWeakSerrated();
        }
    },
    /**
     * 教会
     */
    RIGHTEOUS {
        @Override
        public boolean match(ConditionContext context) {
            return context.isFoeWeakRighteous();
        }
    } ,
    /**
     * 公开状态
     */
    OPEN {
        @Override
        public boolean match(ConditionContext context) {
            return context.isFoeStayOpen();
        }
    } ,
    /**
     * 永不生效
     */
    INVALID {
        @Override
        public boolean match(ConditionContext context) {
            return false;
        }
    } ,
    ;

    public abstract boolean match(ConditionContext context);

}
