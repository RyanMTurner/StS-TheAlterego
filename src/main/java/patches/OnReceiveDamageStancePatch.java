package patches;

import alterego_mod.AlteregoMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import stances.PassionlipBrynhildr;
import stances.PassionlipDurga;
import stances.PassionlipParvati;

@SpirePatch(clz = AbstractPlayer.class, method = "damage", paramtypez = DamageInfo.class)
public class OnReceiveDamageStancePatch {
    @SpirePostfixPatch()
    public static void damage(AbstractPlayer __instance, DamageInfo info) {
        if(__instance.stance instanceof PassionlipParvati) {
            ((PassionlipParvati) AbstractDungeon.player.stance).onDamaged(info);
        }
    }
}