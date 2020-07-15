package patches;

import alterego_mod.AlteregoMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import stances.PassionlipBrynhildr;
import stances.PassionlipDurga;

@SpirePatch(clz = AbstractMonster.class, method = "die", paramtypez = boolean.class)
public class OnMonsterDeathStancePatch {
    @SpirePrefixPatch()
    public static void die(AbstractMonster __instance, boolean b) {
        if(AbstractDungeon.player.stance instanceof PassionlipBrynhildr) {
            ((PassionlipBrynhildr) AbstractDungeon.player.stance).onMonsterDeath(__instance);
        }
    }
}