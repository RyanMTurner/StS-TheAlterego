package cards;

import basemod.helpers.CardModifierManager;
import cardmodifiers.AlteregoAffectCostOnceModifier;
import com.evacipated.cardcrawl.mod.stslib.actions.common.RefundAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipSanta
        extends CustomCard {
    public static final String ID = "alterego_mod:Santa";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/Santa.png";
    private static final int COST = -1;

    public PassionlipSanta() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, Passionlip_Purple,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (AbstractDungeon.player.hasRelic(ChemicalX.ID)) {
            effect += 2;
            AbstractDungeon.player.getRelic(ChemicalX.ID).flash();
        }

        AbstractCard generatedCard = null;
        if (effect == 1) {
            generatedCard = AbstractDungeon.commonCardPool.getRandomCard(true);
        }
        else if (effect == 2) {
            generatedCard = AbstractDungeon.uncommonCardPool.getRandomCard(true);
        }
        else if (effect >= 3) {
            generatedCard = AbstractDungeon.rareCardPool.getRandomCard(true);
        }

        if (generatedCard != null) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(generatedCard, 1));
            if (this.upgraded) {
                CardModifierManager.addModifier(generatedCard, new AlteregoAffectCostOnceModifier(-1));
            }
        }

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
            if (effect - 3 > 0) {
                this.addToBot(new RefundAction(this, effect - 3));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipSanta();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}