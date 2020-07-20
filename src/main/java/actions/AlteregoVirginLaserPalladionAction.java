//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package actions;

import alterego_mod.AlteregoMod;
import basemod.helpers.CardModifierManager;
import cardmodifiers.AlteregoCloneModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class AlteregoVirginLaserPalladionAction extends LoseHPAction {

    public AlteregoVirginLaserPalladionAction(AbstractCreature target, AbstractCreature source, int amount) {
        super(target, source, amount);
    }

    @Override
    public void update() {
        if (this.duration == 0.33F && this.target.currentHealth > 0) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
        }

        this.tickDuration();
        if (this.isDone) {
            ArrayList<AbstractPower> damagePreventionPowers = new ArrayList<AbstractPower>();
            for (AbstractPower pow : this.target.powers) {
                if (isDamagePreventionPower(pow)) {
                    damagePreventionPowers.add(pow);
                }
            }
            for (AbstractPower pow : damagePreventionPowers) {
                this.target.powers.remove(pow);
            }

            this.target.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.HP_LOSS));

            for (AbstractPower pow : damagePreventionPowers) {
                this.target.powers.add(pow);
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(0.1F));
            }
        }
    }

    public static boolean isDamagePreventionPower(AbstractPower power) {
        return power instanceof InvinciblePower
                || power instanceof IntangiblePower
                || power instanceof IntangiblePlayerPower;
    }
}