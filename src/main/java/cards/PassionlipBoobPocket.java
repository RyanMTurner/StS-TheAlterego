package cards;

import actions.AlteregoBoobPocketAction;
import alterego_mod.AlteregoMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.ThroughViolence;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import basemod.abstracts.CustomCard;

import java.util.ArrayList;
import java.util.function.Predicate;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipBoobPocket
        extends CustomCard
        implements StartupCard {
    public static final String ID = "alterego_mod:BoobPocket";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "images/cards/BoobPocket.png";
    private static final int COST = 1;

    public static ArrayList<AbstractCard> storing;

    public PassionlipBoobPocket() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, Passionlip_Purple,
                CardRarity.UNCOMMON, CardTarget.SELF);
        if (storing == null) {
            storing = new ArrayList<AbstractCard>();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AlteregoBoobPocketAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipBoobPocket();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public boolean atBattleStartPreDraw() {
        for (int i = 0; i < storing.size(); i++) {
            this.addToBot(new MoveCardsAction(AbstractDungeon.player.hand, AbstractDungeon.player.drawPile, new CardPredicate(storing.get(i))));
        }
        storing.clear();
        return true;
    }

    private class CardPredicate implements Predicate<AbstractCard> {

        AbstractCard self;

        public CardPredicate(AbstractCard self) {
            this.self = self;
        }

        @Override
        public boolean test(AbstractCard other) {
            return other.uuid == self.uuid;
        }
    }
}