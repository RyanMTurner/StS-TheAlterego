package cards;

import actions.AlteregoClonesAction;
import actions.AlteregoRemoveRandomDebuffAction;
import alterego_mod.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.DualWieldAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;

import basemod.abstracts.CustomCard;
import stances.PassionlipBrynhildr;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipValentines
        extends CustomCard {
    public static final String ID = "alterego_mod:Valentines";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/Valentines.png";
    private static final int COST = 1;

    public PassionlipValentines() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, Passionlip_Purple,
                CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(PassionlipBrynhildr.STANCE_ID));
        if (!this.upgraded) {
            this.addToBot(new AlteregoRemoveRandomDebuffAction(p, p));
        }
        else {
            this.addToBot(new RemoveDebuffsAction(p));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipValentines();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            GraveField.grave.set(this, true);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}