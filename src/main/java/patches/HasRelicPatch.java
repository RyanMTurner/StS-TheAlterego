package patches;

import alterego_mod.AlteregoMod;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import powers.AlteregoPerceptionDisorder;
import stances.PassionlipBrynhildr;
import stances.PassionlipDurga;

@SpirePatch(clz = AbstractPlayer.class, method = "hasRelic", paramtypez = String.class)
public class HasRelicPatch {
    @SpirePrefixPatch()
    public static SpireReturn<Boolean> hasRelic(AbstractPlayer __instance, String targetID) {
        if(targetID == "Runic Dome" && AbstractDungeon.player.hasPower(AlteregoPerceptionDisorder.POWER_ID)) {
            return SpireReturn.Return(true);
        }
        return SpireReturn.Continue();
    }
}

