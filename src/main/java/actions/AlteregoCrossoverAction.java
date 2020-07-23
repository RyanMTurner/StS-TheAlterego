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
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;
import java.util.Iterator;

public class AlteregoCrossoverAction extends AbstractGameAction {

    public AlteregoCrossoverAction() {
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, 1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.25F;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (duration == Settings.ACTION_DUR_FAST) {
            AbstractCard newCard = generateCard();
            newCard.superFlash();
            AbstractDungeon.player.hand.addToTop(newCard);
            AbstractDungeon.player.hand.refreshHandLayout();

            AbstractDungeon.player.hand.glowCheck();
            isDone = true;
        }

        tickDuration();
    }

    private AbstractCard generateCard() {
        int roll = AbstractDungeon.cardRandomRng.random(99);
        AbstractCard.CardRarity cardRarity;
        if (roll < 55) {
            cardRarity = AbstractCard.CardRarity.COMMON;
        } else if (roll < 85) {
            cardRarity = AbstractCard.CardRarity.UNCOMMON;
        } else {
            cardRarity = AbstractCard.CardRarity.RARE;
        }

        AbstractCard generatedCard = CardLibrary.getAnyColorCard(cardRarity);
        int tries = 0;
        while (generatedCard.color == AbstractDungeon.player.getCardColor() && tries < 100) {
            generatedCard = CardLibrary.getAnyColorCard(cardRarity);
            tries++;
        }

        return generatedCard;
    }
}