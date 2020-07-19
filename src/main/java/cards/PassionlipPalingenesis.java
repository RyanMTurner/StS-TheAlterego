package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipPalingenesis
        extends CustomCard {
    public static final String ID = "alterego_mod:Palingenesis";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/Palingenesis.png";
    private static final int COST = 1;

    public PassionlipPalingenesis() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Passionlip_Purple,
                CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
    }

    int dexAmt = 0;
    int energyAmt = 0;
    int damageAmt = 0;

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.baseMagicNumber)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, dexAmt)));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(energyAmt));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, damageAmt));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipPalingenesis();
    }

    @Override
    public void upgradeName() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = NAME + "+" + this.timesUpgraded;
        this.initializeTitle();

        switch(this.timesUpgraded % 3) {
            case 0: //Strength up
                this.baseMagicNumber += 1;
                break;
            case 1: //Dex up
                dexAmt += 1;
                break;
            case 2: //Energy & damage up
                energyAmt += 1;
                damageAmt += 4;
                break;
        }
    }

    @Override
    public void upgrade() {
        this.upgradeName();
        setDescription();
        this.initializeDescription();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    private void setDescription() {
        String newDescription = DESCRIPTION;
        if (dexAmt > 0) {
            newDescription += EXTENDED_DESCRIPTION[0] + dexAmt + EXTENDED_DESCRIPTION[1];
        }
        if (energyAmt > 0) {
            newDescription += EXTENDED_DESCRIPTION[0] + energyAmt + EXTENDED_DESCRIPTION[2];
        }
        if (damageAmt > 0) {
            newDescription += EXTENDED_DESCRIPTION[3] + damageAmt + EXTENDED_DESCRIPTION[4];
        }
        this.rawDescription = newDescription;
    }
}