package com.medolia.arch.game.soullike.damage;

import com.google.common.collect.ImmutableSet;
import com.medolia.arch.game.soullike.ElementType;

/**
 * @author lbli
 * @date 2022/6/4
 */
public enum CalCondition {
    /**
     * 内脏暴击
     */
    VISCERAL_ATK {
        @Override
        public boolean match(CalContext context) {
            return context.isVisceralATK();
        }
    },
    /**
     * 满血
     */
    FULL_HP {
        @Override
        public boolean match(CalContext context) {
            return context.getHpPercent() >= 1.0;
        }
    },
    /**
     * 所有物理
     */
    PHYSICAL_ALL {
        @Override
        public boolean match(CalContext context) {
            return ImmutableSet.of(ElementType.PHYSICAL_NORMAL, ElementType.PHYSICAL_BLUNT, ElementType.PHYSICAL_THRUST)
                    .contains(context.getElementType());
        }
    },
    /**
     * 濒死低血量
     */
    LOW_HP {
        @Override
        public boolean match(CalContext context) {
            return context.getHpPercent() < 30;
        }
    },
    /**
     * 野兽
     */
    BEAST {
        @Override
        public boolean match(CalContext context) {
            return context.isFoeIsBeast();
        }
    },
    /**
     * 眷族
     */
    KIN {
        @Override
        public boolean match(CalContext context) {
            return context.isFoeIsKin();
        }
    },
    /**
     * 锯齿
     */
    SERRATED {
        @Override
        public boolean match(CalContext context) {
            return context.isFoeWeakSerrated();
        }
    },
    /**
     * 教会
     */
    RIGHTEOUS {
        @Override
        public boolean match(CalContext context) {
            return context.isFoeWeakRighteous();
        }
    } ,
    /**
     * 公开状态
     */
    OPEN {
        @Override
        public boolean match(CalContext context) {
            return context.isFoeStayOpen();
        }
    } ,
    /**
     * 永不生效
     */
    INVALID {
        @Override
        public boolean match(CalContext context) {
            return false;
        }
    } ,
    /**
     * 永远有效
     */
    ALWAYS {
        @Override
        public boolean match(CalContext context) {
            return true;
        }
    }
    ;

    /**
     * take effect when condition matches
     */
    public abstract boolean match(CalContext context);

}
