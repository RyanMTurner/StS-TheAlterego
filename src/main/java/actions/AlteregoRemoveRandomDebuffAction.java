//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package actions;

import alterego_mod.AlteregoMod;
import basemod.helpers.CardModifierManager;
import cardmodifiers.AlteregoCloneModifier;
import cards.PassionlipBoobPocket;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.*;
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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class AlteregoRemoveRandomDebuffAction extends RemoveSpecificPowerAction {

    public AlteregoRemoveRandomDebuffAction(AbstractCreature target, AbstractCreature source) {
        super(target, source, getRandomDebuffOnCreature(target));
    }

    public static AbstractPower getRandomDebuffOnCreature(AbstractCreature creature) {
        ArrayList<AbstractPower> debuffs = new ArrayList<AbstractPower>();
        if (creature.powers.size() == 0) {
            return null;
        }
        else {
            for (AbstractPower power : creature.powers) {
                if (power.type == AbstractPower.PowerType.DEBUFF) {
                    debuffs.add(power);
                }
            }
            if (debuffs.size() == 0) {
                return null;
            }
            else {
                int pwrIndex = AbstractDungeon.cardRandomRng.random(0, debuffs.size() - 1);
                return debuffs.get(pwrIndex);
            }
        }
    }

}