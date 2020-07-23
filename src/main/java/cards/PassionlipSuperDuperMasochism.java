package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
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
import powers.AlteregoSuperDuperMasochism;
import powers.AlteregoSuperDuperMasochismUpgraded;
import stances.PassionlipDurga;
import stances.PassionlipParvati;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipSuperDuperMasochism
        extends CustomCard {
    public static final String ID = "alterego_mod:SuperDuperMasochism";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "images/cards/SuperDuperMasochism.png";
    private static final int COST = 1;

    public PassionlipSuperDuperMasochism() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, Passionlip_Purple,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new ChangeStanceAction(PassionlipParvati.STANCE_ID));

        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(p, p,
                this.upgraded ? new AlteregoSuperDuperMasochismUpgraded(p, p) : new AlteregoSuperDuperMasochism(p, p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipSuperDuperMasochism();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-5);
        }
    }
}