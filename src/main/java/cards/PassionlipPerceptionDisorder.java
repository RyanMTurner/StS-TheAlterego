package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import powers.AlteregoPerceptionDisorder;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipPerceptionDisorder
        extends CustomCard {
    public static final String ID = "alterego_mod:PerceptionDisorder";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "images/cards/PerceptionDisorder.png";
    private static final int COST = 1;

    public PassionlipPerceptionDisorder() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Passionlip_Purple,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.baseMagicNumber)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AlteregoPerceptionDisorder(p, p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipPerceptionDisorder();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }
}