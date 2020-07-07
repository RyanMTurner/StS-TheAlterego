package patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import stances.PassionlipDurga;

//Tyvm gk: https://github.com/erasels/TheProdigy/blob/master/theProdigy/src/main/java/theProdigy/patches/stances/OnUseCardStancePatch.java
@SpirePatch(clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
public class OnUseCardStancePatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void patch(UseCardAction __instance, AbstractCard c, AbstractCreature target) {
        if(AbstractDungeon.player.stance instanceof PassionlipDurga && !c.dontTriggerOnUseCard) {
            ((PassionlipDurga) AbstractDungeon.player.stance).onPlayCard(c, __instance);
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(CardGroup.class, "group");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}