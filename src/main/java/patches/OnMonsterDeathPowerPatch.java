package patches;

import alterego_mod.AlteregoMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import powers.AlteregoVow;
import stances.PassionlipBrynhildr;
import stances.PassionlipDurga;

@SpirePatch(clz = AbstractMonster.class, method = "die", paramtypez = boolean.class)
public class OnMonsterDeathPowerPatch {
    @SpirePrefixPatch()
    public static void die(AbstractMonster __instance, boolean b) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(AlteregoVow.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new InstantKillAction(mo));
            }
        }
    }
}