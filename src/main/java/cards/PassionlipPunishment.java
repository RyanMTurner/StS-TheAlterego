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
import powers.AlteregoPunishment;
import powers.AlteregoPunishmentUpgraded;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipPunishment
        extends CustomCard {
    public static final String ID = "alterego_mod:Punishment";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/Punishment.png";
    private static final int COST = 1;

    public PassionlipPunishment() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Passionlip_Purple,
                CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AlteregoPunishmentUpgraded(p, p, 1)));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AlteregoPunishment(p, p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipPunishment();
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