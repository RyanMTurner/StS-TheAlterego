package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.cards.purple.TalkToTheHand;
import com.megacrit.cardcrawl.cards.red.Armaments;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import powers.AlteregoAlterEgo;
import powers.AlteregoAlterEgoUpgraded;
import powers.AlteregoRingBreak;
import powers.AlteregoTempInvincible;
import stances.PassionlipDurga;
import stances.PassionlipParvati;

import java.util.ArrayList;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipTrickOrTreatment
        extends CustomCard {
    public static final String ID = "alterego_mod:TrickOrTreatment";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/TrickOrTreatment.png";
    private static final int COST = 2;

    public PassionlipTrickOrTreatment() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Passionlip_Purple,
                CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //https://github.com/kiooeht/Hubris/blob/master/src/main/java/com/evacipated/cardcrawl/mod/hubris/cards/EnhanceAbility/EnhanceAbility.java
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new PassionlipTrick());
        choices.add(new PassionlipTreatment());

        if (upgraded) {
            choices.forEach(AbstractCard::upgrade);
        }

        addToBot(new ChooseOneAction(choices));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipTrickOrTreatment();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}