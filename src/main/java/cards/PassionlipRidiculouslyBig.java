package cards;

import actions.AlteregoClonesAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.DualWieldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.WeakPower;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipRidiculouslyBig
        extends CustomCard {
    public static final String ID = "alterego_mod:RidiculouslyBig";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "images/cards/RidiculouslyBig.png";
    private static final int COST = 1;

    public PassionlipRidiculouslyBig() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, Passionlip_Purple,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 12;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AddTemporaryHPAction(p, p, this.baseMagicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipRidiculouslyBig();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(8);
        }
    }
}