package patches;

import alterego_mod.AlteregoMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import powers.AlteregoSakuraLabyrinth;
import stances.PassionlipBrynhildr;
import stances.PassionlipDurga;

@SpirePatch(clz = SlowPower.class, method = "atEndOfRound")
public class AtEndOfRoundSlowPatch {
    @SpirePostfixPatch()
    public static void atEndOfRound(AbstractPower __instance) {
        AbstractPower sakuraLabyrinthPower = null;
        AlteregoMod.logger.info("Alterego Slow patch");
        for (int i = 0; i < __instance.owner.powers.size(); i++) {
            if (__instance.owner.powers.get(i) instanceof AlteregoSakuraLabyrinth) {
                sakuraLabyrinthPower = __instance.owner.powers.get(i);
                break;
            }
        }

        if (sakuraLabyrinthPower != null) {
            AlteregoMod.logger.info("Setting slow to Sakura Labyrinth amount");
            __instance.amount = sakuraLabyrinthPower.amount;
            __instance.updateDescription();
        }
    }
}