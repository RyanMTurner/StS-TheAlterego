package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
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
import powers.AlteregoAlter;
import powers.AlteregoAlterLite;
import powers.AlteregoTantrum;
import stances.PassionlipDurga;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipAlter
        extends CustomCard {
    public static final String ID = "alterego_mod:Alter";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/Alter.png";
    private static final int COST = 3;

    public PassionlipAlter() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, Passionlip_Purple,
                CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int healAmt = (p.maxHealth - p.currentHealth) - p.currentHealth;
        this.addToBot(new HealAction(p, p, healAmt));
        if (!this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AlteregoAlter(p, p)));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AlteregoAlterLite(p, p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipAlter();
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