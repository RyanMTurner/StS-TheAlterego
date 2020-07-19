//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package actions;

import alterego_mod.AlteregoMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.Iterator;

public class AlteregoBunnysuitAction extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private AbstractCard cardUsed;
    int cardsDiscarded = 0;

    public AlteregoBunnysuitAction(AbstractCard cardUsed) {
        this.cardUsed = cardUsed;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.cardsDiscarded = 0;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (!cardUsed.upgraded && this.p.hand.size() <= 5) {
                ArrayList<AbstractCard> cardsInHandPreDiscard = new ArrayList<>();
                for (AbstractCard card : p.hand.group) {
                    cardsInHandPreDiscard.add(card);
                }
                for (AbstractCard card : cardsInHandPreDiscard) {
                    onDiscardCard(card);
                    this.p.hand.moveToDiscardPile(card);
                }
                this.addToBot(new DrawCardAction(p, cardsDiscarded));
                this.isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 5, cardUsed.upgraded, cardUsed.upgraded);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for(Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); this.p.hand.moveToDiscardPile(c)) {
                    c = (AbstractCard)var1.next();
                    onDiscardCard(c);
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
                this.addToBot(new DrawCardAction(p, cardsDiscarded));
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
        TEXT = uiStrings.TEXT;
    }

    void onDiscardCard(AbstractCard c) {
        //AlteregoMod.logger.info("Discarded card " + c.name);
        cardsDiscarded++;
    }

}