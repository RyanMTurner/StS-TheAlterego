package cards;

import actions.AlteregoRemoveRandomPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.cards.tempCards.ThroughViolence;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import stances.PassionlipDurga;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipDestructionOfProperty
        extends CustomCard {
    public static final String ID = "alterego_mod:DestructionOfProperty";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/DestructionOfProperty.png";
    private static final int COST = 1;

    public PassionlipDestructionOfProperty() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, Passionlip_Purple,
                CardRarity.UNCOMMON, CardTarget.ALL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new AlteregoRemoveRandomPowerAction(p, p));
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new AlteregoRemoveRandomPowerAction(mo, p));
            }
        }
        else {
            int effect = EnergyPanel.totalCount;
            if (this.energyOnUse != -1) {
                effect = this.energyOnUse;
            }

            if (AbstractDungeon.player.hasRelic(ChemicalX.ID)) {
                effect += 2;
                AbstractDungeon.player.getRelic(ChemicalX.ID).flash();
            }

            if (this.upgraded) {
                effect += 2;
            }

            for (int i = 0; i < effect; i++) {
                AbstractDungeon.actionManager.addToBottom(new AlteregoRemoveRandomPowerAction(p, p));
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    AbstractDungeon.actionManager.addToBottom(new AlteregoRemoveRandomPowerAction(mo, p));
                }
            }

            if (!this.freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipDestructionOfProperty();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(-1);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}