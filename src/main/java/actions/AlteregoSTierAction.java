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
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Seek;
import com.megacrit.cardcrawl.cards.green.Adrenaline;
import com.megacrit.cardcrawl.cards.green.WellLaidPlans;
import com.megacrit.cardcrawl.cards.green.WraithForm;
import com.megacrit.cardcrawl.cards.purple.Blasphemy;
import com.megacrit.cardcrawl.cards.purple.LessonLearned;
import com.megacrit.cardcrawl.cards.purple.SpiritShield;
import com.megacrit.cardcrawl.cards.red.Feed;
import com.megacrit.cardcrawl.cards.red.Immolate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;
import java.util.Iterator;

public class AlteregoSTierAction extends AbstractGameAction {

    boolean upgraded;
    CardGroup cardPool;

    public AlteregoSTierAction(boolean upgraded) {
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, 1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.25F;
        this.upgraded = upgraded;

        cardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        //Ironclad
        cardPool.addToTop(new Feed());
        cardPool.addToTop(new Immolate());
        //Silent
        cardPool.addToTop(new Adrenaline());
        cardPool.addToTop(new WraithForm());
        cardPool.addToTop(new WellLaidPlans());
        //Defect
        cardPool.addToTop(new Seek());
        //Watcher
        cardPool.addToTop(new Blasphemy());
        cardPool.addToTop(new SpiritShield());
        cardPool.addToTop(new LessonLearned());
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (duration == Settings.ACTION_DUR_FAST) {
            for (int i = 0; i < (this.upgraded ? 2 : 1); i++) {
                AbstractCard newCard = cardPool.getRandomCard(true);
                cardPool.removeCard(newCard);
                newCard.superFlash();
                AbstractDungeon.player.hand.addToTop(newCard);
            }
            AbstractDungeon.player.hand.refreshHandLayout();

            AbstractDungeon.player.hand.glowCheck();
            isDone = true;
        }

        tickDuration();
    }
}